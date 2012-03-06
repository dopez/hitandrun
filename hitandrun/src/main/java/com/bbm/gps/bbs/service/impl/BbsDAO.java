package com.bbm.gps.bbs.service.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.bbs.service.BbsMemoVO;
import com.bbm.gps.bbs.service.BbsSearchVO;
import com.bbm.gps.bbs.service.BbsVO;

/** 
 * 게시판에 대한 데이터 접근 클래스를 정의한다
 * <p><b>NOTE:</b> 넘어온 요청에 대해 DB작업을 수행하는 메소드들의 집합
 * DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
 * @author 포탈통계 황기연 
 * @since 2011.06.21 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.09.16     황기연      최초 생성 
 * 
 * </pre> 
 */
@Repository("bbsDAO")
public class BbsDAO extends GpsAbstractDAO {
	/** 
	 * 메인페이지게시글목록조회
	 * @param bbsSearchVO 게시글검색 정보를 담고있는 VO
	 * @return 게시글목록
	 * @exception Exception
 	 */ 
	@SuppressWarnings("unchecked")
	public List<BbsVO> selectBbsIndexList(BbsSearchVO bbsSearchVO)throws Exception{
		return (List<BbsVO>)super.list("bbsDAO.selectBbsIndexList", bbsSearchVO);
	}
	
	/** 
	 * 게시글목록조회
	 * @param bbsSearchVO 게시글검색 정보를 담고있는 VO
	 * @return 게시글목록
	 * @exception Exception
 	 */ 
	@SuppressWarnings("unchecked")
	public List<BbsVO> selectBbsList(BbsSearchVO bbsSearchVO)throws Exception{
		return (List<BbsVO>)super.listPaging("bbsDAO.selectBbsList", bbsSearchVO, bbsSearchVO.getFirstIndex(), bbsSearchVO.getRecordCountPerPage());
	}
	
	/** 
	 * 게시글목록 총 갯수 조회
	 * @param bbsSearchVO 게시글검색 정보를 담고있는 VO
	 * @return 게시글목록
	 * @exception Exception
 	 */ 
	public int selectBbsListTotCnt(BbsSearchVO bbsSearchVO)throws Exception{
		return (Integer)super.selectByPk("bbsDAO.selectBbsListTotCnt", bbsSearchVO);
	}
	
	/** 
	 * 게시글상세조회시 조회수 증가
	 * @param bbsSearchVO 게시글 정보를 담고있는 VO
	 * @return 
	 * @exception Exception
 	 */
	public void updateCo(BbsSearchVO bbsSearchVO)throws Exception{
		super.update("bbsDAO.updateCo",bbsSearchVO);
	}
	
	/** 
	 * 게시글상세조회
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return 게시글상세내용
	 * @exception Exception
 	 */
	public BbsVO selectBbs(BbsSearchVO bbsSearchVO)throws Exception{
		return (BbsVO)super.selectByPk("bbsDAO.selectBbs",bbsSearchVO);
	}
	
	/** 
	 * 공지사항 등록가능 여부
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return Y,N
	 * @exception Exception
 	 */
	public String noticeWriteAt(BbsVO bbsVO)throws Exception{
		return (String)super.selectByPk("bbsDAO.noticeWriteAt",bbsVO);
	}
	
	/** 
	 * 게시글등록처리
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return 
	 * @exception Exception
 	 */ 
	public void insertBbs(BbsVO bbsVO)throws Exception{
		super.insert("bbsDAO.insertBbs",bbsVO);
	}
	
	/** 
	 * 게시글수정처리
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */ 
	public void updateBbs(BbsVO bbsVO)throws Exception{
		super.update("bbsDAO.updateBbs",bbsVO);
	}
	
	/** 
	 * 게시글삭제처리
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */ 
	public int deleteBbs(BbsSearchVO bbsSearchVO)throws Exception{
		return (Integer)super.delete("bbsDAO.deleteBbs",bbsSearchVO);
	}
	
	/** 
	 * 게시글삭제처리시 관련된 메모글 모두 삭제
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */ 
	public void deleteAllBbsMemo(BbsVO bbsVO)throws Exception{
		super.delete("bbsDAO.deleteAllBbsMemo",bbsVO);
	}
	
	/** 
	 * 게시글관련 메모목록
	 * @param bbsSearchVO 게시글 검색 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */ 
	@SuppressWarnings("unchecked")
	public List<BbsMemoVO> selectBbsMemoList(BbsSearchVO bbsSearchVO)throws Exception{
		return (List<BbsMemoVO>)super.list("bbsDAO.bbsMemoList",bbsSearchVO);
	}
	
	/** 
	 * 게시글관련 메모 등록
	 * @param bbsMemoVO 게시글 메모 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */
	public void insertBbsMemo(BbsMemoVO bbsMemoVO)throws Exception{
		super.insert("bbsDAO.insertBbsMemo",bbsMemoVO);
	}
	
	/** 
	 * 메모삭제시 비밀번호 확인
	 * @param bbsMemoVO 게시글 메모 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */
	public int memoPasswordConfirm(BbsMemoVO bbsMemoVO)throws Exception{
		return (Integer)super.selectByPk("bbsDAO.memoPasswordConfirm",bbsMemoVO);
	}
	
	/** 
	 * 게시글관련 메모 삭제
	 * @param bbsMemoVO 게시글 메모 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */
	public void deleteBbsMemo(BbsMemoVO bbsMemoVO)throws Exception{
		super.delete("bbsDAO.deleteBbsMemo",bbsMemoVO);
	}
}
