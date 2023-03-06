package com.example.fotoalbum.model;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "photos")
public class Photo {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@NotNull
	@NotEmpty(message = "Il titolo non puo essere vuoto")
	@Column(nullable = false)
	private String title; 
	
	@NotNull
	@NotEmpty(message = "La descrizione non puo essere vuoto")
	@Column(nullable = false)
	private String description; 
	
	@NotNull
	@NotEmpty(message = "L'url non puo essere vuoto")
	@Column(nullable = false)
	private String url; 
	
	@NotNull
	@NotEmpty(message = "Il tag non puo essere vuoto")
   // @Pattern(regexp= "^[#]" + "[a-zA-Z0-9]{1,100}", message = "Il tag deve cominciare con #")  
	@Column(nullable = false)
	private String tag; 
	
	@NotNull(message = "la visibilità non puo essere nulla")
	@Column(nullable = false)
	private Boolean visible;
	
	@OneToMany(mappedBy = "photo", cascade = CascadeType.REMOVE )
	private List<Comment> comments;
	
	@ManyToMany
	private List<Category> categories;
	


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public List<Category> getCategories() {
		return categories;
	}


	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}


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
