package com.assignment.services;

import com.assignment.entities.WareHouse;

public interface WareHouseService {

	Iterable<WareHouse> getAllWareHouses();

	WareHouse getWareHouse(Long id);

	WareHouse addWareHouse(WareHouse wareHouse);

	WareHouse updateWareHouse(WareHouse wareHouse);

	void deleteWareHouse(Long id);

	WareHouse getWareHouseByCity(String city);

}
