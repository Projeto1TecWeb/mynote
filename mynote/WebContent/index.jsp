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
<!-- <script src="./index.js"></script>
 -->

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
				<input id="search" type="search" required onFocus="onQuerySubmit(1)">
				<label class="label-icon" for="search"><i
					class="material-icons">search</i></label> <i class="material-icons">close</i>
			</div>

		</div>
	</nav>


	<div class="container">
		<!-- 		<div> -->
		<!-- 			<div class="row"> -->
		<!-- 				<div class="col s12"> -->
		<!-- 					<div class="row"> -->
		<!-- 						<div class="input-field col s12"> -->
		<!-- 							<i class="material-icons prefix">add</i> <input type="text" -->
		<!-- 								id="autocomplete-input" class="autocomplete"> <label -->
		<!-- 								for="autocomplete-input">Make a note...</label> -->
		<!-- 						</div> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 		</div> -->

		<div class="row">
			<div class="input-field col s12 m12 l12" id=''>

				<input id="newNote" type="text" class="validate col s11 m11 l11">
				<label for="icon_prefix">Make a Note...</label> <a
					id='addNoteButton' onclick="onMakeNoteChange()"
					class="waves-effect waves btn-floating btn-small offset-s11 offset-m11 offset-l11 grey lighten-1"
					style="margin-left: 1%"><i class="material-icons">add</i></a>


			</div>



		</div>



		<%-- 		<%
			out.println("Você está conectando da porta:" + request.getRemotePort());
		%>
 --%>


		<div class="row note" id='alou'>
			<jsp:useBean id="dao" class="mynote.DAO" />
			<c:forEach var="note" items="${dao.listaNota}" varStatus="idNote" >
				<!-- 				<script>
					console.log(JSON.parse('${note.getInfo()}'))
				</script>s
			 -->
				<div class='col s12 m4 l3 notinha'id='notinha${note.idNote}' >
					<div class="card hoverable" id='note${note.idNote}' style="background-color:${note.color};">
						<div class="card-content" >
							<span class="card-title activator grey-text text-darken-4">Card
								Title<i class="material-icons right tooltipped"
								data-position="left" data-tooltip="Customize!">more_vert</i>
							</span>
							<p>
								<i class="material-icons">check</i> ${note.icon}
							</p>

							<div contenteditable="true" id="editor${note.idNote}"
								class="card-panel "
								onfocus="onNoteTextChange(${note.idNote})" style="background-color:${note.color}">

								<span id='noteText${idNote.index}' class="white-text">${note.noteText}
								</span>
							</div>

						</div>

						<div class="card-reveal">
							<div>
								<span class="card-title grey-text text-darken-4">delete
									note<i class="material-icons right">close</i>
								</span>
								<ul class="collapsible">
									<li>
										<div class="collapsible-header">
											<i class="material-icons">filter_drama</i>First
										</div>
										<div class="collapsible-body">
											<span>Lorem ipsum dolor sit amet.</span>
										</div>
									</li>
									<li>
										<div class="collapsible-header">
											<i class="material-icons">color_lens</i> <input type="color"
												id="colorPicker${note.idNote}" name="color" value="#e66465" onclick='onColorPicker(${note.idNote})' for="head">Color</label>

										</div>
										<div class="collapsible-body">
											<span>Lorem ipsum dolor sit amet.</span>
										</div>
									</li>
									<li>
										<div class="collapsible-header">
											<i class="material-icons">delete</i>Delete Note
										</div>
										<div class="collapsible-body">
											<span>Lorem ipsum dolor sit amet.</span>
										</div>
									</li>
								</ul>



							</div>
						</div>

						<div contenteditable="true" id="tag${note.idNote}"
							class="card-panel "
							onfocus="onTagChange(${note.idNote})" style="background-color:${note.color}";>

							<span id='note${idNote.index}' class="white-text">#${note.tag}
							</span>
						</div>
						<div class="chips">
							<input class="custom-class">
						</div>
						<div class="chips">oiii</div>

					</div>
				</div>
			</c:forEach>


		</div>
	</div>
</body></html>
