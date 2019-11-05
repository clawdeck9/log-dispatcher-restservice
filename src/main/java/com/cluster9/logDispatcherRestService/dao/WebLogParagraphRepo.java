package com.cluster9.logDispatcherRestService.dao;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;


public interface WebLogParagraphRepo extends JpaRepository<WebLogParagraph, Long>{
	
	public Page<WebLogParagraph> findByTag(@Param("tag") String tag,  Pageable pageable	);
	
	public List<WebLogParagraph> findByTag(@Param("tag") String tag);

	public Page<WebLogParagraph> findById(@NotNull @Param("id") Long id,  Pageable pageable);
	
	public Optional<WebLogParagraph> findById(@Param("id") Long id);
	
	// check if the return type can be of wlp directly
//	public WebLogParagraph findById(@Param("id") Long id);
	
	@Override
	public void deleteById(Long id);
	
	@Override
	public boolean existsById(Long id);
	
//	@Query("select p from WebLogParagraph p where p.fileName like :kw")
//	public Page<WebLogParagraph> logParagraphByKeyword(@Param("kw") String kw, Pageable pageable);
	
}
