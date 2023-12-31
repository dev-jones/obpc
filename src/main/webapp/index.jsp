<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

☆ 제품을 입력해주세요 ☆<br>
<textarea rows="15" cols="50" id="command"></textarea><br>
<button id="query">최저가</button><br>
↓↓↓↓↓↓↓↓결과↓↓↓↓↓↓↓↓<br>
<textarea rows="15" cols="50" id="result"></textarea>




<script>

$('#query').click(function() {
// 	console.log($(this).val());
	
	valueParser($('#command').val());
});

function valueParser(_val) {
	
	$.ajax({
        type : "GET",
        url : "/textparser",
        data : {
        	"data": _val
        },
        async: false,
        contentType : "application/json; charset:UTF-8",
        success : function(res){
            $('#result').val(res);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("통신 실패.");
        }
	});
}

</script>

</body>
</html>