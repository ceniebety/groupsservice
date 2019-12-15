package com.arisan.online.groupservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FollowGroupDto {

	@JsonProperty("namaGroup")
	private String namaGroup;

	public String getNamaGroup() {
		return namaGroup;
	}

	public void setNamaGroup(String namaGroup) {
		this.namaGroup = namaGroup;
	}
}
