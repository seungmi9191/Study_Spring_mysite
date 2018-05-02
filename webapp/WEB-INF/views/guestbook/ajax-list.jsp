<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
	
	
	<title>방명록</title>
</head>
<body>

	<div id="container">
		
		<!-- header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- /header -->
		
		<!-- navigation -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<!-- /navigation -->
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
				
						
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input id="btnAdd" type="button" value=" 확인 " /></td>
							</tr>
						</table>
						<!-- <input id="btnModal" type="button" value="삭제창" /> -->
				
					<br/> <!-- 방명록 리스트 -->
					<ul id="guestbook-list">
							<!-- 리스트가 들어가서 반복됨 -->
					</ul>
					
					
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- footer -->
		
	</div> <!-- /container -->
	
	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label> 
						<input type="password" name="modalPassword" id="modalPassword" value=""><br> 
					<label>방명록번호</label>	
						<input type="text" name="modalPassword" value="" id="modalNo"> <br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>

<script type="text/javascript">
$(document).ready(function(){
	
	fetchList();
	
}); //객체화 한 다음 그릴 준비 다됐어요~알려주는거

$("#btn_del").on("click", function(){
	var no = $("[id=modalNo]").val();
	var password = $("[id=modalPassword]").val();
	
	/*리스트 요청-ajax 데이터만 주고받고*/
    $.ajax({
        
    	//화면에서 리스트 요청을 위해 컨트롤러 갔다올거야~ 컨트롤러는 아래 url주소로~
        url : "${pageContext.request.contextPath }/api/gb/delete",  //내가 보내줄 컨트롤러 지정
        type : "post",
        /* contentType : "application/json", */
        data : {no:no, password:password}, //파라미터값:넘어갈 때 담아뒀던 이름 -> 여러개면 &

        dataType : "json",
        success : function(result){ //이름은 상관없이 list 받아주는 거
            /*성공시 처리해야될 코드 작성*/
			/*remove 작성하기*/
        },
        error : function(XHR, status, error) {
            console.error(status + " : " + error);
        }
    });
	
	
	System.out.println("클릭됨");
});

/* $("#btnModal").on("click", function() {
	console.log("모달창 클릭");
	$("#del-pop").modal();
});
*/

$("ul").on("click","input",function(){ //버튼만 잡아야해서 input 써주기
		var modalNo = $(this).attr("class"); //this가 class가 가지고있는 no값 하나를 가리킴
		$("[id=modalNo]").val(modalNo);//그릇 담아줌
		$("#del-pop").modal();
		
	});
 
$("#btnAdd").on("click", function(){
	event.preventDefault(); 
	console.log("click btnAdd");
	
	var name = $("[name=name]").val(); //name은 위의 <input ~~~~ name>
	var password = $("[name=password]").val();
	var content = $("[name=content]").val();
	console.log(content);
	
	/*리스트 요청-ajax 데이터만 주고받고*/
    $.ajax({
        
    	//화면에서 리스트 요청을 위해 컨트롤러 갔다올거야~ 컨트롤러는 아래 url주소로~
        url : "${pageContext.request.contextPath }/api/gb/add",  //내가 보내줄 컨트롤러 지정
        type : "post",
        /* contentType : "application/json", */
        data : {name:name, password:password, content:content}, //파라미터값:넘어갈 때 담아뒀던 이름 -> 여러개면 &

        dataType : "json",
        success : function(guestVo){ //이름은 상관없이 list 받아주는 거
            /*성공시 처리해야될 코드 작성*/
          	render(guestVo, "up");
          	$("[name=name]").val(""); //name은 위의 <input ~~~~ name>
        	$("[name=password]").val("");
        	$("[name=content]").val("");

        },
        error : function(XHR, status, error) {
            console.error(status + " : " + error);
        }
    });
	
});

function fetchList() {
	/*리스트 요청-ajax 데이터만 주고받고*/
    $.ajax({
        
    	//화면에서 리스트 요청을 위해 컨트롤러 갔다올거야~ 컨트롤러는 아래 url주소로~
        url : "${pageContext.request.contextPath }/api/gb/list",  //내가 보내줄 컨트롤러 지정
        type : "post",
        /* contentType : "application/json", */
        /* data : ?parameterValue=name, //파라미터값:이름 -> 여러개면 & */

        dataType : "json",
        success : function(list){ //이름은 상관없이 list 받아주는 거
            /*성공시 처리해야될 코드 작성*/
            console.log(list);
        	for(var i=0; i<list.length; i++){
        		
        		render(list[i], "down");
        	}	

        },
        error : function(XHR, status, error) {
            console.error(status + " : " + error);
        }
    });
}

function render(guestVo, updown){
	var str = "";
	str += "<li>";
	str += "	<table>";
	str += "		<tr>";
	str += "			<td>[" + guestVo.no + "]</td>";
	str += "			<td>" + guestVo.name + "</td>";
	str += "			<td>" + guestVo.regDate + "</td>";
	str += "			<td><input type='button' value='삭제' id='btnModal' class='"+guestVo.no+"'></td>";
	str += "		</tr>";
	str += "		<tr>";
	str += "			<td colspan=4>";
	str += 					guestVo.content;
	str += "			</td>";
	str += "		</tr>";		
	str += "	</table>";
	str += "<br/>";
	str += "</li>";
	
	
	if(updown == "up") {
		$("#guestbook-list").prepend(str);
	} else if(updown =="down") {
		$("#guestbook-list").append(str);
	} else {
		console.log("error!");
	}

}



</script>
</html>