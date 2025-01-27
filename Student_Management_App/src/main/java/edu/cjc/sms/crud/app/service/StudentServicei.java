package edu.cjc.sms.crud.app.service;

import java.util.List;

import edu.cjc.sms.crud.app.model.Student;

public interface StudentServicei {

	public void saveStudentDetails(Student s);
	
	public List<Student> getAllStudents();
	
	public List<Student> searchStudentByBatch(String batchNumber);

	public Student getSinglrStudent(int id);

	public void updateStudentFees(int studentId, double amount);

	public void deleteStudent(int id);

	public List<Student> pagingData(int pageNo);

	

}
