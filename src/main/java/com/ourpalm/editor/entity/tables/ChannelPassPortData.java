package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.ArrayData;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "ChannelPassPortData")
@Table(name = "ChannelPassPortData")
public class ChannelPassPortData implements TableEntity {

	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Comment(search = "search_LIKE_name", desc = "name")
	@Column(name = "name", nullable = true)
	private String name;

	@Comment(search = "search_EQ_passport", desc = "passport")
	@Column(name = "passport", nullable = true)
	@ArrayData
	private String passport;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

}