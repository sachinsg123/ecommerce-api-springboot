package com.ecommerceapi.model;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.EmbeddableInstantiator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String title;
	
	private String description;
	
	private int price;
	
	private int discountPrice;
	
	private int discountpercent;
	
	private int quantity;
	
	private String brand;
	
	private String color;
	
	@Column(name="num_ratings")
	private int numRatings;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Embedded
	@ElementCollection
	@Column(name="sizes")
	private Set<Size> size = new HashSet<Size>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Rating> ratings = new ArrayList<Rating>();
	
	@OneToMany
	private List<Review> reviews = new ArrayList<Review>();
	
	@ManyToOne
	private Category category;
	
	private LocalDateTime createdAt;
	
	

	public Product() {
		
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String title, String description, int price, int discountPrice, int discountpercent,
			int quantity, String brand, String color, int numRatings, String imageUrl, Set<Size> size,
			List<Rating> ratings, List<Review> reviews, Category category, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.discountPrice = discountPrice;
		this.discountpercent = discountpercent;
		this.quantity = quantity;
		this.brand = brand;
		this.color = color;
		this.numRatings = numRatings;
		this.imageUrl = imageUrl;
		this.size = size;
		this.ratings = ratings;
		this.reviews = reviews;
		this.category = category;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getDiscountpercent() {
		return discountpercent;
	}

	public void setDiscountpercent(int discountpercent) {
		this.discountpercent = discountpercent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(int numRatings) {
		this.numRatings = numRatings;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<Size> getSize() {
		return size;
	}

	public void setSize(Set<Size> size) {
		this.size = size;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
