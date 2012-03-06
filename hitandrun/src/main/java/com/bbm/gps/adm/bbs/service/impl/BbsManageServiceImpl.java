package com.bbm.gps.adm.bbs.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import com.bbm.common.cmm.service.NaraCmmService;
import com.bbm.common.util.sim.service.EgovFileScrty;
import com.bbm.gps.adm.bbs.service.BbsDbVO;
import com.bbm.gps.adm.bbs.service.BbsInfoVO;
import com.bbm.gps.adm.bbs.service.BbsManageService;
import com.bbm.gps.adm.bbs.service.BbsSearchVO;
import com.bbm.gps.adm.menu.service.SystemVO;
import com.bbm.gps.adm.user.service.UserManageVO;
import com.bbm.gps.bbs.service.impl.BbsDAO;

/** 
 * 게시판관리에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b>서비스에 선언 되어있는 메소드들의 구현 클래스로 데이터 접근 클래스의 메소드를 호출한다
 * 메소드들 중에는 parameter를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다
 * @author 범정부통계포털 이관형 
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
@Service("bbsManageService")
public class BbsManageServiceImpl extends AbstractServiceImpl implements BbsManageService {

	/** bbsManageDAO 서비스 호출 */ 
	@Resource(name="bbsManageDAO")
    private BbsManageDAO bbsManageDAO;
	
	/** bbsDAO 서비스 호출 */ 
	@Resource(name="bbsDAO")
    private BbsDAO bbsDAO;
	
	/** NaraCmmService 호출 */ 
	@Resource(name="NaraCmmService")
	private NaraCmmService naraCmmService;
	
	/** propertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * 게시판 DB목록 조회
	 * @param bbsDbVO
	 * @return List
	 * @throws Exception
	 * @see DB_TNAME, DB_NAME,DB_COMMENT,DB_MNGR_NM,DB_MNGR_ID,DB_MNGR_PASSWORD,REGISTER_ID,REGISTER_IP,REGIST_DT,UPDT_DT
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public List<BbsDbVO> selectBbsDbList(BbsDbVO bbsDbVO)throws Exception{
		return (List<BbsDbVO>)this.bbsManageDAO.selectBbsDbList(bbsDbVO);
	}

	/**
	 * 게시판 DB목록 상세보기
	 * @param bbsDbVO
	 * @return BbsDbVO
	 * @throws Exception
	 * @see DB_TNAME, DB_NAME,DB_COMMENT,DB_MNGR_NM,DB_MNGR_ID,DB_MNGR_PASSWORD,REGISTER_ID,REGISTER_IP,REGIST_DT,UPDT_DT
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public BbsDbVO selectBbsDb(BbsDbVO bbsDbVO)throws Exception{
		return (BbsDbVO)this.bbsManageDAO.selectBbsDb(bbsDbVO);
	}
	
	/**
	 * 관리자목록
	 * @param userManageVO
	 * @return List
	 * @throws Exception
	 * @see	userId,nm,email
	 * @see TABLE NAME : TN_USER
	 */
	public List<UserManageVO> selectMngrList(UserManageVO userManageVO)throws Exception{
		List<UserManageVO> list = (List<UserManageVO>)this.bbsManageDAO.selectMngrList(userManageVO);
		if(list.size() > 0){
			for (UserManageVO vo : list) {
				vo.setEmail(EgovFileScrty.decode(vo.getEmail()));
			}
		}
		return list;
	}
	
	/**
	 * 게시판 DB명 중복확인
	 * @param bbsDbVO
	 * @return int
	 * @throws Exception
	 * @see COUNT(DB_NAME)
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public boolean dbNameDuplicateAt(BbsDbVO bbsDbVO)throws Exception{
		if((Integer)this.bbsManageDAO.dbNameDuplicateAt(bbsDbVO) > 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 게시판 DB목록 등록
	 * @param bbsDbVO
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public void insertBbsDb(BbsDbVO bbsDbVO)throws Exception{
		this.bbsManageDAO.insertBbsDb(bbsDbVO);
	}

	/**
	 * 게시판 DB목록 수정
	 * @param bbsDbVO
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public void updateBbsDb(BbsDbVO bbsDbVO)throws Exception{
		this.bbsManageDAO.updateBbsDb(bbsDbVO);
	}

	/**
	 * 게시판 DB목록 삭제
	 * @param bbsDbVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_DB
	 */
	public void deleteBbsDb(BbsDbVO bbsDbVO)throws Exception{
		//게시판DB삭제
		if(this.bbsManageDAO.deleteBbsDb(bbsDbVO) > 0){
			//게시판DB 하위의 게시판 목록 검색
			List<BbsInfoVO> bbsIdList = this.bbsManageDAO.selectBbsIdList(bbsDbVO);
			if(bbsIdList.size() > 0){
				//목록이있으면 모두 삭제
				for (int i = 0; i < bbsIdList.size(); i++) {
					BbsInfoVO bbsInfoVO  = new BbsInfoVO();
					bbsInfoVO = (BbsInfoVO)bbsIdList.get(i);
					this.deleteBbsInfo(bbsInfoVO);
				}
			}
		}
	}
	
	/**
	 * 시스템목록
	 * @param bbsSearchVO
	 * @return List
	 * @throws Exception
	 * @see	sysId,sysNm
	 * @see TABLE NAME : TN_SYSTEM ,TN_AUTHOR ,TN_USER_AUTHOR 
	 */
	public List<SystemVO> selectSystemList(BbsSearchVO bbsSearchVO)throws Exception{
		return (List<SystemVO>)this.bbsManageDAO.selectSystemList(bbsSearchVO);
	}
	
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
	public List<BbsInfoVO> selectBbsInfoList(BbsSearchVO bbsSearchVO)throws Exception{
		List<BbsInfoVO> bbsInfoList = (List<BbsInfoVO>)this.bbsManageDAO.selectBbsInfoList(bbsSearchVO);
		if(bbsInfoList.size() > 0){
			for (BbsInfoVO bbsInfoVO : bbsInfoList) {
				com.bbm.gps.bbs.service.BbsSearchVO searchVO = new com.bbm.gps.bbs.service.BbsSearchVO();
				searchVO.setBbsId(bbsInfoVO.getBbsId());
				bbsInfoVO.setBbsTotCnt((Integer)this.bbsDAO.selectBbsListTotCnt(searchVO));//게시글 총갯수
			}
		}
		return bbsInfoList;
	}
	
	/**
	 * 게시판 설정 목록 총 갯수
	 * @param bbsSearchVO
	 * @return int
	 * @throws Exception
	 * @see cnt
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public int selectBbsInfoListTotCnt(BbsSearchVO bbsSearchVO)throws Exception{
		return (Integer)this.bbsManageDAO.selectBbsInfoListTotCnt(bbsSearchVO);
	}
	
	/**
	 * 게시판 설정 EXCEL목록
	 * @param bbsSearchVO
	 * @return List
	 * @throws Exception
	 * @see 게시판ID,게시판명,게시판관리자ID,게시판관리자명,등록일,등록자ID
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public List<BbsInfoVO> selectBbsInfoExcelList(BbsSearchVO bbsSearchVO)throws Exception{
		return (List<BbsInfoVO>)this.bbsManageDAO.selectBbsInfoExcelList(bbsSearchVO);
	}
	
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
	public BbsInfoVO selectBbsInfo(BbsInfoVO bbsInfoVO)throws Exception{
		return (BbsInfoVO)this.bbsManageDAO.selectBbsInfo(bbsInfoVO);
	}
	
	/**
	 * 게시판명 중복확인
	 * @param bbsInfoVO
	 * @return int
	 * @throws Exception
	 * @see COUNT(BBS_NM)
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public boolean bbsNameDuplicateAt(BbsInfoVO bbsInfoVO)throws Exception{
		if((Integer)this.bbsManageDAO.bbsNameDuplicateAt(bbsInfoVO) > 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 게시판 기본 정보 등록
	 * @param bbsInfoVO
	 * @return String
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public void insertBbsInfo(BbsInfoVO bbsInfoVO)throws Exception{
		String bbsId = this.bbsManageDAO.insertBbsInfo(bbsInfoVO);
		//게시판등록시 게시글테이블,인덱스,메모테이블 생성
		if(bbsId != null && !bbsId.equals("")){
			Map<String,String> map = new HashMap<String, String>();
			map.put("bbsTableNm","TN_"+bbsId+"_BBS");
			map.put("bbsConstraintNm","XPK_TN_"+bbsId+"_BBS");
			map.put("indexNm1","XIE1_TN_"+bbsId+"_BBS");
			map.put("indexNm2","XIE2_TN_"+bbsId+"_BBS");
			map.put("memoTableNm","TN_"+bbsId+"_MEMO");
			map.put("memoConstraintNm","XPK_TN_"+bbsId+"_MEMO");
			map.put("tableSpaceNm", "TS_GPS_D01");
			
			this.bbsManageDAO.createBbs(map);
			this.bbsManageDAO.createIndex1(map);
			this.bbsManageDAO.createIndex2(map);
			this.bbsManageDAO.createMemo(map);
		}
	}
	
	/**
	 * 게시판 기본 정보 수정
	 * @param bbsInfoVO
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public void updateBbsInfo(BbsInfoVO bbsInfoVO,MultipartHttpServletRequest multiRequest)throws Exception{
		MultipartFile file = null;
		String _atchFileId = "";
		
		// 타이틀이미지
    	file = multiRequest.getFile("file_1");
    	if(file!=null){
    		_atchFileId = this.naraCmmService.insertAtchFile(multiRequest, file,  "BBS_",  "", propertiesService.getString("GPS.bbsPath"));
    	}
	    	if( !"".equals( _atchFileId )) {
	    		bbsInfoVO.setTitleImage(_atchFileId);
	    	}else{
	    		bbsInfoVO.setTitleImage(bbsInfoVO.getTitleImage());
	    	}

    	// new 이미지
    	file = multiRequest.getFile("file_2");
    	if(file!=null){
    		_atchFileId = this.naraCmmService.insertAtchFile(multiRequest, file,  "BBS_",  "", propertiesService.getString("GPS.bbsPath"));
    	}
    	if( !"".equals( _atchFileId )) {
    		bbsInfoVO.setNewIconImage(_atchFileId);
    	}else{
    		bbsInfoVO.setNewIconImage(bbsInfoVO.getNewIconImage());
    	}
    	
    	// cool 이미지
    	file = multiRequest.getFile("file_3");
    	if(file!=null){
    		_atchFileId = this.naraCmmService.insertAtchFile(multiRequest, file,  "BBS_",  "", propertiesService.getString("GPS.bbsPath"));
    	}
    	if( !"".equals( _atchFileId )) {
    		bbsInfoVO.setCoolIconImage(_atchFileId);
    	}else{
    		bbsInfoVO.setCoolIconImage(bbsInfoVO.getCoolIconImage());
    	}
    	
    	// hot 이미지
    	file = multiRequest.getFile("file_4");
    	if(file!=null){
    		_atchFileId = this.naraCmmService.insertAtchFile(multiRequest, file,  "BBS_",  "", propertiesService.getString("GPS.bbsPath"));
    	}
    	if( !"".equals( _atchFileId )) {
    		bbsInfoVO.setHotIconImage(_atchFileId);
    	}else{
    		bbsInfoVO.setHotIconImage(bbsInfoVO.getHotIconImage());
    	}
    	
		this.bbsManageDAO.updateBbsInfo(bbsInfoVO);
		
		//적용페이지가있으면 메뉴타입 게시판으로 변경
		if(bbsInfoVO.getSubpageId() != null && !bbsInfoVO.getSubpageId().equals("")){
			//적용페이지의 게시판ID 메뉴에등록
    		this.bbsManageDAO.updateMenuBbsId(bbsInfoVO);
    	}
	}
	
	/**
	 * 게시판 설정 목록 삭제
	 * @param bbsInfoVO
	 * @return int
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public void deleteBbsInfo(BbsInfoVO bbsInfoVO)throws Exception{
		if(this.bbsManageDAO.deleteBbsInfo(bbsInfoVO) > 0){
			Map<String,String> map = new HashMap<String, String>();
			map.put("bbsTableNm","TN_"+bbsInfoVO.getBbsId()+"_BBS");
			map.put("memoTableNm","TN_"+bbsInfoVO.getBbsId()+"_MEMO");
			
			//게시판 삭제시 게시글,메모테이블 drop
			this.bbsManageDAO.dropBbs(map);
			this.bbsManageDAO.dropMemo(map);
		}
	}
	
	/** 
	 * 게시판 스킨 목록
	 * @return List
	 * @throws Exception
 	 */ 
	public List<String> bbsSkinList()throws Exception{
		List<String> bbsSkinList = new ArrayList<String>();
		String bbsSkinPath = this.propertiesService.getString("GPS.bbsSkinPath");
		File folder = new File(bbsSkinPath);
		if(folder.isDirectory()){
			String f[] = folder.list();
			for(int i=0;i<f.length;i++){
				if(!f[i].equals(".metadata")){
				bbsSkinList.add(f[i]);
				}
			}
		}
		return bbsSkinList;
	}
	
	/**
	 * 링크될 메뉴가 다른 게시판과 링크되어있는지 검색
	 * @param bbsInfoVO
	 * @return BbsInfoVO
	 * @throws Exception
	 * @see bbsId , bbsNm , subpageNm 
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	public BbsInfoVO selectSameSubpageId(BbsInfoVO bbsInfoVO)throws Exception{
		return (BbsInfoVO)this.bbsManageDAO.selectSameSubpageId(bbsInfoVO);
	}
}
