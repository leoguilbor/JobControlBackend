package br.com.leoguilbor.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.leoguilbor.domain.Service;
import br.com.leoguilbor.dto.NewServiceDTO;
import br.com.leoguilbor.exception.ObjectNotFoundException;
import br.com.leoguilbor.repository.CategoryRepository;
import br.com.leoguilbor.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService {
	@Autowired
	private ServiceRepository rep;
	@Autowired
	private CategoryRepository categoryRep;
	
	public List<Service> listAll() {
		return rep.findAll();
	}
	
	public Service findOne(Long id) {
		// TODO Auto-generated method stub
		Optional<Service> Service = rep.findById(id);
		return Service.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:"+ id 
				+ " Tipo:" + Service.class.getName()));
	}
	@Transactional
	public Service insert(Service Service) {
		// TODO Auto-generated method stub
		Service.setId(null);
		return rep.save(Service);
	}
	@Transactional
	public Service update(Service Service) {
		// TODO Auto-generated method stub
		return rep.save(Service);
	}
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		findOne(id);
		rep.deleteById(id);
	}

	public Service fromDTO(NewServiceDTO newServiceDTO) {
		// TODO Auto-generated method stub
		return new Service(newServiceDTO.getId(),newServiceDTO.getName(),newServiceDTO.getDescription(),newServiceDTO.getTerm(),categoryRep.findById(newServiceDTO.getCategory()).orElse(null));
	}
}
