package com.bbm.gps.bbs.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;
/** 
 * 게시판에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b>해당 클래스에는 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 BbsManageServiceImpl에 되어있다
 * @author 포탈통계 이관형 
 * @since 2011.06.21 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.21     이관형      최초 생성 
 * 
 * </pre> 
 */
public interface BbsService {
	/** 
	 * 메인페이지게시글목록조회
	 * @param bbsSearchVO 게시글검색 정보를 담고있는 VO
	 * @return 게시글목록
	 * @exception Exception
 	 */ 
	public List<BbsVO> selectBbsIndexList(BbsSearchVO bbsSearchVO)throws Exception;
	
	/** 
	 * 게시글목록조회
	 * @param bbsSearchVO 게시글검색 정보를 담고있는 VO
	 * @return 게시글목록
	 * @exception Exception
 	 */
	public List<BbsVO> selectBbsList(BbsSearchVO bbsSearchVO)throws Exception;
	
	/** 
	 * 게시글목록 총 갯수 조회
	 * @param bbsSearchVO 게시글검색 정보를 담고있는 VO
	 * @return 게시글목록
	 * @exception Exception
 	 */ 
	public int selectBbsListTotCnt(BbsSearchVO bbsSearchVO)throws Exception;
	
	/** 
	 * 게시글상세조회시 조회수 증가
	 * @param bbsSearchVO 게시글 정보를 담고있는 VO
	 * @return 
	 * @exception Exception
 	 */
	public void updateCo(BbsSearchVO bbsSearchVO)throws Exception;
	
	/** 
	 * 게시글상세조회
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return 게시글상세내용
	 * @exception Exception
 	 */
	public BbsVO selectBbs(BbsSearchVO bbsSearchVO)throws Exception;
	
	/** 
	 * 공지사항등록가능여부
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return Y,N
	 * @exception Exception
 	 */
	public String noticeWriteAt(BbsVO bbsVO)throws Exception;
	
	/** 
	 * 게시글등록처리
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return 
	 * @exception Exception
 	 */ 
	public void insertBbs(BbsVO bbsVO,MultipartHttpServletRequest multiRequest)throws Exception;
	
	/** 
	 * 게시글수정처리
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */ 
	public void updateBbs(BbsVO bbsVO,MultipartHttpServletRequest multiRequest)throws Exception;
	
	/** 
	 * 게시글삭제처리
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */ 
	public void deleteBbs(BbsVO bbsVO)throws Exception;
	
	/** 
	 * 게시글관련 메모목록
	 * @param bbsSearchVO 게시글 검색 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */
	public List<BbsMemoVO> selectBbsMemoList(BbsSearchVO bbsSearchVO)throws Exception;
	
	/** 
	 * 게시글관련 메모 등록
	 * @param bbsMemoVO 게시글 메모 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */
	public void insertBbsMemo(BbsMemoVO bbsMemoVO)throws Exception;
	
	/** 
	 * 메모삭제시 비밀번호 확인
	 * @param bbsMemoVO 게시글 메모 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */
	public boolean memoPasswordConfirm(BbsMemoVO bbsMemoVO)throws Exception;
	
	/** 
	 * 게시글관련 메모 삭제
	 * @param bbsMemoVO 게시글 메모 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */
	public void deleteBbsMemo(BbsMemoVO bbsMemoVO)throws Exception;
	
	/**
	 * 게시판 아이디 패턴이외의 문자 제거
	 * @param str
	 * @return 바뀌어진 값을 넘겨준다.
	 */
	public String bbsIdReplace(String str);
}
