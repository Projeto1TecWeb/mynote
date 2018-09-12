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
	<!-- 	<nav class="#64b5f6 blue lighten-2" class="z-depth-2">
		<div class="nav-wrapper">
			<a href="#" class="brand-logo" id="logoName"><i
				class="large material-icons" id="mainIcon">assignment</i>myNote</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="badges.html">Account</a></li>
				<li><i class="large material-icons" id="accountIcon">account_box</i></li>
			</ul>

		</div>
	</nav> -->


	<nav class='row blue lighten-2'>
		<div class="nav-wrapper">
			<a href="#" class="brand-logo col s3 m3 l3 left" id="logoName"><i
				class="large material-icons" id="mainIcon">assignment</i>myNote</a>

			<div
				class="input-field col s6 m6 l6 offset-s3 offset-m3 offset-l3 center">
				<input id="search" type="search" required> <label
					class="label-icon" for="search"><i class="material-icons">search</i></label>
				<i class="material-icons">close</i>
			</div>

		</div>
	</nav>


	<div class="container">
		<div>
			<div class="row">
				<div class="col s12">
					<div class="row">
						<div class="input-field col s12">
							<i class="material-icons prefix">add</i> <input type="text"
								id="autocomplete-input" class="autocomplete"> <label
								for="autocomplete-input">Make a note...</label>
						</div>
					</div>
				</div>
			</div>
		</div>



		<%-- 		<%
			out.println("Você está conectando da porta:" + request.getRemotePort());
		%>
 --%>


		<div class="row note">
			<jsp:useBean id="dao" class="mynote.DAO" />
			<c:forEach var="note" items="${dao.lista}" varStatus="idNote">
				<div class='col s12 m4 l3 notinha'>
					<div class="card indigo hoverable">
						<div class="card-content" contenteditable="true">
							<span class="card-title activator grey-text text-darken-4">Card
								Title<i class="material-icons right tooltipped"
								data-position="left" data-tooltip="Customize!">more_vert</i>
							</span>
							<p>
								<i class="material-icons">check</i> ${note.icon}
							</p>


							<p>
								<a href="#" onclick="M.toast({html: 'I am a tag'})">
									#${note.tag}</a>
							</p>
<%-- 							<div contenteditable="true" id="tag${note.idNote}"
								class="card-panel indigo lighten-1"
								onfocus="onTagChange(${note.idNote})">

								<span id='noteText${idNote.index}' class="white-text">#${note.tag}
								</span>
							</div> --%>
						</div>

						<div class="card-reveal">
							<div>
								<span class="card-title grey-text text-darken-4">Card
									Title<i class="material-icons right">close</i>
								</span>
								<!-- 								<div class="input-field ">
								<i class="tiny material-icons right" >insert_chart</i>
									<select class="icons">
									
										<option value="" disabled selected>Change color</option>
										<option class=''value="" data-icon="./icons/baseline_code_black_18dp.png">
					code</option> 
					 
					
										<option value="" data-icon="images/office.jpg">example
											2</option>
										<option value="" data-icon="images/yuna.jpg">example
											3</option>
									</select> <label>Images in select</label>
								</div> -->
							</div>
						</div>

						<div contenteditable="true" id="editor${note.idNote}"
							class="card-panel indigo lighten-1"
							onfocus="onNoteTextChange(${note.idNote})">

							<span id='noteText${idNote.index}' class="white-text">${note.noteText}
							</span>
						</div>
					</div>
				</div>
			</c:forEach>




		</div>
	</div>
</body>
</html>
