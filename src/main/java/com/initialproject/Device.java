/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Aaron Magi
 */
// tag::code[]
@Data
@Entity
@Table(name = "device")
public class Device {

	private @Id String serial;
	private String manufacturer;
	private String model;
	private String brand;
	private String product;
	private String imei;
	private String phoneNumber;
	private String softwareVersion;
	private String operatorName;
	private String simCountryCode;
	private String simOperator;
	private String simSerialNo;
	private String subscriberId;
	private String networkType;
	private String phoneType;
	private String cellSignalStrength;
	private String cellLocation;
	private String wifiStrength;

	private Device() {}

	public Device(String manufacturer,
			String model,
			String serial,
			String brand,
			String product,
			String imei,
			String phoneNumber,
			String softwareVersion,
			String operatorName,
			String simCountryCode,
			String simOperator,
			String simSerialNo,
			String subscriberId,
			String networkType,
			String phoneType,
			String cellSignalStrength,
			String cellLocation,
			String wifiStrength) {
		this.manufacturer = manufacturer; 
		this.model = model;  
		this.serial = serial;
		this.brand = brand;
		this.product = product;
		this.imei = imei;
		this.phoneNumber = phoneNumber;
		this.softwareVersion = softwareVersion;
		this.operatorName = operatorName;
		this.simCountryCode = simCountryCode;
		this.simOperator = simOperator;
		this.simSerialNo = simSerialNo;
		this.subscriberId = subscriberId;
		this.networkType = networkType;
		this.phoneType = phoneType;
		this.cellSignalStrength = cellSignalStrength;
		this.cellLocation = cellLocation;
		this.wifiStrength = wifiStrength;
	}
}
// end::code[]