package com.arisan.online.groupservice.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "groups")
public class Group {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	
	@Column(name = "nama_group", nullable = false, length=150)
	@JsonProperty("namaGroup")
	private String namaGroup;
	
	@JsonProperty("maxMember")
	@Column(name = "max_member", nullable = false)
	private int maxMember;
	
	@Column(name = "tanggal_arisan", nullable = false)
	@JsonProperty("tanggalArisan")
	private int tanggalArisan;
	
	@Column(name = "max_bayar_arisan", nullable = false)
	@JsonProperty("maxBayarArisan")
	private int maxBayarArisan;
	
	@Column(name = "jumlah_arisan", nullable = false)
	@JsonProperty("jumlahArisan")
	private BigDecimal jumlahArisan;
	
	@Column(name = "created_date", nullable = false, length=150)
	private Date createdDate;
	
	@Column(name = "created_by", nullable = false, length=150)
	private String createdBy;
	
	@OneToMany(mappedBy="group")
	private List<User> users;
	
	public String getNamaGroup() {
		return namaGroup;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMaxMember() {
		return maxMember;
	}

	public void setMaxMember(int maxMember) {
		this.maxMember = maxMember;
	}

	public int getTanggalArisan() {
		return tanggalArisan;
	}

	public void setTanggalArisan(int tanggalArisan) {
		this.tanggalArisan = tanggalArisan;
	}

	public int getMaxBayarArisan() {
		return maxBayarArisan;
	}

	public void setMaxBayarArisan(int maxBayarArisan) {
		this.maxBayarArisan = maxBayarArisan;
	}

	public BigDecimal getJumlahArisan() {
		return jumlahArisan;
	}

	public void setJumlahArisan(BigDecimal jumlahArisan) {
		this.jumlahArisan = jumlahArisan;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
