package com.bbm.gps.adm.bbs.service;

import com.bbm.gps.adm.menu.service.SystemVO;
import com.bbm.gps.adm.user.service.UserManageVO;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/** 
 * 게시판관리에 대한 서비스 인터페이스 클래스를 정의한다. 
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

public interface BbsManageService {
	
	/**
	 * 게시판 DB목록 조회
	 * @param bbsDbVO
	 * @return List
	 * @throws Exception
	 * @see DB_TNAME, DB_NAME,DB_COMMENT,DB_MNGR_NM,DB_MNGR_ID,DB_MNGR_PASSWORD,REGISTER_ID,REGISTER_IP,REGIST_DT,UPDT_DT
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public List<BbsDbVO> selectBbsDbList(BbsDbVO bbsDbVO)throws Exception;

	/**
	 * 게시판 DB목록 상세보기
	 * @param bbsDbVO
	 * @return BbsDbVO
	 * @throws Exception
	 * @see DB_TNAME, DB_NAME,DB_COMMENT,DB_MNGR_NM,DB_MNGR_ID,DB_MNGR_PASSWORD,REGISTER_ID,REGISTER_IP,REGIST_DT,UPDT_DT
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public BbsDbVO selectBbsDb(BbsDbVO bbsDbVO)throws Exception;
	
	/**
	 * 관리자목록
	 * @param userManageVO
	 * @return List
	 * @throws Exception
	 * @see	userId,nm,email
	 * @see TABLE NAME : TN_USER
	 */
	public List<UserManageVO> selectMngrList(UserManageVO userManageVO)throws Exception;
	
	/**
	 * 게시판 DB명 중복확인
	 * @param bbsDbVO
	 * @return int
	 * @throws Exception
	 * @see COUNT(DB_NAME)
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public boolean dbNameDuplicateAt(BbsDbVO bbsDbVO)throws Exception;
	
	/**
	 * 게시판 DB목록 등록
	 * @param bbsDbVO
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public void insertBbsDb(BbsDbVO bbsDbVO)throws Exception;

	/**
	 * 게시판 DB목록 수정
	 * @param bbsDbVO
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public void updateBbsDb(BbsDbVO bbsDbVO)throws Exception;

	/**
	 * 게시판 DB목록 삭제
	 * @param bbsDbVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public void deleteBbsDb(BbsDbVO bbsDbVO)throws Exception;
	
	/**
	 * 시스템목록
	 * @param bbsSearchVO
	 * @return List
	 * @throws Exception
	 * @see	sysId,sysNm
	 * @see TABLE NAME : TN_SYSTEM ,TN_AUTHOR ,TN_USER_AUTHOR 
	 */
	public List<SystemVO> selectSystemList(BbsSearchVO bbsSearchVO)throws Exception;
	
	/** 
	 * 게시판 설정 목록 조회
	 * @param bbsSearchVO 게시판설정 정보를 담고있는 VO
	 * @return 게시판설정목록
	 * @throws Exception 
	 * @see BBS_ID,  DB_TNAME,  BBS_NM,  BBS_TITLE,  BBS_DC,  BBS_MNGR_NM,  SKIN_INFO,  TITLE_IMAGE,  
	 * @see TABLE_SIZE,  NEW_ICON_IMAGE,  NEW_ICON_INDICT_PD,  COOL_ICON_IMAGE,  COOL_ICON_INDICT_RDCNT,  
	 * @see HOT_ICON_IMAGE,  HOT_ICON_INDICT_RDCNT,  SUBPAGE_ID,  CTGRY_CODE,  LOGIN_USE_AT,  ANSWER_USE_AT,  
	 * @see WEBEDITOR_USE_AT,  MEMO_USE_AT,  AVATA_USE_AT,  RECOMMEND_USE_AT,  TITLE_DECO_USE_AT,  LIST_USE_AT,  
	 * @see RELATE_USE_AT,  ARND_USE_AT,  NTCE_PD_USE_AT,  NTCE_TRGET_USE_AT,  PASSWORD_USE_AT,  NCM_USE_AT,  
	 * @see FILE_UPLOAD_USE_AT,  ALBUM_AT,  ALBUM_STLE_AT,  EMAIL_SNDNG_AT,  UPLOAD_FILE_CO,  PGE_LIST_CO,  
	 * @see PGE_GROUP_CO,  NOTICE_CO,  ALBUM_COLUMN_CO,  ALBUM_LINE_CO,  LIST_EX_AUTHOR,  BDT_REDNG_AUTHOR,  BDT_WRITE_AUTHOR,  ANSWER_WRITE_AUTHOR,  
	 * @see MEMO_WRITE_AUTHOR,  WEBEDITOR_WRITE_AUTHOR,  REGISTER_IP,  REGIST_DT,  REGISTER_ID,  UPDT_DT
	 * @see TABLE NAME : TN_BBS_INFO
 	 */ 
	public List<BbsInfoVO> selectBbsInfoList(BbsSearchVO bbsSearchVO)throws Exception;
	
	/**
	 * 게시판 설정 목록 총 갯수
	 * @param bbsSearchVO
	 * @return int
	 * @throws Exception
	 * @see cnt
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public int selectBbsInfoListTotCnt(BbsSearchVO bbsSearchVO)throws Exception;
	
	/**
	 * 게시판 설정 EXCEL목록
	 * @param bbsSearchVO
	 * @return List
	 * @throws Exception
	 * @see 게시판ID,게시판명,게시판관리자ID,게시판관리자명,등록일,등록자ID
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public List<BbsInfoVO> selectBbsInfoExcelList(BbsSearchVO bbsSearchVO)throws Exception;
	
	/** 
	 * 게시판 설정목록 상세보기
	 * @param bbsInfoVO
	 * @return BbsInfoVO
	 * @throws Exception
	 * @see BBS_ID,  DB_TNAME,  BBS_NM,  BBS_TITLE,  BBS_DC,  BBS_MNGR_NM,  SKIN_INFO,  TITLE_IMAGE,  
	 * @see TABLE_SIZE,  NEW_ICON_IMAGE,  NEW_ICON_INDICT_PD,  COOL_ICON_IMAGE,  COOL_ICON_INDICT_RDCNT,  
	 * @see HOT_ICON_IMAGE,  HOT_ICON_INDICT_RDCNT,  SUBPAGE_ID,  CTGRY_CODE,  LOGIN_USE_AT,  ANSWER_USE_AT,  
	 * @see WEBEDITOR_USE_AT,  MEMO_USE_AT,  AVATA_USE_AT,  RECOMMEND_USE_AT,  TITLE_DECO_USE_AT,  LIST_USE_AT,  
	 * @see RELATE_USE_AT,  ARND_USE_AT,  NTCE_PD_USE_AT,  NTCE_TRGET_USE_AT,  PASSWORD_USE_AT,  NCM_USE_AT,  
	 * @see FILE_UPLOAD_USE_AT,  ALBUM_AT,  ALBUM_STLE_AT,  EMAIL_SNDNG_AT,  UPLOAD_FILE_CO,  PGE_LIST_CO,  
	 * @see PGE_GROUP_CO,  NOTICE_CO,  ALBUM_COLUMN_CO,  ALBUM_LINE_CO,  LIST_EX_AUTHOR,  BDT_REDNG_AUTHOR,  BDT_WRITE_AUTHOR,  ANSWER_WRITE_AUTHOR,  
	 * @see MEMO_WRITE_AUTHOR,  WEBEDITOR_WRITE_AUTHOR,  REGISTER_IP,  REGIST_DT,  REGISTER_ID,  UPDT_DT
	 * @see TABLE NAME : TN_BBS_INFO
 	 */ 
	public BbsInfoVO selectBbsInfo(BbsInfoVO bbsInfoVO)throws Exception;
	
	/**
	 * 게시판명 중복확인
	 * @param bbsInfoVO
	 * @return int
	 * @throws Exception
	 * @see COUNT(BBS_NM)
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public boolean bbsNameDuplicateAt(BbsInfoVO bbsInfoVO)throws Exception;
	
	/**
	 * 게시판 기본 정보 등록
	 * @param bbsInfoVO
	 * @return String
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public void insertBbsInfo(BbsInfoVO bbsInfoVO)throws Exception;
	
	/**
	 * 게시판 기본 정보 수정
	 * @param bbsInfoVO
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public void updateBbsInfo(BbsInfoVO bbsInfoVO,MultipartHttpServletRequest multiRequest)throws Exception;
	
	/**
	 * 게시판 설정 목록 삭제
	 * @param bbsInfoVO
	 * @return int
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public void deleteBbsInfo(BbsInfoVO bbsInfoVO)throws Exception;
	
	/** 
	 * 게시판 스킨 목록
	 * @return List
	 * @throws Exception
 	 */ 
	public List<String> bbsSkinList()throws Exception;
	
	/**
	 * 링크될 메뉴가 다른 게시판과 링크되어있는지 검색
	 * @param bbsInfoVO
	 * @return BbsInfoVO
	 * @throws Exception
	 * @see bbsId , bbsNm , subpageNm 
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public BbsInfoVO selectSameSubpageId(BbsInfoVO bbsInfoVO)throws Exception;
}
