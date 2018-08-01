package br.com.db1.parser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.db1.parser.model.LogFile;

@Repository
public interface LogFileRepository extends JpaRepository<LogFile, Long> {

}
