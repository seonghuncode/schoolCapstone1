package com.ysh.exam.capstone.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ysh.exam.capstone.vo.Info;
import com.ysh.exam.capstone.vo.Room;

@Mapper
public interface InfoRepository {




//	@Select("SELECT * FROM info ORDER BY id ASC")
//	public List<Info> showAllinfo();

	
	//우선 두고 필요 없는 기능이면 없애기
	@Select("""
			SELECT A.*,
			M.roomName AS joinName
			FROM info AS A
			LEFT JOIN room AS M
			ON A.roomId = M.id
			ORDER BY A.id ASC
			""")
	public List<Info> showAllinfo();
	
	

	
	

	

	
	
	/*
		//INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = ?, body = ?
		//@Insert("INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = #{title}, `body` = #{body}")
		public void writeArticle(@Param("memberId") int memberId, @Param("title") String title,@Param("body") String body );
		//insert의 경우는 return을 해주지 않는다(write는 들어가는 값이기 때문에 리턴값이 없다)
		
		//(SELECT * FROM article WHERE id = ?) 이러한 쿼리가 실행이 되야 기능이 동작한다
		//@Select("SELECT * FROM article WHERE id = #{id}") // == 아래의 메서드가 실행 되면 이와 같은 쿼리문이 실행 된다 id는 아래의 id를 받는다. 실행하고 Article에 담아서 리턴을 한다
		public Article getArticle(@Param("id") int id);
		
		//(DELETE * FROM article WHERE id = ?)
		//@Delete("DELETE FROM article WHERE id = #{id}")
		public void deleteArticle(@Param("id") int id);
		
		//(UPDATE article SET title = ?, body = ?, updateDate = NOW() WHERE id = ?)
		//@Update("UPDATE article SET title = #{title}, `body` = #{body}, updateDate = NOW() WHERE id = #{id}")
		public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

		//(SELECT * FROM article ORDER BY id DESC)
		//@Select("SELECT * FROM article ORDER BY id DESC")
		public List<Article> getArticles();

		//위의 insert의 경우 리턴을 안해주기 때문에 mysql의 기능인 last_insert_id라는 기능으로 마지막에 insert한 id값을 가지고 온다
		//@Select("SELECT LAST_INSERT_ID()")
		public int getLastInsertId();
		
		*/

}
