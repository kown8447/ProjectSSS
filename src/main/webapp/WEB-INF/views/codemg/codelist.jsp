<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- 
<c:forEach items="${codelist}" var="code">
${code.code} ${code.code_name}<br>
</c:forEach>
 --%>
<table>
	<thead>
		<tr>
			<td>
				구분
			</td>
			<td>
				코드
			</td>
			<td>
				이름
			</td>
			<td>
				생년월일
			</td>
			
		</tr>
	</thead>
	<c:forEach items="${codelist}" var="code">
	<tbody>
		<tr>
			<td>
				${code.code_type}
			</td>
			<td>
				${code.code}
			</td>
			<td>
				<a href="codedetail.htm?code=${code.code}">${code.code_name}</a>
			</td>
			<td>
				${code.code_birth}
			</td>
		</tr>
	</tbody>
	</c:forEach>
</table>
