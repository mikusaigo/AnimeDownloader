package com.yuki.animedownloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yuki.animedownloader")
public class AnimeDownloaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimeDownloaderApplication.class, args);
    }

}
