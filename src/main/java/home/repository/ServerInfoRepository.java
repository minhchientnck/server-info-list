package home.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import home.daos.ServerInfoDao;
import home.model.ServerInfo;


@Repository
public interface ServerInfoRepository extends CrudRepository<ServerInfo, Long>, ServerInfoDao {
	ServerInfo findByInfoId(Long infoId);
}
