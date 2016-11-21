/*
 * @Class : LectureMgController
 * @Date : 2016.11.18
 * @Author : 조장현
 * @Desc
 * 과목 리스트 출력 , 과목 신청 기능
*/
package kr.or.initspring.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import kr.or.initspring.dto.commons.SubjectDTO;
import kr.or.initspring.dto.lectureMg.CustomLectureMgDTO;
import kr.or.initspring.service.lectureService;

@Controller
@RequestMapping("/lecture/")
@Secured({"ROLE_PROFESSOR"})
public class LectureMgController {

	@Autowired
	private View jsonview;
	
	@Autowired
	private lectureService lectureservice;
	
	@RequestMapping(value="lectureRegister.htm",method=RequestMethod.GET)
    public String insertSubject(){
    	return "lecture.registersubject";
    }
	
	@RequestMapping(value="lectureDetail.htm")
	public String detailSubject(){
		return "lecture.subjectDetail";
	}
	
	@RequestMapping(value="lectureEdit.htm")
	public String updateSubject(){
		return "lecture.subjectUpdate";
	}
    

	@RequestMapping(value="lectureView.htm")
	public String subjectList(Model model){
		
		List<CustomLectureMgDTO> subjectdto = new ArrayList<CustomLectureMgDTO>();
		
		subjectdto = lectureservice.Request_List();
		System.out.println(subjectdto.toString());
		model.addAttribute("subjectlist",subjectdto);
		return "lecture.listview";
	}
	
	
	@RequestMapping(value="lectureRegister.htm",method=RequestMethod.POST)
	public String insertsubject(SubjectDTO dto,Model model,Principal principal) throws Exception{
		System.out.println(dto.toString());
		int result = 0;
		result = lectureservice.insert_Subject(dto,principal);
		System.out.println("타는거야뭐야 이건 "+result);
		
		if(result>0){
			System.out.println("들어갓나봄");
		}else{
			System.out.println("시발");
		}
		
		model.addAttribute("insertresult",result);
		return "lecture.listview";
	}



}
