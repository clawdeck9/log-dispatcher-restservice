package com.cluster9.logDispatcherRestService.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cluster9.logDispatcherRestService.entities.Tag;

@Repository
public interface TagRepo extends PagingAndSortingRepository<Tag, Long> {
	
	@Query("select p from Tag p where p.name like :name")
	public Page<Tag> tagsByName(@Param("name") String name, Pageable pageable);
	
	@Query("select distinct p from Tag p where p.name like :name")
	public Tag tagByName(@Param("name") String name);

}
