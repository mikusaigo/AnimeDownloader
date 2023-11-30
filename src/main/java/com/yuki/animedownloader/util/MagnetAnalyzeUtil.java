package com.yuki.animedownloader.util;

import com.frostwire.jlibtorrent.SessionManager;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class MagnetAnalyzeUtil {

    public static void downloadFilesFromMagnet(String magnetUri, File targetFile){
        SessionManager sessionManager = new SessionManager();
        sessionManager.download(magnetUri, targetFile);
    }

}
