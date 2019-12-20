package com.coupon;

import java.sql.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.coupon.beans.Category;
import com.coupon.beans.Company;
import com.coupon.beans.Coupon;
import com.coupon.beans.Customer;
import com.coupon.db.CompaniesDBDAO;
import com.coupon.db.CouponsDBDAO;
import com.coupon.exception.CompanyExistsException;
import com.coupon.exception.CouponDateException;
import com.coupon.exception.CouponPurchasedException;
import com.coupon.exception.CouponStockException;
import com.coupon.exception.CustomerExistsException;
import com.coupon.exception.InvalidClientTypeException;
import com.coupon.exception.LoginException;
import com.coupon.facade.AdminFacade;
import com.coupon.facade.CompanyFacade;
import com.coupon.facade.CustomerFacade;
import com.coupon.job.CouponExpirationDailyJob;
import com.coupon.login.ClientType;
import com.coupon.login.LoginManager;

@SpringBootApplication
public class CouponApplication {

	public static void main(String[] args) throws CompanyExistsException, CustomerExistsException, CouponPurchasedException, CouponDateException, CouponStockException, LoginException {
		ConfigurableApplicationContext ctx = SpringApplication.run(CouponApplication.class, args);		
		
		// Thread
		CouponExpirationDailyJob job = ctx.getBean(CouponExpirationDailyJob.class);
		Thread t = new Thread(job);
		t.start();
		
		// TESTING
		LoginManager logManager = ctx.getBean(LoginManager.class);
		try {
			// ************************* //
			// *** Administrator Log *** //
			AdminFacade admin = (AdminFacade) logManager.login("admin@admin.com", "admin", ClientType.Administrator);
			
			// Add a Company - works
//			admin.addCompany(new Company("comp13", "comp13@gmail.com", "pass"));
			// Update a Company - works
//			admin.updateCompany(new Company(1, "Intel","Gregory@intel.gov.org", "pass"));
			// Delete a Company - works
//			admin.deleteCompany(2);
			// Get all Company - works
//			List<Company>companies = admin.getAllCompanies();
//			for (Company c : companies) {
//				System.out.println(c);
//			}
			// Get one Company - works
//			System.out.println(admin.getOneCompany(1));

			// Add a Customer - works
//			admin.addCustomer(new Customer("cust6", "poor", "cust6@gmail.com", "pass"));
			// Update a Customer - works
//			admin.updateCustomer(new Customer(4, "cust4", "superrich", "cust4@yahoo.com", "pass"));
			// Delete a Customer - works
//			admin.deleteCustomer(4);
			// Get all Customer - works
//			List<Customer>customer = admin.getAllCustomers();
//			for (Customer c : customer) {
//				System.out.println(c);
//			}
			// Get one Customer - works
//			System.out.println(admin.getOneCustomer(5));
			
			// ******************* //
			// *** Company Log *** //
			CompanyFacade company = (CompanyFacade) logManager.login("comp10@gmail.com", "pass", ClientType.Company);

			// Add a Coupon - works
//			company.addCoupon(new Coupon( company.getCompanyDetails() , 1900, Category.Food, "coup26", "good stuff", "coupon.jpg", new Date(119, 11, 16), new Date(119, 10, 18), 26.00));
			// Update a Coupon - works
//			company.updateCoupon(new Coupon(18, company.getCompanyDetails(), 1800, Category.Electricity, "coup18", "good stuff", "coupon.jpg", new Date(119, 10, 18), new Date(119, 11, 18), 18.00));
			// Delete a Coupon - works
//			company.deleteCoupon(17);
			// Get Company Coupons #1 - works
//			List<Coupon>coupons = company.getCompanyCoupons();
//			for (Coupon c : coupons) {
//				System.out.println(c);
//			}
			// Get Company Coupons #2 - works
//			List<Coupon>coupons = company.getCompanyCoupons(Category.Food);
//			for (Coupon c : coupons) {
//				System.out.println(c);
//			}
			// Get Company Coupons #3 - works
//			List<Coupon>coupons = company.getCompanyCoupons(75);
//			for (Coupon c : coupons) {
//				System.out.println(c);
//			}
			// Get a Company Details - works
//			System.out.println(company.getCompanyDetails());

			// ******************** //
			// *** Customer Log *** //
			CustomerFacade customer = (CustomerFacade) logManager.login("cust1@gmail.com", "pass", ClientType.Customer);
			
			// Add a Coupon to a Customer - works
//			customer.purchaseCoupon(18);
			// Get Customer Coupons #1 - works
//			List<Coupon>coupons = customer.getCustomerCoupons();;
//			for (Coupon c : coupons) {
//				System.out.println(c);
//			}
			// Get Customer Coupons #2 - works
//			List<Coupon>coupons = customer.getCustomerCoupons(Category.Electricity);
//			for (Coupon c : coupons) {
//				System.out.println(c);
//			}
			// Get Customer Coupons #3 - works
//			List<Coupon>coupons = customer.getCustomerCoupons(1001);
//			for (Coupon c : coupons) {
//				System.out.println(c);
//			}
			// Get Customer Details - works
			System.out.println(customer.getCustomerDetails());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			job.stop();
		}
	}
}