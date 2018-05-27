package com.newbie.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * DecidedZone entity. @author MyEclipse Persistence Tools
 */

public class DecidedZone implements java.io.Serializable {

	// Fields

	private String id;
	private Staff bcStaff;
	private String name;
	private Set bcSubareas = new HashSet(0);

	// Constructors

	/** default constructor */
	public DecidedZone() {
	}

	/** minimal constructor */
	public DecidedZone(String id) {
		this.id = id;
	}

	/** full constructor */
	public DecidedZone(String id, Staff bcStaff, String name, Set bcSubareas) {
		this.id = id;
		this.bcStaff = bcStaff;
		this.name = name;
		this.bcSubareas = bcSubareas;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Staff getBcStaff() {
		return this.bcStaff;
	}

	public void setBcStaff(Staff bcStaff) {
		this.bcStaff = bcStaff;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getBcSubareas() {
		return this.bcSubareas;
	}

	public void setBcSubareas(Set bcSubareas) {
		this.bcSubareas = bcSubareas;
	}

}