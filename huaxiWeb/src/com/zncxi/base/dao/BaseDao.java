package com.zncxi.base.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseDao<T, PK extends Serializable> extends PagingAndSortingRepository<T, Serializable>, JpaSpecificationExecutor<T>{

}
