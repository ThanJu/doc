package me.phoibe.doc.cms;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tengzhaolei
 * @Title: TestHikaricpApplicationTests
 * @Description: class
 * @date 2018/8/24 2:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHikaricpApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void contextLoads() {
        System.out.println("加密密码是：");
        System.out.println(stringEncryptor.encrypt( "lumia"));
    }

}
