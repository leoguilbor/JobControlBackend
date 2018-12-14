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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leoguilbor.domain.Service;
import br.com.leoguilbor.dto.NewServiceDTO;
import br.com.leoguilbor.dto.ServiceDTO;
import br.com.leoguilbor.service.ServiceService;

@RestController
@RequestMapping(value="services")
public class ServiceResource {
	@Autowired
	private ServiceService sService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ServiceDTO>> listAll(){
		List<Service> services = sService.listAll();
		List<ServiceDTO> jDTOs= services.stream().map(item-> new ServiceDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(jDTOs);
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Service> findOne(@PathVariable Long id) {
		Service Service = sService.findOne(id);	
		return ResponseEntity.ok().body(Service);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> insert(@Valid @RequestBody NewServiceDTO newServiceDTO) {
		Service service = sService.fromDTO(newServiceDTO);
		Service newService = sService.insert(service);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newService.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> edit(@RequestBody Service Service) {
		Service newService = sService.update(Service);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		 sService.delete(id);	
		return ResponseEntity.noContent().build();
	}
	
}
