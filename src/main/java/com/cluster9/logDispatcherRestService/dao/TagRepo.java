package com.cluster9.logDispatcherRestService.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.cluster9.logDispatcherRestService.entities.Tag;
import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;


public interface TagRepo extends PagingAndSortingRepository<Tag, Long> {
	@Query("select p from Tag p where p.name like :tn")
	public Page<Tag> findByName(@Param("tn") String kw, Pageable pageable);

	@Query("select distinct p from Tag p where p.name like :tn")
	public Tag findByName(@Param("tn") String kw);
	
	
}

//	@Query(SELECT 'exist' WHERE EXISTS(SELECT 1 FROM Tag where 
//       WHERE CustId = @CustId))
//utiliser ceci si ne tourne pas sans page
//@Query("select p from WebLogParagraph p where p.fileName like :kw")
//public Page<WebLogParagraph> logParagraphByKeyword(@Param("kw") String kw, Pageable pageable);