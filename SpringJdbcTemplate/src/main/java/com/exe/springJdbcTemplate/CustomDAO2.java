package com.exe.springJdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


//Spring의 JDBC
public class CustomDAO2 {
	
	//JdbcTemplate
	private JdbcTemplate jdbcTemplate;
	
	//의존성 주입 - 이 메서드가 app-context.xml로 가서 프로퍼티 네임에 set이 빼고 들어감
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
//------------------------------------------------------------------	
	//NamedParameterJdbcTemplate - (insert만 이 템플릿으로 할 것이다,)
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	//의존성 주입 - 이 메서드가 app-context.xml로 가서 프로퍼티 네임에 set이 빼고 들어감
	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		
		this.namedJdbcTemplate = namedParameterJdbcTemplate;
	}
	

	Connection conn = null;
	
	//데이터 저장
	public void insertData(CustomDTO dto) {
		
		StringBuilder sql = new StringBuilder(); 
		
		//JdbcTemplate사용	
		sql.append("insert into custom (id,name,age) values (?,?,?)");
	
		jdbcTemplate.update(sql.toString(),dto.getId(),dto.getName(),dto.getAge());
	
		//-----------------------------------------------------------------------------------
		
		/*
		 * //NamedParameterJdbcTemplate사용
		 * sql.append("insert into custom (id,name,age) values (:id,:name,:age)");
		 * 
		 * //애는 JdbcTemplate과 다르게 바로못쓴다. 객체가 있다 (모델 엔 뷰와 비슷) MapSqlParameterSource
		 * params = new MapSqlParameterSource();
		 * 
		 * params.addValue("id", dto.getId()); // "id"에 dto.getId를 넣는다
		 * params.addValue("name", dto.getName()); params.addValue("age", dto.getAge());
		 * 
		 * namedJdbcTemplate.update(sql.toString(), params);
		 */
		
	}
	
	//데이터 수정
	public void updateData(CustomDTO dto) {
		
		StringBuffer sql = new StringBuffer();//버퍼보단 빌더가 빠르다.
		
		sql.append("update custom set name=?,age=? where id=?");
		
		jdbcTemplate.update(sql.toString(),dto.getName(),dto.getAge(),dto.getId());
			
	}
	
	//데이터 삭제
	public void deleteData(String id) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete custom where id=?");
		
		jdbcTemplate.update(sql.toString(),id);
			
		
	}
	
	//모든 데이터 출력
	public List<CustomDTO> getList() {
		
		StringBuilder sql = new StringBuilder();
			
		sql.append("select id,name,age from custom");
		
		//RowMapper(인터페이스) : ResultSet과 같은 것
		List<CustomDTO> lists =  //sql.toString() : 쿼리를 문자화 한것
				jdbcTemplate.query(sql.toString(), 
						new RowMapper<CustomDTO>() {
					//mapRow : while문과 같은 역할을 해준다.
					public CustomDTO mapRow(ResultSet rs, int rowNum) //rowNum:index번호
						throws SQLException {
						
						CustomDTO dto = new CustomDTO();
						
						dto.setId(rs.getString("id"));
						dto.setName(rs.getString("name"));
						dto.setAge(rs.getInt("age"));
						
						return dto;
					}
					
			});
		
		return lists;
					
	}
	
	//하나의 데이터 출력
	public CustomDTO getReadData(String id) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select id,name,age from custom where id=?");
		
		CustomDTO dtoOne = 
				jdbcTemplate.queryForObject(sql.toString(),
						new RowMapper<CustomDTO>() {

							public CustomDTO mapRow(ResultSet rs, int rowNum) 
									throws SQLException {
								
								CustomDTO dto = new CustomDTO();
								
								dto.setId(rs.getString("id"));
								dto.setName(rs.getString("name"));
								dto.setAge(rs.getInt("age"));
								
								return dto;
							}
					
				},id);
		
		return dtoOne;	
					
	}
	
}