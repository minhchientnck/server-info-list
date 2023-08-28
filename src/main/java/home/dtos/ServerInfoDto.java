package home.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfoDto  {
	private Long id;
	private Long infoId;
	private Long keyNum;
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

}
