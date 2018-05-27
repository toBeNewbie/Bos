package com.newbie.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String telephone;
	private String haspda;
	private String deltag;
	private String station;
	private String standard;
	private Set decidedZones = new HashSet(0);

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** minimal constructor */
	public Staff(String id) {
		this.id = id;
	}

	/** full constructor */
	public Staff(String id, String name, String telephone, String haspda,
			String deltag, String station, String standard, Set decidedZones) {
		this.id = id;
		this.name = name;
		this.telephone = telephone;
		this.haspda = haspda;
		this.deltag = deltag;
		this.station = station;
		this.standard = standard;
		this.decidedZones = decidedZones;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHaspda() {
		return this.haspda;
	}

	public void setHaspda(String haspda) {
		this.haspda = haspda;
	}

	public String getDeltag() {
		return this.deltag;
	}

	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Set getDecidedZones() {
		return this.decidedZones;
	}

	public void setDecidedZones(Set decidedZones) {
		this.decidedZones = decidedZones;
	}

}