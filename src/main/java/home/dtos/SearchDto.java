package home.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
	private Long id;
	private Long key;
	private String name;
	private String category;
	private Integer catId;
	private String cpu;
	private Integer cpuCount;
	private Boolean isHighio;
	private String traffic;
	private Integer bandwidth;
	private Integer ramSize;
	private Double price;
	private Double setupPrice;
	private Integer hddSize;
	private Integer hddCount;
	private Boolean isEcc;
	private String datacenter;
	private String datacenterHr;
	private Boolean fixedPrice;
	private Integer nextReduce;
	private Boolean nextReduceHr;
	private Long nextReduceTimestamp;
}
