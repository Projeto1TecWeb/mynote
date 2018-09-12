<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<html>
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/style.css" />
<script src="./js/index.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<meta charset="UTF-8">
<title>Notes</title>
</head>
<body>
<body style="background-color: rgba(241, 247, 246, 0.836);">
	<nav class="#64b5f6 blue lighten-2" class="z-depth-2">
		<div class="nav-wrapper">
			<a href="#" class="brand-logo" id="logoName"><i
				class="large material-icons" id="mainIcon">assignment</i>Note Log</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="badges.html">Account</a></li>
				<li><i class="large material-icons" id="accountIcon">account_box</i></li>
			</ul>
		</div>
	</nav>

	<div class="container">

		<div class="row" id="searchBar">
			<div class="col s6 offset-s4">
				<div class="row">
					<form class="col s12">
						<div class="row">
							<div class="input-field col s6">
								<i class="material-icons prefix">search</i>
								<textarea id="icon_prefix2" class="materialize-textarea"></textarea>
								<label for="icon_prefix2">Search</label>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>


		<%
			out.println("Você está conectando da porta:" + request.getRemotePort());
		%>

		<div class="row note">
			<jsp:useBean id="dao" class="mynote.DAO" />
			<c:forEach var="note" items="${dao.lista}" varStatus="idNote">
				<div class='col s1 m4 l3 notinha'>
					<div class="card indigo ">
						<div class="card-content">
							<span class="card-title activator grey-text text-darken-4">Card
								Title<i class="material-icons right tooltipped"
								data-position="left" data-tooltip="Customize!">more_vert</i>
							</span>
							<p>
								<i class="material-icons">check</i> ${note.icon}
							</p>


							<p>
								<a href="#" onclick="M.toast({html: 'I am a tag'})">
									${note.tag}</a>
							</p>
						</div>

						<div class="card-reveal">
							<span class="card-title grey-text text-darken-4">Card
								Title<i class="material-icons right">close</i>
							</span>
							<p>Here is some c</p>
						</div>
		
						<div contenteditable="true" id="editor${note.idNote}"
							class="card-panel indigo lighten-1"  onfocus="alou(${note.idNote})">

							<span id='noteText${idNote.index}'  class="white-text">${note.noteText} </span>
						</div>
					</div>
				</div>
			</c:forEach>





		</div>
	</div>
</body>
</html>
