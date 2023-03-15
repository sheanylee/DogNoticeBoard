<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>boardInsert</title>

    <!-- Custom fonts for this template -->
    <link href="${path }/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${path }/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="${path }/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
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
                            <div class="table-responsive">
                        		<form action="write" method="post" enctype="multipart/form-data">
		                            <input type="hidden" name="board_writer" value="${getDto.custom_user_nick}">
		                            <table class="table table-hover">
		                            	<tr>
		                            		<th class="table-active" width="15%">게시판 타입</th>
		                            		<th>자유게시판</th>
		                            	</tr>
		                            	<tr>
		                            		<th class="table-active" width="15%">작성자</th>
		                            		<th>${getDto.custom_user_nick}</th>
		                            	</tr>
		                            	<tr>
		                            		<th class="table-active" width="15%">제목</th>
		                            		<th><input type="text" name="board_title" size="85%" required="required" value="${BoardDto.board_title}"></th>
		                            	</tr>
		                            	<tr>
		                            		<th class="table-active" width="15%">내용</th>
		                            		<th><textarea name="board_text" rows="4" cols="85%" required="required">${BoardDto.board_text}</textarea></th>
		                            	</tr>
		                            	<tr>
		                            		<th class="table-active" width="15%">파일</th>
		                            		<th><input type="file" name="img"></th>
		                            	</tr>
		                            </table>
		                            <button class="btn btn-secondary my-2 my-sm-0" type="button" onclick="location.href='${path}/board/board'">목록</button>
		                            <button class="btn btn-primary my-2 my-sm-0" type="submit">확인</button>
	                            </form>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->



    <!-- Bootstrap core JavaScript-->
    <script src="${path }/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${path }/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath }/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${path }/resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${path }/resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="${path }/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${path }/resources/js/demo/datatables-demo.js"></script>

</body>
</html>