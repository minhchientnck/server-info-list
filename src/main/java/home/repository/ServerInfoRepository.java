package home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import home.model.ServerInfo;


@Repository
public interface ServerInfoRepository extends CrudRepository<ServerInfo, Long> {
	ServerInfo findByInfoId(Long infoId);
	
	@Query("SELECT s FROM ServerInfo s WHERE s.name LIKE %?1%")
	List<ServerInfo> findByNameLike(String name);
	
	@Query("SELECT s FROM ServerInfo s WHERE s.cpu LIKE %?1%")
	List<ServerInfo> findByCpuLike(String cpu);
}
