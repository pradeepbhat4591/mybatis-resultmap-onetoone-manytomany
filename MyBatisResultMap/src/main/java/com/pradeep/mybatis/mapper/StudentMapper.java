package com.pradeep.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.pradeep.mybatis.model.Address;
import com.pradeep.mybatis.model.Student;

public interface StudentMapper {

	@Select("SELECT ID ,STUDENTID, STREET, CITY, STATE, ZIP, COUNTRY FROM address WHERE STUDENTID=#{id}")
	Address findAddressById(int id);

	@Select("SELECT * FROM student WHERE ID=#{studId} ")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "name", property = "name"),
			@Result(column = "email", property = "email"),
			@Result(column = "phone", property = "phone"),
			@Result(column = "percentage", property = "percentage"),
			@Result(column = "branch", property = "branch"),
			@Result(property = "address", column = "id", one = @One(select = "com.pradeep.mybatis.mapper.StudentMapper.findAddressById")) })
	Student selectStudentWithAddress(int studId);
	
	
	@Select("SELECT * FROM student WHERE ID=#{studId} ")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "name", property = "name"),
			@Result(column = "email", property = "email"),
			@Result(column = "phone", property = "phone"),
			@Result(column = "percentage", property = "percentage"),
			@Result(column = "branch", property = "branch"),
			@Result(property = "addresses", column = "id", many = @Many(select = "com.pradeep.mybatis.mapper.StudentMapper.findAddressesByStudentId")) })
	Student selectStudentWithAddresses(int studId);
	
	@Select("SELECT ID ,STUDENTID, STREET, CITY, STATE, ZIP, COUNTRY FROM address WHERE STUDENTID=#{id}")
	List<Address> findAddressesByStudentId(int id);

}
