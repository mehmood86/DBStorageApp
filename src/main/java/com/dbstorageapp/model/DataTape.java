package com.dbstorageapp.model;

import java.sql.Timestamp;

import com.dbstorageapp.helperMethods.HelperClass;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class DataTape {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int access_cnt;
	private Timestamp accessed_at;
	private long bytes;
	private String campaign;
	private Timestamp closed_at;
	private Timestamp created_at;
	private Timestamp deleted_at;
	private Timestamp eol_at;
	private Timestamp expired_at;
	private int length;
	private String name;
	private int run_number;
	private Timestamp updated_at;

	public DataTape() {
	};

	public DataTape(int access_cnt, Timestamp accessed_at, long bytes, String campaign, Timestamp closed_at,
			Timestamp created_at, Timestamp deleted_at, Timestamp eol_at, Timestamp expired_at, int length, String name,
			int run_number, Timestamp updated_at) {
		this.access_cnt = access_cnt;
		this.accessed_at = accessed_at;
		this.bytes = bytes;
		this.campaign = campaign;
		this.closed_at = closed_at;
		this.created_at = created_at;
		this.deleted_at = deleted_at;
		this.eol_at = eol_at;
		this.expired_at = expired_at;
		this.length = length;
		this.name = name;
		this.run_number = run_number;
		this.updated_at = updated_at;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAccess_cnt() {
		return access_cnt;
	}

	public void setAccess_cnt(int access_cnt) {
		this.access_cnt = access_cnt;
	}

	public Timestamp getAccessed_at() {
		return accessed_at;
	}

	public void setAccessed_at(Timestamp accessed_at) {
		this.accessed_at = accessed_at;
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

	public Timestamp getClosed_at() {
		return closed_at;
	}

	public void setClosed_at(Timestamp closed_at) {
		this.closed_at = closed_at;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Timestamp deleted_at) {
		this.deleted_at = deleted_at;
	}

	public Timestamp getEol_at() {
		return eol_at;
	}

	public void setEol_at(Timestamp eol_at) {
		this.eol_at = eol_at;
	}

	public Timestamp getExpired_at() {
		return expired_at;
	}

	public void setExpired_at(Timestamp expired_at) {
		this.expired_at = expired_at;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
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

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public boolean updateOnlyOnChanges(String[] allFields) {

		boolean isChanged = false;

		if (this.getAccess_cnt() != HelperClass.toInteger(allFields[0])) {
			this.setAccess_cnt(HelperClass.toInteger(allFields[0]));
			isChanged = true;
		}

		if (this.getAccessed_at() != null && !this.getAccessed_at().equals(HelperClass.toTimestamp(allFields[1]))) {
			this.setAccessed_at(HelperClass.toTimestamp(allFields[1]));
			isChanged = true;
		}

		if (this.getBytes() != HelperClass.toLong(allFields[2])) {
			this.setBytes(HelperClass.toLong(allFields[2]));
			isChanged = true;
		}

		if (!this.getCampaign().equals(allFields[3])) {
			this.setCampaign(allFields[3]);
			isChanged = true;
		}

		if (this.getClosed_at() != null && !this.getClosed_at().equals(HelperClass.toTimestamp(allFields[4]))) {
			this.setClosed_at(HelperClass.toTimestamp(allFields[4]));
			isChanged = true;
		}

		if (this.getCreated_at() != null && !this.getCreated_at().equals(HelperClass.toTimestamp(allFields[5]))) {
			this.setCreated_at(HelperClass.toTimestamp(allFields[5]));
			isChanged = true;
		}

		if (this.getDeleted_at() != null && !this.getDeleted_at().equals(HelperClass.toTimestamp(allFields[6]))) {
			this.setDeleted_at(HelperClass.toTimestamp(allFields[6]));
			isChanged = true;
		}

		if (this.getEol_at() != null && !this.getEol_at().equals(HelperClass.toTimestamp(allFields[7]))) {
			this.setEol_at(HelperClass.toTimestamp(allFields[7]));
			isChanged = true;
		}

		if (this.getExpired_at() != null && !this.getExpired_at().equals(HelperClass.toTimestamp(allFields[8]))) {
			this.setExpired_at(HelperClass.toTimestamp(allFields[8]));
			isChanged = true;
		}

		if (this.getLength() != HelperClass.toInteger(allFields[9])) {
			this.setLength(HelperClass.toInteger(allFields[9]));
			isChanged = true;
		}

		if (this.getRun_number() != HelperClass.toInteger(allFields[11])) {
			this.setRun_number(HelperClass.toInteger(allFields[11]));
			isChanged = true;
		}

		if (this.getUpdated_at() != null && !this.getUpdated_at().equals(HelperClass.toTimestamp(allFields[12]))) {
			this.setUpdated_at(HelperClass.toTimestamp(allFields[12]));
			isChanged = true;
		}
		return isChanged;
	}
}
