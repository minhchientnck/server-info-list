package home.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import home.dtos.PaginationDto;
import home.dtos.SearchDto;
import home.dtos.ServerInfoDto;
import home.service.ServerInfoService;
import home.util.JsonUtil;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ServerInfoService serverInfoService;

	@Mock
	private MockHttpSession httpSession;

	@InjectMocks
	private ApiController apiController;

	private String fetchDataUrl = "/api/fetchData";
	private String searchUrl = "/api/search";

	private String domainName = "/domainName";
	private String referer;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(apiController)
				.defaultRequest(null)
				.addPlaceholderValue("api.url.fetchData", fetchDataUrl)
				.addPlaceholderValue("api.url.findBySearch", searchUrl)
				.build();
		referer = "https://" + domainName;
	}

	@Test
	public void testFetchDataAndUpdate() throws Exception {
		Mockito.when(serverInfoService.fetchData()).thenReturn(2);
		mockMvc.perform(get(fetchDataUrl)
				.header("referer", referer))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testFetchDataNoUpdate() throws Exception {
		Mockito.when(serverInfoService.fetchData()).thenReturn(0);
		mockMvc.perform(get(fetchDataUrl)
				.header("referer", referer))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testSearchData() throws Exception {
		SearchDto searchDto = new SearchDto();
		searchDto.setId(123L);
		searchDto.setKey(456L);
		searchDto.setCpu("Cpu");
		
		PaginationDto<ServerInfoDto> paginationDto = new PaginationDto<>();
		paginationDto.setTotalPage(1);
		paginationDto.setPage(1);
		paginationDto.setSize(1);
		
		List<ServerInfoDto> dtos = new ArrayList<>();
		ServerInfoDto dto = new ServerInfoDto();
		dto.setId(123L);
		dto.setKey(456L);
		dto.setCpu("Cpu");
		dtos.add(dto);
		paginationDto.setData(dtos);
		
		Mockito.when(serverInfoService.findBySearch(any(), any())).thenReturn(paginationDto);
		mockMvc.perform(post(searchUrl+"?page=1&size=15")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(JsonUtil.toJsonString(searchDto)))
				.andExpect(status().isOk());
	}
}
