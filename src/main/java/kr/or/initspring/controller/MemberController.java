/*
 * @Class : MemberContorller
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 회원정보과 관련된 로직을 처리하는 컨트롤러.
 * 회원 정보 열람, 수정, 삭제 등
*/

package kr.or.initspring.controller;

import java.io.File;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import kr.or.initspring.dto.commons.CodeMgDTO;
import kr.or.initspring.service.CodeService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.initspring.dto.join.MemberDTO;
import kr.or.initspring.service.MemberService;

@Controller
@Secured({"ROLE_STUDENT", "ROLE_ADMIN", "ROLE_PROFESSOR"})
@RequestMapping("/member/")
public class MemberController{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private CodeService codeservice;
	
	private JavaMailSender mailSender;	//Email 전송을 위한 클래스
	
	@Autowired
	private VelocityEngine velocityEngine;	//HTML 파일을 통째로 전송하기 위한 velocity
	
	@RequestMapping(value="updatePwd.htm", method=RequestMethod.GET)
	public String updatePwd(){
		return "member.updatePwd";
	}
	
	@Autowired
	private View jsonview;	//비동기 처리를 위한 jsonview 객체
	   /*
	    * @method Name : editDetail
	    * @Author : 김영빈
	    * @description
	    * 사용자가 정보수정 클릭시 정보 상세내역들을 뿌려주기 위한 controller
	   */   
	@RequestMapping(value="edit.htm", method=RequestMethod.GET)
	public String editDetail(Model model,Principal principal ){	
		
		String member_id = principal.getName();
		MemberDTO member = memberservice.getMember(member_id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birth = sdf.format(member.getMember_birth());
		int year = Integer.parseInt(birth.substring(0, 4));
		int month =  Integer.parseInt(birth.substring(5, 7));
		
		int day =  Integer.parseInt(birth.substring(8, 10));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1 ); // <-- months start
											// at 0.
		cal.set(Calendar.DAY_OF_MONTH, day);
		Date date = new Date(cal.getTimeInMillis());
		
		String address = member.getMember_addr();
		String [] array = address.split("\\?");
		member.setAddr_num(array[0]);
		member.setMember_addr(array[1]);
		member.setMember_addr_detail(array[2]);
		member.setMember_birth(date);
		
		model.addAttribute("member",member);
		return "member.memberEdit";
	}
	   /*
	    * @method Name : editComplete
	    * @Author : 김영빈
	    * @description
	    * 사용자가 정보 수정한 정보를 받아서 사용자의 개인정보 db update 
	   */  
	@RequestMapping(value="edit.htm", method=RequestMethod.POST)
	public String editComplete(Principal principal ,MemberDTO member_temp ,HttpServletRequest request) throws Exception{
		String viewpage = "";
		String member_id = principal.getName();
		String pwd = bCryptPasswordEncoder.encode(member_temp.getMember_pwd());
		member_temp.setMember_addr(member_temp.getAddr_num()+"?" +member_temp.getMember_addr()+"?" +member_temp.getMember_addr_detail());
		
		String member_addr = member_temp.getMember_addr();
		String member_phone = member_temp.getMember_phone();
		String member_email = member_temp.getMember_email();
		int timetable_share = member_temp.getTimetable_share();
		boolean result = false;
		CommonsMultipartFile file = member_temp.getFile();
		String filename = file.getOriginalFilename();
		String path = request.getServletContext().getRealPath("/profiles");
		String fullpath = path + "\\" + filename;
		
		if (!filename.equals("")) {
			// 서버에 파일 쓰기 작업
			FileOutputStream fs = new FileOutputStream(fullpath);
			fs.write(file.getBytes());
			fs.close();
		}else{
			filename = memberservice.getFileName(member_id);
		}
		if(memberservice.getRole(member_id).equals("ROLE_STUDENT")){
			result = memberservice.updateStudentInfo(member_id, pwd,member_addr, member_phone , member_email,filename,timetable_share);
		}else{
			result = memberservice.updateMemberInfo(member_id, pwd,member_addr, member_phone , member_email,filename);
		}
		
		
		
		if(result==true){
			viewpage = "redirect:/index.htm";
		}else{
			viewpage = "redirect:edit.htm";
		}
		return viewpage;
	}
	/*
	 * @method Name : updatePwd
	 * @Author : 권기엽
	 * @description
	 * 회원의 비밀번호 찾기 이후, 임시비밀번호에서 정식 비밀번호로 바꾸는 함수
	 * 해당 함수가 정상적으로 진행될 경우, 비밀번호 변경 및 member_temp 컬럼의 값이 0 으로 변경됨
	*/
	@RequestMapping(value="updatePwd.htm", method=RequestMethod.POST)
	public String updatePwd(String member_pwd, Principal principal) throws Exception{
		String member_id = principal.getName();
		String pwd = bCryptPasswordEncoder.encode(member_pwd);
		boolean result = false;
		String viewpage = "";
		result = memberservice.updatePwd(member_id, pwd);
		
		if(result==true){
			viewpage = "redirect:/index.htm";
		}else{
			viewpage = "redirect:updatePwd.htm";
		}
		return viewpage;
	}
	
	/*
	 * @method Name : codeMg
	 * @Author : 성홍모
	 * @description
	 * 관리자가 코드 관리뷰로 이동
	*/
	@RequestMapping("code.htm")
	public String codeMg(){
		return "codemg.code";
		
	}
	/*
	 * @method Name : registerCode
	 * @Author : 성홍모
	 * @description
	 * 관리자가 코드 등록
	*/
	@RequestMapping(value="codeRegister.htm", method=RequestMethod.POST)
	public String registerCode(CodeMgDTO code){
		System.out.println("코드 등록 컨트롤");
		int result = 0;
		String viewpage = "";
		
		result = codeservice.insertCode(code);
		
		if(result == 1){
			viewpage = "codemg.code";
		}
		
		return viewpage;
	}
	/*
	 * @method Name : viewCodeList
	 * @Author : 성홍모
	 * @description
	 * 관리자가 코드 리스트 출력
	*/
	@RequestMapping("codelist.htm")
	public String viewCodeList(Model model){
		System.out.println("코드 리스트 보여주기 컨트롤");
		
		List<CodeMgDTO> codelist = codeservice.codelist();
		System.out.println(codelist);
		model.addAttribute("codelist", codelist);
		return "codemg.codelist";
	}
	/*
	 * @method Name : detailCode
	 * @Author : 성홍모
	 * @description
	 * 관리자가 코드 상세보기
	*/
	@RequestMapping("codedetail.htm")
	public String detailCode(Model model, String code){
		System.out.println("코드 상세보기 컨트롤");
		
		CodeMgDTO codeDTO = codeservice.detailcode(code);
		System.out.println(codeDTO);
		model.addAttribute("code", codeDTO);
		return "codemg.codedetail";
	}
	/*
	 * @method Name : updateCode
	 * @Author : 성홍모
	 * @description
	 * 관리자가 코드 수정
	*/
	@RequestMapping(value = "updateCode.htm", method=RequestMethod.POST)
	public String updateCode(Model model,  String code, int code_type, String code_name, Date code_birth){
		System.out.println("코드 수정하기 컨트롤");
		System.out.println(code + "/"+ code_type +"/"+ code_name +"/"+ code_birth);
		
		int result = 0;
		String viewpage = "";
		
		result = codeservice.updateCode(code, code_type, code_name, code_birth);
		CodeMgDTO codeDTO = new CodeMgDTO();
		
		codeDTO.setCode(code);
		codeDTO.setCode_type(code_type);
		codeDTO.setCode_name(code_name);
		codeDTO.setCode_birth(code_birth);
		
		model.addAttribute("code", codeDTO);
		
		if(result ==1){
			viewpage = "redirect:codelist.htm";
		}
		
		return viewpage;
	}
	/*
	 * @method Name : download
	 * @Author : 성홍모
	 * @description
	 * 코드 등록 양식을 액셀로 다운로드 받는 매소드
	*/
	@RequestMapping("excel.htm")
	public ModelAndView download(HttpServletRequest request, HttpServletResponse response){
		
		
		System.out.println("액셀컨트롤러");
		String baseDir = request.getRealPath("/WEB-INF/Template");
		
		File downloadFile = new File(baseDir,"code_mg.xlsx");
		
		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : excelUpload
	 * @Author : 성홍모
	 * @description
	 * 액셀로 코드 등록을 일괄처리하는 매소드
	*/
	@RequestMapping(value = "compExcelUpload.htm", method=RequestMethod.POST)
	public View excelUpload(MultipartHttpServletRequest request, Model model){
		System.out.println("exceForm 실행후 컨트롤러 실험");	
		codeservice.insertExcelList(request, model);

		return jsonview;
	}
	/*
	 * @method Name : deleteCode
	 * @Author : 성홍모
	 * @description
	 * 코드를 삭제하는 매소드
	*/
	@RequestMapping(value = "codeDelete.htm", method = RequestMethod.POST)
	public View deleteCode(String code, Model model){
		System.out.println("code =" + code);
		System.out.println("삭제 컨트롤러!");
		
		boolean result=false;
		
		int dbResult = codeservice.deleteCode(code,model);
		
		if(dbResult >0){
			System.out.println("성공");
			result = true;
		}else if(dbResult==0){
			model.addAttribute("reason", "삭제대상이 이미 제거 되어 있습니다");
		}
		
		model.addAttribute("result", result);
		
		return jsonview;
	}
	
	
	/*
	    * @method Name : authEmail
	    * @Author : 김영빈
	    * @description
	    * 이메일 인증을 위한 함수.
	    * 사용자로부터 입력받은 메일을 테이블과 대조하여, 유효한 메일인지 확인.
	    * 확인이 된 메일은 인증번호(sessionID())를 html에 담아서 발송 -> 발송시에는 velocityEngine을 사용하여 통째로 전송
	   */   
	@RequestMapping("authEmail.htm")
	public View authEmail(String member_email, HttpServletRequest request, Model model){
		
		HttpSession session = request.getSession();
		
		final String sessionID = session.getId();
		final String address = member_email;
		
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(address);
				message.setFrom("kown8447@gmail.com");
				message.setSubject("SSS 회원가입 인증 메일");
				final Map<String, Object> model = new HashMap<String, Object>();
				model.put("authkey", sessionID);
				final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, 
								"authMail.html","UTF-8",model);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);
		
	
		model.addAttribute("mailresult", "success");
		model.addAttribute("sessionID", sessionID);
		return jsonview;
	}
	
}
