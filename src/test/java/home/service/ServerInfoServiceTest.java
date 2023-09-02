package home.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import home.dtos.PaginationDto;
import home.dtos.SearchDto;
import home.dtos.ServerInfoDto;
import home.repository.ServerInfoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ServerInfoServiceTest {
	@Mock
	private ServerInfoRepository serverInfoRepository;

	@InjectMocks
	private ServerInfoService serverInfoService;

	@Before
	public void setUp() {
		MockMvcBuilders.standaloneSetup(serverInfoService)
			.addPlaceholderValue("resource.data.url", "/data")
			.build();
	}
	
	@Test
	public void fetchDataTest() throws IOException {
		SearchDto searchDto = new SearchDto();
		searchDto.setId(123L);
		searchDto.setKey(456L);
		searchDto.setCpu("Cpu");
		
		Pageable pageable = PageRequest.of(1, 10);
		
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
		Mockito.when(serverInfoRepository.findBySearchDto(searchDto, pageable))
			.thenReturn(paginationDto);

		PaginationDto<ServerInfoDto> result  = serverInfoService.findBySearch(searchDto, pageable);
		assertEquals("1", String.valueOf(result.getData().size()));
	}
}
