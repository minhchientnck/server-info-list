package home.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "server_disk_data")
public class ServerDiskData implements Serializable {

	private static final long serialVersionUID = -3775901055103577598L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nvme")
	private List<Integer> nvme;
	
	@Column(name = "sata")
	private List<Integer> sata;
	
	@Column(name = "hdd")
	private List<Integer> hdd;
	
	@Column(name = "general")
	private List<Integer> general;
}
