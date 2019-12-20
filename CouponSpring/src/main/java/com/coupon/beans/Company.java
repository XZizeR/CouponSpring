package com.coupon.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// for spring
@Component
@Scope("prototype")
// for hibernate
@Entity // this class is a DB table.
@Table(name="Companies",uniqueConstraints = {@UniqueConstraint(columnNames = "email", name ="uniqueConstraint")}) 
// Table - optional name for the DB table.
// unique - there could not be another one like this.
// nullable - can create an empty field t/f.
public class Company {
	
	// Fields
	@Id // primary key!
	@GeneratedValue(strategy=GenerationType.IDENTITY) // this will be an identity column
	@Column(name="Company_ID")
	private int companyID;
	
	@NotBlank(message="Name may not be blank")// the input is n
	@Column(name="Name")
	private String name;
	
	@NotBlank(message="Name may not be blank")
	@Column(name="Email")
	private String email;
	
	@NotBlank(message="Name may not be blank")
	@Column(name="Password")
	private String password;
	
	@OneToMany(mappedBy="companyID",fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Coupon> coupons;
	// fetch=FetchType.EAGER - so it create the data on the spot
	// CascadeType.REMOVE - propagates the remove operation from parent to child entity

	// Constructors
	// for hibernate
	public Company() {
		super();
	}
	// to create
	public Company(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	// to update
	public Company(int companyID, String name, String email, String password) {
		super();
		this.companyID = companyID;
		this.name = name;
		this.email = email;
		this.password = password;
	}	
	// to show all
	public Company(String name, String email, String password, ArrayList<Coupon> coupons) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}

	// Getters/Setters
	public int getCompanyID() {
		return companyID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Coupon> getCoupons() {
		return coupons;
	}
	
	// establishes the foreign key
	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}
	// disables the foreign key
	public void removeCoupon(Coupon coupon) {
		this.coupons.remove(coupon);
	}
	
	// toString
	@Override
	public String toString() {
		return "Company [companyID=" + companyID + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons="
				+ coupons + "]";
	}
	
}