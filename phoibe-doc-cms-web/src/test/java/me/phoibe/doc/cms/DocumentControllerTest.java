package me.phoibe.doc.cms;

import me.phoibe.doc.cms.dao.PhoibeDocumentMapper;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import me.phoibe.doc.cms.service.PhoibeDocumentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tengzhaolei
 * @Title: DocumentControllerTest
 * @Description: class
 * @date 2018/8/23 1:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class DocumentControllerTest {

    @Resource
    private PhoibeDocumentService phoibeDocumentService;

    @Test
    public void testSave(){
        PhoibeDocument phoibeDocument = new PhoibeDocument();
        short arms = 1;
        phoibeDocument.setArms(arms);
        phoibeDocument.setAuditStatus(arms);
        phoibeDocument.setAuditUserId(new BigDecimal(1));
        phoibeDocument.setCombatType(arms);
        phoibeDocument.setContent("adfdasfdasfadf".getBytes());
        phoibeDocument.setCreateTime(new Date());
        phoibeDocument.setDescription("sdfadsfd");
        phoibeDocument.setFilePath("test");
        phoibeDocument.setFileSize(new BigDecimal(1234556789));
        phoibeDocument.setFormat("doc");
        phoibeDocument.setName("test");
        phoibeDocument.setProgress((short) (arms +10));
        phoibeDocument.setScore(new BigDecimal(1.2));
        phoibeDocument.setTag("1,34,546,121");
        phoibeDocument.setUpdateTime(new Date());
        phoibeDocument.setUserId(new BigDecimal(1234));
        phoibeDocument.setStatus(arms);
        int result = phoibeDocumentService.save(phoibeDocument);
        System.out.println(result);
    }
}
