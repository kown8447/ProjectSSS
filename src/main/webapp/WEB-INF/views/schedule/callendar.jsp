<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="se"
	uri="http://www.springframework.org/security/tags"%>
<!-- 
   @Project : InitSpring
   @File name : callendar.jsp
   @Author : 김영빈
   @Data : 2016.11.28
   @Desc : 캘린더 view
-->        	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 캘린더  -->
	<div class="container">
		<div id='calendar'></div>
	</div>
	<!-- 캘린더  일정추가 모달-->
	<se:authorize access="hasAnyRole('ROLE_ADMIN')">
		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">학사일정 추가</h4>
					</div>
					<div class="modal-body">

						<form>
							제목 : <input type="text" id="title" name="title" /><br> 내용 :
							<input type="text" id="content" name="content" /><br> <label
								for="" class="wrapper"> 시작일 :<input id="startdate"
								name="startdate" type="text" class="datepicker"
								placeholder="Click me!" /><br> <label for=""
								class="wrapper"> 종료일 :<input id="enddate" name="enddate"
									type="text" class="datepicker" placeholder="Click me!" /><br>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" id="insert">학사일정
							추가</button>
					</div>
				</div>
			</div>
		</div>
	</se:authorize>
<!-- 캘린더 클릭시 모달   -->
	
	<div id="fullCalModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span> <span class="sr-only">close</span>
					</button>
					<h4 id="modalTitle" class="modal-title"></h4>
				</div>
				<div id="modalBody" class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<se:authorize access="hasAnyRole('ROLE_ADMIN')">
						<button class="btn btn-primary" id="update">학사 일정 수정</button>
						<button class="btn btn-primary" id="delete">학사 일정 삭제</button>
				</se:authorize>
				</div>
			</div>
		</div>
	</div>

<!-- 캘린더 일정 수정 모달  -->
	<div class="modal fade" id="updatemodal" tabindex="-1" role="dialog"
		aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">학사일정 수정</h4>
				</div>
				<div class="modal-body">
					<form>
						제목 : <input type="text" id="title1" name="title" /> 내용 : <input
							type="text" id="content1" name="content" /> <label for=""
							class="wrapper"> 시작일 :<input id="startdate1"
							name="startdate" type="text" class="datepicker" /> <label for=""
							class="wrapper"> 종료일 :<input id="enddate1" name="enddate"
								type="text" class="datepicker" />
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="up">학사일정
						수정 완료</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 캘린더 일정 일괄 등록  form-->
	<se:authorize access="hasAnyRole('ROLE_ADMIN')">
		<form id="excelForm" action="compExcelUpload.htm">
			학사일정 일괄등록: <input id="excel" name="excel" type="file" accept=".xlsx" placeholder="Text input">
			<button type="button" id="excelUp" onclick="check()">등록하기</button>
		</form>
			
		<a href="excel.htm"> 양식다운로드 </a>
	</se:authorize>
</body>

</html>