package com.ourpalm.editor.service.impl;

import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.ourpalm.editor.dao.TablesDao;
import com.ourpalm.editor.dto.SimpleFileInfo;
import com.ourpalm.editor.entity.tables.ChannelData;
import com.ourpalm.editor.entity.tables.conf.deploy.ChannelFileData;
import com.ourpalm.editor.service.DeployService;
import com.ourpalm.editor.util.FileUtils;

/**
 * <code>DeployService</code>实现类。
 * 
 * @author zhangbo
 * 
 */
@Service("deployService")
public class DeployServiceImpl implements DeployService {
	
	@Autowired
	TablesDao tablesDao;

	@Override
	public List<SimpleFileInfo> getFileTree(String channelName) {
		List<SimpleFileInfo> ret = Lists.newArrayList();
		ChannelFileData fileData = tablesDao.getFirst(ChannelFileData.class, Restrictions.eq("channel", channelName));
		if (fileData != null) {
			String root = fileData.getFolder();
			ret.addAll(listFileTree0(root));
		}
		return ret;
	}

	private List<SimpleFileInfo> listFileTree0(String root) {
		List<SimpleFileInfo> ret = Lists.newArrayList();
		File r = new File(root);
		if (r.exists()) {
			File[] childrenFiles = r.listFiles();
			for (int i = 0; i < childrenFiles.length; ++i) {
				BigInteger id = new BigInteger(String.valueOf(i + 1));
				SimpleFileInfo fi = getFileTreeRecursively0(childrenFiles[i], id);
				if (fi != null)
					ret.add(fi);
			}
		}
		return ret;
	}

	private SimpleFileInfo getFileTreeRecursively0(File root, BigInteger id) {
		SimpleFileInfo info = new SimpleFileInfo();
		info.setId(id.toString());
		info.setName(root.getName());
		info.setSize(FileUtils.formatSize(root.length()));
		SimpleDateFormat dateFmt = new SimpleDateFormat("HH:mm:ss - MM/dd/YYYY", Locale.CHINA);
		String dateStr = dateFmt.format(new Date(root.lastModified()));
		info.setDate(dateStr);
		if (root.isDirectory()) {
			info.setSize("");// do not display size while the file is a folder.
			info.setState("closed");
			ArrayList<SimpleFileInfo> childrenList = Lists.newArrayList();
			File[] c = root.listFiles();
			for (int i = 0; i < c.length; ++i) {
				String groupString = id.toString();
				BigInteger childrenId = new BigInteger(groupString + (i + 1));
				SimpleFileInfo fi = getFileTreeRecursively0(c[i], childrenId);
				if (fi != null)
					childrenList.add(fi);
			}
			info.setChildren(childrenList);
		}
		return info;
	}

	@Override
	public List<String> getAllChannels() {
		List<String> list = Lists.newArrayList();
		List<ChannelData> channels = tablesDao.getAll(ChannelData.class);
		for (ChannelData c : channels) {
			list.add(c.getName());
		}
		int hasDefaultFolder = tablesDao.count(ChannelFileData.class, Restrictions.eq("channel", DEFAULT_CHANNEL_NAME));
		if (hasDefaultFolder > 0) {
			list.add(DEFAULT_CHANNEL_NAME);
		}
		return list;
	}

	@Override
	public String getChannelVersion(String channelName) {
		ChannelFileData data = tablesDao.getFirst(ChannelFileData.class, Restrictions.eq("channel", channelName));
			return data == null ? null : data.getResVersion();
	}

	@Override
	public String[] getFileNamesOfChannel(String channelName) {
		// TODO 未开发此功能
		// 思路：根据channelName获得各自的文件
		return null;
	}

	@Override
	public String joinFileDestPath(String channelName, String originalFileName) {
		ChannelFileData channelFileData = tablesDao.getFirst(ChannelFileData.class, Restrictions.eq("channel", channelName));
		if (channelFileData == null || Strings.isNullOrEmpty(channelFileData.getFolder())) {
			return null;
		} else {
			return channelFileData.getFolder() + originalFileName;
		}
	}

}
