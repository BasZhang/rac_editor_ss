package com.ourpalm.editor.service;

import java.util.List;

import com.ourpalm.editor.dto.SimpleFileInfo;

/**
 * 编辑器对被控项目的逻辑操作
 * 
 * @author zhangbo
 * 
 */
public interface DeployService {
	public final String DEFAULT_CHANNEL_NAME = "(COMMON_RES)";

	/**
	 * 根据渠道名，得到其资源文件结构。
	 * 
	 * @param channelName
	 *            渠道名
	 * @return 资源根目录下（不包含根目录）的文件结构。
	 */
	public List<SimpleFileInfo> getFileTree(String channelName);

	/**
	 * 得到数据库记录的所有的渠道名。
	 * 
	 * @return 所有渠道名列表。
	 */
	public List<String> getAllChannels();

	/**
	 * 得到渠道资源配置版本。
	 * 
	 * @param channelName
	 *            渠道名
	 * @return 资源版本
	 */
	public String getChannelVersion(String channelName);

	/**
	 * 得到渠道名对应的所有资源文件路径。
	 * 
	 * @param channelName
	 *            渠道名
	 * @return 渠道名对应的所有资源文件路径。
	 */
	public String[] getFileNamesOfChannel(String channelName);

	/**
	 * 连接渠道名和文件名，文件名和含父文件夹。
	 * 
	 * @param channelName
	 *            渠道名
	 * @param originalFileName
	 *            文件名
	 * @return 完整的文件路径。
	 */
	public String joinFileDestPath(String channelName, String originalFileName);
}
