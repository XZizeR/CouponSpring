package com.coupon.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "Coupons", uniqueConstraints = { @UniqueConstraint(columnNames = "title", name = "uniqueConstraint") })
public class Coupon {
	
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Coupon_ID")
	private int couponID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_ID")
	private Company companyID;

	@NotNull
	@Min(0)
	@Column(name = "Amount")
	private int amount;

	@Column(name = "Category_ID")
	private Category category;

	@NotBlank(message = "Name may not be blank")
	@Column(name = "Title")
	private String title; // unique

	@NotBlank(message = "Name may not be blank")
	@Column(name = "Description")
	private String description;

	@NotBlank(message = "Name may not be blank")
	@Column(name = "Image")
	private String image;

	@Column(name = "Start_Date")
	private Date startDate;

	@Column(name = "End_Date")
	private Date endDate;

	@DecimalMin(value = "0.0", inclusive = true)
	@Column(name = "Price")
	private double price;

	// Constructor
	// for hibernate
	public Coupon() {
		super();
	}

	// to purchase
	public Coupon(int couponID) {
		super();
		this.couponID = couponID;
	}

	// to update
	public Coupon(int couponID, Company companyID, int amount, Category category, String title, String description,
			String image, Date startDate, Date endDate, double price) {
		super();
		this.couponID = couponID;
		this.companyID = companyID;
		this.amount = amount;
		this.category = category;
		this.title = title;
		this.description = description;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
	}

	// to create
	public Coupon(Company companyID, int amount, Category category, String title, String description, String image,
			Date startDate, Date endDate, double price) {
		super();
		this.companyID = companyID;
		this.amount = amount;
		this.category = category;
		this.title = title;
		this.description = description;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
	}

	// Getters/Setters
	public int getCouponID() {
		return couponID;
	}

	public Company getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Company companyID) {
		this.companyID = companyID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		Coupon coup = (Coupon) obj;
		return this.getCouponID() == coup.getCouponID();
	}

	@Override
	public String toString() {
		return "Coupon [couponID=" + couponID + ", companyID=" + companyID.getCompanyID() + ", amount=" + amount + ", category="
				+ category + ", title=" + title + ", description=" + description + ", image=" + image + ", startDate="
				+ startDate + ", endDate=" + endDate + ", price=" + price + "]";
	}
	
}