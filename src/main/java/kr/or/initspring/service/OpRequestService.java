package kr.or.initspring.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import kr.or.initspring.dao.OpRequestDAO;
import kr.or.initspring.dto.commons.AskTimeDTO;
import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.LiberalDTO;
import kr.or.initspring.dto.commons.MajorDTO;
import kr.or.initspring.dto.commons.OpRequestDTO;
import kr.or.initspring.dto.commons.PeriodDTO;
import kr.or.initspring.dto.commons.SubjectDTO;
import kr.or.initspring.dto.oprequest.OpClassRoomDTO;
import kr.or.initspring.dto.oprequest.OpRequestDetailDTO;
import kr.or.initspring.dto.oprequest.OpRequestPeriodDTO;
import kr.or.initspring.dto.oprequest.OpRequsetCheckDTO;

@Service
public class OpRequestService {

	@Autowired
	private SqlSession sqlsession;
	
	/*
	 * @method Name : oprequestlist
	 * @Author : 성홍모
	 * @description
	 * 개설신청리스트를 불러와주는 매소드
	*/
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public List<OpRequsetCheckDTO> oprequestlist(Model model){
		
		OpRequestDAO oprequsetdao = sqlsession.getMapper(OpRequestDAO.class);
		
		List<OpRequsetCheckDTO> oprequest = oprequsetdao.OpRequestList();
		LiberalDTO liberal = new LiberalDTO();
		MajorDTO major = new MajorDTO();
		
		int i = 0;
		for( i = 0; i < oprequest.size();i++){
			if(oprequest.get(i).getSubject_type()==1){
				liberal = oprequsetdao.Liberal(oprequest.get(i).getSubject_code());
			    if(liberal.getRequired_choice().equals("0")){
			    	oprequest.get(i).setRequired_choice("교양필수");
			    }else{
			    	oprequest.get(i).setRequired_choice("교양선택");
			    }
			}else{
				major = oprequsetdao.Major(oprequest.get(i).getSubject_code());
				if(major.getRequired_choice().equals("0")){
					oprequest.get(i).setRequired_choice("전공필수");
				}else{
					oprequest.get(i).setRequired_choice("전공선택");
				}
			}
		}
		model.addAttribute("oprequest", oprequest);
		return oprequest;
	}
	
	/*
	 * @method Name : detail
	 * @Author : 성홍모
	 * @description
	 * 개설신청리스트를 상세보기 해주는 매소드
	*/
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public Boolean detail(String subject_code, Model model){

		OpRequestDAO dao = sqlsession.getMapper(OpRequestDAO.class);

		boolean result = false;
		List<AskTimeDTO> asklist = dao.AskTime(subject_code);
		
		String classcode = asklist.get(0).getClassroom_code();
		
		if(!asklist.isEmpty()){

			OpClassRoomDTO classDTO = dao.className(classcode);
			OpRequestDetailDTO detailDTO = dao.detail(subject_code);
			
			model.addAttribute("room",classDTO);
			model.addAttribute("detail", detailDTO);
			
			SubjectDTO subject = dao.Subject(subject_code);
			
			int subjectType = subject.getSubject_type();
			
			OpRequsetCheckDTO opdto = new OpRequsetCheckDTO();
			
			if(subjectType == 0){
				MajorDTO major = dao.Major(subject_code);
				String majorType = major.getRequired_choice();
				if(majorType.equals("0")){
					opdto.setRequired_choice("전공필수");
				}else{
					opdto.setRequired_choice("전공선택");
				}
			}else if(subjectType == 1){
				LiberalDTO liberal=dao.Liberal(subject_code);
				String liberalType = liberal.getRequired_choice();
				if(liberalType.equals("0")){
					opdto.setRequired_choice("교양필수");
				}else{
					opdto.setRequired_choice("교양선택");
				}
			}
			model.addAttribute("opcheck", opdto);
			
			result = true;
		}
		String beforeName = "";
		try {
			BeforeSubjectDTO before = dao.Before(subject_code);
			if(before == null){
				beforeName = "없음";
			}
			if(before.getSubject_code().equals(subject_code)){
				beforeName = before.getBefore_name();	
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<OpRequestPeriodDTO> periodList = dao.OpPeriod(subject_code);
		model.addAttribute("period", periodList);
		model.addAttribute("beforeSubject", beforeName);
		
		return result;
	}
	/*
	 * @method Name : updateSuccess
	 * @Author : 성홍모
	 * @description
	 * 개설신청과목을 승인해주는 매소드
	*/
	@Transactional(rollbackFor = { Exception.class, SQLException.class, NullPointerException.class, RuntimeException.class })
	public int updateSuccess(String subject_code) throws Exception{
		
		OpRequestDAO dao = sqlsession.getMapper(OpRequestDAO.class);
		int result = 0;
		
		try {
			dao.insertIntoOpened(subject_code);
			dao.insertIntoLecture(subject_code);
			dao.UpdateSuccess(subject_code);
			result = dao.updateSubjectState(subject_code);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	/*
	 * @method Name : UpdateReject
	 * @Author : 성홍모
	 * @description
	 * 개설신청과목을 거절해주는 매소드, 거절사유를 추가해주는 매소드
	*/
	@Transactional(rollbackFor = { Exception.class, SQLException.class, NullPointerException.class, RuntimeException.class })
	public boolean UpdateReject(String subject_code, String reject_reason) throws Exception{
		
		OpRequestDAO dao = sqlsession.getMapper(OpRequestDAO.class);
		boolean result = false;
		if(reject_reason==null || reject_reason.equals("")){
			reject_reason="no reason";
		}
		try {
			dao.UpdateReject(subject_code);
			dao.insertRejection(subject_code, reject_reason);
			result = true;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
}
