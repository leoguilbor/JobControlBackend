package br.com.leoguilbor.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leoguilbor.domain.Job;
import br.com.leoguilbor.dto.JobDTO;
import br.com.leoguilbor.service.JobService;

@RestController
@RequestMapping(value="jobs")
public class JobResource {
	@Autowired
	private JobService service;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<JobDTO>> listAll(){
		List<Job> jobs = service.listAll();
		List<JobDTO> jDTOs= jobs.stream().map(item-> new JobDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(jDTOs);
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<JobDTO> findOne(@PathVariable Long id) {
		Job job = service.findOne(id);	
		return ResponseEntity.ok().body(new JobDTO(job));
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Job job) {
		Job newJob = service.insert(job);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newJob.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> edit(@RequestBody Job job) {
		service.update(job);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);	
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/date",method=RequestMethod.GET)
	public ResponseEntity<?> listByMonth(
			@RequestParam(defaultValue="",name="month") Integer month,
			@RequestParam(defaultValue="",name="year")  Integer year) {
		
		List<Job> jobs = service.listByMonth(month,year);	
		List<JobDTO> jDTOs= jobs.stream().map(item-> new JobDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(jDTOs);
	}	
	@RequestMapping(value= "/search",method=RequestMethod.GET)
	public ResponseEntity<?> searchByText(
			@RequestParam(defaultValue="",name="text") String text) {
		
		List<Job> jobs = service.searchByText(text);	
		List<JobDTO> jDTOs= jobs.stream().map(item-> new JobDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(jDTOs);
	}	
	
}
