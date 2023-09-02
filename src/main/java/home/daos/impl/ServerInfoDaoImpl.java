package home.daos.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;

import home.daos.ServerInfoDao;
import home.dtos.PaginationDto;
import home.dtos.SearchDto;
import home.dtos.ServerInfoDto;
import home.model.ServerInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ServerInfoDaoImpl implements ServerInfoDao {

	private static final int PAGE = 1;
	private static final int PAGE_SIZE = 10;

	@PersistenceContext
	EntityManager em;

	@Override
	public PaginationDto<ServerInfoDto> findBySearchDto(SearchDto searchDto, Pageable pageable) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ServerInfo> query = builder.createQuery(ServerInfo.class);
		Root<ServerInfo> serverInfo = query.from(ServerInfo.class);

		List<Predicate> predicates = new ArrayList<>();

		if (searchDto.getId() != null) {
			predicates.add(builder.equal(serverInfo.get("id"), searchDto.getId()));
		}

		if (searchDto.getKey() != null) {
			predicates.add(builder.equal(serverInfo.get("keyNum"), searchDto.getKey()));
		}

		if (searchDto.getName() != null) {
			predicates.add(builder.like(serverInfo.get("name"), "%" + searchDto.getName() + "%"));
		}

		if (searchDto.getCategory() != null) {
			predicates.add(builder.like(serverInfo.get("category"), "%" + searchDto.getCategory() + "%"));
		}

		if (searchDto.getCatId() != null) {
			predicates.add(builder.equal(serverInfo.get("catId"), searchDto.getCatId()));
		}

		if (searchDto.getCpu() != null) {
			predicates.add(builder.like(serverInfo.get("cpu"), "%" + searchDto.getCpu() + "%"));
		}

		if (searchDto.getCpuCount() != null) {
			predicates.add(builder.equal(serverInfo.get("cpuCount"), searchDto.getCpuCount()));
		}

		if (searchDto.getIsHighio() != null) {
			predicates.add(builder.equal(serverInfo.get("isHighio"), searchDto.getIsHighio()));
		}

		if (searchDto.getTraffic() != null) {
			predicates.add(builder.equal(serverInfo.get("traffic"), "%" + searchDto.getTraffic() + "%"));
		}

		if (searchDto.getBandwidth() != null) {
			predicates.add(builder.equal(serverInfo.get("bandwidth"), searchDto.getBandwidth()));
		}

		if (searchDto.getRamSize() != null) {
			predicates.add(builder.equal(serverInfo.get("ramSize"), searchDto.getRamSize()));
		}

		if (searchDto.getIsEcc() != null) {
			predicates.add(builder.equal(serverInfo.get("isEcc"), searchDto.getIsEcc()));
		}

		if (searchDto.getPrice() != null) {
			predicates.add(builder.greaterThanOrEqualTo(serverInfo.get("price"), searchDto.getPrice()));
		}

		if (searchDto.getSetupPrice() != null) {
			predicates.add(builder.greaterThanOrEqualTo(serverInfo.get("setupPrice"), searchDto.getSetupPrice()));
		}

		if (searchDto.getHddSize() != null) {
			predicates.add(builder.equal(serverInfo.get("hddSize"), searchDto.getHddSize()));
		}

		if (searchDto.getHddCount() != null) {
			predicates.add(builder.equal(serverInfo.get("hddCount"), searchDto.getHddCount()));
		}

		if (searchDto.getHddCount() != null) {
			predicates.add(builder.equal(serverInfo.get("hddCount"), searchDto.getHddCount()));
		}

		if (searchDto.getDatacenter() != null) {
			predicates.add(builder.like(serverInfo.get("datacenter"), "%" + searchDto.getDatacenter() + "%"));
		}

		if (searchDto.getDatacenterHr() != null) {
			predicates.add(builder.like(serverInfo.get("datacenter"), "%" + searchDto.getDatacenterHr() + "%"));
		}

		if (searchDto.getFixedPrice() != null) {
			predicates.add(builder.equal(serverInfo.get("fixedPrice"), searchDto.getFixedPrice()));
		}

		if (searchDto.getNextReduce() != null) {
			predicates.add(builder.equal(serverInfo.get("nextReduce"), searchDto.getNextReduce()));
		}

		if (searchDto.getNextReduceHr() != null) {
			predicates.add(builder.equal(serverInfo.get("nextReduceHr"), searchDto.getNextReduceHr()));
		}

		if (searchDto.getNextReduceTimestamp() != null) {
			predicates.add(builder.equal(serverInfo.get("nextReduceTimestamp"), searchDto.getNextReduceTimestamp()));
		}
		CriteriaQuery<ServerInfo> select = query.where(predicates.toArray(new Predicate[0]));
		TypedQuery<ServerInfo> typedQuery = em.createQuery(select);
		int totalCount = typedQuery.getResultList().size();
		int pageNo = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();
		if (pageNo <= 0) {
			pageNo = PAGE;
		}
		if (pageSize <= 0) {
			pageSize = PAGE_SIZE;
		}
		int totalPage = (int) Math.ceil(totalCount / (pageSize * 1.0));

		typedQuery.setFirstResult((pageNo - 1) * pageSize);
		typedQuery.setMaxResults(pageSize);
		List<ServerInfoDto> serverInfoDtos = typedQuery.getResultList().stream().map(ServerInfoDto::new)
				.collect(Collectors.toList());

		return new PaginationDto<>(totalPage, pageNo, serverInfoDtos.size(), serverInfoDtos);
	}

}
