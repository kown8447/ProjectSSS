package kr.or.initspring.controller;


import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import kr.or.initspring.dao.ScheduleBoardDAO;
import kr.or.initspring.dto.commons.Academic_CalendarDTO;

import kr.or.initspring.service.ScheduleBoardService;

@Controller
@RequestMapping("/schedule/")
public class ScheduleBoardController {
   @Autowired
   private SqlSession sqlsession;

   @Autowired
   private View jsonview;
   
   @Autowired
   private ScheduleBoardService scheduleBoardService;
   
   /*
    * @method Name : goview
    * @Author : 김영빈
    * @description
    * 요청을 받아 callendar 뿌려줌 
   */   
   @RequestMapping("/schedule.htm")
   public String goview(){
      return "schedule.callendar";
   }
   /*
    * @method Name : open
    * @Author : 김영빈
    * @description
    * db 에 있는 정보들을  callendar에 뿌려줌 
   */   
   @RequestMapping("/scview.htm")
   public View open(ModelMap map){
      List<Academic_CalendarDTO> list = scheduleBoardService.getList();
      map.addAttribute("list",list);
      return jsonview;
   }
   /*
    * @method Name : scinsert
    * @Author : 김영빈
    * @description
    * db 에  학사일정 추가한 내용 insert
   */   
   @RequestMapping(value="/scinsert.htm", method=RequestMethod.POST)
   public View scinsert(ModelMap map , String title, String content , String start , String end) throws ParseException {
      Academic_CalendarDTO dto = new Academic_CalendarDTO();
       SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
       Date to = new Date(sdf.parse(start).getTime());
       Date to1 = new Date(sdf.parse(end).getTime());
       dto.setCalendar_content(content);
       dto.setCalendar_title(title);
       dto.setCalendar_start(to);
       dto.setCalendar_end(to1);
       int result = scheduleBoardService.insert(dto);
       map.addAttribute("result",result);
       return jsonview;
   }
   /*
    * @method Name : scdelte
    * @Author : 김영빈
    * @description
    * db 에  있는  학사일정을 삭제 
   */   
   @RequestMapping(value="/scdelete.htm", method=RequestMethod.POST)
   public View scdelte(ModelMap map , String id){
      int result = scheduleBoardService.delete(id);
      map.addAttribute("result",result);
      return jsonview;
   }
   /*
    * @method Name : scupdate
    * @Author : 김영빈
    * @description
    * db 에  있는  학사일정을 update
   */   
   @RequestMapping(value="/scupdate.htm", method=RequestMethod.POST)
   public View scupdate(ModelMap map , String id, String title, String content , String start , String end) throws ParseException{
      Academic_CalendarDTO dto = new Academic_CalendarDTO();
     SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      Date to = new Date(sdf.parse(start).getTime());
      Date to1 = new Date(sdf.parse(end).getTime());
      
      
      dto.setCalendar_code(id);
      dto.setCalendar_content(content);
      dto.setCalendar_title(title);
      dto.setCalendar_start(to);
      dto.setCalendar_end(to1);
      int result = scheduleBoardService.update(dto);
      map.addAttribute("result",result);
      return jsonview;
   }

   /*
    * @method Name : excelUpload
    * @Author : 김영빈
    * @description
    * 엑셀 업로드 함수 
   */  
   @RequestMapping(value = "compExcelUpload.htm", method=RequestMethod.POST)
   public View excelUpload(MultipartHttpServletRequest request, Model model){
      scheduleBoardService.insertExcelList(request, model);
      return jsonview;
   }
   /*
    * @method Name : download
    * @Author : 김영빈
    * @description
    * 엑셀 다운로드 
   */   
   @SuppressWarnings("deprecation")
   @RequestMapping("excel.htm")
   public ModelAndView download(HttpServletRequest request, HttpServletResponse response ){
      String baseDir = request.getRealPath("/WEB-INF/Template");
      File downloadFile = new File(baseDir,"Schedule.xlsx");
      
      return new ModelAndView("pageView", "downloadFile", downloadFile);
     }


}