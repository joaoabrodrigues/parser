package br.com.db1.parser.repository;

import br.com.db1.parser.model.LogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "log", path = "log")
public interface LogItemRepository extends JpaRepository<LogItem, Long> {

    List<LogItem> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<LogItem> findByIp(@Param("ip") String ip);

}
