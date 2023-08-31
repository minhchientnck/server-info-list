package home.dtos;

import java.util.List;

import home.model.ServerInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfoDto {
	private Long id;
	private Long key;
	private String name;
	private List<String> description;
	private List<String> information;
	private String category;
	private Integer catId;
	private String cpu;
	private Integer cpuCount;
	private Boolean isHighio;
	private String traffic;
	private Integer bandwidth;
	private List<String> ram;
	private Integer ramSize;
	private Double price;
	private Double setupPrice;
	private List<String> hddArr;
	private List<String> hddHr;
	private Integer hddSize;
	private Integer hddCount;
	private ServerDiskDataDto serverDiskData;
	private Boolean isEcc;
	private String datacenter;
	private String datacenterHr;
	private List<String> specials;
	private List<String> dist;
	private Boolean fixedPrice;
	private Integer nextReduce;
	private Boolean nextReduceHr;
	private Long nextReduceTimestamp;

	public ServerInfoDto(ServerInfo info) {
		this.id = info.getInfoId();
		this.key = info.getKeyNum();
		this.name = info.getName();
		this.description = info.getDescription();
		this.information = info.getInformation();
		this.category = info.getCategory();
		this.catId = info.getCatId();
		this.cpu = info.getCpu();
		this.cpuCount = info.getCpuCount();
		this.isHighio = info.getIsHighio();
		this.traffic = info.getTraffic();
		this.bandwidth = info.getBandwidth();
		this.ram = info.getRam();
		this.ramSize = info.getRamSize();
		this.price = info.getPrice();
		this.setupPrice = info.getSetupPrice();
		this.hddArr = info.getHddArr();
		this.hddHr = info.getHddHr();
		this.hddSize = info.getHddSize();
		this.hddCount = info.getHddCount();
		ServerDiskDataDto diskDataDto = new ServerDiskDataDto(info.getServerDiskData());
		this.serverDiskData = diskDataDto;
		this.isEcc = info.getIsEcc();
		this.datacenter = info.getDatacenter();
		this.datacenterHr = info.getDatacenterHr();
		this.specials = info.getSpecials();
		this.dist = info.getDist();
		this.fixedPrice = info.getFixedPrice();
		this.nextReduce = info.getNextReduce();
		this.nextReduceHr = info.getNextReduceHr();
		this.nextReduceTimestamp = info.getNextReduceTimestamp();
	}
}
