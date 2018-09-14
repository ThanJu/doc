package me.phoibe.doc.cms.controller;

import me.phoibe.doc.cms.domain.dto.DPhoebeDocument;
import me.phoibe.doc.cms.domain.po.PhoibeDocument;
import me.phoibe.doc.cms.entity.MultipartFileParam;
import me.phoibe.doc.cms.entity.ResultStatus;
import me.phoibe.doc.cms.entity.ResultVo;
import me.phoibe.doc.cms.service.PhoibeDocumentService;
import me.phoibe.doc.cms.service.StorageService;
import me.phoibe.doc.cms.utils.CollectionUtils;
import me.phoibe.doc.cms.utils.JsonUtils;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.executor.ReuseExecutor;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/phoibe/uopload")
public class BreakPointController {
	private Logger logger = LoggerFactory.getLogger(BreakPointController.class);

	@Autowired
	private StorageService storageService;
	@Autowired
	private PhoibeDocumentService phoibeDocumentService;

	@Value("${breakpoint.upload.window}")
	private String window;
	@Value("${breakpoint.upload.linux}")
	private String linux;
	@Value("${breakpoint.upload.status}")
	private String status;
	/**
	 * 秒传判断，断点判断
	 *
	 * @return
	 */
	@RequestMapping(value = "checkFileMd5", method = RequestMethod.POST)
	@ResponseBody
	public Object checkFileMd5(String md5,String filename) throws IOException {
		
		String finalDirPath="";
		if(status.equals("1")) {
			finalDirPath = window;
		}else {
			finalDirPath = linux;
		}
		
		String value = finalDirPath + md5+"/"+filename;
		File confFile = new File(value+".conf");
		if(!confFile.exists()){
			return new ResultVo(ResultStatus.NO_HAVE);
		}else {
			byte[] completeList = FileUtils.readFileToByteArray(confFile);
			List<String> missChunkList = new LinkedList<>();
			for (int i = 0; i < completeList.length; i++) {
				if (completeList[i] != Byte.MAX_VALUE) {
					missChunkList.add(i + "");
				}
			}
			return new ResultVo<>(ResultStatus.ING_HAVE, missChunkList);
		}
	}

	/**
	 * 上传文件
	 *
	 * @param param
	 * @param request
	 * @return
		@EnableAsync
		@Async
	 * @throws Exception
	 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public ResponseEntity fileUpload(MultipartFileParam param, HttpServletRequest request) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			logger.info("上传文件start。");
			try {
				// 方法1
				// storageService.uploadFileRandomAccessFile(param);
				// 方法2 这个更快点
				storageService.uploadFileByMappedByteBuffer(param);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("文件上传失败。{}", param.toString());
			}
			logger.info("上传文件end。");
		}
		return ResponseEntity.ok().body("上传成功。");
	}
	
	/**在线文档编辑后保存
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "docSaveUpload", method = RequestMethod.POST)
	public String docSaveUpload(MultipartFileParam param, HttpServletRequest request) throws IOException {
	
		

		Map<String,String[]> paramMap = request.getParameterMap();
		Map<String,String> result = new HashedMap();
		String docId = request.getParameter("docId");
		
		String finalDirPath="";
		if(status.equals("1")) {
			finalDirPath = window;
		}else {
			finalDirPath = linux;
		}
		
	//	String value = finalDirPath + filepath;
		try {       
			if (request.getContentLength() > 0) 
			{           
				DPhoebeDocument dPhoibeDocument = phoibeDocumentService.fetchDocumentById(Integer.parseInt(docId));

				String getFilePath = dPhoibeDocument.getFilePath();	
				
				InputStream in = request.getInputStream();
				
				
				String  lastindexStr1 =getFilePath.substring(0,getFilePath.lastIndexOf("/"));

				String filaname = getFilePath.substring(getFilePath.lastIndexOf("/")+1,getFilePath.length());
				
				if(lastindexStr1.lastIndexOf("/")>0) {
					String  lastindexStr2 =lastindexStr1.substring(0,lastindexStr1.lastIndexOf("/"));
					String value = getFilePath.substring(lastindexStr1.lastIndexOf("/")+1,getFilePath.lastIndexOf("/"));
					//CollectionUtils.
					if(CollectionUtils.isNumeric00(value)) {
						int vession = Integer.parseInt(value) + 1;
						getFilePath = lastindexStr2 + "/" +vession + "/";
					}else {
						getFilePath = lastindexStr1 + "/1/";
					}
				}else {
					getFilePath = lastindexStr1 + "/1/";
				}
				 

				String realFileName =finalDirPath +"" + getFilePath;
				
				System.out.println("realFileName " + realFileName);

				File fileDir = new File(realFileName);
				
				if(!fileDir.exists()){  
					fileDir.mkdirs();  
				} 
				
				byte[] buffer = new byte[in.available()];
				in.read(buffer);
				
				File targetFile = new File(realFileName + filaname);
				OutputStream outStream = new FileOutputStream(targetFile);
				outStream.write(buffer);
				outStream.close();
				
				File file = new File(realFileName + filaname);
				FileOutputStream fos = new FileOutputStream(file); 
				 byte[] reviceData = new byte[1024];
			        int iRead;
			        while ((iRead = in.read(reviceData)) != -1) {
			        	fos.write(reviceData, 0, iRead);
			        }
				fos.close();
				
				/*PhoibeDocument phoibeDocument = new PhoibeDocument();

				phoibeDocument.setId(Long.parseLong(docId));
				phoibeDocument.setFilePath(getFilePath + filaname);
				
				int resultNum = phoibeDocumentService.update(phoibeDocument);
				*/
				System.out.println("文件上传成功！路径 : " + realFileName ); 
				result.put("success", "true");  
			} 
			else
			{
				System.out.println("文件上传失败! input stream 长度是 0");
				result.put("success", "false");
			}
		} 
		catch (IOException e){
			System.out.println("文件上传失败! 发生了异常");
			result.put("success", "false");
			e.printStackTrace();
		}
		
		/*byte[] buffer = new byte[in.available()];
		in.read(buffer);
		
		File targetFile = new File(finalDirPath);
		OutputStream outStream = new FileOutputStream(targetFile);
		outStream.write(buffer); */

		return JsonUtils.toJson(result);
	}
}
