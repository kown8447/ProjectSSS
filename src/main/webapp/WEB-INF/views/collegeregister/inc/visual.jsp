<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="nav-side-menu">
    <div class="brand">학 적 조 회</div>
    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
        <div class="menu-list">
            <ul id="menu-content" class="menu-content collapse out" align="center">
                <li  class="collapsed active">
                  <a href="${pageContext.request.contextPath}/collegeregister/viewstudent.htm" menuid="10" followlink="true">개인정보</a>
                </li>

                <li>
                   <a href="${pageContext.request.contextPath}/collegeregister/record.htm" menuid="10" followlink="true">성적조회</a>
                </li>
            
                <li>
                  <a href="${pageContext.request.contextPath}/collegeregister/register.htm">등록/장학</a>
                </li>   
            </ul>
     </div>
</div>