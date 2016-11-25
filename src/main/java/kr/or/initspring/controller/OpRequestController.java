package kr.or.initspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import kr.or.initspring.dto.commons.OpRequestDTO;
import kr.or.initspring.service.OpRequestService;


@Controller
@RequestMapping("/oprequest/")
public class OpRequestController {
	
	@Autowired
	private OpRequestService oprequestservice;
	
	@Autowired
	private View jsonview;
	
	@RequestMapping("list")
	public String viewOpRequest(Model model){
		System.out.println("oprequset list보기 컨트롤");
		
		List<OpRequestDTO> oprequest = oprequestservice.oprequestlist();
		model.addAttribute("oprequestlist", oprequest);
		
		return "oprequest.oprequestlist";
	}
}
