package home.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerDiskDataDto {
	private Long id;
	private List<Integer> nvme;
	private List<Integer> sata;
	private List<Integer> hdd;
	private List<Integer> general;
}
