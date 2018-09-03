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
import me.phoibe.doc.cms.utils.PlatDateTimeUtil;

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

	@Value("${breakpoint.upload.window}")
	private String window;
	@Value("${breakpoint.upload.linux}")
	private String linux;
	@Value("${breakpoint.upload.status}")
	private String status;
	
	@GetMapping("list/{index}/{limit}")
	public String listDoucument(@PathVariable Integer index, @PathVariable Integer limit,
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
		pageParam.setStart(index * limit + 1);
		pageParam.setLimit(limit);
		pageParam.setParam(param == null ? new DPhoebeDocument() : param);
		pageParam.setOrderBy(orderBy);
		pageParam.setSort(sort);

		PageList<DPhoebeDocument> pageList = phoibeDocumentService.fetchDocumentByPageList(pageParam);

		return JsonUtils.toJson(new Result<PageList<DPhoebeDocument>>(Code.SUCCESS, pageList));
	}

	@GetMapping("list/user/{index}/{limit}")
	public String listDoucumentUser(@PathVariable Integer index, @PathVariable Integer limit) {

		PageParam<DPhoebeDocument> pageParam = new PageParam<>();
		pageParam.setStart(index * limit + 1);
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

	@RequestMapping(value = { "save" })
	public String saveOrUpdate(@RequestBody Map rb, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("UTF-8");

			Map<String, Object> map = null;
			String filemd5 = (String) rb.get("filemd5");
			String filename = (String) rb.get("filename");
			String suffix ="";
			if(filename!=""&&null!=filename) {
				suffix = filename.substring(filename.lastIndexOf(".")+1, filename.length());
			}
			String fileSize = (String) rb.get("filesize");
			PhoibeDocument phoibeDocument = new PhoibeDocument();
			phoibeDocument.setName((String) rb.get("name"));
			phoibeDocument.setWarstate((String) rb.get("warcountry"));
			String combat_type =(String)rb.get("combat_type");
			combat_type = combat_type.replaceAll(" ", "");
			phoibeDocument.setCombatType(Short.parseShort(combat_type));
			String srms =(String)rb.get("arms");
			srms = srms.replaceAll(" ", "");
			phoibeDocument.setArms(Short.parseShort(srms));
			phoibeDocument.setWaraddr((String) rb.get("waraddr"));
			phoibeDocument.setWartime(PlatDateTimeUtil.formatStr((String) rb.get("wartime"),"YYYY-MM-DD"));
			phoibeDocument.setWinner((String) rb.get("winner"));
			phoibeDocument.setLoser((String) rb.get("loser"));
			phoibeDocument.setWarnum((String) rb.get("warnum"));
			phoibeDocument.setDescription((String) rb.get("description"));
			phoibeDocument.setAuditStatus((short) (1));
			phoibeDocument.setAuditUserId(1l);
			phoibeDocument.setContent("正文内容正文内容正文内容正文内容正文内容正文内容".getBytes());
			String finalDirPath="";
			if(status.equals("1")) {
				finalDirPath = window;
			}else {
				finalDirPath = linux;
			}
			phoibeDocument.setFilePath(finalDirPath + filemd5+"/"+filename);
			phoibeDocument.setFileSize(new BigDecimal(fileSize));
			phoibeDocument.setFormat(suffix);;
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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("success", false);
		}
		LOGGER.info(JsonUtils.toJson(resultMap));
		return JsonUtils.toJson(resultMap);
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
