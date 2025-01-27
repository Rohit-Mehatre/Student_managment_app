package edu.cjc.sms.crud.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.cjc.sms.crud.app.model.Student;
import edu.cjc.sms.crud.app.repository.StudentRepository;
import edu.cjc.sms.crud.app.service.StudentServicei;

@Service
public class StudentServiceimpl implements StudentServicei{

	@Autowired
	private StudentRepository sr;
	
	
	@Override
	public void saveStudentDetails(Student st) {
		
		sr.save(st);
	}


	@Override
	public List<Student> getAllStudents() {
		
		return sr.findAll();
	}


	@Override
	public List<Student> searchStudentByBatch(String batchNumber) {
		
		List<Student> batchStudent=sr.findAllByBatchNumber(batchNumber);
		return batchStudent;
	}


	@Override
	public Student getSinglrStudent(int id) {
		Optional<Student> list = sr.findById(id);
		return list.get();
	}


	@Override
	public void updateStudentFees(int studentId, double amount) {
		Optional<Student> list = sr.findById(studentId);
		Student st=list.get();
		
		st.setFeespaid(st.getFeespaid()+amount);
		sr.save(st);
	}


	@Override
	public void deleteStudent(int id) {
		sr.deleteById(id);
		
	}


	@Override
	public List<Student> pagingData(int pageNo) {
		Pageable page=PageRequest.of(pageNo, 3,Sort.by("studentId").ascending());         
		  
		List<Student>	list   = sr.findAll(page).getContent();
			
			return list;
	}






}
