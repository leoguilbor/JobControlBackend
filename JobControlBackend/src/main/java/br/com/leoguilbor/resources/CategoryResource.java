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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leoguilbor.domain.Category;
import br.com.leoguilbor.dto.CategoryDTO;
import br.com.leoguilbor.service.CategoryService;

@RestController
@RequestMapping(value="categorys")
public class CategoryResource {
	@Autowired
	private CategoryService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> listAll(){
		List<Category> categorys = service.listAll();
		List<CategoryDTO> jDTOs= categorys.stream().map(item-> new CategoryDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(jDTOs);
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Category> findOne(@PathVariable Long id) {
		Category category = service.findOne(id);	
		return ResponseEntity.ok().body(category);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Category category) {
		Category newCategory = service.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCategory.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> edit(@RequestBody Category category) {
		Category newCategory = service.update(category);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		 service.delete(id);	
		return ResponseEntity.noContent().build();
	}
	
}
