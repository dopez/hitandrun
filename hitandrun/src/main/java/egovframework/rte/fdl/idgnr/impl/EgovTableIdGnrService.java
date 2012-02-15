package egovframework.rte.fdl.idgnr.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import egovframework.rte.fdl.cmmn.exception.FdlException;


/**
 * @Class Name : EgovTableIdGnrService.java
 * @Description : 테이블에 의한 ID 제공 클래스
 * @Modification Information
 * @
 * @  수정일     수정자               수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01 김태호      최초 생성
 *
 *  @author Taeho kim
 *  @since 2009. 02. 01
 *  @version 1.0
 *  
 *  Copyright (C) 2009 by MOPAS  All right reserved. 
 */
public class EgovTableIdGnrService extends
		AbstractDataBlockIdGnrService {

	/**
	 * ID생성을 위한 테이블 정보 디폴트는 ids임.
	 */
	private String table = "ids";

	/**
	 * 테이블 정보에 기록되는 대상 키정보 대개의 경우는 아이디로 생성되는 테이블명을 기재함
	 */
	private String tableName = "id";


	/**
	 * 생성자
	 */
	public EgovTableIdGnrService() {
	}

	/**
	 * blockSize 대로 ID 지정
	 * 
	 * @param blockSize
	 *            지정되는 blockSize
	 * @param useBigDecimals
	 *            BigDecimal 사용 여부
	 * @return BigDecimal을 사용하면 BigDecimal 아니면 long 리턴
	 * @throws FdlException
	 *             ID생성을 위한 블럭 할당이 불가능할때
	 */
	private Object allocateIdBlock(int blockSize, boolean useBigDecimals)
			throws FdlException {

		if (getLogger().isDebugEnabled()) {
			getLogger().debug(
					messageSource
							.getMessage("debug.idgnr.allocate.idblock",
									new Object[] { new Integer(blockSize),
											tableName }, Locale.getDefault()));
		}

		try {
			Connection conn = getConnection();
			try {
				
				boolean autoCommit = conn.getAutoCommit();

				Statement stmt = conn.createStatement();
				try {

					int tries = 0;
					while (tries < 50) {
						
						String query = "SELECT next_id FROM " + table
								+ " WHERE table_name = '" + tableName + "' FOR UPDATE ";
						ResultSet rs = stmt.executeQuery(query);
						if (!rs.next()) {
							
							if (getLogger().isErrorEnabled())
								getLogger()
										.error(
												messageSource
														.getMessage(
																"error.idgnr.tableid.notallocate.id",
																new String[] {
																		tableName,
																		table },
																Locale
																		.getDefault()));
							if (!autoCommit) {
								conn.rollback();
							}

							throw new FdlException(messageSource,
									"error.idgnr.tableid.notallocate.id",
									new String[] { tableName, table }, null );
						}
						
						Object nextId;
						Object newNextId;
						if (useBigDecimals) {
							BigDecimal oldNextId = rs.getBigDecimal(1);
							newNextId = oldNextId
									.add(new BigDecimal(blockSize));
							nextId = oldNextId;
						}
						else {
							long oldNextId = rs.getLong(1);
							newNextId = new Long(oldNextId + blockSize);
							nextId = new Long(oldNextId);
						}

						try {

							query = "UPDATE " + table + " SET next_id = "
									+ newNextId + " "
									+ " WHERE table_name = '" + tableName
									+ "' " + "   AND next_id = " + nextId
									+ "";
							
							int updated = stmt.executeUpdate(query);
							if (updated >= 1) {
							
								if (!autoCommit) {
									conn.commit();
								}

								return nextId;
							}
							else {
								
								if (getLogger().isDebugEnabled())
									getLogger()
											.debug(
													messageSource
															.getMessage(
																	"debug.idgnr.updated.norows",
																	new String[] {},
																	Locale
																			.getDefault()));
							}
						}
						catch (SQLException e) {
							
							if (getLogger().isWarnEnabled())
								getLogger().warn(
										messageSource.getMessage(
												"warn.idgnr.update.idblock",
												new String[] {}, Locale
														.getDefault()));
						}

						if (!autoCommit) {
							conn.rollback();
						}

						tries++;
					}

					if (getLogger().isErrorEnabled())
						getLogger().error(
								messageSource.getMessage("error.idgnr.null.id",
										new String[] {}, Locale.getDefault()));
					return null;
				}
				finally {
					stmt.close();
				}
			}
			finally {
				conn.close();
			}
		}
		catch (SQLException e) {
			if (getLogger().isErrorEnabled())
				getLogger().error(
						messageSource.getMessage("error.idgnr.get.connection",
								new String[] {}, Locale.getDefault()), e);
			throw new FdlException(messageSource,
					"error.idgnr.get.connection", e);
		}
	}

	/**
	 * blockSize 대로 ID 지정(BigDecimal)
	 * 
	 * @param blockSize
	 *            지정되는 blockSize
	 * @return 할당된 블럭의 첫번째 아이디
	 * @throws FdlException
	 *             ID생성을 위한 블럭 할당이 불가능할때
	 */
	protected BigDecimal allocateBigDecimalIdBlock(int blockSize)
			throws FdlException {
		return (BigDecimal) allocateIdBlock(blockSize, true);
	}

	/**
	 * blockSize 대로 ID 지정(long)
	 * 
	 * @param blockSize
	 *            지정되는 blockSize
	 * @return 할당된 블럭의 첫번째 아이디
	 * @throws FdlException
	 *             ID생성을 위한 블럭 할당이 불가능할때
	 */
	protected long allocateLongIdBlock(int blockSize) throws FdlException {
		Long id = (Long) 
		allocateIdBlock(blockSize, false);

		return id.longValue();
	}
	
	/**
	 * ID생성을 위한 테이블 정보 Injection
	 * 
	 * @param table
	 *              config로 지정되는 정보
	 */
	public void setTable(String table) {
		this.table = table;
	}	
	
	/**
	 * ID 생성을 위한 테이블의 키정보 ( 대개의경우는 대상 테이블명을 기재함 )
	 * 
	 * @param tableName
	 *                  config로 지정되는 정보
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}	
	
}
