package home.daos;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import home.dtos.PaginationDto;
import home.dtos.SearchDto;
import home.dtos.ServerInfoDto;

@Repository
public interface ServerInfoDao {
	PaginationDto<ServerInfoDto> findBySearchDto(SearchDto searchDto, Pageable pageable);
}
