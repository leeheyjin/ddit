<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>미지의 세계</h1>
<form>
	<input type="text" name="goodWords" value="경진"><br>
	<input type="text" name="badWords" value="예린"><br>
	<input type="file" name="suinFile" value="" onchange="fChg(this)">
</form>
<div id="disp"></div>
<script>    
const myDisp = document.querySelector("#disp");
const myForm = document.forms[0];

function fChg(pThis) {
	
	let formData = new FormData(myForm);
	
	//아작스로 파일보내기를 하려면 꼭 FormData를 써야 함!
	/*
	let formData = new FormData(); // 무조건 자동으로 multipart/form-data로 전송됨
	console.log("로그",pThis.files[0]);
	formData.append("suinFile",pThis.files[0]);
	*/
	
	let xhr = new XMLHttpRequest();
	xhr.open("post","/zzang/mFile",true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            let myImg = document.createElement("img");	// 이미지 태크 만들기
            myImg.src = xhr.responseText;	// 이미지 경로 세팅
            myImg.width = 100;
            myImg.height = 100;
            myDisp.appendChild(myImg);
        }
    }
    // get방식 이외에는 보내는 데이터를 send()안에 매개변수로 보내야 함
    xhr.send(formData); // 꼬옥 문자열로
}
</script>
</body>
</html>