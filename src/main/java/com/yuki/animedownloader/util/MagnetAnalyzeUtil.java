package com.yuki.animedownloader.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import com.acgist.snail.Snail;
import com.acgist.snail.context.GuiContext;
import com.acgist.snail.context.ProtocolContext;
import com.acgist.snail.context.TorrentContext;
import com.acgist.snail.context.exception.DownloadException;
import com.acgist.snail.context.exception.NetException;
import com.acgist.snail.context.initializer.TorrentInitializer;
import com.acgist.snail.gui.event.adapter.MultifileEventAdapter;
import com.acgist.snail.pojo.IStatisticsSession;
import com.acgist.snail.pojo.ITaskSession;
import com.acgist.snail.pojo.bean.Torrent;
import com.acgist.snail.pojo.bean.TorrentInfo;
import com.acgist.snail.protocol.magnet.MagnetProtocol;
import com.yuki.animedownloader.enums.FileSizeUnitEnum;
import com.yuki.animedownloader.model.FileSize;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MagnetAnalyzeUtil {

    /**
     * 通过磁力链接下载其包含的文件
     * @param magnetUri 磁力链接
     * @throws DownloadException
     * @throws NetException
     */
    public static void downloadFilesFromMagnet(String magnetUri, String savePath) throws DownloadException {
//        if (CharSequenceUtil.isNotBlank(savePath)){
//            DownloadConfig.setPath(savePath);
//        }
        File torrentFile = downloadTorrentFromMagnet(magnetUri);
        File[] files = FileUtil.ls(torrentFile.getAbsolutePath());
        final String torrentPath = files[0].getAbsolutePath();
        final Snail snail = Snail.SnailBuilder.newBuilder()
                .enableTorrent()
                .buildSync();
        // 注册文件选择事件
        GuiContext.register(new MultifileEventAdapter());
        // 解析种子文件
        final Torrent torrent = TorrentContext.loadTorrent(torrentPath);
        TorrentInfo info = torrent.getInfo();
        log.info("种子名称：{}，种子文件列表：{}", info.files(), info.getFiles());
        // 过滤下载文件
//        final List<String> list = torrent.getInfo().files().stream()
//                .filter(TorrentFile::notPaddingFile)
//                .map(TorrentFile::path)
//                .filter(path -> path.endsWith(".mkv") || path.endsWith(".mp4"))
//                .toList();
        // 设置下载文件
        //        MultifileEventAdapter.files(DescriptionWrapper.newEncoder(list).serialize());
        // 添加下载

        ITaskSession download = snail.download(torrentPath);
        IStatisticsSession statistics = download.statistics();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // 通过scheduleAtFixedRate方法定期执行任务
        scheduler.scheduleAtFixedRate(() -> {
            String fileName = download.downloadFile().getName();
            if (download.statusDownload() && !download.statusCompleted()){
                String schedule = NumberUtil.decimalFormat("#.##%", (statistics.downloadSize() / 1024d) / FileUtils.parseToKB(FileSize.of(download.getSize() / 1024d, FileSizeUnitEnum.KB)).getSize());
                log.info("文件名:{} --------- 下载速度：{}KB/s，已下载：{}{},下载进度：{}", fileName, NumberUtil.round(statistics.downloadSpeed() / 1024d, 1), NumberUtil.round(statistics.downloadSize()/ (double) (1024 * 1024), 3), FileSizeUnitEnum.MB.getUnit(), schedule);
            }

            if (download.statusFail()){
                log.info("文件名:{} 下载失败", fileName);
                // 停止任务
                download.downloader().release();
                scheduler.shutdown();
            }

            // 停止条件，这里假设条件在任务内部某处被修改
            if (download.statusCompleted()) {
                log.info("文件名:{} 下载完成", fileName);
                // 停止任务
                download.downloader().release();
                scheduler.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
        // 等待下载完成
        snail.lockDownload();
    }

    /**
     * 从magnet磁力链接下载种子文件
     * @param magnetUri 磁力链接
     * @return
     * @throws DownloadException
     */
    public static File downloadTorrentFromMagnet(String magnetUri) throws DownloadException {
        log.info("要下载的资源的磁力链接：{}", magnetUri);
        TorrentInitializer.newInstance().sync();
        ProtocolContext.getInstance().register(MagnetProtocol.getInstance()).available(true);
        final var taskSession = MagnetProtocol.getInstance().buildTaskSession(magnetUri);
        final var downloader = taskSession.buildDownloader();
        downloader.run();
        final var file = new File(taskSession.getFile());
        log.info(file.getAbsolutePath());
        taskSession.delete();
        return file;
    }

}
