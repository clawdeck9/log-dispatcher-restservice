package com.cluster9.logDispatcherRestService.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;


public interface WebLogParagraphRepo extends JpaRepository<WebLogParagraph, Long>{
	
	@Query("select p from WebLogParagraph p where p.fileName like :kw")
	public Page<WebLogParagraph> logParagraphByKeyword(@Param("kw") String kw, Pageable pageable);
	
	@Query("select p from WebLogParagraph p where p.tag like :tag")
	public Page<WebLogParagraph> logParagraphByTag(@Param("tag") String tag,  Pageable pageable	);
	
//	tag are String type?
//	knows the fields by name?
//	where p.tag not null
	@Query("select distinct p.tag from WebLogParagraph p order by p.tag")
	public Page<String> tags(Pageable pageable);
	
	@Query("select p from WebLogParagraph p where p.id like :id")
	public Page<WebLogParagraph> logParagraphById(@Param("id") Long id, @NotNull Pageable pageable);
	
//	@query("delete p from WebLogParagraph p where p.id like :id")
//	trying to use a crud repo method directly
	@Override
	public void deleteById(Long id);
	
	@Override
	public boolean existsById(Long id);
	
}
