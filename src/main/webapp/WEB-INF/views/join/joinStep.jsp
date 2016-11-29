<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : joinStep.jsp
   @Author : 송아름
   @Data : 2016.11.22
   @Desc : 회원가입 대상 선택 view
-->

<div align="center">
	<img src="${pageContext.request.contextPath}/images/joinheader.jpg" style="margin-bottom: 5%;">
	<table>
		<tr>
			<td>
				<button type="button" name="code_type" id="code_type" class="btn"
					style="border: 0px; background-color: white;">
					<img src="${pageContext.request.contextPath}/images/sjoin.jpg">
				</button>
			</td>
			<td>
				<button type="button" name="code_type" id="code_type1" class="btn"
					style="border: 0px; background-color: white;">
					<img src="${pageContext.request.contextPath}/images/pjoin.jpg">
				</button>
			</td>
			<td>
				<button type="button" name="code_type" id="code_type2" class="btn"
					style="border: 0px; background-color: white;">
					<img src="${pageContext.request.contextPath}/images/ajoin.jpg">
				</button>
			</td>
		</tr>
	</table>
</div>
