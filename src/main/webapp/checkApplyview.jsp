<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.sql.*"%>
<%@ page import="java.util.List,java.util.ArrayList"%>
<%@ page import="org.iisi.bean.CheckPSE"%>
<%@ page import="org.iisi.db.JDBCCheckApply"%>
<%@ page import="org.iisi.bean.Apply"%>

<!DOCTYPE html>
<html lang="en">

<head>
<script src="js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>請假系統-審核調職申請</title>
</head>

<body>
	<%
		JDBCCheckApply db=new JDBCCheckApply();
	if(request.getAttribute("status")!=null){
		int status=Integer.parseInt(request.getAttribute("status").toString());
		
		if(status==3){
%>
	<script>
		sweetAlert("不可調動", "此部門主管僅有一人不可調動!!", "error");
	</script>

	<%
	
	}else {
%>
	<script>
		sweetAlert("失敗", "系統發生錯誤,請重新嘗試!!", "error");
	</script>

	<%
	}
	
	}
	
	%>
	<div id="wrapper">

		<jsp:include page="Home.jsp">
			<jsp:param name="status" value="checkApply" />
		</jsp:include>

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
					<a href="#menu-toggle" 
							id="menu-toggle"><img src="img/right.png"></a>
							
                          <a href="#menu-toggle2" 
							id="menu-toggle2"><img src="img/left.png"></a>
	<script>
	$("#menu-toggle").hide();

		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#menu-toggle").hide();
			$("#menu-toggle2").show();
			$("#wrapper").toggleClass("toggled");
		});
		
		$("#menu-toggle2").click(function(e) {
			e.preventDefault();
			$("#menu-toggle2").hide();
			$("#menu-toggle").show();
			$("#wrapper").toggleClass("toggled");
		});
	</script>
						<h1><img src="img/applpcheck.png"></h1>
						<form action="checkApplyServlet" method=post>
							<input type="hidden" name="apid"
								value=<%=request.getParameter("apid")%>>
							<table class="CSSTableGenerator" border="1">
								<tbody>

									<%
										int apid=Integer.parseInt(request.getParameter("apid"));
																					    List<Apply> list=(ArrayList<Apply>)db.ListUncheckApply(apid);
																													
																													
																						for(Apply apply: list){
									%>
									<tr>
										<td>申請日期 :</td>
										<td colspan=3><input type="hidden" name="eid"
											value=<%=apply.getEid() %>> <input type="hidden"
											name="ap_depid" value=<%=apply.getAp_dep_id() %>> <input
											type="hidden" name="ap_jobid"
											value=<%=apply.getAp_job_id() %>> <input
											type="hidden" name="jobid" value=<%=apply.getJob_id() %>>

											<input type="hidden" name="depid"
											value=<%=apply.getDep_id() %>> <%=apply.getAp_date()%></td>
									</tr>
									<tr>
										<td>員工:</td>
										<td><%=apply.getName()%></td>
										<td>員工編號:</td>
										<td><%=apply.getEid()%></td>
									</tr>
									<tr>
										<td>原部門職位:</td>
										<td><%=apply.getDep_name()%> &nbsp;&nbsp;<%=apply.getJob_name()%></td>
										<td>申請部門職位:</td>
										<td><%=apply.getAp_dep_name()%> &nbsp;&nbsp;<%=apply.getAp_job_name()%></td>
									</tr>
									<tr>
										<td>理由:</td>
										<td colspan=3><%=apply.getReason()%></td>
									</tr>

									<%
										}
									%>





								</tbody>

							</table>
							<br> <br> <span style="float: right"><button
									type="submit" name="pass" class="btn btn-warning">通過</button>&nbsp;&nbsp;
								<button type="submit" name="notpass" class="btn btn-danger">不通過</button></span>

						</form>
						<br>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<script src="js/myJS.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Menu Toggle Script -->
	
</body>

</html>
