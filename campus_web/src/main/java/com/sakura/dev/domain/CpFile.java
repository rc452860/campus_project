package com.sakura.dev.domain;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by rc452 on 2017/6/22.
 */
@Data
@Entity
public class CpFile {
	@Id
	@GeneratedValue
	private Long cpId;
	private String cpOriginalName;
	private String cpConvertName;
	private Date cpEntryTime;

	public CpFile(){}

	public CpFile(String cpOriginalName, String cpConvertName) {
		this.cpOriginalName = cpOriginalName;
		this.cpConvertName = cpConvertName;
		this.cpEntryTime = new Date();
	}

	public CpFile(String cpOriginalName, String cpConvertName, Date cpEntryTime) {
		this.cpOriginalName = cpOriginalName;
		this.cpConvertName = cpConvertName;
		this.cpEntryTime = cpEntryTime;
	}
}
