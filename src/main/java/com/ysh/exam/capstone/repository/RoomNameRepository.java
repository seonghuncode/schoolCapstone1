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
public interface RoomNameRepository {


	
	@Insert("INSERT INTO room SET regDate = NOW(), roomName = #{roomName}")
	public void writeRoomName(@Param("roomName") String roomname);
	
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

//	@Select("SELECT * FROM room WHERE roomName = #{roomName}")
	@Select("""
			SELECT A.*,
			M.pm AS joinPm,
			M.temperature AS joinTemperature,
			M.humadity AS joinHumadity
			FROM room AS A
			LEFT JOIN info AS M
			ON A.infoId = M.roomId
			WHERE roomName = #{roomName}
			""")
	public Room getRoom(@Param("roomName") String roomName);
	
	
	
	@Select("""
			SELECT A.*,
			M.pm AS joinPm,
			M.temperature AS joinTemperature,
			M.humadity AS joinHumadity,
			M.updateDate AS joinUpdateDate
			FROM room AS A
			LEFT JOIN info AS M
			ON A.infoId = M.roomId
			WHERE roomName = #{roomName}
			""")
	public List<Room> getRoomInfo(@Param("roomName") String roomName);


//	@Select("SELECT * FROM room ORDER BY id ASC")
//	public List<Room> getRooms();
	
	@Select("""
			SELECT A.*,
			M.pm AS joinPm,
			M.temperature AS joinTemperature,
			M.humadity AS joinHumadity
			FROM room AS A
			LEFT JOIN info AS M
			ON A.infoId = M.roomId
			ORDER BY A.id ASC
			""")
	public List<Room> getRooms();
	

	@Delete("DELETE FROM room WHERE roomName = #{roomName}")
	public void doDelete(@Param("roomName") String roomname);
	

	//방을 방들 경우 방 이름을 입력 했을때 해당 방에 대한 데이터가 다수 이면 하나의 값만 받아야 하는데 여러 값이 나오면서 getRoom에서 값을 담을 수 없어 오류가 발생
	//조인한 상태에서 찾은것이 아니라 room테이블 자체에서만 조회한다.(room자체해는 중복되는 방이 없기 때문)
	@Select("""
			SELECT *
			FROM room 
			WHERE roomName = #{roomName}
			""")
	public Room getSameRooms(@Param("roomName") String roomName);

	@Select("""
			UPDATE room SET roomName = #{roomnameNew} WHERE id = #{roomId}
			""")
	public void doModify(@Param("roomId") int roomId, @Param("roomnameNew") String roomnameNew);



	
	
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
