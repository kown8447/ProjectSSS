<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="nav-side-menu">
    <div class="brand">게 시 판</div>
    <i class="toggle-btn" data-toggle="collapse" data-target="#menu-content">▼</i>
        <div class="menu-list">
            <ul id="menu-content" class="menu-content collapse out" align="center">
                <li  class="collapsed active">
                  <a href="${pageContext.request.contextPath}/notice/notice.htm">공지사항</a>
                </li>

                <li>
                   <a href="${pageContext.request.contextPath}/qnanotice/qnanotice.htm">Q & A</a>
                </li>
            
                <li>
                  <a href="${pageContext.request.contextPath}/schedule/schedule.htm" >학사일정</a>
                </li>     
            </ul>
     </div>
</div>

