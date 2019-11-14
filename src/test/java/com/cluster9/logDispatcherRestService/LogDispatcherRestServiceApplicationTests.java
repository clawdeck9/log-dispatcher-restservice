package com.cluster9.logDispatcherRestService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.cluster9.logDispatcherRestService.controllers.LogController;
import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;
import com.cluster9.logDispatcherRestService.service.ErrorBindingService;

//@RunWith(SpringRunner.class)
@SpringBootTest
@Import({LoadDatabase.class})


public class LogDispatcherRestServiceApplicationTests {
	
	@Autowired
	WebLogParagraphRepo logRepo;
	
	@MockBean
	WebLogParagraphRepo mockLogRepo;
	@MockBean
	ErrorBindingService mockEBS;
	
	// change : get this from  props
	Long id = Long.valueOf(3);
	String tag = "dp";
	
	// first of all, it's good to know the autowiring is ok
	@Test
	public void autowiredTesting() {
		assertThat(logRepo).isNotNull();
		assertThat(mockLogRepo).isNotNull();
		Assertions.assertTrue(mockEBS!=null);
	}
	
	
//	log repo: get by id, check the class name
	@Test
	public void givenAnId_getALogOpt() {
		Optional<WebLogParagraph> oplog = logRepo.findById(id);
		assertThat(logRepo).isNotNull();
		assertThat(oplog.isPresent()).isTrue();
//		assertTrue("wlp type checked", oplog.get().getClass().getName()=="com.cluster9.logDispatcherRestService.entities.WebLogParagraph");
	}

	@Test
	public void givenAnId_getALogPage() {
		Pageable p = PageRequest.of(0, 5);
		Page<WebLogParagraph> logPage = logRepo.findById(id, p);
		assertThat(logPage != null).isTrue();
		assertThat(logPage.getContent().get(0).getClass().getName()=="com.cluster9.logDispatcherRestService.entities.WebLogParagraph").isTrue();
	}

//	log repo: get by id, check the class name
	@Test
	public void givenATag_getALogPage() {
		Pageable p = PageRequest.of(0, 5);
		Page<WebLogParagraph> page = logRepo.findByTag(tag, p);
		assertThat(page ==  null).isFalse();
		if (page != null) {
			assertThat(page.getSize() > 0);
			page.forEach(wlp -> {
				assertThat(wlp == null).isTrue();
				assertEquals("a tag is wrong: ", tag, wlp.getTag());
			});
		}
	}
	
	@Test
	public void addALog_getThisLog() {
		assertThat(logRepo).isNotNull();
		logRepo.saveAndFlush(new WebLogParagraph(3, "filename", "tagname", "thetitle"));
		
		Pageable p = PageRequest.of(0, 5);
		Page<WebLogParagraph> page = logRepo.findByTag("tagname", p);
		assertThat(page ==  null).isFalse();
		if (page != null) {
			assertThat(page.getSize() > 0);
			page.forEach(wlp -> {
				assertThat(wlp != null).isTrue();
				assertEquals("a tag is wrong: ", tag, wlp.getTag());
			});
		}
		
	}

	
//	test the test
//	@Test
//	public void whenAssertingEquality_thenEqual() {
//	    String expected = "test";
//	    String actual = "test";
//	 
//	    assertEquals(expected, actual);
//	}
}
