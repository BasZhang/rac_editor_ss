package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "等级")
@Table(name = "Level")
public class Level implements TableEntity {

	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Comment(search = "search_EQ_prestige", desc = "声望")
	@Column(name = "prestige", nullable = false)
	private int prestige;

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

	public int getPrestige() {
		return prestige;
	}

	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

}