<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--
		form태그의 속성
		1. action: form 데이터를 받아서 받아서 처리할 문서명 또는 서블릿URL
		2. method: 전송방식 - GET(default), POST 등
		3. target: action에서 지정한 문서를 열 때 현재 페이지에 불러올지(_self), 새 탭에 불러올지(_blank), 특정 iframe내에 불러올지 등을 지정(잘안씀)
		4. enctype: 서버로 파일을 전송할 때 multipart/form-data 값이 사용됨. 
	-->
	<%-- <% %/> 영역을 스크립트릿이라고 한다.  --%>
	<%
	String name = "홍길동";
	%>

	<%-- <%= %>: = 옆에 변수나 수식을 입력해 jsp에서 변수나 수식의 결과를 출력할 때 사용한다 --%>

	<h2><%=name%>의 Request연습 form<%=3 * 4 + 7%></h2>

	<form action="/webTest/requestTest01.do" method="get">
		<table border="1">
			<tr>
				<td><label for="userName">이름</label></td>
				<td><input type="text" name="userName" size="10"></td>
			</tr>
			<tr>
				<td><label for="job">직업</label></td>
				<td><select name="job">
						<option value="무직">무직</option>
						<option value="햑생">햑생</option>
						<option value="회사원">회사원</option>
						<option value="전문직">전문직</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="hobby">취미</label></td>
				<td>
					<input type="checkbox" name="hobby" value="여행">여행
					<input type="checkbox" name="hobby" value="독서">독서
					<input type="checkbox" name="hobby" value="게임">게임
					<input type="checkbox" name="hobby" value="배드민턴">배드민턴
					<input type="checkbox" name="hobby" value="테니스">테니스
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input type="submit" value="전송">
					<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>