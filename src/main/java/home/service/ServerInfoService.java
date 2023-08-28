package home.service;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import home.model.ServerDiskData;
import home.model.ServerInfo;
import home.repository.ServerInfoRepository;
import home.util.JsonUtil;

@Service
public class ServerInfoService {
	private static final Logger LOG = LoggerFactory.getLogger(ServerInfoService.class);

	@Value("${resource.data.url}")
	private String resourceUrl;

	private ServerInfoRepository serverInfoRepository;

	public ServerInfoService(ServerInfoRepository serverInfoRepository) {
		this.serverInfoRepository = serverInfoRepository;
	}

	public int syncData() throws IOException {
		URL url = new URL(resourceUrl);
		String json = IOUtils.toString(url, Charset.forName("UTF-8"));
		JSONObject object = new JSONObject(json);
		JSONArray array = new JSONArray(object.get("server").toString());
		List<ServerInfo> serverInfos = new ArrayList<>();

		array.forEach(o -> {
			JSONObject info = new JSONObject(o.toString());
			ServerInfo serverInfo = new ServerInfo();
			serverInfo.setBandwidth(JsonUtil.getInt(info, "bandwidth"));
			serverInfo.setCategory(JsonUtil.getString(info, "category"));
			serverInfo.setCatId(JsonUtil.getInt(info, "cat_id"));
			serverInfo.setCpu(JsonUtil.getString(info, "cpu"));
			serverInfo.setCpuCount(JsonUtil.getInt(info, "cpu_count"));
			serverInfo.setDatacenter(JsonUtil.getString(info, "datacenter"));
			serverInfo.setDatacenterHr(JsonUtil.getString(info, "datacenter_hr"));
			serverInfo.setDescription(JsonUtil.jsonObjectToList(info.get("description")));
			serverInfo.setDist(JsonUtil.jsonObjectToList(info.get("dist")));
			serverInfo.setFixedPrice(JsonUtil.getBoolean(info, "get"));
			serverInfo.setHddArr(JsonUtil.jsonObjectToList(info.get("hdd_hr")));
			serverInfo.setHddCount(JsonUtil.getInt(info, "hdd_count"));
			serverInfo.setHddHr(JsonUtil.jsonObjectToList(info.get("hdd_hr")));
			serverInfo.setHddSize(JsonUtil.getInt(info, "hdd_size"));
			serverInfo.setInfoId(JsonUtil.getLong(info, "id"));
			serverInfo.setInformation(JsonUtil.jsonObjectToList(info.get("information")));
			serverInfo.setIsEcc(JsonUtil.getBoolean(info, "is_ecc"));
			serverInfo.setIsHighio(JsonUtil.getBoolean(info, "is_highio"));
			serverInfo.setKeyNum(JsonUtil.getLong(info, "key"));
			serverInfo.setName(JsonUtil.getString(info, "name"));
			serverInfo.setNextReduce(JsonUtil.getInt(info, "next_reduce"));
			serverInfo.setNextReduceHr(JsonUtil.getBoolean(info, "next_reduce_hr"));
			serverInfo.setNextReduceTimestamp(JsonUtil.getLong(info, "next_reduce_timestamp"));
			serverInfo.setPrice(JsonUtil.getDouble(info, "price"));
			serverInfo.setRam(JsonUtil.jsonObjectToList(info.get("ram")));
			serverInfo.setRamSize(JsonUtil.getInt(info, "ram_size"));
			ServerDiskData serverDiskData = new ServerDiskData();
			JSONObject serverDiskDataJson = (JSONObject) info.get("serverDiskData");
			serverDiskData.setGeneral(JsonUtil.jsonObjectToList(serverDiskDataJson.get("general")));
			serverDiskData.setHdd(JsonUtil.jsonObjectToList(serverDiskDataJson.get("hdd")));
			serverDiskData.setNvme(JsonUtil.jsonObjectToList(serverDiskDataJson.get("nvme")));
			serverDiskData.setSata(JsonUtil.jsonObjectToList(serverDiskDataJson.get("sata")));
			serverInfo.setServerDiskData(serverDiskData);
			serverInfo.setSetupPrice(JsonUtil.getDouble(info, "setup_price"));
			serverInfo.setSpecials(JsonUtil.jsonObjectToList(info.get("specials")));
			serverInfo.setTraffic(JsonUtil.getString(info, "traffic"));
			serverInfos.add(serverInfo);
		});
		long count = serverInfoRepository.count();
		if (count != serverInfos.size()) {
			Iterable<ServerInfo> result = serverInfoRepository.saveAll(serverInfos);
			int size = ((List<ServerInfo>) result).size();
			LOG.info("Insert {} rows into database", size);
			return size;
		}
		LOG.info("No row to insert into database");
		return 0;
	}

	public List<ServerInfo> findAllServerInfo() {
		return (List<ServerInfo>) serverInfoRepository.findAll();
	}
	
	public List<ServerInfo> findServerInfoFilterByName(String name) {
		return serverInfoRepository.findByNameLike(name);
	}
	
	public List<ServerInfo> findServerInfoById(String infoId) {
		return serverInfoRepository.findByInfoId(infoId);
	}
}
