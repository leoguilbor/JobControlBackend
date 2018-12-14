package br.com.leoguilbor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.leoguilbor.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{

	@Query("select j from Job j where date like %:monthPeriod ")
	List<Job> listBetweenDates(@Param("monthPeriod") String monthPeriod);
	
	@Query("select j from Job j where "
			+ "lower(name) like lower(:text) or "
			+ "lower(description) like lower(:text) or "
			+ "lower(j.client.name) like lower(:text) or "
			+ "lower(j.service.name) like lower(:text)")
	List<Job> searchByText(@Param("text") String text);
	
}
