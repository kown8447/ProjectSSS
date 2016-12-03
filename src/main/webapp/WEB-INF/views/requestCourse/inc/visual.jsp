<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="nav-side-menu">
    <div class="brand">학 적 조 회</div>
    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
        <div class="menu-list">
            <ul id="menu-content" class="menu-content collapse out" align="center">
                <li  class="collapsed active">
                  <a href="${pageContext.request.contextPath}/requestcourse/courseMain.htm">수강안내</a>
                </li>

                <li>
                   <a href="${pageContext.request.contextPath}/requestcourse/viewOpLecture.htm">개설강의</a>
                </li>
            
                <li>
                  <a href="${pageContext.request.contextPath}/requestcourse/preRegister.htm">예비수강신청</a>
                </li>  
                
                <li>
                  <a href="${pageContext.request.contextPath}/requestcourse/searchOtherTimetable.htm">타학생예비시간표조회</a>
                </li>   
                
                <li>
                  <a href="${pageContext.request.contextPath}/requestcourse/realRegiser.htm">수강신청</a>
                </li>  
                
                <li>
                  <a href="${pageContext.request.contextPath}/requestcourse/correctRegiser.htm">수강정정</a>
                </li>    
            </ul>
     </div>
</div>