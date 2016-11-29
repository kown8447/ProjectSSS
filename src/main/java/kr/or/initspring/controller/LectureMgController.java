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
import kr.or.initspring.dto.commons.PeriodDTO;
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

		System.out.println(principal.getName());
		lectureservice.selectBefore(principal.getName(),model);
		
    	return "lecture.registersubject";
    }
	
	@RequestMapping(value="lectureDetail.htm")
	public String detailSubject(Model model,String subject_code){
		CustomLectureMgDTO list = lectureservice.subjectDetail(subject_code);
		model.addAttribute("list",list);
		System.out.println(list.toString());
		
		return "lecture.subjectDetail";
	}
	
	@RequestMapping(value="lectureEdit.htm")
	public String updateSubject(Model model,String subject_code){
		System.out.println(subject_code);
		CustomLectureMgDTO list = lectureservice.subjectDetail(subject_code);
		model.addAttribute("list",list);
		
		return "lecture.subjectupdate";
	}
	
	@RequestMapping(value="lectureEditOk.htm")
	public String EditComplete(CustomLectureMgDTO dto){
		lectureservice.updatesubject(dto);
		
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
		int result = 0;
		result = lectureservice.insert_Subject(dto,before_code ,principal,required_choice, beforedto, majordto, liberdto ,department_code);
	
		return "redirect:lectureView.htm";
	}
	
	@RequestMapping(value="lectureDelete.htm")
	public String deleteSubject(String subject_code){
		lectureservice.deleteSubject(subject_code);
		
		return "redirect:lectureView.htm";
	}
	
	@RequestMapping(value="lecturePost.htm")
	public String postSubject(String subject_code,Model model){
		CustomLectureMgDTO list = lectureservice.subjectDetail(subject_code);
	
		model.addAttribute("list",list);
		return "lecture.postsubject";		
	}
	
	@RequestMapping(value="lecturePeriod.htm")
	public View getPeriod(Model model){
		List<PeriodDTO> periodlist = lectureservice.getPeriodList();
		model.addAttribute("periodlist",periodlist);
		return jsonview;
	}
	
	
	@RequestMapping(value="requestsubject.htm")
	public View viewtimetable(Model model,String building_code){
		List<CustomLectureMgDTO> list = lectureservice.selectBuilding(building_code);
		System.out.println(list.toString());

		model.addAttribute("classroom",list);
		return jsonview;
	}

	@RequestMapping(value="requestclassroom.htm")
	public View viewTimeTableByClassroomCode(Model model,String classroom_code){
		List<CustomLectureMgDTO> classroom = lectureservice.viewtimetable(classroom_code);
		model.addAttribute("time",classroom);
		
		return jsonview;
	}
	
}
