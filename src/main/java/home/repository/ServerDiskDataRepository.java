package home.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import home.model.ServerDiskData;

@Repository
public interface ServerDiskDataRepository extends CrudRepository<ServerDiskData, Long> {

}
