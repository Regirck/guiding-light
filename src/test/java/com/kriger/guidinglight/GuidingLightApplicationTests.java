package com.kriger.guidinglight;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class GuidingLightApplicationTests {

    @Test
    public void contextLoads() {
    }

}
