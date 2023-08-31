package home.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDto<T> {
	private Integer totalPage;
	private Integer page;
	private Integer size;
	private List<T> data;
}
