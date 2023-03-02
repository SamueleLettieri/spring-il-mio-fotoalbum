package com.example.fotoalbum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "photos")
public class Photo {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	
	private String title; 
	
	
	private String description; 
	
	
	private String url; 
	
	
	private String tag; 
	
	
	private Boolean visible;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public Boolean getVisible() {
		return visible;
	}


	public void setVisible(Boolean visible) {
		this.visible = visible;
	} 
	
	
	
}
