package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "比赛目标类型")
@Table(name = "MatchTypeData")
public class MatchTypeData implements TableEntity {
	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
	private Long code;

	@Comment(search = "search_EQ_name", desc = "名称")
	@Column(name = "name", nullable = false)
	private String name;
	
	@Comment(search = "search_EQ_strategy", desc = "算法（Place|AvgSpeed|PerfectTurn|DriftingTime|Pursuit|TimeAttack）")
	@Column(name = "strategy", nullable = false)
	private String strategy;

	@Comment(search = "search_EQ_isUpdate", desc = "是否更新")
	@Column(name = "isUpdate", nullable = false)
	private int isUpdate;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

}
