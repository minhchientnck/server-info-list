package home.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping(value = "${api.url.syncData}")
	@ApiOperation(value = "Synchronize data", notes = "Returns HTTP 404 if list is failed")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "susscess"),
			@ApiResponse(code = 404, message = "failed") })
	public ResponseEntity<?> SynchronizedData() throws IOException {
		int rows = serverInfoService.syncData();
		ResponsePayload response = new ResponsePayload().setKeyValue("message", "Synchronized data successfully")
				.setKeyValue("total", rows).build();
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = "${api.url.findAllServerInfo}")
	@ApiOperation(value = "find all data", notes = "Returns HTTP 404 if list is failed")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "susscess"),
			@ApiResponse(code = 404, message = "failed") })
	public ResponseEntity<?> findAllServerInfo() {
		List<ServerInfo> infos = serverInfoService.findAllServerInfo();
		return ResponseEntity.ok().body(infos);
	}
	
	@GetMapping(value = "${api.url.filterServerInfoByName}")
	public ResponseEntity<?> filterServerInfoByName() {
		return null;
	}
}
