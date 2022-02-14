package com.assignment.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entities.WareHouse;
import com.assignment.exceptions.ResourceNotFoundException;
import com.assignment.repos.WareHouseRepo;
import com.assignment.services.WareHouseService;

@Service
@Transactional
public class WareHouseServiceImpl implements WareHouseService {

	@Autowired
	private WareHouseRepo wareHouseRepo;
	
	@Override
	public Iterable<WareHouse> getAllWareHouses() {
		return wareHouseRepo.findAll();
	}

	@Override
	public WareHouse getWareHouse(Long id){
		return wareHouseRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
	}

	@Override
	public WareHouse addWareHouse(WareHouse wareHouse) {
		return wareHouseRepo.save(wareHouse);
	}

	@Override
	public WareHouse updateWareHouse(WareHouse wareHouse) {
		return wareHouseRepo.save(wareHouse);
	}

	@Override
	public void deleteWareHouse(Long id) {
		wareHouseRepo.deleteById(id);
		
	}

	@Override
	public WareHouse getWareHouseByCity(String city) {
		return wareHouseRepo.findByCity(city);
	}
}
