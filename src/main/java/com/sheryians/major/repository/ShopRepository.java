package com.sheryians.major.repository;
import java.util.List;

import com.sheryians.major.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Product, Integer>{
    //Custom query
    @Query(value = "select * from product p where p.name like %:keyword%", nativeQuery = true)
    List<Product> findByKeyword(@Param("keyword") String keyword);
}