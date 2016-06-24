package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.ArrayData;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "比赛条件")
@Table(name = "ConditionData")
public class ConditionData implements TableEntity {

	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Comment(search = "search_LIKE_conditionName", desc = "条件枚举（Certificate|HasCar|Level|Stars|GoldCup|Prestige）")
	@Column(name = "conditionName", nullable = false)
	private String conditionName;

	@Comment(search = "search_LIKE_params", desc = "条件参数")
	@Column(name = "params", nullable = true)
	@ArrayData
	private String params;

	@Comment(search = "search_EQ_isUpdate", desc = "是否更新")
	@Column(name = "isUpdate", nullable = false)
	private int isUpdate;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

}
