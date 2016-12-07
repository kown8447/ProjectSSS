<%--
@Project : InitSpring
@File name : registersubject.jsp
@Author : 조장현
@Data : 2016.11.23
@Desc : 과목 등록하기
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/lecture/registersubject.js"></script>
<body>
<h4>▶&nbsp;과목 등록</h4><br><br>
   <div class="container">
   
      <div class="form-group">
         
            <form action="lectureRegister.htm" method="post">
            <div style="border: 1px solid green; padding: 3%; border-radius: 1em; width: 60%; margin: auto;">
               <div class="form-inline">
                  <label class="col-sm-2 control-label col-sm-offset-2">구분</label>
                  <select id="subject_type" name="subject_type" class="form-control col-sm-offset-1 ">
                     <option value=0>전공</option>
                     <option value=1>교양</option>
                  </select> 
                  <select id="required_choice" name="required_choice" class="form-control ">
                     <option value=0>필수</option>
                     <option value=1>선택</option>
                  </select>
               </div><br>
                <div class="form-inline">
                
                  <label class="col-sm-2 control-label col-sm-offset-2">선수과목</label>
                     <select id="before_code" name="before_code" class="form-control col-sm-offset-1 ">
                        <option value="0">없음</option>
                           <c:forEach items="${before}" var="i">
                              <option value="${i}">${i}</option>
                           </c:forEach>
                     </select>
               </div>
                   <br>
                <div class="form-inline"> 
                  <label class="col-sm-2 control-label col-sm-offset-2">과목명</label> <input type="text" id="subject_name" name="subject_name" class="form-control col-sm-offset-1">
               </div>
                  <br>    
               <div class="form-inline">     
                  <label class="col-sm-2 control-label col-sm-offset-2">정원</label> <input type="number" id="subject_seats" name="subject_seats" class="form-control col-sm-offset-1">
               </div>
                  <br>
               <div class="form-inline">               
                   <label class="col-sm-2 control-label col-sm-offset-2">학점</label> <input type="number" id="subject_credit" name="subject_credit" class="form-control col-sm-offset-1">
               </div>
                  <br>
               <div class="form-inline">     
                  <label class="col-sm-2 control-label col-sm-offset-2">수강대상</label>    <input type="number" id="grade_limit" name="grade_limit" class="form-control col-sm-offset-1">
               </div>
               <br>
         </div><br>
         <div align="center">
         <input type="button" value="취소" id="cancel" class="btn btn-default">
         <input type="submit" id="submit" value="등록하기" class="btn btn-success">
      </div>
   </form>
   </div>
</div>
</body>
</html>