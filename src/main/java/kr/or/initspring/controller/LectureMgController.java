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

import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.LiberalDTO;
import kr.or.initspring.dto.commons.MajorDTO;
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
    public String insertSubject(Model model,Principal principal){
		System.out.println("매핑은되냐");
		System.out.println(principal.getName());
		lectureservice.selectBefore(principal.getName(),model);
		System.out.println("나는서비스를돌앗다");
		
    	return "lecture.registersubject";
    }
	
	@RequestMapping(value="lectureDetail.htm")
	public String detailSubject(Model model,String subject_name){
		System.out.println("매핑되엇슴");
		System.out.println("서브젝트네임입니다:"+subject_name);
		CustomLectureMgDTO list = lectureservice.subjectDetail(subject_name);
		System.out.println("서비스통과후컨트롤러");
		model.addAttribute("list",list);
		
		return "lecture.subjectDetail";
	}
	
	@RequestMapping(value="lectureEdit.htm")
	public String updateSubject(Model model,String subject_name){
		
		CustomLectureMgDTO list = lectureservice.subjectDetail(subject_name);
		model.addAttribute("list",list);
		
		return "lecture.subjectupdate";
	}
    

	@RequestMapping(value="lectureView.htm")
	public String subjectList(Model model){
		
		List<CustomLectureMgDTO> subjectdto = new ArrayList<CustomLectureMgDTO>();
		
		subjectdto = lectureservice.Request_List();
		subjectdto.addAll(lectureservice.Request_List2());
		System.out.println(subjectdto.toString());
		model.addAttribute("subjectlist",subjectdto);
	
		return "lecture.listview";
	}
	
	
	@RequestMapping(value="lectureRegister.htm",method=RequestMethod.POST)
	public String insertsubject(SubjectDTO dto,String before_code,Principal principal,String required_choice,
					BeforeSubjectDTO beforedto,MajorDTO majordto,LiberalDTO liberdto,String department_code) throws Exception{
		System.out.println(dto.toString());
		int result = 0;
		System.out.println("컨틀로러매핑임니다");
	
		System.out.println("비포어코드비비비"+before_code);
		result = lectureservice.insert_Subject(dto,before_code ,principal,required_choice, beforedto, majordto, liberdto ,department_code);
		
		System.out.println("매핑완료");
	
		return "lecture.listview";
	}
	
	@RequestMapping(value="lectureDelete.htm")
	public int deleteSubject(String subject_name){
		
		int result = 0;
		
		
		return 0;
	}
}
