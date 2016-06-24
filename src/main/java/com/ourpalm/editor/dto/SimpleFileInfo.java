/**
 * 
 */
package com.ourpalm.editor.dto;

import java.util.List;

/**
 * 每个文件的简要信息
 * 
 * @author zhangbo
 * 
 */
public class SimpleFileInfo {
	/**
	 * id，这个{@code String}是由{@code BigInteger}转来的
	 */
	private String id;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 文件修改时间
	 */
	private String date;
	/**
	 * 大小
	 */
	private String size;
	/**
	 * 文件夹状态， closed
	 */
	private String state;
	/**
	 * 子目录
	 */
	private List<SimpleFileInfo> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<SimpleFileInfo> getChildren() {
		return children;
	}

	public void setChildren(List<SimpleFileInfo> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SimpleFileInfo [children=");
		builder.append(children);
		builder.append(", date=");
		builder.append(date);
		builder.append(", id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", size=");
		builder.append(size);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}

}
