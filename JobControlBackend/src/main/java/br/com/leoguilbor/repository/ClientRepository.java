package br.com.leoguilbor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.leoguilbor.domain.Client;
import br.com.leoguilbor.domain.Job;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	@Query("select c from Client c where "
			+ "lower(name) like lower(:text) or "
			+ "lower(address) like lower(:text) or "
			+ "lower(city) like lower(:text) or "
			+ "lower(telephone) like lower(:text)")
	List<Client> searchByText(@Param("text") String text);

}
