package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "公告")
@Table(name = "NoticeData")
public class NoticeData implements TableEntity {
	
	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;
	
	@Comment(search = "search_LIKE_platform", desc = "渠道")
	@Column(name = "platform", nullable = true)
	private String platform;

	@Lob
	@Comment(search = "search_LIKE_content", desc = "公告内容")
	@Column(name = "content", nullable = true)
	private String content;

	@Comment(search = "search_LIKE_type", desc = "公告类型")
	@Column(name = "type", nullable = false)
	private String type;

	@Comment(search = "search_EQ_isUpdate", desc = "是否更新")
	@Column(name = "isUpdate", nullable = false)
	private int isUpdate;

	@Override
	public Long getCode() {
		return code;
	}

	@Override
	public void setCode(Long code) {
		this.code = code;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NoticeData [code=");
		builder.append(code);
		builder.append(", content=");
		builder.append(content);
		builder.append(", isUpdate=");
		builder.append(isUpdate);
		builder.append(", platform=");
		builder.append(platform);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

}
