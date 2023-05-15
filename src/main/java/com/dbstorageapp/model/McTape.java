package com.dbstorageapp.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class McTape {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int access_cnt;
	private long bytes;
	private String campaign;
	private int length;
	private Date accessed_at;	
	private Date closed_at;
	private Date created_at;
	private Date deleted_at;
	private Date eol_at;
	private Date expired_at;	
	private String name;
	private int run_number;
	private Date updated_at;

	public McTape() {		
	}

	public McTape(int access_cnt, long bytes, String campaign, int length, Date accessed_at, Date closed_at,
			Date created_at, Date deleted_at, Date eol_at, Date expired_at, String name, int run_number,
			Date updated_at) {
		super();
		this.access_cnt = access_cnt;
		this.bytes = bytes;
		this.campaign = campaign;
		this.length = length;
		this.accessed_at = accessed_at;
		this.closed_at = closed_at;
		this.created_at = created_at;
		this.deleted_at = deleted_at;
		this.eol_at = eol_at;
		this.expired_at = expired_at;
		this.name = name;
		this.run_number = run_number;
		this.updated_at = updated_at;
	}

	public int getAccess_cnt() {
		return access_cnt;
	}

	public void setAccess_cnt(int access_cnt) {
		this.access_cnt = access_cnt;
	}

	public long getBytes() {
		return bytes;
	}

	public void setBytes(long bytes) {
		this.bytes = bytes;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Date getAccessed_at() {
		return accessed_at;
	}

	public void setAccessed_at(Date accessed_at) {
		this.accessed_at = accessed_at;
	}

	public Date getClosed_at() {
		return closed_at;
	}

	public void setClosed_at(Date closed_at) {
		this.closed_at = closed_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	public Date getEol_at() {
		return eol_at;
	}

	public void setEol_at(Date eol_at) {
		this.eol_at = eol_at;
	}

	public Date getExpired_at() {
		return expired_at;
	}

	public void setExpired_at(Date expired_at) {
		this.expired_at = expired_at;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRun_number() {
		return run_number;
	}

	public void setRun_number(int run_number) {
		this.run_number = run_number;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}	
}
