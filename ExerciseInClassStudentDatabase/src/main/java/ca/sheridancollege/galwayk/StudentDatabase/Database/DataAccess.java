package ca.sheridancollege.galwayk.StudentDatabase.Database;

import ca.sheridancollege.galwayk.StudentDatabase.Beans.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataAccess 
{
	protected NamedParameterJdbcTemplate jdbc;
	
	public DataAccess(NamedParameterJdbcTemplate jdbc)
	{
		this.jdbc = jdbc;
	}
	
	public List<Student> getStudentList()
	{
		String sqlQuery = "select * from Students;";
		List<Student> studentList = new ArrayList<Student>();
		
		List<Map<String, Object>> rows = 
			jdbc.queryForList(sqlQuery, new HashMap());
		
		for (Map<String, Object> row : rows)
		{
			Student student = new Student();
			student.setId((int)row.get("id"));
			student.setFirstName((String)row.get("firstName"));
			student.setLastName((String)row.get("lastName"));
			student.setProgram((String)row.get("program"));
			student.setCoop((boolean)row.get("coop"));
			student.setInternship((boolean)row.get("internship"));
			student.setSchoolYear((int)row.get("schoolYear"));
			
			studentList.add(student);
		}
		return studentList;
	}
	
	public String addStudent(Student student)
	{
		String sqlQuery;
		String queryType;
		if (student.getId() == 0) 
		{
			queryType = "inserted";
			sqlQuery = "insert into Students (firstName, lastName, "
				+ "schoolYear, program, coop, internship) VALUES (:firstName, "
				+ ":lastName, :schoolYear, :program, :coop, :internship);";
		}
		else 
		{
			queryType = "updated";
			sqlQuery = "update Students set firstName = :firstName, "
					+ "lastName = :lastName, program = :program, "
					+ "schoolYear = :schoolYear,"
					+ "coop = :coop, internship = :internship WHERE "
					+ "id = :id;";
		}
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("firstName", student.getFirstName())
		.addValue("lastName", student.getLastName())
		.addValue("schoolYear", student.getSchoolYear())
		.addValue("program", student.getProgram())
		.addValue("coop", student.isCoop())
		.addValue("internship", student.isInternship())
		.addValue("id", student.getId());
		
		jdbc.update(sqlQuery, params);
		return String.format("Successfully %s student %s %s", queryType, 
			student.getFirstName(), student.getLastName());
	}
	
	public Student getStudent(int id)
	{
		String sqlQuery = "Select * from Students where id = :id;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		List<Map<String, Object>> rows = jdbc.queryForList(sqlQuery, params);
		Map<String, Object> row = rows.get(0);
		
		Student student = new Student();
		student.setId((int)row.get("id"));
		student.setFirstName((String)row.get("firstName"));
		student.setLastName((String)row.get("lastName"));
		student.setProgram((String)row.get("program"));
		student.setCoop((boolean)row.get("coop"));
		student.setInternship((boolean)row.get("internship"));
		student.setSchoolYear((int)row.get("schoolYear"));
		
		return student;
	}
	
	public String deleteStudent(int id)
	{
		
		String sqlQuery = "Delete from Students where id = :id;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		jdbc.update(sqlQuery, params);
		
		return String.format("Successfully deleted student.");
	}
	
	public String deleteAll()
	{
		String sqlQuery = "Delete from Students;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		jdbc.update(sqlQuery, params);
		
		return "Successfully deleted all students.";
	}
}
