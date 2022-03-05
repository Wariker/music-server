package com.wang.music;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class MusicApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testPath(){
        String storeAvatorPath = "/song/test";
        String[] SongUrlSplit = storeAvatorPath.split("\\/");
        String fileName = SongUrlSplit[SongUrlSplit.length-1];
        String path = System.getProperty("user.dir")+System.getProperty("file.separator")
                +"song"+System.getProperty("file.separator") + fileName;
        System.out.println(path);
        File file = new File(path);
        file.delete();
    }
}
