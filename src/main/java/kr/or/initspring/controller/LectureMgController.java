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

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import kr.or.initspring.dao.LectureMgDAO;
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
	public String detailSubject(Model model,String subject_code){
		System.out.println("매핑되엇슴");

		System.out.println("서브젝트코드입니다:"+subject_code);

	
		CustomLectureMgDTO list = lectureservice.subjectDetail(subject_code);
		System.out.println("서비스통과후컨트롤러");
		System.out.println("상세보기 비포네임 : "+list.getBefore_name());
		System.out.println("상세보그 서브젝트코드 : "+list.getSubject_code());
		model.addAttribute("list",list);
		System.out.println(list.toString());
		
		return "lecture.subjectDetail";
	}
	
	@RequestMapping(value="lectureEdit.htm")
	public String updateSubject(Model model,String subject_code){
		System.out.println("수정컨트롤러 임수정님ㅠㅠ");
		System.out.println(subject_code);
		CustomLectureMgDTO list = lectureservice.subjectDetail(subject_code);
		System.out.println("수정값돌고와습니다");
		model.addAttribute("list",list);
		
		return "lecture.subjectupdate";
	}
	
	@RequestMapping(value="lectureEditOk.htm")
	public String EditComplete(CustomLectureMgDTO dto){
		System.out.println("에디드컴플리트들어왓슴니당");
		System.out.println(dto.getBefore_name());
		System.out.println("에디트컨트롤러 서브젝트코드:"+dto.getSubject_code());
		lectureservice.updatesubject(dto);
		System.out.println("수정끝");
		
		return "redirect:lectureView.htm";
	}
    

	@RequestMapping(value="lectureView.htm")
	public String subjectList(Model model,Principal principal){
		
		List<CustomLectureMgDTO> subjectdto = new ArrayList<CustomLectureMgDTO>();
		
		subjectdto = lectureservice.Request_List(principal);
		System.out.println(subjectdto.toString());
		model.addAttribute("subjectlist",subjectdto);
	
		return "lecture.listview";
	}
	
	
	@RequestMapping(value="lectureRegister.htm",method=RequestMethod.POST)
	public String insertsubject(SubjectDTO dto,String before_code,Principal principal,String required_choice,
					BeforeSubjectDTO beforedto,MajorDTO majordto,LiberalDTO liberdto,String department_code) throws Exception{
		System.out.println("디티오:"+dto.toString());
		int result = 0;
		System.out.println("컨틀로러매핑임니다");
		
		System.out.println("비포어코드비비비"+before_code);
		result = lectureservice.insert_Subject(dto,before_code ,principal,required_choice, beforedto, majordto, liberdto ,department_code);
		
		System.out.println("매핑완료");
	
		return "redirect:lectureView.htm";
	}
	
	@RequestMapping(value="lectureDelete.htm")
	public String deleteSubject(String subject_code){
		System.out.println("삭제 서브젝트 코드:"+subject_code);
		System.out.println("삭제컨트롤러");
		lectureservice.deleteSubject(subject_code);
		
		return "redirect:lectureView.htm";
	}
	
	@RequestMapping(value="lecturePost.htm")
	public String postSubject(String subject_code,Model model){
		
		System.out.println("신청 컨트롤러들어왓슴니당");
		CustomLectureMgDTO list = lectureservice.subjectDetail(subject_code);
		System.out.println("서비스통과"+list.getBefore_name());
		
	
		model.addAttribute("list",list);
		return "lecture.postsubject";		
	}
	
}
