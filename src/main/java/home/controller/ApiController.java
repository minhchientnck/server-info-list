package home.controller;

import java.io.IOException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import home.dtos.PaginationDto;
import home.dtos.SearchDto;
import home.dtos.ServerInfoDto;
import home.payload.ResponsePayload;
import home.service.ServerInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ApiController {

	private ServerInfoService serverInfoService;

	public ApiController(ServerInfoService serverInfoService) {
		this.serverInfoService = serverInfoService;
	}

	@GetMapping(value = "${api.url.fetchData}")
	@ApiOperation(value = "fetch data", notes = "Returns HTTP 404 if list is failed")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "susscess"),
			@ApiResponse(code = 404, message = "failed") })
	public ResponseEntity<?> fetchData() throws IOException {
		int rows = serverInfoService.fetchData();
		ResponsePayload response = new ResponsePayload().setKeyValue("message", "fetched data successfully")
				.setKeyValue("total", rows).build();
		return ResponseEntity.ok().body(response);
	}

	@PostMapping(value = "${api.url.findBySearch}")
	@ApiOperation(value = "Search data", notes = "Returns HTTP 404 if list is failed")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "susscess"),
			@ApiResponse(code = 404, message = "failed") })
	public ResponseEntity<PaginationDto<ServerInfoDto>> findBySearch(@RequestBody SearchDto searchDto,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int size) {
		Pageable paging = PageRequest.of(page, size);
		PaginationDto<ServerInfoDto> serverInfoDtos = serverInfoService.findBySearch(searchDto, paging);
		return ResponseEntity.ok().body(serverInfoDtos);
	}
}
