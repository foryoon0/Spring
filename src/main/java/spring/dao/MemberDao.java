package spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import spring.vo.Member;

public class MemberDao { 
	// 쿼리를 실행시킬 때 필요한 정보 => 연결객체, 쿼리, 결과를 받아줄 리스트,(?를 채우기 위한 매개값)
	
	// jdbcTemplete 이라는 객체를 이용해서 DB연결 제어  => .query();

	private JdbcTemplate jdbcTemplate;
	//private MapperSQLToMember mapper= new MapperSQLToMember();
	//private RowMapper<Member> mapper= new MapperSQLToMember();
	private RowMapper<Member> mapper= new RowMapper<Member>() {//익명 구현 객체

				@Override
				public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getTimestamp("regdate"));
						member.setId(rs.getLong("id"));
					return member;
				}   
		
		};
	
	public MemberDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Member> selectAll() { //jdbcTemplate 중요한 점 : 1.정보를 알아올 쿼리, 2. 정보를 옮겨담을 Mapper, 3. 정보를 담아줄 List
		String sql = "SELECT * FROM members ORDER BY id ASC";
		
		//List<Member> list= jdbcTemplate.query(sql, new MapperSQLToMember() );
//		List<Member> list= jdbcTemplate.query(sql, mapper );
//		List<Member> list= jdbcTemplate.query(sql, new RowMapper<Member>() {
//
//						@Override
//						public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//							Member member = new Member(
//									rs.getString("email"),
//									rs.getString("password"),
//									rs.getString("name"),
//									rs.getTimestamp("regdate"));
//							member.setId(rs.getLong("id"));
//						return member;
//						}
//			
//				});
		
		List<Member> list= jdbcTemplate.query(sql, (rs,rowNum)->{
									Member member = new Member(
											rs.getString("email"),
											rs.getString("password"),
											rs.getString("name"),
											rs.getTimestamp("regdate"));
									member.setId(rs.getLong("id"));
								return member;
						});
		
		return list;
	}
	
	
	
	public Member selectByEmail(String email) {
		
		List<Member> list= jdbcTemplate.query("SELECT * FROM members WHERE email=?", new RowMapper<Member>() {

							@Override
							public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
										Member member = new Member(
												rs.getString("email"),
												rs.getString("password"),
												rs.getString("name"),
												rs.getTimestamp("regdate"));
										member.setId(rs.getLong("id"));
									return member;
							}} ,email);
		
		return list.isEmpty()?null:list.get(0);
	}
	
	public int count() {  //어짜피 결과가 반드시 하나인 경우
		Integer cnt = jdbcTemplate.queryForObject(
				"SELECT count(*) FROM members", Integer.class);
		
		return cnt;
	}
	
	public void update(Member member) {
				//   DB에 정보를 삽입,수정,삭제 기능
		int cnt = jdbcTemplate.update("UPDATE members SET name=?, password=? WHERE email=?",
								member.getName(),
								member.getPassword(),
								member.getEmail());
		System.out.println("업데이트로 변경된 데이터의 개수 : "+cnt);
	}

//	public void insert(Member member) {       기존의 Insert 기능
//		int cnt = jdbcTemplate.update(new PreparedStatementCreator() {
//							// 미완성의 쿼리를 수동으로 완성시키는 기능
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				PreparedStatement psmt = con.prepareStatement(
//							"INSERT INTO members VALUES(members_seq.nextval,?,?,?,?)");
//				
//					psmt.setString(1,member.getEmail());
//					psmt.setString(2,member.getPassword());
//					psmt.setString(3,member.getName());
//					psmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
//				
//				return psmt;
//			}
//		});
//		
//		System.out.println("INSERT로 삽입된 데이터의 개수 : "+cnt);
//	}

	public void insert(Member member) {   // 시퀀스의 값을 미리 알아오기 =>KeyHolder
		KeyHolder keyholder = new GeneratedKeyHolder();
		
		int cnt = jdbcTemplate.update(new PreparedStatementCreator() {
							// 미완성의 쿼리를 수동으로 완성시키는 기능
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(
							"INSERT INTO members VALUES(members_seq.nextval,?,?,?,?)",new String[] {"id"});
				
					psmt.setString(1,member.getEmail());
					psmt.setString(2,member.getPassword());
					psmt.setString(3,member.getName());
					psmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
				
				return psmt;
			}
		},keyholder);
		
		Number keyNum = keyholder.getKey();
		System.out.println("넣게될 시퀀스 값 : "+keyNum.longValue());
		
		System.out.println("INSERT로 삽입된 데이터의 개수 : "+cnt);
	}
	
	///--------------------------------------------------------------------------
	// 날짜로 데이터 검색하기
	public List<Member> selectByRegdate(Date from, Date to){
		List<Member> list = jdbcTemplate.query(
				"SELECT * FROM members WHERE regdate BETWEEN ? AND ? ORDER BY REGDATE ASC ",
				new RowMapper<Member>() {

					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getTimestamp("regdate"));
						member.setId(rs.getLong("id"));
						return member;
					}
					
				},from,to);
		return list;
	}
	
	//-------------------------------------------------------------------
	// 아이디를 통해서 조회
	public Member selectById(Long id) {
		
		List<Member> list= jdbcTemplate.query("SELECT * FROM members WHERE id=?", new RowMapper<Member>() {

							@Override
							public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
										Member member = new Member(
												rs.getString("email"),
												rs.getString("password"),
												rs.getString("name"),
												rs.getTimestamp("regdate"));
										member.setId(rs.getLong("id"));
									return member;
							}} ,id);
		
		return list.isEmpty()?null:list.get(0);
	}
	
	
	
}









