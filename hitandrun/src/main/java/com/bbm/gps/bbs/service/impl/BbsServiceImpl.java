package com.bbm.gps.bbs.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import com.bbm.common.cmm.service.NaraCmmService;
import com.bbm.common.util.sim.service.EgovFileScrty;
import com.bbm.gps.bbs.service.BbsMemoVO;
import com.bbm.gps.bbs.service.BbsSearchVO;
import com.bbm.gps.bbs.service.BbsService;
import com.bbm.gps.bbs.service.BbsVO;

/** 
 * 게시판에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b>서비스에 선언 되어있는 메소드들의 구현 클래스로 데이터 접근 클래스의 메소드를 호출한다
 * 메소드들 중에는 parameter를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다
 * @author 범정부통계포털 황기연 
 * @since 2011.09.16 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.09.16      황기연      최초 생성 
 * 
 * </pre> 
 */
@Service("bbsService")
public class BbsServiceImpl extends AbstractServiceImpl implements BbsService{
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
	 * 메인페이지게시글목록조회
	 * @param bbsSearchVO 게시글검색 정보를 담고있는 VO
	 * @return 게시글목록
	 * @exception Exception
 	 */ 
	public List<BbsVO> selectBbsIndexList(BbsSearchVO bbsSearchVO)throws Exception{
		return (List<BbsVO>)this.bbsDAO.selectBbsIndexList(bbsSearchVO);
	}
	
	/** 
	 * 게시글목록조회
	 * @param bbsSearchVO 게시글검색 정보를 담고있는 VO
	 * @return 게시글목록
	 * @exception Exception
 	 */
	public List<BbsVO> selectBbsList(BbsSearchVO bbsSearchVO)throws Exception{
		return (List<BbsVO>)this.bbsDAO.selectBbsList(bbsSearchVO);
	}
	
	/** 
	 * 게시글목록 총 갯수 조회
	 * @param bbsSearchVO 게시글검색 정보를 담고있는 VO
	 * @return 게시글목록
	 * @exception Exception
 	 */ 
	public int selectBbsListTotCnt(BbsSearchVO bbsSearchVO)throws Exception{
		return (Integer)this.bbsDAO.selectBbsListTotCnt(bbsSearchVO);
	}
	
	/** 
	 * 게시글상세조회시 조회수 증가
	 * @param bbsSearchVO 게시글 정보를 담고있는 VO
	 * @return 
	 * @exception Exception
 	 */
	public void updateCo(BbsSearchVO bbsSearchVO)throws Exception{
		this.bbsDAO.updateCo(bbsSearchVO);
	}
	
	/** 
	 * 게시글상세조회
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return 게시글상세내용
	 * @exception Exception
 	 */
	public BbsVO selectBbs(BbsSearchVO bbsSearchVO)throws Exception{
		BbsVO bbsVO = new BbsVO();
		bbsVO = (BbsVO)this.bbsDAO.selectBbs(bbsSearchVO);
		if(bbsVO!=null && bbsVO.getEmail()!=null){
			//개인정보 복호화
			bbsVO.setEmail(EgovFileScrty.decode(bbsVO.getEmail()));
		}
		return bbsVO;
	}
	
	/** 
	 * 공지사항등록가능여부
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return Y,N
	 * @exception Exception
 	 */
	public String noticeWriteAt(BbsVO bbsVO)throws Exception{
		return (String)this.bbsDAO.noticeWriteAt(bbsVO);
	}
	
	/** 
	 * 게시글등록처리
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return 
	 * @exception Exception
 	 */ 
	@SuppressWarnings("unchecked")
	public void insertBbs(BbsVO bbsVO,MultipartHttpServletRequest multiRequest)throws Exception{
		Map<String, MultipartFile> imgfileMap = new HashMap<String, MultipartFile>();
		Map<String, MultipartFile> fileMap = new HashMap<String, MultipartFile>();
		String _atchFileId = "";
    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
    	Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
    	MultipartFile file = null;
	    
    	file = multiRequest.getFile("imgFile");
    	
    	while (itr.hasNext()) {
    	    Entry<String, MultipartFile> entry = itr.next();
    	    if( entry.getKey().indexOf("imgFile") > -1  ) {
    	    	imgfileMap.put( entry.getKey(), entry.getValue() );
    	    }else{
    	    	fileMap.put( entry.getKey(), entry.getValue() );
    	    }
    	}
    	
    	
    	//이미지파일 업로드
    	if(!imgfileMap.isEmpty()){
    		_atchFileId = this.naraCmmService.insertAtchFile(multiRequest, file,  "BBS_",  "", propertiesService.getString("GPS.bbsPath"));	
	        if( !"".equals( _atchFileId ) ) {
	        	bbsVO.setImageFileId(_atchFileId);
    		}
    	}
    	
    	//첨부파일업로드
    	if(!files.isEmpty()){
    		_atchFileId = this.naraCmmService.insertAttachFile(multiRequest, fileMap, this.getClass().getPackage().toString(), "",  "");			
	        if( !"".equals( _atchFileId ) ) {
	        	bbsVO.setAtchmnflId(_atchFileId);
    		}
    	}
    	
    	//계층형처리
    	if(bbsVO.getBbsSn() > 0){
    		bbsVO.setRelate(bbsVO.getBbsSn());
    	}
    	
    	//개인정보 암호화
    	bbsVO.setEmail(EgovFileScrty.encode(bbsVO.getEmail()));
		bbsVO.setPassword(EgovFileScrty.encryptPassword(bbsVO.getPassword()));
		this.bbsDAO.insertBbs(bbsVO);
	}
	
	/** 
	 * 게시글수정처리
	 * @param bbsVO 게시글 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */ 
	@SuppressWarnings("unchecked")
	public void updateBbs(BbsVO bbsVO,MultipartHttpServletRequest multiRequest)throws Exception{
		Map<String, MultipartFile> imgfileMap = new HashMap<String, MultipartFile>();
		Map<String, MultipartFile> fileMap = new HashMap<String, MultipartFile>();
		String _atchFileId = "";
    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
    	Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
    	MultipartFile file = null;
    	
    	file = multiRequest.getFile("imgFile");
    	
    	while (itr.hasNext()) {
    	    Entry<String, MultipartFile> entry = itr.next();
    	    if( entry.getKey().indexOf("imgFile") > -1  ) {
    	    	imgfileMap.put( entry.getKey(), entry.getValue() );
    	    }else{
    	    	fileMap.put( entry.getKey(), entry.getValue() );
    	    }
    	}
    	
    	
    	//이미지파일 업로드
    	if(!imgfileMap.isEmpty()){
    		_atchFileId = this.naraCmmService.insertAtchFile(multiRequest, file,  "BBS_",  "", propertiesService.getString("GPS.bbsPath"));	
	        if( !"".equals( _atchFileId ) ) {
	        	bbsVO.setImageFileId(_atchFileId);
    		}
    	}else{
    		bbsVO.setImageFileId(bbsVO.getImageFileId());
    	}
    	
    	//첨부파일업로드
    	if(!files.isEmpty()){
    		_atchFileId = this.naraCmmService.insertAttachFile(multiRequest, fileMap, this.getClass().getPackage().toString(), "",  "");			
	        if( !"".equals( _atchFileId ) ) {
	        	bbsVO.setAtchmnflId(_atchFileId);
    		}
    	}else{
    		bbsVO.setAtchmnflId(bbsVO.getAtchmnflId());
    	}
    	//개인정보 암호화
    	bbsVO.setEmail(EgovFileScrty.encode(bbsVO.getEmail()));
		bbsVO.setPassword(EgovFileScrty.encryptPassword(bbsVO.getPassword()));
		this.bbsDAO.updateBbs(bbsVO);
	}
	
	/** 
	 * 게시글삭제처리
	 * @param bbsSearchVO 게시글 검색 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */ 
	public void deleteBbs(BbsVO bbsVO)throws Exception{
		if(this.bbsDAO.deleteBbs(bbsVO) > 0){
			this.bbsDAO.deleteAllBbsMemo(bbsVO);
		}
	}
	
	/** 
	 * 게시글관련 메모목록
	 * @param bbsSearchVO 게시글 검색 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */ 
	public List<BbsMemoVO> selectBbsMemoList(BbsSearchVO bbsSearchVO)throws Exception{
		return (List<BbsMemoVO>)this.bbsDAO.selectBbsMemoList(bbsSearchVO);
	}
	
	/** 
	 * 게시글관련 메모 등록
	 * @param bbsMemoVO 게시글 메모 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */
	public void insertBbsMemo(BbsMemoVO bbsMemoVO)throws Exception{
		this.bbsDAO.insertBbsMemo(bbsMemoVO);
	}
	
	/** 
	 * 메모삭제시 비밀번호 확인
	 * @param bbsMemoVO 게시글 메모 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */
	public boolean memoPasswordConfirm(BbsMemoVO bbsMemoVO)throws Exception{
		if(this.bbsDAO.memoPasswordConfirm(bbsMemoVO) > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/** 
	 * 게시글관련 메모 삭제
	 * @param bbsMemoVO 게시글 메모 정보를 담고있는 VO
	 * @return
	 * @exception Exception
 	 */
	public void deleteBbsMemo(BbsMemoVO bbsMemoVO)throws Exception{
		this.bbsDAO.deleteBbsMemo(bbsMemoVO);
	}
	
	/**
	 * 게시판 아이디 패턴이외의 문자 제거
	 * @param strString
	 * @return 바뀌어진 값을 넘겨준다.
	 */
	public String bbsIdReplace(String str) {
		String match = "[^NARA0-9\\s]";
		if(str!=null && !str.isEmpty()){
		str = str.replaceAll(match, "");
		}
		return str;
	}
}
