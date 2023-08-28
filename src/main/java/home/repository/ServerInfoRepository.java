package home.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import home.model.ServerInfo;
import java.util.List;


@Repository
public interface ServerInfoRepository extends CrudRepository<ServerInfo, Long> {
	List<ServerInfo> findByInfoId(String infoId);
	List<ServerInfo> findByNameLike(String name);
}
