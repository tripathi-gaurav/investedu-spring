package com.investedu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.investedu.model.Investment;

public interface InvestmentRepo extends CrudRepository<Investment, Integer>{

}
