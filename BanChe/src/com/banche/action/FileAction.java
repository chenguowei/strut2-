package com.banche.action;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.banche.Constants;
import com.banche.FreeVist;
import com.dijing.server.web.action.OutputStringAction;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月20日上午9:26:37
 */
public class FileAction extends ActionSupport {

	static Logger logger = LogManager.getLogger(FileAction.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File upfile;
	private String upfileContentType;
	private String upfileFileName;
	
	private String flag;
	
	/**
	 * bc_File_uploadFile
	 * @return
	 * @throws Exception
	 */
	public String uploadFile() throws Exception {
		OutputStringAction out = new OutputStringAction();
		logger.debug("upfileFileName: " + upfileFileName);
		if(upfileFileName==null){
			out.jsonResultError("");
			return null;
		}
		int index = upfileFileName.lastIndexOf('.');
		String suffix = upfileFileName.substring(index);
		String filename = UUID.randomUUID().toString() + suffix;
		String filepath = Constants.getUploadPath() + filename;
		String relativePath = Constants.getRelativePath() + filename;
		relativePath = relativePath.replace("\\", "/");
		File file = new File(filepath);
		FileUtils.copyFile(upfile, file);	
		
		JSONObject json = new JSONObject();
		json.put("path", relativePath);
		json.put("flag", flag);
		out.jsonResultSuccess("", json);
		return null;
	}

	public File getUpfile() {
		return upfile;
	}

	public void setUpfile(File upfile) {
		this.upfile = upfile;
	}

	public String getUpfileContentType() {
		return upfileContentType;
	}

	public void setUpfileContentType(String upfileContentType) {
		this.upfileContentType = upfileContentType;
	}

	public String getUpfileFileName() {
		return upfileFileName;
	}

	public void setUpfileFileName(String upfileFileName) {
		this.upfileFileName = upfileFileName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
