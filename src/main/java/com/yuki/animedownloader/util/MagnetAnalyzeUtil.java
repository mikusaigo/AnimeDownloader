package com.yuki.animedownloader.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import com.acgist.snail.Snail;
import com.acgist.snail.context.GuiContext;
import com.acgist.snail.context.ProtocolContext;
import com.acgist.snail.context.TorrentContext;
import com.acgist.snail.context.exception.DownloadException;
import com.acgist.snail.context.exception.NetException;
import com.acgist.snail.context.initializer.TorrentInitializer;
import com.acgist.snail.gui.event.adapter.MultifileEventAdapter;
import com.acgist.snail.pojo.bean.Torrent;
import com.acgist.snail.protocol.magnet.MagnetProtocol;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class MagnetAnalyzeUtil {

    /**
     * 通过磁力链接下载其包含的文件
     * @param magnetUri 磁力链接
     * @throws DownloadException
     * @throws NetException
     */
    public static void downloadFilesFromMagnet(String magnetUri) throws DownloadException {
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
        // 过滤下载文件
//        final List<String> list = torrent.getInfo().files().stream()
//                .filter(TorrentFile::notPaddingFile)
//                .map(TorrentFile::path)
//                .filter(path -> path.endsWith(".mkv") || path.endsWith(".mp4"))
//                .toList();
        // 设置下载文件
        //        MultifileEventAdapter.files(DescriptionWrapper.newEncoder(list).serialize());
        // 添加下载
        snail.download(torrentPath);

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
        assert file.exists();
        assert ArrayUtil.isNotEmpty(file.list());
        taskSession.delete();
        return file;
    }

}
