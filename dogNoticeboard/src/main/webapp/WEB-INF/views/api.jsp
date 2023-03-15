<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 줄바꾸기 -->
<% pageContext.setAttribute("br", "<br/>");%>
<% pageContext.setAttribute("cn", "\n");%>

<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>API</title>
    <link href="${path}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="${path}/resources/css/sb-admin-2.min.css" rel="stylesheet">
	
</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
       		<c:import url="/WEB-INF/views/common/sidebar.jsp"/>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
            	<c:import url="/WEB-INF/views/common/topbar.jsp"/>
                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
	                	<div class="card-body">
	                		<!-- <form action="api" method="post"> -->
	                		<table style="width:100%;">
	                			<tr>
	                				<th><label for="exampleTextarea" class="form-label mt-4">한국어 입력</label></th>
	                			</tr>
	                			<tr>
	                				<td><textarea class="form-control" id="inputKorean" rows="3"></textarea></td>
	                			</tr>
	                			<tr>
	                				<td><button class="btn btn-lg btn-primary" type="button" onclick="papago()" style="width:100%;">번역</button></td>
	                			</tr>
			                	<tr id="papago1" style="display:none;">
			                		<th><label for="exampleTextarea" class="form-label mt-4">중국어</label></th>
			                	</tr>
			                	<tr id="papago2" style="display:none;">
			                		<td id="chinese" style="color:blue;"></td>
			                	</tr>
			                	<tr id="papago3" style="display:none;">
			                		<th><label for="exampleTextarea" class="form-label mt-4">영어</label></th>
			                	</tr>
			                	<tr id="papago4" style="display:none;">
			                		<td id="english" style="color:blue;"></td>
			                	</tr>
	                		</table>
	                		<!-- </form> -->
	                	</div>
		            </div>
                </div>
            </div>
            <!-- /.container-fluid -->		
		</div>
     </div>           

	<script src="${path }/resources/js/api.js"></script>

    <!-- Bootstrap core JavaScript-->
    <script src="${path}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${path}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${path}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${path}/resources/js/sb-admin-2.min.js"></script>

	<!-- 번역 api -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	
	

</body>

</html>