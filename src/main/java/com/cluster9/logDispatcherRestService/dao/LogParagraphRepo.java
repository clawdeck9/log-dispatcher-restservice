package com.cluster9.logDispatcherRestService.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.cluster9.logDispatcherRestService.entities.LogParagraph;


public interface LogParagraphRepo extends JpaRepository<LogParagraph, Long>{
	
	@Query("select p from LogParagraph p where p.fileName like :kw")
	public Page<LogParagraph> logParagraphByKeyword(@Param("kw") String kw, Pageable pageable);
	
}
