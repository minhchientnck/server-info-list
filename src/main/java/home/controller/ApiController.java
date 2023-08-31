package home.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import home.dtos.ServerInfoDto;
import home.model.ServerInfo;
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

	@GetMapping(value = "${api.url.findAll}")
	@ApiOperation(value = "find all data", notes = "Returns HTTP 404 if list is failed")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "susscess"),
			@ApiResponse(code = 404, message = "failed") })
	public ResponseEntity<List<ServerInfoDto>> findAllServerInfo() {
		List<ServerInfoDto> serverInfoDtos = serverInfoService.findAllServerInfo();
		return ResponseEntity.ok().body(serverInfoDtos);
	}

	@PostMapping(value = "${api.url.filterByName}")
	public ResponseEntity<List<ServerInfoDto>> filterServerInfoByName(@RequestBody String name) {
		List<ServerInfoDto> serverInfoDtos = serverInfoService.findServerInfoFilterByName(name);
		return ResponseEntity.ok().body(serverInfoDtos);
	}
	
	@PostMapping(value = "${api.url.filterByCpu}")
	public ResponseEntity<List<ServerInfoDto>> filterServerInfoByCpu(@RequestBody String cpu) {
		List<ServerInfoDto> serverInfoDtos = serverInfoService.findServerInfoByCpu(cpu);
		return ResponseEntity.ok().body(serverInfoDtos);
	}
	
	@GetMapping(value = "${api.url.findByInfoId}")
	public ResponseEntity<ServerInfoDto> filterServerInfoByInfoId(@PathVariable Long infoId) {
		ServerInfoDto serverInfoDto = serverInfoService.findServerInfoByInfoId(infoId);
		return ResponseEntity.ok().body(serverInfoDto);
	}
	
}
