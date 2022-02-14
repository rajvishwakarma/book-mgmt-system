package com.assignment.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.entities.WareHouse;

@Repository
public interface WareHouseRepo extends CrudRepository<WareHouse, Long> {

	@Query("select w from WareHouse w where w.city =:city")
	WareHouse findByCity(@Param("city") String city);

}
