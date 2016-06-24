package com.ourpalm.editor.entity.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "渠道")
@Table(name = "ChannelData")
public class ChannelData implements TableEntity {
	@Id
	@Comment(search = "search_EQ_code", desc = "渠道id")
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Comment(search = "search_LIKE_name", desc = "渠道名")
	@Column(name = "name", nullable = false)
	private String name;

	@Comment(search = "search_EQ_sign", desc = "渠道码")
	@Column(name = "sign", nullable = false)
	private String sign;

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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelData [code=");
		builder.append(code);
		builder.append(", isUpdate=");
		builder.append(isUpdate);
		builder.append(", name=");
		builder.append(name);
		builder.append(", sign=");
		builder.append(sign);
		builder.append("]");
		return builder.toString();
	}

}
