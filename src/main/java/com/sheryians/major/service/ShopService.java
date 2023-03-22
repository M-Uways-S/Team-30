package com.sheryians.major.service;
import java.util.List;

import com.sheryians.major.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sheryians.major.repository.ShopRepository;
@Service
public class ShopService {
    @Autowired
    private ShopRepository repository;

    /*
     * TODO: Get the List of Shops
     */
    public List<Product> getAllShops(){
        List<Product> list =  (List<Product>)repository.findAll();
        return list;
    }

    /*
     * TODO: Get Shop By keyword
     */
    public List<Product> getByKeyword(String keyword){
        return repository.findByKeyword(keyword);
    }
}