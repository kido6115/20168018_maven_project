<%-- <%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.List,java.util.ArrayList"%>
<%@ page import="Db.SearchHour"%>
<%@ page import="Bean.edit"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src="js/sweetalert.min.js"></script>
<title>�а��t��-�ק�/�R������</title>


</head>

<body>
	<%
		if(session.getAttribute("echeck")!=null) {			
		if (((String)session.getAttribute("echeck")).equals("1")){
	%>
	<script>
		sweetAlert('�а��ɶ��w��!!!');
	</script>
	<%
		session.setAttribute("echeck","");}
	%>

	<%
		if (((String)session.getAttribute("echeck")).equals("2")){
	%>
	<script>
		sweetAlert('�ק令�\!!!');
	</script>
	<%
		session.setAttribute("echeck","");}
		}
	%>

	<div id="wrapper">

		<jsp:include page="Home.jsp">
			<jsp:param name="status" value="editPSE" />
		</jsp:include>
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<a href="#menu-toggle" id="menu-toggle"><img
							src="img/right.png"></a> <a href="#menu-toggle2"
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
						<h1>
							<img src="img/edit.png">
						</h1>
						<form action="deleteServlet" method="post">

							<table class="CSSTableGenerator">
								<tr>
									<td scope="col">���</td>
									<td scope="col">�D����s��</td>
									<td scope="col">�ӽЮɶ�</td>
									<td scope="col">�h�^��]</td>
									<td scope="col">�d�ݤ��e</td>
								</tr>



								<%
									ArrayList<edit> showed = (ArrayList<edit>) request.getAttribute("edit");
												for(edit ee : showed) {
								%>
								<tr>
									<td scope="row"><input type="checkbox" name="checkbox"
										value="<%=ee.getPid()%>" id="checkbox"></td>

									<td>
										<%
											out.println(ee.getPid());
										%>
									</td>
									<td>
										<%
											out.println(ee.getTime());
										%>
									</td>
									<td>
										<%
											if(ee.getReply()==null)
																																																										{out.println("�L");}
																																																									else {out.println(ee.getReply());
																																																									}
										%>
									</td>
									<td width="20%"><table align="center">
										<form action="view_pseServlet" method="post"></form>
										<form action="view_pseServlet" method="post" style="float:left">
											<input type="hidden" name="pid" value="<%=ee.getPid()%>">

											<input class="btn btn-info" type="submit" value="�d�ݤ��e">
										</form>
										&nbsp;&nbsp;&nbsp;&nbsp;
 
										<form action="modifyPSEServlet" method="post" style="float:left">
											<input type="hidden" name="pid" value="<%=ee.getPid()%>">

											<input class="btn btn-info" type="submit" value="�קﰲ��">
										</form>
										</table>

									</td>
								</tr>
								<%
									}
								%>
							</table>

							<br> <input class="btn btn-primary" type="button"
								name="button2" id="button2" value="�R������"
								onclick="if(confirm('�T�{�n�R���ܡH')) this.form.submit();">

						</form>

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



</body>

</html>
 --%>
 
 
 
 <%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.List,java.util.ArrayList"%>
<%@ page import="Db.SearchHour"%>
<%@ page import="Bean.edit"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src="js/sweetalert.min.js"></script>
<title>�а��t��-�ק�/�R������</title>


</head>

<body>
<%
		if(session.getAttribute("echeck")!=null) {			
			
		if (((String)session.getAttribute("echeck")).equals("2")){
	%>
	<script>
sweetAlert('�ק令�\!!!');
</script>
	<%
		session.setAttribute("echeck","");}
			}
	%>

	<div id="wrapper">

		<jsp:include page="Home.jsp">
			<jsp:param name="status" value="editPSE" />
		</jsp:include>
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
					<a href="#menu-toggle" id="menu-toggle"><img
							src="img/right.png"></a> <a href="#menu-toggle2"
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
						<h1>�ק�/�R������</h1>
						<form action="deleteServlet" method="post">

							<table class="CSSTableGenerator">
								<tr>
									<td scope="col">���</td>
									<td scope="col">�D����s��</td>
									<td scope="col">�ӽЮɶ�</td>
									<td scope="col">�h�^��]</td>
									<td scope="col">�d�ݤ��e</td>
								</tr>



								<%
									ArrayList<edit> showed = (ArrayList<edit>) request.getAttribute("edit");
																						int i=0;					//editJDBC��ArrayList<edit> showed		
																		for(edit ee : showed) {
																			i++;
								%>
								<tr>
									<td scope="row"><input type="checkbox" name="checkbox"
										value="<%=ee.getPid()%>" id="checkbox"></td>

									<td>
										<%
											out.println(ee.getPid());
										%>
									</td>
									<td>
										<%
											out.println(ee.getTime());
										%>
									</td>
									<td>
										<%
											if(ee.getReply()==null)
																		{out.println("�L");}
																	else {out.println(ee.getReply());
																	}
										%>
									</td>
									<td width="20%"><input class="btn btn-info" name="�d�ݤ��e" type="button"
										id="�d�ݤ��e" value="�d�ݤ��e"
										onclick="javascript:location.href='view_pseServlet?pid_n=<%=i%>'"> 
										<input
										class="btn btn-info" type="button" name="button" id="button"
										value="�קﰲ��"
										onclick="javascript:location.href='modifyPSEServlet?pid_n=<%=i%>'"></td>
										<%
										session.setAttribute("PID"+i,ee.getPid()); %>
								</tr>
								<%
									}
								%>
							</table>

							<br> <input class="btn btn-primary" type="button"
								name="button2" id="button2" value="�R������"
								onclick="if(confirm('�T�{�n�R���ܡH')) this.form.submit();">
						</form>

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

	

</body>

</html>
 