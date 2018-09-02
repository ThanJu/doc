package me.phoibe.doc.cms.controller;

import me.phoibe.doc.cms.entity.MultipartFileParam;
import me.phoibe.doc.cms.entity.ResultStatus;
import me.phoibe.doc.cms.entity.ResultVo;
import me.phoibe.doc.cms.service.StorageService;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/phoibe/uopload")
public class BreakPointController {
	private Logger logger = LoggerFactory.getLogger(BreakPointController.class);

	@Autowired
	private StorageService storageService;

	@Value("${breakpoint.upload.dir}")
	private String finalDirPath;
	/**
	 * 秒传判断，断点判断
	 *
	 * @return
	 */
	@RequestMapping(value = "checkFileMd5", method = RequestMethod.POST)
	@ResponseBody
	public Object checkFileMd5(String md5,String filename) throws IOException {
		
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
	 * @throws Exception
	 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
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

}
