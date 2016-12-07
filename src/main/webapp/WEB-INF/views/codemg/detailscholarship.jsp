<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class = "row">
<div class = "col-sm-3"></div>
<div class="col-sm-6"  >
<h4>▶&nbsp;장학생 상세정보 </h4>
<br><br>
<form action="updateScholarship.htm" id="updateScholarship_from">
	<div style="border: 1px solid green; padding: 3%; border-radius: 1em;  margin: auto;">
		<c:set value="${scholarship}" var="sl"/>
		<c:set value="${scsystem}" var="sc"/>
		<c:set value="${semester}" var="sm"/>
		<table class="table">
           <tr>
           		<td>장학수혜코드</td>
           		<td>
           			<div class="col-sm-6">
                        <input type="text" id="scholarship_code" name="scholarship_code" value="${sl.scholarship_code }" readonly="readonly" class="form-control">
                    </div>
           		</td>
           </tr>
           <tr>
           		<td>학번</td>
           		<td>
           			<div class="col-sm-6">
                        <input type="text" id="student_code" name="student_code" value="${sl.student_code }" readonly="readonly" class="form-control">
                    </div>
           		</td>
           </tr>
           <tr>
           		<td>장학코드</td>
           		<td>
           			<div class="col-sm-6">
                        <select id="sys_code" name="sys_code" class="form-control">
							<c:forEach items="${scsystem}" var="sclsit">
							<option value="${sclsit.sys_code }">${sclsit.scholaship_name }</option>
							</c:forEach>
						</select>
                    </div>
           		</td>
           </tr>
           <tr>
           		<td>학기코드</td>
           		<td>
           			<div class="col-sm-6">
                        <select id="semester_code" name="semester_code" class="form-control">
							<c:forEach items="${semester}" var="smlsit">
							<option value="${smlsit.semester_code }">${smlsit.semester_name }</option>
							</c:forEach>
						</select>
                    </div>
           		</td>
           </tr>
           <tr>
           		<td>지급일</td>
           		<td>
           			<div class="col-sm-6">
                        <input type="text" id="scholarship_payday" name="scholarship_payday" value="${sl.scholarship_payday }" class="form-control">
                    </div>
           		</td>
           </tr>
        </table>    
	</div>
</form>
	<div align="center">
		<a href="registerscholarship.htm"><button class="btn btn-default">목록</button></a> &nbsp;&nbsp;<button type="submit" id ="edit_scholarship" class="btn btn-success">수정하기</button>
	</div>
</div>
</div>