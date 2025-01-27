package edu.cjc.sms.crud.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.sms.crud.app.model.Student;
import edu.cjc.sms.crud.app.service.StudentServicei;

@Controller
public class AdminController {

	@Autowired
	private StudentServicei ss;

	@RequestMapping("/")
	public String preLogin() {
		return "login";
	}

	@RequestMapping("/login")
	public String onLogin(@RequestParam("username") String us, @RequestParam("password") String ps, Model m) {
		if (us.equals("admin") && ps.equals("admin")) {

			List<Student> student = ss.getAllStudents();
			m.addAttribute("data", student);
			return "adminscreen";
		} else {
			m.addAttribute("login_fail", "Enter Valid Login Details.");
			return "login";
		}
	}

	@RequestMapping("/enroll_student")
	public String saveStudent(@ModelAttribute Student stu, Model m) {
		ss.saveStudentDetails(stu);
		List<Student> student = ss.getAllStudents();
		m.addAttribute("data", student);
		return "adminscreen";
	}

	@RequestMapping("/search")
	public String getBatchStudent(@RequestParam String batchNumber, Model m) {
		List<Student> result = ss.searchStudentByBatch(batchNumber);

		if (result.size() > 0) {
			m.addAttribute("data", result);
		} else {
			List<Student> student = ss.getAllStudents();
			m.addAttribute("data", student);
			m.addAttribute("message", "No Record are Available for the Batch'" + batchNumber + "'-----!!");
		}
		return "adminscreen";
	}

	@RequestMapping("/fees")
	public String onFees(@RequestParam int id, Model m) {
		Student st = ss.getSinglrStudent(id);
		m.addAttribute("st", st);
		return "fees";

	}

	@RequestMapping("/payfees")
	public String payFees(@RequestParam int studentId, @RequestParam double amount, Model m) {
		ss.updateStudentFees(studentId, amount);
		List<Student> student = ss.getAllStudents();
		m.addAttribute("data", student);
		return "adminscreen";

	}

	@RequestMapping("/remove")
	public String deleteStudent(@RequestParam("id") int id, Model m) {

		ss.deleteStudent(id);

		m.addAttribute("data", ss.getAllStudents());
		return "adminscreen";
	}

	@RequestMapping("/paging")
	public String pagingStudents(@RequestParam("pageNo") int pageNo, Model m) {

		List<Student> list = ss.pagingData(pageNo);
		m.addAttribute("data", list);
		return "adminscreen";
	}
}
