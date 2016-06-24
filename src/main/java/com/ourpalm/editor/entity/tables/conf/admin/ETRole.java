package com.ourpalm.editor.entity.tables.conf.admin;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "_tt_etrole")
@Deprecated
public class ETRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String roleCode;

	private String despripe;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "_tt_user_role", joinColumns = { @JoinColumn(name = "roleid") }, inverseJoinColumns = { @JoinColumn(name = "userid") })
	private List<ETUser> users = new ArrayList<ETUser>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getDespripe() {
		return despripe;
	}

	public void setDespripe(String despripe) {
		this.despripe = despripe;
	}

	public List<ETUser> getUsers() {
		return users;
	}

	public void setUsers(List<ETUser> users) {
		this.users = users;
	}

}