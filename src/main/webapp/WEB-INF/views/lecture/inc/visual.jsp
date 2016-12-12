<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="nav-side-menu">
    <div class="brand">교수 메뉴</div>
    <i class="toggle-btn" data-toggle="collapse" data-target="#menu-content">▼</i>
        <div class="menu-list">
            <ul id="menu-content" class="menu-content collapse out" align="center">
                <li  class="collapsed active">
                <a href="${pageContext.request.contextPath}/lecture/lectureView.htm">과목목록</a>
              
                </li>
                <li>
                  <a href="${pageContext.request.contextPath}/lecture/lectureRegister.htm">과목등록</a>
                </li>
			
                <li>
                   <a href="${pageContext.request.contextPath}/lecture/lectureMyclass.htm">성적입력</a>
                </li>
            
               
            </ul>
     </div>
</div>

