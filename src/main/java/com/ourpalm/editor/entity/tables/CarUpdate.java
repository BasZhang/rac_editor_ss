package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "车道具配置")
@Table(name = "CarUpdate")
public class CarUpdate implements TableEntity {

	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Comment(search = "search_LIKE_typeLv", desc = "类型(例1_2   前面是类型 1速度 2加速度 3悬挂 4刹车    后面是等级)")
	@Column(name = "typeLv", nullable = false)
	private String typeLv;

	@Comment(search = "search_EQ_needCoin", desc = "升级需要的金币")
	@Column(name = "needCoin", nullable = false)
	private int needCoin;
	
	@Comment(search = "search_EQ_needTime", desc = "升级需要的时间(秒)")
	@Column(name = "needTime", nullable = false)
	private long needTime;

	@Comment(search = "search_EQ_quickNeedGem", desc = "立即完成需要的钻石")
	@Column(name = "quickNeedGem", nullable = false)
	private int quickNeedGem;

	@Comment(search = "search_EQ_propId", desc = "道具ID")
	@Column(name = "propId", nullable = false)
	private int propId;

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

	public String getTypeLv() {
		return typeLv;
	}

	public void setTypeLv(String typeLv) {
		this.typeLv = typeLv;
	}

	public int getNeedCoin() {
		return needCoin;
	}

	public void setNeedCoin(int needCoin) {
		this.needCoin = needCoin;
	}

	public long getNeedTime() {
		return needTime;
	}

	public void setNeedTime(long needTime) {
		this.needTime = needTime;
	}

	public int getQuickNeedGem() {
		return quickNeedGem;
	}

	public void setQuickNeedGem(int quickNeedGem) {
		this.quickNeedGem = quickNeedGem;
	}

	public int getPropId() {
		return propId;
	}

	public void setPropId(int propId) {
		this.propId = propId;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

}