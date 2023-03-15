<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>JQGrid</title>
    <link href="${path}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="${path}/resources/css/sb-admin-2.min.css" rel="stylesheet">
    
    <!-- 1월17일 jqgrid -->
	<link rel="stylesheet" type="text/css" media="screen" href="${path}/resources/jqGrid-4.7.1/css/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" media="screen" href="${path}/resources/jquery-ui-1.13.2.custom/jquery-ui.css">
	<link rel="stylesheet" type="text/css" media="screen" href="${path}/resources/jquery-ui-1.13.2.custom/jquery-ui.min.css">
	
	<script src="${path}/resources/vendor/jquery/jquery-3.6.3.min.js" type="text/javascript"></script>
	<script src="${path}/resources/jqGrid-4.7.1/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
	<script src="${path}/resources/jqGrid-4.7.1/js/minified/jquery.jqGrid.min.js" type="text/javascript"></script>
	
    <script type="text/javascript">
        $(document).ready(function(){
            $("#jqgrid").jqGrid({          
                url:"user",
                datatype:"JSON",
                mtype : "POST",
                caption: "회원목록",
                colModel: [{label:"번호", name:"custom_user_seq", align:"center"},
                           {label:"이름", name:"custom_user_name", align:"center"},
                           {label:"아이디", name:"custom_user_nick", align:"center"},
                           {label:"이메일", name:"custom_user_email", align:"center"},
                           {label:"비밀번호", name:"custom_user_pswd", align:"center"},
                           {label:"생년월일", name:"custom_user_birth", align:"center"},
                           {label:"전화번호", name:"custom_user_phone", align:"center"},
                           {label:"주소", name:"custom_user_address", align:"center"},
                           {label:"권한", name:"custom_user_auth", align:"center"},
                           {label:"탈퇴여부", name:"custom_user_del_yn", align:"center"},
                           {label:"가입일", name:"custom_user_reg_date", align:"center"},
                           {label:"수정일", name:"custom_user_mod_date", align:"center"}],
                height: "auto",
                width: 970,
                pager: "#pager", //페이징 설정
                viewrecords: true //하단 레코드수 보기
            });
        });
    </script>
	<!-- 1월17일 jqgrid -->
	
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
	                		
	                		<!-- 1월17일 jqgrid -->
							<table id="jqgrid"></table>
							<div id="pager"></div>
							
	                	</div>
		            </div>
                </div>
            </div>
            <!-- /.container-fluid -->			
		</div>
     </div>           

    <!-- jqgrid 충돌 -->
    <!-- Bootstrap core JavaScript-->
    <%-- <script src="${path}/resources/vendor/jquery/jquery.min.js"></script> --%>
    
    <script src="${path}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${path}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${path}/resources/js/sb-admin-2.min.js"></script>

</body>

</html>