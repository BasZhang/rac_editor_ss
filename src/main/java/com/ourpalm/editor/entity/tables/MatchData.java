package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.ArrayData;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "比赛")
@Table(name = "MatchData")
public class MatchData implements TableEntity {

	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Comment(search = "search_LIKE_name", desc = "名字")
	@Column(name = "name", nullable = true)
	private String name;
	
	@Comment(search = "search_EQ_type", desc = "比赛类型")
	@Column(name = "type", nullable = false)
	private int type;
	
	@Comment(search = "search_EQ_goal", desc = "达成1、2、3星的目标")
	@Column(name = "goal", nullable = true)
	@ArrayData
	private String goal;
	
	@Comment(search = "search_EQ_rewardsId", desc = "达成0、1、2、3星得到的奖励ID（0是安慰奖）")
	@Column(name = "rewardsId", nullable = true)
	@ArrayData
	private String rewardsId;

	@Comment(search = "search_EQ_conditionIds", desc = "条件ID或-1")
	@Column(name = "conditionIds", nullable = true)
	@ArrayData
	private String conditionIds;

	@Comment(search = "search_EQ_preMatch", desc = "前置比赛ID或-1")
	@Column(name = "preMatch", nullable = false)
	private int preMatch;

	@Comment(search = "search_EQ_unreachableScore", desc = "理论不可达到的极限分数")
	@Column(name = "unreachableScore", nullable = false)
	private int unreachableScore;

	@Comment(search = "search_EQ_passScore", desc = "过关分数")
	@Column(name = "passScore", nullable = false)
	private int passScore;
	
	@Comment(search = "search_EQ_tourId", desc = "所属赛事Id")
	@Column(name = "tourId", nullable = false)
	private int tourId;

	@Comment(search = "search_EQ_matchGroup", desc = "比赛组")
	@Column(name = "matchGroup", nullable = false)
	private int matchGroup;

	@Comment(search = "search_LIKE_carIds", desc = "可用的车ID")
	@Column(name = "carIds", nullable = true)
	@ArrayData
	private String carIds;

	@Comment(search = "search_EQ_needRank", desc = "是否参与排名")
	@Column(name = "needRank", nullable = false)
	private int needRank;

	@Comment(search = "search_EQ_isCertificate", desc = "是否是考资格证的比赛")
	@Column(name = "isCertificate", nullable = false)
	private int isCertificate;

	@Comment(search = "search_EQ_isUpdate", desc = "是否更新")
	@Column(name = "isUpdate", nullable = false)
	private int isUpdate;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getRewardsId() {
		return rewardsId;
	}

	public void setRewardsId(String rewardsId) {
		this.rewardsId = rewardsId;
	}

	public String getConditionIds() {
		return conditionIds;
	}

	public void setConditionIds(String conditionIds) {
		this.conditionIds = conditionIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public int getUnreachableScore() {
		return unreachableScore;
	}

	public void setUnreachableScore(int unreachableScore) {
		this.unreachableScore = unreachableScore;
	}

	public int getPassScore() {
		return passScore;
	}

	public void setPassScore(int passScore) {
		this.passScore = passScore;
	}

	public int getMatchGroup() {
		return matchGroup;
	}

	public void setMatchGroup(int matchGroup) {
		this.matchGroup = matchGroup;
	}

	public int getTourId() {
		return tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public String getCarIds() {
		return carIds;
	}

	public void setCarIds(String carIds) {
		this.carIds = carIds;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	public int getPreMatch() {
		return preMatch;
	}

	public void setPreMatch(int preMatch) {
		this.preMatch = preMatch;
	}

	public int getNeedRank() {
		return needRank;
	}

	public void setNeedRank(int needRank) {
		this.needRank = needRank;
	}

	public int getIsCertificate() {
		return isCertificate;
	}

	public void setIsCertificate(int isCertificate) {
		this.isCertificate = isCertificate;
	}

}