package br.com.leoguilbor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.leoguilbor.domain.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long>{

}
