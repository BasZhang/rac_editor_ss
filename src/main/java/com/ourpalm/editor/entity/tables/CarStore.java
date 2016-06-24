package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "车商店")
@Table(name = "CarStore")
public class CarStore implements TableEntity {

	@Id
	@Comment(search = "search_EQ_code", desc = "id")
	@Column(name = "code")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Comment(search = "search_EQ_needStar", desc = "需要的星星")
	@Column(name = "needStar", nullable = false)
	private int needStar;

	@Comment(search = "search_EQ_needGem", desc = "钻石购买价格")
	@Column(name = "needGem", nullable = false)
	private int needGem;

	@Comment(search = "search_EQ_needCoin", desc = "金币购买价格")
	@Column(name = "needCoin", nullable = false)
	private int needCoin;

	@Comment(search = "search_EQ_useNeedLv", desc = "使用等级")
	@Column(name = "useNeedLv", nullable = false)
	private int useNeedLv;

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

	public int getNeedStar() {
		return needStar;
	}

	public void setNeedStar(int needStar) {
		this.needStar = needStar;
	}

	public int getNeedGem() {
		return needGem;
	}

	public void setNeedGem(int needGem) {
		this.needGem = needGem;
	}

	public int getNeedCoin() {
		return needCoin;
	}

	public void setNeedCoin(int needCoin) {
		this.needCoin = needCoin;
	}

	public int getUseNeedLv() {
		return useNeedLv;
	}

	public void setUseNeedLv(int useNeedLv) {
		this.useNeedLv = useNeedLv;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

}