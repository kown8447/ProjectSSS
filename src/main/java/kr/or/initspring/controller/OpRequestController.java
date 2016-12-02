
/*
 * @Class : OpRequestController
 * @Date : 2016.11.26
 * @Author : 성홍모
 * @Desc
 * 과목개설신청 관련된 로직을 처리하는 컨트롤러
 * 과목개설신청 리스트, 수정, 상세보기
*/

package kr.or.initspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import kr.or.initspring.dto.commons.OpRequestDTO;
import kr.or.initspring.dto.oprequest.OpRequestDetailDTO;
import kr.or.initspring.dto.oprequest.OpRequsetCheckDTO;
import kr.or.initspring.service.OpRequestService;


@Controller
@RequestMapping("/oprequest/")
public class OpRequestController {
	
	@Autowired
	private OpRequestService oprequestservice;
	
	@Autowired
	private View jsonview;
	
	/*
	 * @method Name : viewOpRequest
	 * @Author : 성홍모
	 * @description
	 * 관리자가 개설신청관리 페이지에서 신청 리스트를 출력하는 컨트롤러
	*/
	@RequestMapping("list.htm")
	public String viewOpRequest(Model model){
		List<OpRequsetCheckDTO> oprequest = oprequestservice.oprequestlist(model);
		return "oprequest.oprequestlist";
	}
	/*
	 * @method Name : requsetDetail
	 * @Author : 성홍모
	 * @description
	 * 관리자가 개설신청 상세보기를 해주는 컨트롤러
	*/
	@RequestMapping("requestDetail.htm")
	public String requsetDetail(@RequestParam("code") String subject_code, Model model){
		String view = "";
		boolean result = oprequestservice.detail(subject_code, model);
		
		if(result){
			view = "oprequest.opRequestDetail";
		}
		return view;
	}
	/*
	 * @method Name : updateCheck
	 * @Author : 성홍모
	 * @description
	 * 관리자가 개설신청을 수정해주는 컨트롤러, 성공시 리스트 보기 출력
	*/
	@RequestMapping("updateSuccess.htm")
	public String updateCheck(@RequestParam("code") String subject_code) throws Exception{
		
		String view = "";
		System.out.println("승인버튼 누를시 컨트롤러 " + subject_code);
	
		int result = oprequestservice.updateSuccess(subject_code);
		
		if(result == 1){
			view = "redirect:list.htm";
		}
		return view;
	}
	/*
	 * @method Name : UpdateReject
	 * @Author : 성홍모
	 * @description
	 * 관리자가 개설신청을 여부를 거절하게 해주는 컨트롤러
	*/
	@RequestMapping("UpdateReject.htm")
	public String UpdateReject(@RequestParam("code") String subject_code, @RequestParam("rejectReason") String reject_reason) throws Exception{
		
		String view = "";
		boolean result = oprequestservice.UpdateReject(subject_code, reject_reason);
		
		if(result==true){
			view ="redirect:list.htm"; 
		}
		return view;
	}
}
