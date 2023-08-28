package home.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "server_info")
public class ServerInfo implements Serializable {

	private static final long serialVersionUID = 4172327313616921659L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "info_id")
	private Long infoId;
	
	@Column(name = "key_num")
	private Long keyNum;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private List<String> description;
	
	@Column(name = "information")
	private List<String> information;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "cat_id")
	private Integer catId;
	
	@Column(name = "cpu")
	private String cpu;
	
	@Column(name = "cpu_count")
	private Integer cpuCount;
	
	@Column(name = "is_highio")
	private Boolean isHighio;
	
	@Column(name = "traffic")
	private String traffic;
	
	@Column(name = "bandwidth")
	private Integer bandwidth;
	
	@Column(name = "ram")
	private List<String> ram;
	
	@Column(name = "ram_size")
	private Integer ramSize;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "setup_price")
	private Double setupPrice;
	
	@Column(name = "hdd_arr")
	private List<String> hddArr;

	@Column(name = "hdd_hr")
	private List<String> hddHr;
	
	@Column(name = "hdd_size")
	private Integer hddSize;
	
	@Column(name = "hdd_count")
	private Integer hddCount;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "serverDiskData", referencedColumnName = "id")
	private ServerDiskData serverDiskData;
	
	@Column(name = "isEcc")
	private Boolean isEcc;
	
	@Column(name = "datacenter")
	private String datacenter;
	
	@Column(name = "datacenter_hr")
	private String datacenterHr;
	
	@Column(name = "specials")
	private List<String> specials;
	
	@Column(name = "dist")
	private List<String> dist;
	
	@Column(name = "fixed_price")
	private Boolean fixedPrice;
	
	@Column(name = "next_reduce")
	private Integer nextReduce;
	
	@Column(name = "next_reduce_hr")
	private Boolean nextReduceHr;
	
	@Column(name = "nextReduce_timestamp")
	private Long nextReduceTimestamp;
}
