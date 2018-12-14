package br.com.leoguilbor.dto;

import java.io.Serializable;

import br.com.leoguilbor.domain.Job;

public class JobDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String description;
	private String date;
	private String servicem;
	private String client;

	public JobDTO() {	
	}
	
	public JobDTO(Job job) {
		this.id = job.getId();
		this.name = job.getName();
		this.description = job.getDescription();
		this.date = job.getDate();
		this.servicem = job.getService().getName();
		this.client = job.getClient().getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getServicem() {
		return servicem;
	}

	public void setServicem(String servicem) {
		this.servicem = servicem;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
	
	
	
}
