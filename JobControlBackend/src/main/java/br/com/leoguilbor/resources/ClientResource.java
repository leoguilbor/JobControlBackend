package br.com.leoguilbor.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leoguilbor.domain.Client;
import br.com.leoguilbor.dto.ClientDTO;
import br.com.leoguilbor.service.ClientService;

@RestController
@RequestMapping(value="clients")
public class ClientResource {
	@Autowired
	private ClientService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> listAll(){
		List<Client> Clients = service.listAll();
		List<ClientDTO> jDTOs= Clients.stream().map(item-> new ClientDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(jDTOs);
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<ClientDTO> findOne(@PathVariable Long id) {
		Client Client = service.findOne(id);	
		return ResponseEntity.ok().body(new ClientDTO(Client));
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> insert(@Valid @RequestBody ClientDTO clientDTO) {
		Client client = service.fromDTO(clientDTO);
		Client newClient = service.insert(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newClient.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> edit(@RequestBody Client Client) {
		service.update(Client);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		 service.delete(id);	
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/search", method=RequestMethod.GET)
	public ResponseEntity<?> searchByText(@RequestParam(defaultValue="",name="text") String text) {
		List<Client> Clients = service.searchByText(text);
		List<ClientDTO> jDTOs= Clients.stream().map(item-> new ClientDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(jDTOs);
	}	
}
