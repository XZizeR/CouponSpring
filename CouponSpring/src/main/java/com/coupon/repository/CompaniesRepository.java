package com.coupon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coupon.beans.Company;

public interface CompaniesRepository extends JpaRepository<Company, Integer>{ // <what, primary key>
	
	List<Company>findCompaniesByCompanyID(int companyId);
	
	Company findCompanyByEmailAndPassword(String email, String password);
}