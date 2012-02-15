package com.bbm.common.cmm.util;

import java.io.File;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import egovframework.rte.fdl.property.EgovPropertyService;

public class SendMail {

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;	
	
	Logger log = Logger.getLogger(this.getClass());
	
	private String host = propertiesService.getString("Mail.Host");
	private String toMail = "";
	private String toName = "";
	private String fromMail = "";
	private String fromName = "";
	private String subject = "";
	private String msg = "";
	private String mailKind = "html";
	@SuppressWarnings("unchecked")
	private Vector attachVector;
	private String path = ""; //기본첨부자료 경로
	
	/**
	 * 
	 * @param _host		메일서버 호스트명
	 * @param _to			보내는 사람 메일 주소 (한명(String), 여러명(String[]) 발송가능)
	 * @param _from		받는사람 메일 주소
	 * @param _subject		메일 제목
	 * @param _msg			메일 메시지
	 * @param _mailKind	메일 종류(html, text)
	 * 	@param _FileName	첨부파일
	 */

	public SendMail(String _host, String _to, String _from, String _subject, String _msg, String _mailKind, Vector _attachVector) {
		if (!StringUtils.isBlank(_host)) {
			host = _host;
		}
		toMail = (String) _to; 
		fromMail = _from;
		subject = _subject;
		msg = _msg;
		mailKind = _mailKind;
		attachVector = _attachVector;
	}
	
	public SendMail(String _host, String _to, String _from, String _subject, String _msg, String _mailKind) {
		if (!StringUtils.isBlank(_host)) {
			host = _host;
		}
		toMail = (String) _to; 
		fromMail = _from;
		subject = _subject;
		msg = _msg;
		mailKind = _mailKind;
	}
	
	public SendMail(String _to, String _from, String _subject, String _msg, String _mailKind,  Vector _attachVector) {
		toMail = (String) _to; 
		fromMail = _from;
		subject = _subject;
		msg = _msg;
		mailKind = _mailKind;
		attachVector = _attachVector;
	}
	
	public SendMail(String _to, String _from, String _subject, String _msg, String _mailKind) {
		toMail = (String) _to; 
		fromMail = _from;
		subject = _subject;
		msg = _msg;
		mailKind = _mailKind;
	}
	
	public void setFromName(String _fromName) {
		fromName = _fromName;
	}
	 
	public boolean send() {

		try {
			// 프로퍼티 설정
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
	         
			// Session 생성
			Session session = Session.getDefaultInstance(props, null);
	         
			// MimeMessage 메시지 생성
			MimeMessage message = new MimeMessage(session);
	         
			if (fromName!=null) {
				message.setFrom(new InternetAddress(fromMail, fromName, "euc-kr"));  // 보내는 사람 이메일
			} else {
				message.setFrom(new InternetAddress(fromMail, "euc-kr"));  // 보내는 사람 이메일
			}
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail)); //받는 사람 이메일
			message.setSubject(subject);  // 제목
			message.setSentDate(new java.util.Date());
			
			// Multi Part 생성
			Multipart multipart = new MimeMultipart();
	         
			// 메시지 BodyPart 생성 및 Mutil Part에 추가
			MimeBodyPart msgBodyPart = new MimeBodyPart();
	         
			msgBodyPart.setContent(msg,"text/html; charset=EUC-KR");
			multipart.addBodyPart(msgBodyPart);
	   
			// 첨부 화일 BodyPart 생성 및 Multi Part에 추가
	    	for( int i=0; attachVector != null && i < attachVector.size(); i++) {
	    		Object[] attach = (Object [])attachVector.get(i);
	    		File file = new File(path + (String)attach[0]);
	    		
				msgBodyPart = new MimeBodyPart();
				msgBodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
				msgBodyPart.setFileName(MimeUtility.encodeText(file.getName(),"EUC-KR","B"));
				multipart.addBodyPart(msgBodyPart);
	    	}
			
			// Multi Part 메시지에 content 설정
			message.setContent(multipart);
	   
			// 메일 메시지 전송
			Transport.send(message);			
			
			return true;
		} catch (MessagingException ex) {
	         	System.err.println("SendMail Error : " + toMail);
	         	//ex.printStackTrace();
	         	log.error( this.getClass().getName() + " JAVA-afterBatchJob() " + ex.getMessage());
				return false;
		} catch (Exception e) {
			//e.printStackTrace();
			log.error( this.getClass().getName() + " JAVA-afterBatchJob() " + e.getMessage());
			return false;
		}
	}	
	
}
