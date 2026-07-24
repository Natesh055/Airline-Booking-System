package com.natesh.repository;

import com.natesh.model.City;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByCityCode(String cityCode);
    boolean existsByCityCodeAndId(String cityCode, Long Id);
    Page<City> findByCountryCodeIgnoreCase(String cityCode, Pageable pageable);
    @Query("""
       SELECT c FROM City c
       WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
          OR LOWER(c.cityCode) LIKE LOWER(CONCAT('%', :keyword, '%'))
          OR LOWER(c.countryCode) LIKE LOWER(CONCAT('%', :keyword, '%'))
          OR LOWER(c.countryName) LIKE LOWER(CONCAT('%', :keyword, '%'))
          OR LOWER(c.regionCode) LIKE LOWER(CONCAT('%', :keyword, '%'))
       """)
    Page<City> searchByKeyword(String keyword, Pageable pageable);

}
