package com.cognizant.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public String insert(Student student) {
		String sqlInsert = "insert into student values(?,?)";
		int result = jdbc.update(sqlInsert, student.getId(), student.getName());
		if(result == 1) {
			return "SUCCESS";
		}else {
			return "FAILED";
		}
	}

	@Override
	public String delete(int id) {
		String sqlDelete = "delete from student where id = ?";
		int result = jdbc.update(sqlDelete, id);
		if(result == 1) {
			return "SUCCESS";
		}else {
			return "FAILED";
		}
	}

	@Override
	public String update(Student student) {
		String sqlUpdate = "update student set id = ?, name = ? where id = ?";
		int result = jdbc.update(sqlUpdate, student.getId(), student.getName(), student.getId());
		if(result == 1) {
			return "SUCCESS";
		}else {
			return "FAILED";
		}
	}

	@Override
	public List<Student> getAll() {
		StudentRowMapper rowMapper = new StudentRowMapper();
		String sql = "select * from student";
		List<Student> result = jdbc.query(sql, rowMapper);
		return result;
	}
}
