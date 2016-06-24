package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "道具")
@Table(name = "Prop")
public class Prop implements TableEntity {
	
	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
	private Long code;

	@Comment(search = "search_EQ_type", desc = "道具类型")
	@Column(name = "type", nullable = false)
	private int type;
	
	@Comment(search = "search_EQ_needGem", desc = "购买需要的钻石")
	@Column(name = "needGem", nullable = false)
	private int needGem;

	@Comment(search = "search_EQ_isUpdate", desc = "是否更新")
	@Column(name = "isUpdate", nullable = false)
	private int isUpdate;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public int getNeedGem() {
		return needGem;
	}

	public void setNeedGem(int needGem) {
		this.needGem = needGem;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

}
