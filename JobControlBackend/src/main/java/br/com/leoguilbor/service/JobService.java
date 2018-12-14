package br.com.leoguilbor.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leoguilbor.domain.Job;
import br.com.leoguilbor.exception.ObjectNotFoundException;
import br.com.leoguilbor.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository rep;
	public List<Job> listAll() {
		return rep.findAll();
	}
	
	public Job findOne(Long id) {

		Optional<Job> job = rep.findById(id);
		return job.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:"+ id 
				+ " Tipo:" + Job.class.getName()));
	}
	@Transactional
	public Job insert(Job job) {

		job.setId(null);
		return rep.save(job);
	}
	@Transactional
	public Job update(Job job) {

		return rep.save(job);
	}
	@Transactional
	public void delete(Long id) {

		findOne(id);
		rep.deleteById(id);
	}

	public List<Job> listByMonth(Integer month, Integer year) {

		return rep.listBetweenDates("%/"+month+"/"+year);
	}

	public List<Job> searchByText(String text) {
		// TODO Auto-generated method stub
		return rep.searchByText('%'+text+'%');
	}


}
