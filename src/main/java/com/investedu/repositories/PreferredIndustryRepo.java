package com.investedu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.investedu.model.PreferredIndustry;
import com.investedu.model.Users;

public interface PreferredIndustryRepo extends CrudRepository<PreferredIndustry, Integer>  {
	PreferredIndustry findByUsernameAndSector(String username, String sector);
}
