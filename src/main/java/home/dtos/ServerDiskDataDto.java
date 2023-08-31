package home.dtos;

import java.util.List;

import home.model.ServerDiskData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerDiskDataDto {
	private List<Integer> nvme;
	private List<Integer> sata;
	private List<Integer> hdd;
	private List<Integer> general;

	ServerDiskDataDto(ServerDiskData diskData) {
		this.general = diskData.getGeneral();
		this.hdd = diskData.getHdd();
		this.nvme = diskData.getNvme();
		this.sata = diskData.getSata();
	}
}
