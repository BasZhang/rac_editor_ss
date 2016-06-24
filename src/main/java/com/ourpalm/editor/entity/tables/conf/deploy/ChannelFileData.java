package com.ourpalm.editor.entity.tables.conf.deploy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.ArrayData;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "deploy:渠道资源配置")
@Table(name = "_tt_ChannelFileData")
public class ChannelFileData implements TableEntity {
	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Comment(search = "search_EQ_channel", desc = "渠道名")
	@Column(name = "channel", nullable = false)
	private String channel;

	@Comment(search = "search_EQ_folder", desc = "资源文件夹")
	@Column(name = "folder", nullable = true)
	private String folder;
	@Comment(search = "search_LIKE_filePaths", desc = "资源格式")
	@Column(name = "filePaths", nullable = true)
	@ArrayData
	private String filePaths;

	@Comment(search = "search_EQ_resVersion", desc = "版本")
	@Column(name = "resVersion", nullable = true)
	private String resVersion;

	@Comment(search = "search_LIKE_remote", desc = "远程CDN地址")
	@Column(name = "remote", nullable = true)
	private String remote;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getRemote() {
		return remote;
	}

	public void setRemote(String remote) {
		this.remote = remote;
	}

	public String getFilePaths() {
		return filePaths;
	}

	public void setFilePaths(String filePaths) {
		this.filePaths = filePaths;
	}

	public String getResVersion() {
		return resVersion;
	}

	public void setResVersion(String resVersion) {
		this.resVersion = resVersion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelFileData [channel=");
		builder.append(channel);
		builder.append(", code=");
		builder.append(code);
		builder.append(", filePaths=");
		builder.append(filePaths);
		builder.append(", folder=");
		builder.append(folder);
		builder.append(", remote=");
		builder.append(remote);
		builder.append(", resVersion=");
		builder.append(resVersion);
		builder.append("]");
		return builder.toString();
	}

}
