package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "NotifyMsg")
@Table(name = "NotifyMsg")
public class NotifyMsg implements TableEntity {

	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
	private Long code;

	@Comment(search = "search_LIKE_msg", desc = "msg")
	@Column(name = "msg", nullable = true)
	private String msg;

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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

}
