package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.ArrayData;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "赛事")
@Table(name = "TournamentData")
public class TournamentData implements TableEntity {

	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Comment(search = "search_LIKE_name", desc = "名称")
	@Column(name = "name", nullable = true)
	private String name;

	@Comment(search = "search_EQ_type", desc = "赛事类型（1|2|3）")
	@Column(name = "type", nullable = false)
	private int type;
	
	@Comment(search = "search_EQ_unlockConditions", desc = "解锁条件ID（-1）")
	@Column(name = "unlockConditions", nullable = true)
	@ArrayData
	private String unlockConditions;
	
	@Comment(search = "search_EQ_matchIds", desc = "比赛ID序列")
	@Column(name = "matchIds", nullable = true)
	@ArrayData
	private String matchIds;
	
	@Comment(search = "search_EQ_groupCount", desc = "有几个比赛组")
	@Column(name = "groupCount", nullable = false)
	private int groupCount;

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

	public String getMatchIds() {
		return matchIds;
	}

	public void setMatchIds(String matchIds) {
		this.matchIds = matchIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnlockConditions() {
		return unlockConditions;
	}

	public void setUnlockConditions(String unlockConditions) {
		this.unlockConditions = unlockConditions;
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

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

}