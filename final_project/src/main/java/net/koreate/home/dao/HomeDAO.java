package net.koreate.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import net.koreate.board.vo.BoardVO;
import net.koreate.home.vo.WishVO;
import net.koreate.qnaboard.vo.QnABoardVO;

public interface HomeDAO {

	@Select("SELECT * FROM qna_tbl ORDER BY qno DESC limit 1,5")
	List<QnABoardVO> QnaList();

	/*
	 * @Select("SELECT * FROM board ORDER BY bno DESC limit 1,5") List<BoardVO>
	 * BoardListSearch(BoardVO bvo);
	 */

	@Select("SELECT * FROM qna_tbl WHERE title LIKE CONCAT('%',#{title},'%') OR content LIKE CONCAT('%',#{content},'%') OR userNickname LIKE CONCAT('%',#{userNickname},'%') ORDER BY qno DESC limit 1,5")
	List<QnABoardVO> QnAListSearch(QnABoardVO qvo);

	
	@Select("SELECT * FROM board WHERE title LIKE CONCAT('%',#{title},'%') OR content LIKE CONCAT('%',#{content},'%') ORDER BY bno DESC limit 1,5")
	List<BoardVO> BoardListSearch(BoardVO bvo);

	/*
	 * @Select("SELECT B.*, U.uname AS writer FROM re_tbl_board AS B NATURAL JOIN tbl_user AS U WHERE B.bno = #{bno}"
	 * )
	 */
	@Select("SELECT B.* FROM board AS B NATURAL JOIN wish AS W WHERE uno = #{uno}")
	List<BoardVO> wish(WishVO wish);
}
