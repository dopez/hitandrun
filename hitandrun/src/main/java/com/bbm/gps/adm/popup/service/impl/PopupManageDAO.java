 package com.bbm.gps.adm.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.popup.service.PopupManageVO;

/** 
 * 팝업관리에 대한 데이터 접근 클래스를 정의한다
 * <p><b>NOTE:</b> 넘어온 요청에 대해 DB작업을 수행하는 메소드들의 집합
 * DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
 * @author 범정부통계포털 이관형 
 * @since 2011.06.27 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.05.15     이관형      최초 생성 
 * 
 * </pre> 
 */
@Repository("popupManageDAO")
public class PopupManageDAO extends GpsAbstractDAO {
	/**
	 * 팝업목록 ID에 대한 행삭제(팝업삭제) 
	 * @param popupManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_POPUP
	 */
	public void deletePopup(PopupManageVO popupManageVO) throws Exception {
		delete("PopupManageDAO.deletePopup", popupManageVO);
	}

	/**
	 * 팝업목록 입력 처리
	 * @param popupManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_POPUP
	 */
	public void insertPopup(PopupManageVO popupManageVO) throws Exception {
        insert("PopupManageDAO.insertPopup", popupManageVO);
	}

	/**
	 * 팝업목록수정처리(수정할 대상 게시물ID에 해당하는 행을 업데이트)
	 * @param popupManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_POPUP
	 */
	public void updatePopup(PopupManageVO popupManageVO) throws Exception {
        update("PopupManageDAO.updatePopup", popupManageVO);
	}

	/**
	 * 팝업상세조회(popupManageVO 게시물에 대한 상세화면을 조회)
	 * @param popupManageVO
	 * @return PopupManageVO
	 * @throws Exception
	 * @see POPUP_SN,SYS_ID,ORDR,SJ,HTML_AT,WIDTH,HEIGHT,LC_TOP,LC_LEFT,LC_SCROLL,ACTVTY_BGNDE, 
     * @see ACTVTY_ENDDE,ACTVTY_AT,COOKIE_SKLL,ATCHMNFL_ONE,ATCHMNFL_ONE_MASK,ATCHMNFL_ONE_SIZE, 
     * @see ATCHMNFL_ONE_MIME,ATCHMNFL_TWO,ATCHMNFL_TWO_MASK,ATCHMNFL_TWO_SIZE,ATCHMNFL_TWO_MIME, 
     * @see ATCHMNFL_THREE,ATCHMNFL_THREE_MASK,ATCHMNFL_THREE_SIZE,ATCHMNFL_THREE_MIME, TRGET, URL, 
     * @see URL_TRGET,REGISTER_IP,REGIST_DT,UPDT_DT,REGISTER_ID,POPUP_TY, UPDTUSR_ID, CN
	 * @see TABLE NAME : TN_POPUP
	 */
	public PopupManageVO selectPopup(PopupManageVO popupManageVO) throws Exception {
		return (PopupManageVO)selectByPk("PopupManageDAO.selectPopup", popupManageVO);
	}

    /**
     * 팝업목록조회(popupManageVO 검색조건에따라 팝업목록을 조회)  
     * @param popupManageVO
     * @return List
     * @throws Exception
     * @see POPUP_SN,SYS_ID,ORDR,SJ,HTML_AT,WIDTH,HEIGHT,LC_TOP,LC_LEFT,LC_SCROLL,ACTVTY_BGNDE, 
     * @see ACTVTY_ENDDE,ACTVTY_AT,COOKIE_SKLL,ATCHMNFL_ONE,ATCHMNFL_ONE_MASK,ATCHMNFL_ONE_SIZE, 
     * @see ATCHMNFL_ONE_MIME,ATCHMNFL_TWO,ATCHMNFL_TWO_MASK,ATCHMNFL_TWO_SIZE,ATCHMNFL_TWO_MIME, 
     * @see ATCHMNFL_THREE,ATCHMNFL_THREE_MASK,ATCHMNFL_THREE_SIZE,ATCHMNFL_THREE_MIME, TRGET, URL, 
     * @see URL_TRGET,REGISTER_IP,REGIST_DT,UPDT_DT,REGISTER_ID,POPUP_TY, UPDTUSR_ID, CN
     * @see TABLE NAME : TN_POPUP,TN_SYSTEM
     */
    @SuppressWarnings("unchecked")
	public List selectPopupList(PopupManageVO popupManageVO) throws Exception {
        return list("PopupManageDAO.selectPopupList", popupManageVO);
    }

    /**
     * 팝업목록 총갯수(팝업 목록의 총 갯수를 조회한다.)
     * @param popupManageVO
     * @return int
     * @throws Exception
     * @see COUNT(*) totcnt 
     * @see TABLE NAME : TN_POPUP,TN_SYSTEM
     */
    public int selectPopupListTotCnt(PopupManageVO popupManageVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("PopupManageDAO.selectPopupListTotCnt", popupManageVO);
    }

    /**
     * 팝업사용여부수정
     * @param popupManageVO
     * @throws Exception
     * @see TABLE NAME : TN_POPUP
     */
    public void updatePopupActvtyAt(PopupManageVO popupManageVO) throws Exception {
        update("PopupManageDAO.updatePopupActvtyAt", popupManageVO);
    }

}
