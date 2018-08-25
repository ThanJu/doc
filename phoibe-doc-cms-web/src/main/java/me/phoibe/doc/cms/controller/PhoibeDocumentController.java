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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.util.ResourceUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


		
	@RequestMapping(value = { "upload" })
	public String saveOrUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("UTF-8");
			String title= request.getParameter("title");
			String combat_type= request.getParameter("arms");
			String arms= request.getParameter("arms");
			String description= request.getParameter("description");
			
			File path = new File(ResourceUtils.getURL("classpath:").getPath());

			//F:\Java_WorkSpace\gitdoc\phoibe-doc-cms-web\target\classes/satic/docword/
			String str = path.getAbsolutePath();
			str = str.substring(0,str.lastIndexOf("\\"));
			str = str.substring(0,str.lastIndexOf("\\"));
			str = str.substring(0,str.lastIndexOf("\\"));
			String filepath= str+"/satic/docword/";
			
	        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultipartFile file = multipartRequest.getFile("file"); 
	        Map<String, Object> map = uploadFile(file,filepath);
			String pd = (String) map.get("SUCESS");
			if(pd.equals("true")) {
				String suffix = map.get("suffix").toString();
				suffix.substring(1, suffix.length());
				String datapath = (String) map.get("data");
				String fileSize = (String) map.get("fileSize");
				/*
				//如果是doc文件，读取文件内容
				String path="";
				InputStream is = new FileInputStream(new File(path)); 
				WordExtractor ex = new WordExtractor(is); 
				buffer = ex.getText(); 
				ex.close(); */
				
				 PhoibeDocument phoibeDocument = new PhoibeDocument();
				 phoibeDocument.setArms(Short.parseShort(arms));
				 phoibeDocument.setAuditStatus((short)(1));
				 phoibeDocument.setAuditUserId(new BigDecimal(1));
				 phoibeDocument.setCombatType(Short.parseShort(combat_type));
				 phoibeDocument.setContent("正文内容正文内容正文内容正文内容正文内容正文内容".getBytes());
				 phoibeDocument.setDescription(description);
				 phoibeDocument.setFilePath(datapath);
				 phoibeDocument.setFileSize(new BigDecimal(fileSize));
				 phoibeDocument.setFormat(suffix);
				 phoibeDocument.setName(title);
				 phoibeDocument.setProgress((short) (100));
				 phoibeDocument.setScore(new BigDecimal(1.2));
				 phoibeDocument.setTag("#战役#,#标签#,#讲解#,#视频#");
				 phoibeDocument.setUpdateTime(new Date());
				 phoibeDocument.setUserId(new BigDecimal(1));
				 phoibeDocument.setStatus((short)(2));
				 phoibeDocument.setCreateTime(new Date());

				int result = phoibeDocumentService.save(phoibeDocument);
				
				BeanUtils.copyProperties(request, phoibeDocument);
				if (result>0) {
					resultMap.put("success", true);
				}else {
					resultMap.put("success", false);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("success", false);
		}
		LOGGER.info(JsonUtils.toJson(resultMap));
		return JsonUtils.toJson(resultMap);
	}

	
	 public static Map<String, Object> uploadFile(MultipartFile file,String docPath)
	            throws IOException{
	        String fail = "fail";//上传失败状态
	        String success = "true";//上传成功状态
	         
	        long maxFileSize = 1024000000;
	        String suffix = file.getOriginalFilename().substring(
	                file.getOriginalFilename().lastIndexOf("."));
	        long fileSize = file.getSize();
	        Map<String, Object> map = new HashMap<String, Object>();
	        	map.put("suffix", suffix);
	        	map.put("fileSize", fileSize+"");
	            if (fileSize < maxFileSize) {
	                // System.out.println("fileSize"+fileSize);
	                String fileName = file.getOriginalFilename();
	                File tempFile = new File(docPath, new Date().getTime() + "-gettime-" 
	                        + fileName);
	                try {
	                    if (!tempFile.getParentFile().exists()) {
	                        tempFile.getParentFile().mkdirs();//如果是多级文件使用mkdirs();如果就一层级的话，可以使用mkdir()
	                    }
	                    if (!tempFile.exists()) { 
	                        tempFile.createNewFile();
	                    }
	                    file.transferTo(tempFile);
	                } catch (IllegalStateException e) {
		                map.put("SUCESS", fail);
		                map.put("data", "文件上传失败");
	                    throw e;
	                }
	 
	                map.put("SUCESS", success);
	                map.put("data", docPath +new Date().getTime() + "-gettime-"+ tempFile.getName());
	 
	            } else {
	                map.put("SUCESS", fail);
	                map.put("data", "文件过大，请重新选择文件");
	            }
	 
	        
	        return map;
	    }
}
