package com.ourpalm.editor.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.ourpalm.editor.dto.SimpleFileInfo;
import com.ourpalm.editor.service.DeployService;
import com.ourpalm.editor.service.TablesService;
import com.ourpalm.jmx.RacingGmServiceMBean;

@Controller
public class DeveloperController {

	static final Logger logger = LoggerFactory.getLogger(DeveloperController.class);

	@Autowired
	private RacingGmServiceMBean gmService;

	@Autowired
	TablesService tablesService;

	@Autowired
	DeployService deployService;

	/**
	 * 浏览服务器资源时的页面跳转。
	 */
	@PreAuthorize("hasAnyRole('ADMIN','DEV')")
	@RequestMapping(value = "/deploy_browse", method = RequestMethod.GET)
	public String browse(Model model) {
		model.addAttribute("channels", deployService.getAllChannels());
		return "deploy_browse";
	}

	// /**
	// * 得到所有的渠道。
	// */
	// @RequestMapping(value = "/channel_names", method = RequestMethod.POST)
	// public List<String> channels() {
	// List<String> all = deployService.getAllChannels();
	// return all;
	// }

	/**
	 * 得到某个渠道的资源文件夹信息
	 */
	@ResponseBody
	@RequestMapping(value = "{channelName}/deploy_filetree", method = RequestMethod.POST)
	public List<SimpleFileInfo> getFileTree(@PathVariable String channelName) {
		List<SimpleFileInfo> fileTree = deployService.getFileTree(channelName);
		return fileTree;
	}

	/**
	 * 配置页面跳转。
	 */
	@PreAuthorize("hasAnyRole('ADMIN','DEV')")
	@RequestMapping(value = "/deploy_config", method = RequestMethod.GET)
	public String config() {
		return "deploy_config";
	}

	/**
	 * 获得渠道的版本，当取不到时获取通用资源的版本。
	 */
	@ResponseBody
	@RequestMapping(value = "{channelName}/deploy_version", method = RequestMethod.POST)
	public Object getChannelVersion(@PathVariable String channelName) {
		Map<String, Object> m = Maps.newHashMap();
		String ver = deployService.getChannelVersion(channelName);
		if (Strings.isNullOrEmpty(ver)) {
			ver = deployService.getChannelVersion(DeployService.DEFAULT_CHANNEL_NAME);
		}
		m.put("version", ver);
		return m;
	}

	@ResponseBody
	@RequestMapping(value = "deploy_pushSync", method = RequestMethod.POST)
	public Object pushSyncCommand(Long channelCode, String channelName) {
		Map<String, Object> map = Maps.newHashMap();
		if (!org.apache.commons.lang3.SystemUtils.IS_OS_LINUX) {
			map.put("msg", "非Linux系统不能执行此操作！");
		} else {
			// String[] fileNamesOfChannel =
			// deployService.getFileNamesOfChannel(channelName);
			// TODO 发送 fileNamesOfChannel文件到各自cdn
			map.put("successed", true);
			map.put("msg", "假的，目前这个功能未开发！");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/deploy_upload", method = RequestMethod.POST)
	public Object uploadFile(@RequestParam("channelName") String channelName, @RequestParam("file") MultipartFile file) {
		Map<String, Object> map = Maps.newHashMap();
		if (file.isEmpty()) {
			map.put("msg", "文件无效！");
		} else {
			String uploadDestPath = deployService.joinFileDestPath(channelName, file.getOriginalFilename());
			if (uploadDestPath == null) {
				map.put("msg", "未配置此渠道的资源文件夹！");
			} else {
				try {
					File dest = new File(uploadDestPath);
					if (dest.exists()) {
						map.put("msg", "文件已存在！");
					} else {
						file.transferTo(dest);
						map.put("successed", true);
						map.put("msg", file.getOriginalFilename() + "已上传到服务器" + uploadDestPath);
					}
				} catch (IOException e) {
					e.printStackTrace();
					map.put("msg", "生成文件失败！");
				}
			}
		}
		return map;
	}

	@PreAuthorize("hasAnyRole('ADMIN','DEV')")
	@RequestMapping(value = "/deploy_download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadFile(@RequestParam("channelName") String channelName, @RequestParam("filePath") String filePath) {
		String downDestPath = deployService.joinFileDestPath(channelName, filePath);
		if (downDestPath != null) {
			File dest = new File(downDestPath);
			if (dest.exists() && !dest.isDirectory()) {
				String fileName = dest.getName();
				try {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDispositionFormData("attachment", fileName);
				try {
					byte[] byteArray = org.apache.commons.io.FileUtils.readFileToByteArray(dest);
					return new ResponseEntity<byte[]>(byteArray, headers, HttpStatus.CREATED);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
	}

	@ResponseBody
	@RequestMapping(value = "/deploy_delete", method = RequestMethod.POST)
	public Object deleteFile(@RequestParam("channelName") String channelName, @RequestParam("filePath") String filePath) {
		Map<String, Object> map = Maps.newHashMap();
		String deleteDestPath = deployService.joinFileDestPath(channelName, filePath);
		if (deleteDestPath == null) {
			map.put("msg", "未配置此渠道的资源文件夹！");
		} else {
			File dest = new File(deleteDestPath);
			if (dest.exists()) {
				dest.delete();
				map.put("msg", "删除完成！");
				map.put("successed", true);
			} else {
				map.put("msg", "文件不存在！");
			}
		}
		return map;
	}

	@RequestMapping(value = "dv_showFunctions", method = { RequestMethod.GET })
	public String showFunctions() {
		return "dv_show";
	}

	@ResponseBody
	@RequestMapping(value = "dv_timeout", method = { RequestMethod.POST })
	public String queryLogs(String uid, int seconds) {
		gmService.timeout(uid, seconds);
		return "OK";
	}
}
