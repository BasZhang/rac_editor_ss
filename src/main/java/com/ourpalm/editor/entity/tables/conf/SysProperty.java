package com.ourpalm.editor.entity.tables.conf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ourpalm.editor.TableEntity;
import com.ourpalm.editor.annotation.Comment;

@Entity
@Comment(desc = "tables:系统配置")
@Table(name = "_tt_sys")
public class SysProperty implements TableEntity {
	@Id
	@Comment(search = "search_EQ_code",desc = "id")
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Comment(search = "search_LIKE_propKey",desc = "变量名")
	@Column(name = "propKey", nullable = false)
	private String propKey;
	
	@Comment(search = "search_LIKE_propValue",desc = "变量值")
	@Column(name = "propValue", nullable = false)
	private String propValue = "";

	public SysProperty() {
		super();
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getPropKey() {
		return propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SysProperty [code=");
		builder.append(code);
		builder.append(", propKey=");
		builder.append(propKey);
		builder.append(", propValue=");
		builder.append(propValue);
		builder.append("]");
		return builder.toString();
	}

}
