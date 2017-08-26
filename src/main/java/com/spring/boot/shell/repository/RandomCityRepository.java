package com.spring.boot.shell.repository;

import com.spring.boot.shell.domain.RandomCity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nydiarra on 10/05/17.
 */
public interface RandomCityRepository extends CrudRepository<RandomCity, Long> {
}
