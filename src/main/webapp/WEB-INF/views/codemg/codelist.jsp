<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(function() {
		$('.deleteCode').click(function() {

			var strArray = $(this).attr("id").split("_");
			var btnNum = strArray[strArray.length - 1];

			$.ajax({
				url : "codeDelete.htm",
				method : "post",
				dataType : "json",
				data : {
					code : $('#code' + btnNum).text()
				},
				success : function(data) {
					console.log(data);
					 if (data.result) {
						alert("성공!!!");
						location.href = "codelist.htm";
					} else {
						alert(data.reason);
					} 
				}
			});

		});
	});
</script>

<table>
	<thead>
		<tr>
			<td>구분</td>
			<td>코드</td>
			<td>이름</td>
			<td>생년월일</td>

		</tr>
	</thead>
	<c:forEach items="${codelist}" var="code" varStatus="index">
		<tbody>
			<tr>
				<td>
					<c:choose>
						<c:when test="${code.code_type ==0}">
							학생
						</c:when>
						<c:when test="${code.code_type ==1}">
							교수
						</c:when>
						<c:otherwise>
							관리자
						</c:otherwise>
					</c:choose>
				<%-- ${code.code_type} --%>
				</td>
				<td id="code${index.count}">${code.code}</td>
				<td><a href="codedetail.htm?code=${code.code}">${code.code_name}</a>
				</td>
				<td>${code.code_birth}</td>
				<td>
					<button class="deleteCode" id="deleteCodeBtn_${index.count}"
						name="deleteCodeBtn">삭제하기</button>
				</td>
			</tr>
		</tbody>
	</c:forEach>
</table>
<a href="code.htm">되돌아가기</a>
