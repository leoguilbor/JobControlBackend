package br.com.leoguilbor.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leoguilbor.domain.Category;
import br.com.leoguilbor.exception.ObjectNotFoundException;
import br.com.leoguilbor.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository rep;
	public List<Category> listAll() {
		return rep.findAll();
	}
	
	public Category findOne(Long id) {
		// TODO Auto-generated method stub
		Optional<Category> category = rep.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:"+ id 
				+ " Tipo:" + Category.class.getName()));
	}
	@Transactional
	public Category insert(Category category) {
		// TODO Auto-generated method stub
		category.setId(null);
		return rep.save(category);
	}
	@Transactional
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return rep.save(category);
	}
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		findOne(id);
		rep.deleteById(id);
	}

}
