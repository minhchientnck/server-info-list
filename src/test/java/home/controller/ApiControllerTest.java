package home.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	private String rootPath = "/api";
	private String fetchDataUrl = "/fetchData";
	private String searchUrl = "/search";

	private String domainName = "/domainName";
	private String referer;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(apiController)
				.addPlaceholderValue("api.url.fetchData", fetchDataUrl)
				.addPlaceholderValue("api.url.findBySearch", searchUrl)
				.build();
		referer = "https://" + domainName;
	}

	@Test
	public void testFetchDataAndUpdate() throws Exception {
		Mockito.when(serverInfoService.fetchData()).thenReturn(2);
		String url = rootPath + fetchDataUrl;
		mockMvc.perform(get(url)
				.header("referer", referer))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testFetchDataNoUpdate() throws Exception {
		Mockito.when(serverInfoService.fetchData()).thenReturn(0);
		String url = rootPath + fetchDataUrl;
		mockMvc.perform(get(url)
				.header("referer", referer))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testSearchData() throws Exception {
		SearchDto searchDto = new SearchDto();
		searchDto.setId(123L);
		searchDto.setKey(456L);
		searchDto.setCpu("Cpu");
		
		Pageable paging = PageRequest.of(1, 10);
		PaginationDto<ServerInfoDto> serverInfoDtos = new PaginationDto<>();
		serverInfoDtos.setTotalPage(1);
		serverInfoDtos.setPage(1);
		serverInfoDtos.setSize(1);
		
		List<ServerInfoDto> dtos = new ArrayList<>();
		ServerInfoDto dto = new ServerInfoDto();
		dto.setId(1L);
		dto.setKey(1L);
		dtos.add(dto);
		serverInfoDtos.setData(dtos);
		
		Mockito.when(serverInfoService.findBySearch(searchDto, paging)).thenReturn(serverInfoDtos);
		String url = rootPath + searchUrl;
		
		mockMvc.perform(post(url)
				.header("referer", referer)
				.content(JsonUtil.toJsonString(searchDto)))
				.andExpect(status().isOk());
	}
}
