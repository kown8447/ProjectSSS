<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(function() {
		$('.deleteCode').click(function() {

			var strArray = $(this).attr("id").split("_");
			var btnNum = strArray[strArray.length - 1];

			$.ajax({
				url : "buildingDelete.htm",
				method : "post",
				dataType : "json",
				data : {
					building_code : $('#code' + btnNum).text()
				},
				success : function(data) {
					console.log(data);
					 if (data.result) {
						alert("성공!!!");
						location.href = "buildingList.htm";
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
				<td>건물코드</td>
				<td>건물명</td>
				<td>주소</td>
			</tr>
		</thead>
		<c:forEach items="${building}" var="bd" varStatus="index">
			<tbody>
				<tr>
					<td id="code${index.count}">${bd.building_code}</td>
					<td><a href="buildingDetail.htm?building_code=${bd.building_code}">${bd.building_name}</a></td>
					<td>${bd.building_addr}</td>
					<td>
						<button class="deleteCode" id="deleteCodeBtn_${index.count}" name="deleteCodeBtn">삭제하기</button>
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<a href="code.htm">되돌아가기</a>

