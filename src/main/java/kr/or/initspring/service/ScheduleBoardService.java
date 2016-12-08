package kr.or.initspring.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.initspring.dao.ScheduleBoardDAO;
import kr.or.initspring.dto.commons.Academic_CalendarDTO;
@Service
public class ScheduleBoardService {

      @Autowired
      private SqlSession sqlsession;
      /*
       * @method Name : getList
       * @Author : 김영빈
       * @description
       * 학사일정 전부를 얻어오는 함수 
      */   
      public List<Academic_CalendarDTO> getList(){
         ScheduleBoardDAO scheduleBoardDAO = sqlsession.getMapper(ScheduleBoardDAO.class);
         List<Academic_CalendarDTO> list = scheduleBoardDAO.getScheduleBoardList();
         return list;
      }
      /*
       *  @method Name : insert
       * @Author : 김영빈
       * @description
       * 학사일정을 db 에 추가하는 함수 
       */   
      public int insert(Academic_CalendarDTO dto){
         ScheduleBoardDAO scheduleBoardDAO = sqlsession.getMapper(ScheduleBoardDAO.class);
         return scheduleBoardDAO.insertScheduleBoard(dto);
      }
      /*
       * @method Name : delete
       * @Author : 김영빈
       * @description
       * 학사일정을 db에 삭제하는 함수 
      */   
      public int delete(String id){
         ScheduleBoardDAO scheduleBoardDAO = sqlsession.getMapper(ScheduleBoardDAO.class);
         return scheduleBoardDAO.deleteScheduleBoard(id);
      }
      /*
       * @method Name : update
       * @Author : 김영빈
       * @description
       * 학사일정을 db에 update
      */   
      public int update(Academic_CalendarDTO dto){
         ScheduleBoardDAO scheduleBoardDAO = sqlsession.getMapper(ScheduleBoardDAO.class);
         return scheduleBoardDAO.updateScheduleBoard(dto);
      }
      /*
           * @method Name : insertExcel
           * @Author : 김영빈
           * @description
           * 엑셀에 있는 정보를 db 에 옮김
          */   
      @Transactional(rollbackFor = { Exception.class, SQLException.class })
      public int insertExcel(Academic_CalendarDTO dto){
         int result = 0;
         ScheduleBoardDAO scheduleBoardDAO = sqlsession.getMapper(ScheduleBoardDAO.class);
         result = scheduleBoardDAO.insertScheduleBoard(dto);
         return result;
      }
      /*
       * @method Name : insertExcelList
       * @Author : 김영빈
       * @description
       * 엑셀에 있는 정보를 읽어 db에 insert
      */   
      @Transactional(rollbackFor = { Exception.class, SQLException.class })
      public void insertExcelList(MultipartHttpServletRequest request, Model model){
         ScheduleBoardDAO scheduleBoardDAO = sqlsession.getMapper(ScheduleBoardDAO.class);
         MultipartFile file = request.getFile("excel");
         Academic_CalendarDTO academic_CalendarDTO = new Academic_CalendarDTO();
         boolean result = false;
         if(file != null && file.getSize() > 0){
            try{
               Workbook workbook = new XSSFWorkbook(file.getInputStream());
               Sheet sheet = workbook.getSheetAt(0);
               int last = sheet.getLastRowNum();
             
               for(int i = 1; i<= last; i++){
                  Row row = sheet.getRow(i);
                  String calendar_title = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
                  String calendar_content = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
                  String calendar_start = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
                  String calendar_end = row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
               
                  java.sql.Date  to =null;
                  java.sql.Date  to1 =null;
                  try {
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     to = new Date(sdf.parse(calendar_start).getTime());
                     to1 = new Date(sdf.parse(calendar_end).getTime());
                  } catch (Exception e) {
                     e.printStackTrace();
                  }
                  academic_CalendarDTO.setCalendar_title(calendar_title);
                  academic_CalendarDTO.setCalendar_content(calendar_content);
                  academic_CalendarDTO.setCalendar_start(to);
                  academic_CalendarDTO.setCalendar_end(to1);
                  scheduleBoardDAO.insertScheduleBoard(academic_CalendarDTO);
               
               }
            }catch(Exception e){
         
            }
         }
         result = true;
         model.addAttribute("result", result);
      }
}