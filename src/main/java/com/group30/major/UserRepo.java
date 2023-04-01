package com.group30.major;

import org.springframework.data.repository.CrudRepository;

import com.group30.major.model.Userdata;

public interface UserRepo extends CrudRepository<Userdata, Integer> {
}