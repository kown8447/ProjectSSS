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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.BuildingDTO;
import kr.or.initspring.dto.commons.LiberalDTO;
import kr.or.initspring.dto.commons.MajorDTO;
import kr.or.initspring.dto.commons.PeriodDTO;
import kr.or.initspring.dto.commons.SemesterDTO;
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
	
	@RequestMapping(value="lectureRegister.htm",method=RequestMethod.POST)
	public String insertsubject(SubjectDTO dto,String before_code,Principal principal,String required_choice,
					BeforeSubjectDTO beforedto,MajorDTO majordto,LiberalDTO liberdto,String department_code) throws Exception{
		int result = 0;
		System.out.println("컨트롤러");
		result = lectureservice.insert_Subject(dto,before_code ,principal,required_choice, beforedto, majordto, liberdto ,department_code);
	
		return "redirect:lectureView.htm";
	}
	
	
	@RequestMapping(value="lectureDetail.htm")
	public String detailSubject(Model model,String subject_code){
		CustomLectureMgDTO list = lectureservice.subjectDetail(subject_code);
		model.addAttribute("list",list);
		System.out.println(list.toString());
		
		return "lecture.subjectDetail";
	}
	
	@RequestMapping(value="lectureEdit.htm")
	public String updateSubject(Model model,String subject_code,Principal principal){
		System.out.println(subject_code);
		CustomLectureMgDTO list = lectureservice.subjectDetail(subject_code);
		lectureservice.selectBefore(principal.getName(), model);
		model.addAttribute("list",list);
		
		return "lecture.subjectupdate";
	}
	
	@RequestMapping(value="lectureEditOk.htm")
	public String EditComplete(CustomLectureMgDTO dto){
		try {
			lectureservice.updatesubject(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	
	@RequestMapping(value="lectureDelete.htm")
	public String deleteSubject(String subject_code){
		lectureservice.deleteSubject(subject_code);
		
		return "redirect:lectureView.htm";
	}
	
	@RequestMapping(value="lecturePost.htm")
	public String postSubject(String subject_code,Model model,String success_check){
		CustomLectureMgDTO list = new CustomLectureMgDTO();
		
		//if == 재신청할때
		if(success_check.equals("2")){
			lectureservice.deleteForReSubject(subject_code);
			list = lectureservice.subjectDetail(subject_code);		
		}else{
			list = lectureservice.subjectDetail(subject_code);
		}
		System.out.println(success_check);
		
		List<SemesterDTO> semester = lectureservice.getSemester();
		model.addAttribute("semester",semester);
		model.addAttribute("list",list);
		return "lecture.postsubject";		
	}
	
	@RequestMapping(value="lecturePeriod.htm")
	public View getPeriod(Model model,String professor_code){
		List<PeriodDTO> periodlist = lectureservice.getPeriodList();
		List<BuildingDTO> buildinglist = lectureservice.getBuildingName();
		List<String> myclass = lectureservice.selectMyTime(professor_code);
		
		model.addAttribute("periodlist",periodlist);
		model.addAttribute("buildinglist",buildinglist);
		model.addAttribute("myclass", myclass);
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
	
	@RequestMapping(value="postRequestSubject.htm",method=RequestMethod.POST)
	public String setSubject(CustomLectureMgDTO dto,HttpServletRequest request) throws Exception{
		
		String success_check = request.getParameter("success_check");
		lectureservice.RequestSubject(dto,request,success_check);
		
		return "redirect:lectureView.htm";
		
	}
	
	@RequestMapping(value="selectStudent.htm")
	public View selectStudent(String subject_code,Model model){
		
		System.out.println("학생리스트출력스"+subject_code);
		List<CustomLectureMgDTO> dto = lectureservice.select_Studentlist(subject_code);
		
		System.out.println("학생디티오:"+dto.toString());
		model.addAttribute("student",dto);
		
		return jsonview;
	}
	
	@RequestMapping(value="lectureMyclass.htm")
	public String myclass(Principal principal,Model model){
		
		List<CustomLectureMgDTO> dto = lectureservice.selectMyclass(principal);
		
		model.addAttribute("myclass",dto);
		return "lecture.studentmain";
	}
	
	@RequestMapping(value="insertGrade.htm")
	
	public String insertGrade(String subject_code,String student_code,String semester_code,String record_level,String subject_name){
		
		System.out.println(subject_code);
		System.out.println(student_code);
		System.out.println(semester_code);
		System.out.println(record_level);

		CustomLectureMgDTO dto = new CustomLectureMgDTO();
		dto.setSubject_code(subject_code);
		dto.setStudent_code(student_code);
		dto.setSemester_code(semester_code);
		dto.setRecord_level(record_level);
		dto = lectureservice.insertgrade(dto);
		System.out.println("인쑤얼트그루에이드");
		
		return "lecture.studentmain";
		
	}
	
	@RequestMapping(value="lectureRemoveTime.htm")
	public View selectTimetable(String professor_code,String choice_code,Model model){
		
		System.out.println("폴페서코더드:"+professor_code);
		System.out.println("선택한 코드:"+choice_code);
		
		String choice = "성공";
		
		List<String> mytime = lectureservice.selectMyTime(professor_code);
		System.out.println("마타임:"+mytime.size());
		
		for(int i = 0; i < mytime.size() ; i++){
			if(mytime.get(i).equals(choice_code)){
				choice = "실패";
			}
		}
		model.addAttribute("choice", choice);
		System.out.println("초이스"+choice);
		return jsonview;
		
	}

	
	
	
	
}
