package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "版本")
@Table(name = "Version")
public class Version implements TableEntity {
	
	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
	private Long code;

	@Comment(search = "search_LIKE_version", desc = "版本")
	@Column(name = "version", nullable = false)
	private String version;

	@Comment(search = "search_EQ_must", desc = "是否强行下载")
	@Column(name = "must", nullable = false)
	private int must;

	@Comment(search = "search_EQ_isUpdate", desc = "是否更新")
	@Column(name = "isUpdate", nullable = false)
	private int isUpdate;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getMust() {
		return must;
	}

	public void setMust(int must) {
		this.must = must;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

}
