package com.ysh.exam.capstone.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ysh.exam.capstone.vo.Member;



@Mapper
public interface MemberRepository {
	//아래의 @Insert는 자바 15버전의 문법이다.
	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNo = #{cellphoneNo},
			email = #{email}
			""")
	void join(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name, @Param("nickname") String nickname,
			@Param("cellphoneNo") String cellphoneNo, @Param("email") String email);

	//회원가입을 하고 결과 내용을 보여주가 위함
	@Select("SELECT LAST_INSERT_ID()")
	int getLastInsertId();

	@Select("""
			SELECT *
			FROM `member` AS M
			WHERE M.id = #{id}
			""") //AS M은 나중에 join이 들어올 수도 있기 때문에 미리 해둔 것이다.
	Member getMemberById(@Param("id") int id);
	
	
	
	@Select("""
			SELECT *
			FROM `member` AS M
			WHERE M.loginId = #{loginId}
			""")
	Member getMemberByLoginId(@Param("loginId") String loginId);

	@Select("""
			SELECT *
			FROM `member` AS M
			WHERE M.name = #{name}
			""")
	Member getMemberByName(@Param("name") String name);
	
	
	@Select("""
			SELECT *
			FROM `member` AS M
			WHERE M.email = #{email}
			""")
	Member getMemberByEmail(@Param("email") String email);
	
	@Select("""
			SELECT *
			FROM `member` AS M
			WHERE M.nickname = #{nickname}
			""")
	Member getMemberByNickName(@Param("nickname") String nickname);
	

		
	

}

