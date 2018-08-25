package me.phoibe.doc.cms.controller;

import me.phoibe.doc.cms.dao.PhoibeDocumentMapper;
import me.phoibe.doc.cms.domain.dto.DPhoibeDocument;
import me.phoibe.doc.cms.domain.po.PageList;
import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import me.phoibe.doc.cms.domain.po.PhoibeDocumentExample;
import me.phoibe.doc.cms.entity.Code;
import me.phoibe.doc.cms.entity.Result;
import me.phoibe.doc.cms.service.PhoibeDocumentService;
import me.phoibe.doc.cms.utils.JsonUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author pc
 * @Title: PhoibeDocumentController
 * @Description: 文档管理Controller
 * @date 2018/8/231:01
 */
@RestController
@RequestMapping("phoibe/document")
public class PhoibeDocumentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoibeDocumentController.class);
    @Autowired
    private PhoibeDocumentService phoibeDocumentService;
    @Resource
    private PhoibeDocumentMapper phoibeDocumentMapper;

    @PostMapping("save")
    public String saveDoucument(@RequestBody PhoibeDocument request) {

        LOGGER.info(JsonUtils.toJson(request));

        PhoibeDocument phoibeDocument = new PhoibeDocument();
//        short arms = 1;
//        phoibeDocument.setArms(arms);
//        phoibeDocument.setAuditStatus(arms);
//        phoibeDocument.setAuditUserId(new BigDecimal(1));
//        phoibeDocument.setCombatType(arms);
//        phoibeDocument.setContent("adfdasfdasfadf".getBytes());
//        phoibeDocument.setDescription("sdfadsfd");
//        phoibeDocument.setFilePath("test");
//        phoibeDocument.setFileSize(new BigDecimal(1234556789));
//        phoibeDocument.setFormat("doc");
//        phoibeDocument.setName("test");
//        phoibeDocument.setProgress((short) (arms +10));
//        phoibeDocument.setScore(new BigDecimal(1.2));
//        phoibeDocument.setTag("1,34,546,121");
//        phoibeDocument.setUpdateTime(new Date());
//        phoibeDocument.setUserId(new BigDecimal(1234));
//        phoibeDocument.setStatus(arms);
//        phoibeDocument.setCreateTime(new Date());

        BeanUtils.copyProperties(request, phoibeDocument);
        phoibeDocument.setCreateTime(new Date());

        int result = phoibeDocumentService.save(phoibeDocument);
        System.out.println(result);

        return JsonUtils.toJson(new Result<>(Code.SUCCESS, "ok"));
    }

    @GetMapping("list/{start}/{limit}")
    public String listDoucument(@PathVariable Integer start, @PathVariable Integer limit,@RequestParam(required = false) String f, @ModelAttribute DPhoibeDocument param) {
        String orderBy = "CREATE_TIME";
        String sort = "DESC";

        if("hot".equals(f)){
            orderBy = "HITCOUNT";
        }else if("handpick".equals(f)){
            orderBy = "RECORDER";
        }else{
            orderBy = "AUDIT_TIME";
        }
        PageParam<DPhoibeDocument> pageParam = new PageParam<>();
        pageParam.setStart(start);
        pageParam.setLimit(limit);
        pageParam.setParam(param==null?new DPhoibeDocument():param);
        pageParam.setOrderBy(orderBy);
        pageParam.setSort(sort);

        PageList<DPhoibeDocument> pageList = phoibeDocumentService.fetchDocumentByPageList(pageParam);


        return JsonUtils.toJson(new Result<PageList<DPhoibeDocument>>(Code.SUCCESS, pageList));
    }

    @GetMapping("list/user/{start}/{limit}")
    public String listDoucumentUser(@PathVariable Integer start, @PathVariable Integer limit) {

        PageParam<DPhoibeDocument> pageParam = new PageParam<>();
        pageParam.setStart(start);
        pageParam.setLimit(limit);

        List<DPhoibeDocument> list = phoibeDocumentService.fetchDocumentUserList(pageParam);


        return JsonUtils.toJson(new Result<List<DPhoibeDocument>>(Code.SUCCESS, list));
    }

    @PostMapping("count")
    public String countDoucument(@RequestBody PhoibeDocument request) {
        PhoibeDocumentExample phoibeDocumentExample = new PhoibeDocumentExample();

        phoibeDocumentExample.setDistinct(true);
        PhoibeDocumentExample.Criteria criteria1 = phoibeDocumentExample.createCriteria();
        criteria1.andFormatEqualTo(request.getFormat());
        criteria1.andNameLike(request.getName());

        Long counts = phoibeDocumentMapper.countByExample(phoibeDocumentExample);

        return JsonUtils.toJson(new Result<>(Code.SUCCESS, counts));

//        Random random = new Random();
//        int number = 1000*random.nextInt(1000);
//        return JsonUtils.toJson(new Result<>(Code.SUCCESS, Long.valueOf(number)));
    }




}
