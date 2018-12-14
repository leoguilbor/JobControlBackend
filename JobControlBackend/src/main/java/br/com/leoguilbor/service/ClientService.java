package br.com.leoguilbor.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leoguilbor.domain.Client;
import br.com.leoguilbor.dto.ClientDTO;
import br.com.leoguilbor.exception.ObjectNotFoundException;
import br.com.leoguilbor.repository.ClientRepository;
@Service
public class ClientService {
	@Autowired
	private ClientRepository rep;
	public List<Client> listAll() {
		return rep.findAll();
	}
	
	public Client findOne(Long id) {
		// TODO Auto-generated method stub
		Optional<Client> Client = rep.findById(id);
		return Client.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:"+ id 
				+ " Tipo:" + Client.class.getName()));
	}
	@Transactional
	public Client insert(Client Client) {
		// TODO Auto-generated method stub
		Client.setId(null);
		return rep.save(Client);
	}
	@Transactional
	public Client update(Client Client) {
		// TODO Auto-generated method stub
		return rep.save(Client);
	}
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		findOne(id);
		rep.deleteById(id);
	}

	public Client fromDTO(ClientDTO clientDTO) {
		// TODO Auto-generated method stub
		return new Client(clientDTO.getId(),clientDTO.getName(),clientDTO.getAddress(),clientDTO.getCity(),clientDTO.getTelephone());
	}

	public List<Client> searchByText(String text) {
		// TODO Auto-generated method stub
		return rep.searchByText("%"+text+"%");
	}
}
