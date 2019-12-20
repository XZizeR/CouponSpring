package com.coupon.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.coupon.beans.Category;
import com.coupon.beans.Company;
import com.coupon.beans.Coupon;
import com.coupon.beans.Customer;
import com.coupon.exception.CouponExistsException;

@Service
@Scope("prototype")
public class CompanyFacade extends ClientFacade {

	private int companyId;

	// Login into the system as Company and return Id
	@Override
	public boolean login(String email, String password) {
		Company temp = compDB.isCompanyExist(email, password);
		if (temp != null) {
			companyId = temp.getCompanyID();
			return true;
		}
		return false;
	}

	// Add one Coupon
	public void addCoupon(Coupon coupon) throws CouponExistsException {
		List<Coupon> coupons = coupDB.getAllCoupons();
		for (Coupon coup : coupons) {
			if (coup.getTitle().equals(coupon.getTitle()))
				throw new CouponExistsException();
		}
		coupDB.addCoupon(coupon);
	}

	// Update one Coupon
	public void updateCoupon(Coupon coupon) throws CouponExistsException {
		coupDB.updateCoupon(coupon);
	}

	// Delete one Coupon
	// 1. delete the coupon from the customers
	// 2. delete the coupon from the companies
	// 3. delete coupon
	public void deleteCoupon(int couponID) {
		// disconnect it from the company
		Coupon couponToDelete = coupDB.getOneCoupon(couponID);
		Company comp = couponToDelete.getCompanyID();
		comp.removeCoupon(couponToDelete);
		compDB.updateCompany(comp);
		// disconnect it from the customer
		List<Customer> customers = custDB.getAllCustomers();
		for (Customer customer : customers) {
			if (customer.getCoupons().contains(couponToDelete)) {
				customer.getCoupons().remove(couponToDelete);
				custDB.updateCustomer(customer);
			}
		}
		// delete the coupon
		coupDB.deleteCoupon(couponID);

//		List<Customer> customers = custDB.getAllCustomers();
//		for (Customer cust : customers) {
//			List<Coupon> coupons = cust.getCoupons();
//			for (Coupon coup : coupons) {
//				if(couponID==coup.getCouponID())
//					coupons.remove(coup);
//			}
//		}
//		List<Company>companies=compDB.getAllCompanies();
//		for (Company comp : companies) {
//			List<Coupon> coupons = coupDB.getAllCoupons();
//			for (Coupon coup : coupons) {
//				if(couponID==coup.getCouponID())
//					coupons.remove(couponID);
//			}
//			compDB.updateCompany(comp);
//		}
//		
//		coupDB.deleteCoupon(couponID);
	}

	// getCompanyCoupons #1 - all coupons of the company
	public List<Coupon> getCompanyCoupons() {
		List<Coupon> coupons = new ArrayList<>();
		for (Coupon coup : coupDB.getAllCoupons()) {
			if (companyId == coup.getCompanyID().getCompanyID())
				coupons.add(coup);
		}
		return coupons;
	}

	// getCompanyCoupons #2 - by category
	public List<Coupon> getCompanyCoupons(Category category) {
		List<Coupon> coupons = new ArrayList<>();
		for (Coupon coup : coupDB.getAllCoupons()) {
			if (companyId == coup.getCompanyID().getCompanyID())
				if (coup.getCategory().equals(category))
					coupons.add(coup);
		}
		return coupons;
	}

	// getCompanyCoupons #3 - by max price
	public List<Coupon> getCompanyCoupons(double maxPrice) {
		List<Coupon> coupons = new ArrayList<>();
		for (Coupon coup : coupDB.getAllCoupons()) {
			if (companyId == coup.getCompanyID().getCompanyID())
				if (coup.getPrice() <= maxPrice)
					coupons.add(coup);
		}
		return coupons;
	}

	// getCompanyDetails
	public Company getCompanyDetails() {
		return compDB.getOneCompany(companyId);
	}
}