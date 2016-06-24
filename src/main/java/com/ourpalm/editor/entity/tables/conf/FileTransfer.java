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
@Comment(desc = "deploy:目标文件夹")
@Table(name = "_tt_filetransfer")
public class FileTransfer implements TableEntity {
	@Id
	@Comment(search = "search_EQ_code", desc = "Id")
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	@Comment(search = "search_LIKE_tableName", desc = "表名称")
	@Column(name = "tableName", nullable = false)
	private String tableName;

	@Comment(search = "search_LIKE_toPath", desc = "至目标文件")
	@Column(name = "toPath", nullable = false)
	private String toPath;

	public FileTransfer() {
		super();
	}

	@Override
	public Long getCode() {
		return code;
	}

	@Override
	public void setCode(Long code) {
		this.code = code;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getToPath() {
		return toPath;
	}

	public void setToPath(String toPath) {
		this.toPath = toPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		result = prime * result + ((toPath == null) ? 0 : toPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileTransfer other = (FileTransfer) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		if (toPath == null) {
			if (other.toPath != null)
				return false;
		} else if (!toPath.equals(other.toPath))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileTransfer [code=");
		builder.append(code);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append(", toPath=");
		builder.append(toPath);
		builder.append("]");
		return builder.toString();
	}

}
