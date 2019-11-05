package com.cluster9.logDispatcherRestService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.cluster9.logDispatcherRestService.controllers.LogController;
import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;
import com.cluster9.logDispatcherRestService.service.ErrorBindingService;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class LogDispatcherRestServiceApplicationTests {
	
	@Autowired
	WebLogParagraphRepo logRepo;
	
	@MockBean
	WebLogParagraphRepo mockLogRepo;
	@MockBean
	ErrorBindingService mockEBS;
	
	// change to props
	Long id = Long.valueOf(3);
	String tag = "dp";
	
//	log controller::get a list by tag
//	@Test
//	public void givenATag_getALogList() {
//		List<String> names = new ArrayList<String>
//		List logs = Stream.of(new WebLogParagraph(index, fileName, tag, title))
//		Page page = new PageImpl<WebLogParagraph>(logs, pageable, total)
//	}
	
//	log repo: get by id, check the class name
	@Test
	public void givenAnId_getALogOpt() {
		Optional<WebLogParagraph> oplog = logRepo.findById(id);
		assertTrue("no repo!", logRepo != null);
		assertTrue("no log found", oplog.isPresent());
		assertTrue("wlp type checked", oplog.get().getClass().getName()=="com.cluster9.logDispatcherRestService.entities.WebLogParagraph");
	}

	@Test
	public void givenAnId_getALogPage() {
		Pageable p = PageRequest.of(0, 5);
		Page<WebLogParagraph> logPage = logRepo.findById(id, p);
		assertTrue("no log found", logPage != null);
		assertTrue("wlp type checked", logPage.getContent().get(0).getClass().getName()=="com.cluster9.logDispatcherRestService.entities.WebLogParagraph");
	}

//	log repo: get by id, check the class name
	@Test
	public void givenATag_getALogPage() {
		Pageable p = PageRequest.of(0, 5);
		Page<WebLogParagraph> page = logRepo.findByTag(tag, p);
		assertFalse("no log found by tag", page ==  null);
		if (page != null) {
			page.forEach(wlp -> {
				assertTrue("a wlp was not found", wlp == null);
				assertEquals("a tag is wrong: ", tag, wlp.getTag());
			});
		}
	}

	
//	test the test
	@Test
	public void whenAssertingEquality_thenEqual() {
	    String expected = "test";
	    String actual = "test";
	 
	    assertEquals(expected, actual);
	}
}
