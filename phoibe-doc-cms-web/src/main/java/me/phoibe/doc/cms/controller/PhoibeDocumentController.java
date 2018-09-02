package me.phoibe.doc.cms.controller;

import me.phoibe.doc.cms.dao.PhoibeDocumentMapper;
import me.phoibe.doc.cms.domain.dto.DPhoebeDocument;
import me.phoibe.doc.cms.domain.po.PageList;
import me.phoibe.doc.cms.domain.po.PageParam;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import me.phoibe.doc.cms.entity.Code;
import me.phoibe.doc.cms.entity.Result;
import me.phoibe.doc.cms.service.PhoibeDocumentService;
import me.phoibe.doc.cms.utils.JsonUtils;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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

	@Value("${upload.filePath}")
    private String filepath;
	
	@PostMapping("save")
	public String saveDoucument(@RequestBody PhoibeDocument request) {

		LOGGER.info(JsonUtils.toJson(request));

		PhoibeDocument phoibeDocument = new PhoibeDocument();
		// short arms = 1;
		// phoibeDocument.setArms(arms);
		// phoibeDocument.setAuditStatus(arms);
		// phoibeDocument.setAuditUserId(new BigDecimal(1));
		// phoibeDocument.setCombatType(arms);
		// phoibeDocument.setContent("adfdasfdasfadf".getBytes());
		// phoibeDocument.setDescription("sdfadsfd");
		// phoibeDocument.setFilePath("test");
		// phoibeDocument.setFileSize(new BigDecimal(1234556789));
		// phoibeDocument.setFormat("doc");
		// phoibeDocument.setName("test");
		// phoibeDocument.setProgress((short) (arms +10));
		// phoibeDocument.setScore(new BigDecimal(1.2));
		// phoibeDocument.setTag("1,34,546,121");
		// phoibeDocument.setUpdateTime(new Date());
		// phoibeDocument.setUserId(new BigDecimal(1234));
		// phoibeDocument.setStatus(arms);
		// phoibeDocument.setCreateTime(new Date());

		BeanUtils.copyProperties(request, phoibeDocument);
		phoibeDocument.setCreateTime(new Date());

		int result = phoibeDocumentService.save(phoibeDocument);
		System.out.println(result);

		return JsonUtils.toJson(new Result<>(Code.SUCCESS, "ok"));
	}

	@GetMapping("list/{start}/{limit}")
	public String listDoucument(@PathVariable Integer start, @PathVariable Integer limit,
			@RequestParam(required = false) String f, @ModelAttribute DPhoebeDocument param) {
		String orderBy = "CREATE_TIME";
		String sort = "DESC";

		if(!StringUtils.isEmpty(f)) {
			switch (f) {
				case "hot": {
					orderBy = "HITCOUNT";
					break;
				}
				case "handpick": {
					orderBy = "RECORDER";
					break;
				}
				case "audit": {
					orderBy = "AUDIT_TIME";
					sort = "DESC nulls last";
					break;
				}
				case "storage": {
					orderBy = "STOCK_TIME";
					sort = "DESC nulls last";
					break;
				}


			}
		}
		PageParam<DPhoebeDocument> pageParam = new PageParam<>();
		pageParam.setStart(start);
		pageParam.setLimit(limit);
		pageParam.setParam(param == null ? new DPhoebeDocument() : param);
		pageParam.setOrderBy(orderBy);
		pageParam.setSort(sort);

		PageList<DPhoebeDocument> pageList = phoibeDocumentService.fetchDocumentByPageList(pageParam);

		return JsonUtils.toJson(new Result<PageList<DPhoebeDocument>>(Code.SUCCESS, pageList));
	}

	@GetMapping("list/user/{start}/{limit}")
	public String listDoucumentUser(@PathVariable Integer start, @PathVariable Integer limit) {

		PageParam<DPhoebeDocument> pageParam = new PageParam<>();
		pageParam.setStart(start);
		pageParam.setLimit(limit);

		List<DPhoebeDocument> list = phoibeDocumentService.fetchDocumentUserList(pageParam);

		return JsonUtils.toJson(new Result<List<DPhoebeDocument>>(Code.SUCCESS, list));
	}

	@GetMapping("fetch/{id}")
	public String getDoucument(@PathVariable Integer id) {

		DPhoebeDocument dPhoibeDocument = phoibeDocumentService.fetchDocumentById(id);

		return JsonUtils.toJson(new Result<DPhoebeDocument>(Code.SUCCESS, dPhoibeDocument));

	}

	@GetMapping("count")
	public String countDoucument() {
		PageParam<DPhoebeDocument> pageParam = new PageParam<>();
		pageParam.setParam(new DPhoebeDocument());
		Long count = phoibeDocumentMapper.selectCountByPage(pageParam);

		return JsonUtils.toJson(new Result<>(Code.SUCCESS, count));
	}

	// @DeleteMapping("delete/{id}")
	// public String removeDocument(@PathVariable Integer id) {
	// try {
	// phoibeDocumentService.removeDocumentById(id);
	// } catch (Exception e) {
	// JsonUtils.toJson(new Result<>(Code.FAILED, e.getMessage()));
	// }
	// return JsonUtils.toJson(new Result<>(Code.SUCCESS, ""));
	// }

	@RequestMapping(value = { "upload" })
	public String saveOrUpdate(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("UTF-8");
			String title = request.getParameter("title");
			String combat_type = request.getParameter("combat_type");
			String arms = request.getParameter("arms");
			String description = request.getParameter("description");

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("file");
			Map<String, Object> map = uploadFile(file, filepath);
			String pd = (String) map.get("SUCESS");
			if (pd.equals("true")) {
				String suffix = map.get("suffix").toString();
				suffix = suffix.substring(1, suffix.length());
				String filename = (String) map.get("data");
				String fileSize = (String) map.get("fileSize");
				/*
				 * //如果是doc文件，读取文件内容 String path=""; InputStream is = new FileInputStream(new
				 * File(path)); WordExtractor ex = new WordExtractor(is); buffer = ex.getText();
				 * ex.close();
				 */

				PhoibeDocument phoibeDocument = new PhoibeDocument();
				phoibeDocument.setArms(Short.parseShort(arms));
				phoibeDocument.setAuditStatus((short) (1));
				phoibeDocument.setAuditUserId(1l);
				phoibeDocument.setCombatType(Short.parseShort(combat_type));
				phoibeDocument.setContent("正文内容正文内容正文内容正文内容正文内容正文内容".getBytes());
				phoibeDocument.setDescription(description);
				phoibeDocument.setFilePath(filepath + filename);
				phoibeDocument.setFileSize(new BigDecimal(fileSize));
				phoibeDocument.setFormat(suffix);
				phoibeDocument.setName(title);
				phoibeDocument.setProgress((short) (100));
				phoibeDocument.setScore(new BigDecimal(1.2));
				phoibeDocument.setTag("#战役#,#标签#,#讲解#,#视频#");
				phoibeDocument.setUpdateTime(new Date());
				phoibeDocument.setUserId(1l);
				phoibeDocument.setUserRealName("admin");
				phoibeDocument.setStatus((short) (2));
				phoibeDocument.setCreateTime(new Date());
				short pc = (short) (1 + Math.random() * (10 - 1 + 1));
				phoibeDocument.setPagecount(pc);

				int result = phoibeDocumentService.save(phoibeDocument);

				BeanUtils.copyProperties(request, phoibeDocument);
				if (result > 0) {
					resultMap.put("success", true);
				} else {
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

	public static Map<String, Object> uploadFile(MultipartFile file, String docPath) throws IOException {
		String fail = "fail";// 上传失败状态
		String success = "true";// 上传成功状态

		long maxFileSize = 1024000000;
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		long fileSize = file.getSize();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("suffix", suffix);
		map.put("fileSize", fileSize + "");
		if (fileSize < maxFileSize) {
			// System.out.println("fileSize"+fileSize);
			String fileName = file.getOriginalFilename();
			File tempFile = new File(docPath, new Date().getTime() + "-gettime-" + fileName);
			try {
				if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdirs();// 如果是多级文件使用mkdirs();如果就一层级的话，可以使用mkdir()
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
			map.put("data", tempFile.getName());

		} else {
			map.put("SUCESS", fail);
			map.put("data", "文件过大，请重新选择文件");
		}

		return map;
	}

	@RequestMapping("update/{f}/{id}")
	public String modifyDocument(@PathVariable String f, @PathVariable Integer id) {
		try {
			PhoibeDocument phoibeDocument = new PhoibeDocument();
			phoibeDocument.setId(1l);
			if ("instorage".equals(f)) {
				phoibeDocument.setIsstock(Short.valueOf("1"));
				phoibeDocument.setStockTime(new Date());
				phoibeDocument.setStocker("admin");
			} else if ("outstorage".equals(f)) {
				phoibeDocument.setIsstock(Short.valueOf("0"));
			} else if ("checkpass".equals(f)) {
				phoibeDocument.setAuditStatus(Short.valueOf("2"));
				phoibeDocument.setAuditTime(new Date());
				phoibeDocument.setAuditUserId(1l);
			} else if ("checkrefuse".equals(f)) {
				phoibeDocument.setAuditStatus(Short.valueOf("3"));
				phoibeDocument.setAuditTime(new Date());
				phoibeDocument.setAuditUserId(1l);
			} else {
				throw new Exception("业务参数错误");
			}

			phoibeDocumentService.modifyDocumentById(phoibeDocument);
		} catch (Exception e) {
			JsonUtils.toJson(new Result<>(Code.FAILED, e.getMessage()));
		}
		return JsonUtils.toJson(new Result<>(Code.SUCCESS, ""));
	}

	// phoibe/document/download
	@RequestMapping(value = {"download"})
	public byte[] download(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String pdId= request.getParameter("Id");
		DPhoebeDocument pd = phoibeDocumentService.fetchDocumentById(Integer.parseInt(pdId));

			if(null!=pd){

				String fileAbosultePath = pd.getFilePath();
				
				File file = new File(fileAbosultePath);
				String filename = file.getName();
				filename = filename.substring(filename.indexOf("-gettime-")+9,filename.length());
				
				byte[]bytes=getContent(fileAbosultePath);
				 response.setContentType("multipart/form-data"); 
                 //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
				response.addHeader("Content-Disposition", "attachment;fileName="+new String(filename.getBytes(),"ISO8859-1"));  
				return bytes;   
			}
			return null;
	}

	public byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();
		return buffer;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 严格限制日期转换
		sdf.setLenient(false);

		// true:允许输入空值，false:不能为空值
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

	}
}
