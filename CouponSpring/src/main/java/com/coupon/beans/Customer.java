package com.coupon.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name="Customers",uniqueConstraints = {@UniqueConstraint(columnNames = "email", name ="uniqueConstraint")})
public class Customer {
	
	// Fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Customer_ID", nullable=false)
	private int customerID;
	
	@NotBlank(message = "Name may not be blank")
	@Column(name="First_Name")
	private String firstName;
	
	@NotBlank(message = "Name may not be blank")
	@Column(name="Last_Name")
	private String lastName;
	
	@NotBlank(message = "Name may not be blank")
	@Column(name="Email") // unique
	private String email;
	
	@NotBlank(message = "Name may not be blank")
	@Column(name="Password")
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Coupon>coupons;
	
	// Constructors
	public Customer() { // for hibernate
		super();
	}
	// to create
	public Customer(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	// to update
	public Customer(int customerID, String firstName, String lastName, String email, String password) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	// to show all
	public Customer(int customerID, String firstName, String lastName, String email, String password,
			ArrayList<Coupon> coupons) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}

	// Getters/Setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	public int getCustomerID() {
		return customerID;
	}

	// toString
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", coupons=" + coupons + "]";
	}
	
}