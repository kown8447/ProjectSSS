package kr.or.initspring.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.initspring.dao.OpRequestDAO;
import kr.or.initspring.dto.commons.OpRequestDTO;

@Service
public class OpRequestService {

	@Autowired
	private SqlSession sqlsession;
	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public List<OpRequestDTO> oprequestlist(){
		System.out.println("OpRequest Service");
		OpRequestDAO oprequsetdao = sqlsession.getMapper(OpRequestDAO.class);
		List<OpRequestDTO> oprequest = oprequsetdao.OpRequestList();
		System.out.println(oprequest.toString());
		return oprequest;
	}
}
