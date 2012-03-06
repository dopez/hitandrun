package com.bbm.sample.dcm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.common.util.sim.service.EgovFileScrty;
import com.bbm.sample.dcm.service.LoginExaminationService;
import com.bbm.sample.dcm.service.LoginExaminationLoginVO;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 구현 클래스
 * <p><b>NOTE:</b>
 * @author 포털통계 이관형
 * @since 2011.07.01
 * @version 1.0
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  이관형          최초 생성  
 *  
 *  </pre>
 */
@Service("LoginExaminationService")
public class LoginExaminationServiceImpl extends AbstractServiceImpl implements LoginExaminationService {

    @Resource(name="LoginExaminationDAO")
    private LoginExaminationDAO loginExaminationDAO;

    /**
	 * 일반 로그인을 처리한다
	 * @param vo GpsLoginVO
	 * @return GpsLoginVO
	 * @exception Exception
	 */
    public LoginExaminationLoginVO actionLogin(LoginExaminationLoginVO vo) throws Exception {
    	
    	// 1. 입력한 비밀번호를 암호화한다.
    	//String enpassword = EgovFileScrty.encryptPassword(vo.getPassword());
    	//vo.setPassword(enpassword);
    	
    	// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
    	LoginExaminationLoginVO loginVO = loginExaminationDAO.actionLogin(vo);
    	
    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getUserId().equals("") && !loginVO.getPassword().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginExaminationLoginVO();
    	}
    	
    	return loginVO;
    }
    
}
