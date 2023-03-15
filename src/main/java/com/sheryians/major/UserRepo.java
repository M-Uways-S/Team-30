package com.sheryians.major;

import com.sheryians.major.model.Userdata;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<Userdata, Integer> {
}