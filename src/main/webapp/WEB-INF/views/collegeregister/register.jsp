<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : register.jsp
   @Author : 최준호
   @Data : 2016.11.30
   @Desc : 등록 장학정보 조회 페이지
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="${pageContext.request.contextPath}/js/collegeRegister/register.js"></script>
<div class="wrapper">
  <div class="container">
     등록/장학
    <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs" id="studentRegisterTabs">
      <ul id="myTab" class="nav nav-tabs nav-tabs-responsive" role="tablist">
        <li role="presentation" class="active">
          <a href="#studentRegister" id="studentRegister-tab" role="tab" data-toggle="tab" aria-controls="studentRegister" aria-expanded="true">
            <span class="text">등록</span>
          </a>
        </li>
        <li role="presentation" class="next">
          <a href="#studentSemesterState" role="tab" id="studentSemesterState-tab" data-toggle="tab" aria-controls="studentSemesterState">
            <span class="text">재학</span>
          </a>
        </li>
        <li role="presentation">
          <a href="#studentScholaship" role="tab" id="studentScholaship-tab" data-toggle="tab" aria-controls="studentScholaship">
            <span class="text">장학</span>
          </a>
        </li>
        <li role="presentation">
          <a href="#studentAbsence" role="tab" id="studentAbsence-tab" data-toggle="tab" aria-controls="studentAbsence">
            <span class="text">휴학</span>
          </a>
        </li>
      </ul>
      <div id="myTabContent" class="tab-content">
        <div role="tabpanel" class="tab-pane fade in active" id="studentRegister" aria-labelledby="studentRegister-tab">
          <table class="table table-bordered">
         <tr>
            <th>년도</th>
            <th>분기</th>
            <th>기간</th>
            <th>등록유형</th>
            <th>등록금액</th>
            <th>등록상태</th>
         </tr>
         <c:forEach var="regist" items="${registerList}">
            <tr>
               <td>${regist.semesterYear}</td>
               <td>${regist.semesterType}</td>
               <td>${regist.semester_start}~${regist.semester_end}</td>
               <td><c:choose>
                     <c:when test="${regist.register_type==1}">
             계절학기
            </c:when>
                     <c:when test="${regist.register_type==2}">
            졸업연기
            </c:when>
                     <c:otherwise>
            일반
            </c:otherwise>
                  </c:choose></td>
               <td>${regist.tuition}</td>
               <td><c:choose>
                     <c:when test="${regist.register_state==1}">
             등록
            </c:when>

                     <c:otherwise>
            미납
            </c:otherwise>
                  </c:choose></td>
            </tr>
         </c:forEach>
      </table>
        </div>
        <div role="tabpanel" class="tab-pane fade" id="studentSemesterState" aria-labelledby="studentSemesterState-tab">
          <p>
           <table class="table table-bordered">
         <tr>
            <th>년도</th>
            <th>분기</th>
            <th>학년</th>
            <th>학기</th>
            <th>해당학기 신청학점</th>
            <th>해당학기 이수학점</th>
         </tr>
         <c:forEach var="semester" items="${studentSemesterList}">
            <tr>
               <td>${semester.semesterYear}</td>
               <td>${semester.semesterType}</td>
               <td>${semester.student_grade}</td>
               <td>${semester.student_semester}</td>
               <td>${semester.request_credit}</td>
               <td>${semester.get_credit}</td>
            </tr>
         </c:forEach>
         </table>
          
        </div>
        
        <div role="tabpanel" class="tab-pane fade" id="studentScholaship" aria-labelledby="studentScholaship-tab">
         <table class="table table-bordered">
         <tr>
            <th>번호</th>
            <th>년도</th>
            <th>분기</th>
            <th>장학명</th>
            <th>장학금액</th>
            <th>선발기준</th>
         </tr>
         <c:forEach var="scholarship" items="${scholarshipList}">
            <tr>
               <td>${scholarship.scholashipIndex}</td>
               <td>${scholarship.semesterYear}</td>
               <td>${scholarship.semesterType}</td>
               <td>${scholarship.scholaship_name}</td>
               <td>${scholarship.scholaship_amount}</td>
               <td>${scholarship.scholaship_standard}</td>
            </tr>
         </c:forEach>
        </table>
        </div>
        
         <div role="tabpanel" class="tab-pane fade" id="studentAbsence" aria-labelledby="studentAbsence-tab">
         <table class="table table-bordered">
         <tr>
            <th>번호</th>
            <th>휴학신청일</th>
            <th>휴학기간(학기)</th>
            <th>휴학사유</th>
            <th>복학예정일</th>
         </tr>
         <c:forEach var="absence" items="${absenceList}">
            <tr>
               <td>${absence.absenceIndex}</td>
               <td>${absence.absence_date}</td>
               <td>${absence.absence_term}</td>
               <td>${absence.absence_reason}</td>
               <td>${absence.return_date}</td>
            </tr>
         </c:forEach>
      </table>
        </div>
      </div>
    </div>
  </div>
</div>