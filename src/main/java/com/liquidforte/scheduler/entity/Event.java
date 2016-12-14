package com.liquidforte.scheduler.entity;

import java.sql.Date;
import java.util.Set;

public class Event {
	private String name;
	private String desc;
	private Date date;
	private ReoccurType reoccurType;
	private Set<String> users;
	private Set<String> groups;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ReoccurType getReoccurType() {
		return reoccurType;
	}

	public void setReoccurType(ReoccurType reoccurType) {
		this.reoccurType = reoccurType;
	}

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}

	public Set<String> getGroups() {
		return groups;
	}

	public void setGroups(Set<String> groups) {
		this.groups = groups;
	}
}
