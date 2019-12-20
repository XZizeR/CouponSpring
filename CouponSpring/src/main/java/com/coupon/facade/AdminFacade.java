package com.coupon.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coupon.beans.Company;
import com.coupon.beans.Coupon;
import com.coupon.beans.Customer;
import com.coupon.exception.CompanyExistsException;
import com.coupon.exception.CustomerExistsException;

@Service
public class AdminFacade extends ClientFacade {

	// Login into the system as Admin
	@Override
	public boolean login(String email, String password) {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		}else 
			return false;
	}

	// Add a Company
	public void addCompany(Company company) throws CompanyExistsException {
		List<Company> companies = compDB.getAllCompanies();
		for (Company comp : companies) {
			if(comp.getEmail().equals(company.getEmail()))
				throw new CompanyExistsException();
		}
		compDB.addCompany(company);
		compDB.updateCompany(company);
	}

	// Update a Company
	public void updateCompany(Company company) throws CompanyExistsException {
		List<Company> companies = compDB.getAllCompanies();
		for (Company comp : companies) {
			if(comp.getEmail().equals(company.getEmail()))
				throw new CompanyExistsException();
		}
		compDB.updateCompany(company);
	}

	// Delete a Company
	//1. run on a list of customers
	//2. create a trash list
	//3. run on a list of their coupons
	//4. if the companyID equals to coupons companyID -
	//5. save the coupon id
	//6. save the coupon at the trash list
	//7. run on the trash list
	//8. remove the coupons of the trash list in the coupons list
	//9. delete the coupon by couponID
	//10. update the customer
	//11. delete the company.
	public void deleteCompany(int companyID) {
		List<Customer> customers = custDB.getAllCustomers();
		List<Coupon> trash = new ArrayList<Coupon>();
		for (Customer cust : customers) {
			List<Coupon> custCoupons = cust.getCoupons();
			for (Coupon coup : custCoupons) {
				if(coup.getCompanyID().getCompanyID() == companyID){
					trash.add(coup);
				}
			}
			for (Coupon coup : trash) {
				custCoupons.remove(coup);				
				coupDB.deleteCoupon(coup.getCouponID());
			}
			custDB.updateCustomer(cust);
		}
		List<Coupon> companyCoupons = compDB.getOneCompany(companyID).getCoupons();
		companyCoupons.clear();
		compDB.deleteCompany(companyID);
	}

	// Get all Companies
	public List<Company> getAllCompanies() {
		return compDB.getAllCompanies();
	}

	// Get one Company
	public Company getOneCompany(int companyID) {
		return compDB.getOneCompany(companyID);
	}

	// Add a Customer
	public void addCustomer(Customer customer) throws CustomerExistsException {
		List<Customer> customers = custDB.getAllCustomers();
		for (Customer cust : customers) {
			if(cust.getEmail().equals(customer.getEmail()))
				throw new CustomerExistsException();
		}
		custDB.addCustomer(customer);
		custDB.updateCustomer(customer);
	}

	// Update a Customer
	public void updateCustomer(Customer customer) throws CustomerExistsException {
		List<Customer> customers = custDB.getAllCustomers();
		for (Customer cust : customers) {
			if(cust.getEmail().equals(customer.getEmail()))
				throw new CustomerExistsException();
		}
		custDB.updateCustomer(customer);
	}

	// Delete a Customer
	//1. get the customer
	//2. clear the customers coupon list
	//3. update the customer
	//4. delete the customer
	public void deleteCustomer(int customerID) {
		Customer cust = custDB.getOneCustomer(customerID); 
		cust.getCoupons().clear();
		custDB.updateCustomer(cust);
		custDB.deleteCustomser(customerID);
	}

	// Get all Customers
	public List<Customer> getAllCustomers() {
		return custDB.getAllCustomers();
	}

	// Get one Customer
	public Customer getOneCustomer(int customerID) {
		return custDB.getOneCustomer(customerID);
	}
	
}