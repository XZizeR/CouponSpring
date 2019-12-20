package com.coupon.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coupon.beans.Category;
import com.coupon.beans.Coupon;
import com.coupon.beans.Customer;
import com.coupon.exception.CouponDateException;
import com.coupon.exception.CouponPurchasedException;
import com.coupon.exception.CouponStockException;

@Service
public class CustomerFacade extends ClientFacade {
	
	// Field
	private int customerID;
	
	// Login into the system as Customer and return Id
	@Override
	public boolean login(String email, String password) {
		Customer temp = custDB.isCustomerExist(email, password);
		if (temp != null) {
			customerID=temp.getCustomerID();
			return true;
		}
		return false;
	}

	// Purchase a Coupon // help
	public void purchaseCoupon(int couponID) throws CouponPurchasedException, CouponDateException, CouponStockException {
		// load the logged-in customer
		Customer customer = custDB.getOneCustomer(customerID);
		// load the coupon
		Coupon coupon = coupDB.getOneCoupon(couponID);
		// load a current date
		Date today = new Date();
		// load all the coupons of the logged-in customer
		List<Coupon> customerCoupons = customer.getCoupons();
		
		// checks
		for (Coupon coup : customerCoupons) {
			if (coup.getCouponID() == couponID) {
				throw new CouponPurchasedException();
			}
		}
		if (coupon.getAmount() == 0) {
			throw new CouponStockException();
		}
		if (coupon.getEndDate().before(today)) {
			throw new CouponDateException();
		}
		
		// add the new coupon to the customer list.
		customer.getCoupons().add(coupon);
		// set the list to the customer.
		custDB.updateCustomer(customer);

		// reduce the amount by 1.
		coupon.setAmount(coupon.getAmount() - 1);
		// update the coupon data.
		coupDB.updateCoupon(coupon);
	}

	// Get Customer Coupons #1 - gives all customer coupons.
	public List<Coupon> getCustomerCoupons() {
		List<Coupon> coupons = custDB.getOneCustomer(customerID).getCoupons();
		return coupons;
	}

	// Get Customer Coupons #2 - give all customer coupons by category // help
	public List<Coupon> getCustomerCoupons(Category category) {
		List<Coupon>coupons=new ArrayList<Coupon>();
		for (Coupon coup : custDB.getOneCustomer(customerID).getCoupons()) {
			if(coup.getCategory().equals(category))
				coupons.add(coup);
		}
		return coupons;
	}

	// Get Customer Coupons - give all customer coupons by max price #3
	public List<Coupon> getCustomerCoupons(double maxPrice) {
		List<Coupon>coupons=new ArrayList<Coupon>();
		for (Coupon coup : custDB.getOneCustomer(customerID).getCoupons()) {
			if(coup.getPrice()<=maxPrice)
				coupons.add(coup);
		}
		return coupons;
	}

	// getCustomerDetails
	public Customer getCustomerDetails() {
		return custDB.getOneCustomer(customerID);
	}
	
}