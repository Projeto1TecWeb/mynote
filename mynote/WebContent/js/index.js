console.log("importou o js")

function load(url, element) {
	req = new XMLHttpRequest();
	req.open("GET", url, false);
	req.send(null);

	element.innerHTML = req.responseText;
}

function debounce(func, wait, immediate) {
	var timeout;
	return function () {
		var context = this,
			args = arguments;
		var later = function () {
			timeout = null;
			if (!immediate) func.apply(context, args);
		};
		var callNow = immediate && !timeout;
		clearTimeout(timeout);
		timeout = setTimeout(later, wait);
		if (callNow) func.apply(context, args);
	};
};

function onNoteTextChange(idNote) {
	let noteContainer = document.getElementById("alou")
	
	let note = document.getElementById("editor" + idNote)
	let notezin = document.getElementById('note' + idNote)
	let noteInfo = document.getElementById("notinha" + idNote)

	note.addEventListener("input", debounce(() => {
		console.log('atualizando o bd')

		const url = "/mynote/EditNoteText"
		let noteText = note.innerText

		let params = {
			"noteText": encodeURIComponent(noteText),
			"idNote": encodeURIComponent(idNote)
		}
		console.log(idNote)
		console.log('request enviado')
		fetch(url, {
			method: "POST",
			body: 'note=' + JSON.stringify(params),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}


		}).then((res) => {
			(res.json().then((data) => {
				note.setAttribute("style", "color:black");
				note.innerText = data.noteText
				noteInfo.style="order:-1;"
				let time = document.getElementById('time'+idNote)
				time.innerText = 'Last edited: '+ data.time
				M.toast({
					html: 'Note updated!!'
				})

			}))
		})
	}, 1000), false);
}


function onQuerySubmit(userId) {
	let noteContainer = document.getElementById("alou")

	let note = document.getElementById("search")
	note.addEventListener("input", debounce(() => {
		console.log('enviando a query')

		const url = "/mynote/SearchNote"
		let query = note.value

		let params = {
			"query": encodeURIComponent(query),
			"userId": encodeURIComponent(userId)

		}
		console.log('request enviado')
		fetch(url, {
			method: "POST",
			body: 'query=' + JSON.stringify(params),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}


		}).then((res) => {
			(res.json().then((data) => {


				console.log(data)

				noteContainer.innerHTML = ""

				data.forEach((note) => {
					console.log(note.idNote)
					let noteInfo = document.createElement('div')
					noteInfo.id = "notinha" + note.idNote
					noteInfo.className = 'col s12 m4 l3 notinha grid-item'
					card = createNote(note)
					noteInfo.innerHTML = card

					console.log(noteInfo)
					
					noteContainer.appendChild(noteInfo);
												

					var elems = document.querySelectorAll('.dropdown-trigger');
				    var instDrop = M.Dropdown.init(elems, {'closeOnClick':false});
				})

			}))
		})
	}, 1000), false)

}

function onTagChange(idNote) {
	let noteContainer = document.getElementById("alou")

	let noteInfo = document.getElementById("notinha" + idNote)

	let note = document.getElementById("tag" + idNote)
	note.addEventListener("input", debounce(() => {
		console.log('atualizando o bd')

		const url = "/mynote/EditNoteTag"
		let tag = note.innerText

		let params = {
			"tag": encodeURIComponent(tag),
			"idNote": encodeURIComponent(idNote)
		}
		console.log(idNote)
		console.log('request enviado')
		fetch(url, {
			method: "POST",
			body: 'note=' + JSON.stringify(params),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}


		}).then((res) => {
			(res.json().then((data) => {

				if(tag[0]=='#'){
					note.innerText = tag
				}
				else{
					note.innerText ='#'+ tag
				}
				
				noteInfo.style="order:-1;"
				let time = document.getElementById('time'+idNote)
				time.innerText = 'Last edited: '+ data.time
				M.toast({
					html: 'Tag updated!!'
				})
			}))
		})
	}, 1500), false)
}


function onTitleChange(idNote) {
	let noteContainer = document.getElementById("alou")

	let noteInfo = document.getElementById("notinha" + idNote)

	let note = document.getElementById("noteTitle" + idNote)
	note.addEventListener("input", debounce(() => {
		console.log('atualizando o bd')

		const url = "/mynote/EditNoteTitle"
		let title = note.innerText

		let params = {
			"title": encodeURIComponent(title),
			"idNote": encodeURIComponent(idNote)
		}
		console.log(idNote)
		console.log('request enviado')
		fetch(url, {
			method: "POST",
			body: 'note=' + JSON.stringify(params),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}


		}).then((res) => {
			(res.json().then((data) => {

				noteInfo.style="order:-1;"
				let time = document.getElementById('time'+idNote)
				time.innerText = 'Last edited: '+ data.time
				M.toast({
					html: 'Title Updated!!'
				})
			}))
		})
	}, 1500), false)
}



function onColorPicker(idNote) {
	let colorPicker = document.getElementById("colorPicker" + idNote)
	let note = document.getElementById('note' + idNote)
	let noteContainer = document.getElementById("alou")
	let noteInfo = document.getElementById("notinha" + idNote)
	
	console.log(idNote)
	colorPicker.addEventListener("input", debounce(() => {
		console.log('atualizando a cor')

		const url = "/mynote/EditNoteColor"
		let color = colorPicker.value

		let params = {
			"color": encodeURIComponent(color),
			"idNote": encodeURIComponent(idNote)
		}
		console.log(idNote)
		console.log('request enviado')
		fetch(url, {
			method: "POST",
			body: 'note=' + JSON.stringify(params),
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			}


		}).then((res) => {
			(res.json().then((data) => {
				console.log(color)
//				card = createNote(data)
//				noteInfo.innerHTML = card
				note.style.backgroundColor = `${color}`
//				noteInfo.style="order:-1;"
				let time = document.getElementById('time'+idNote)
				time.innerText = 'Last edited: '+ data.time
//				noteContainer.insertBefore(noteInfo, document.getElementById("notinha" + (data.idNote - 1)))

				M.toast({
					html: 'Color updated!!'
				})


			}))
		})
	}, 100), false)
}


function removeNote(idNote) {
	console.log(idNote)
	let note = document.getElementById('note' + idNote)
	let noteInfo = document.getElementById('notinha'+idNote)
	let noteContainer = document.getElementById('alou')
	console.log('Removendo nota')

	const url = "/mynote/RemoveNote"

	let params = {
		"idNote": encodeURIComponent(idNote)
	}
	console.log(idNote)
	console.log('request enviado')
	fetch(url, {
		method: "POST",
		body: 'note=' + JSON.stringify(params),
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		}


	}).then((res) => {
		(res.json().then((data) => {
			console.log(data)
			noteContainer.removeChild(noteInfo)
			M.toast({
				html: 'Note removed!!'
			})

		}))
	})

}


function createNote(note) {
	card =
		`					<div class="card hoverable" id='note${note.idNote}'
						style="background-color:${note.color};">
						<div class="card-content noteHeader" id='noteHeader${note.idNote}'
							style="background-color: transparent; padding: 10px;">

							<span contenteditable="true"
								class="card-title activator grey-text text-darken-4 "
								id='noteTitle${note.idNote}'
								onfocus="onTitleChange(${note.idNote})">${note.title}</span><i
								class="material-icons dropdown-trigger " href='#'
								data-target='dropdown${note.idNote}' style='margin-left: 8px;'>more_vert
							</i>




						</div>
						<div class='noteText' contenteditable="true"
							id="editor${note.idNote}"
							onfocus="onNoteTextChange(${note.idNote})"
							style="background-color: transparent;">

							<span id='noteText${note.idNote}'>${note.noteText} </span>
						</div>

						<ul id='dropdown${note.idNote}' class='dropdown-content'>
							<li><div>
									<i class="material-icons">color_lens</i> <input type="color"
										id="colorPicker${note.idNote}" name="color"
										value="${note.color}" onclick='onColorPicker(${note.idNote})'>

								</div></li>
							<li><div onClick='removeNote(${note.idNote})'
									style="display: flex; justify-content: flex-start; align-items: center;">
									<i class="material-icons">delete</i>Delete
								</div></li>
						</ul>
						<div id='noteFooter'>
							<div id='noteTag'>
								<div contenteditable="true" class='tag' id="tag${note.idNote}"
									class="card-panel " onfocus="onTagChange(${note.idNote})">

									<span id='note${note.idNote}'>${note.tag} </span>
								</div>
							</div>
							<div id='time${note.idNote}' class='time' style='padding: 5px;'>Last
								edited: ${note.time}</div>

						</div>
					</div>`

	return card

}

function changePass(){
	console.log('cliquei no change pass')
	let dropInst = document.getElementById('accountIcon')
	setTimeout(()=>{
	    dropInst.M_Dropdown.recalculateDimensions();

	},350)
	
}
document.addEventListener('DOMContentLoaded', function () {
	console.log("importou o js")


	var iconPicker = document.querySelectorAll('select');
	var instIcon = M.FormSelect.init(iconPicker, {});

	var sidenav = document.querySelectorAll('.sidenav');
	var inst = M.Sidenav.init(sidenav, {});

	var chips = document.querySelectorAll('.chips');
	var instChips = M.Chips.init(chips, {
		'limit': 3
	});

    
	var collap = document.querySelectorAll('.collapsible');
	var instCollap = M.Collapsible.init(collap, {});
	// var elems = document.querySelectorAll('.tooltipped');
	// var instances = M.Tooltip.init(elems, {});
	
    var elems = document.querySelectorAll('.dropdown-trigger');
    var instDrop = M.Dropdown.init(elems, {'closeOnClick':false});
    
	let newNote = document.getElementById("newNote")
	newNote.addEventListener("keyup", function (event) {
		// Cancel the default action, if needed
		event.preventDefault();
		// Number 13 is the "Enter" key on the keyboard
		if (event.keyCode === 13) {
			// Trigger the button element with a click
			document.getElementById("addNoteButton").click();
		}
	});
	

});

function onMakeNoteChange() {
	let noteContainer = document.getElementById("alou")
	let newNote = document.getElementById("newNote")

	console.log('Creating a new note...')

	const url = "/mynote/AddNote"
	let noteText = newNote.value

	let params = {
		"noteText": encodeURIComponent(noteText),

	}
	console.log('request enviado')
	fetch(url, {
		method: "POST",
		body: 'note=' + JSON.stringify(params),
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		}

	}).then((res) => {
		(res.json().then((data) => {
			newNote.value = ""
			let noteInfo = document.createElement('div')
			noteInfo.id = "notinha" + data.idNote
			noteInfo.className = 'col s12 m4 l3 notinha'

			card = createNote(data)
			noteInfo.innerHTML = card
			noteInfo.style="order:-1;"
			console.log('esse eh id'+data.idNote)


			let firstChild = document.getElementById("alou").firstChild.innerHTML;

			noteContainer.insertBefore(noteInfo,noteContainer.firstElementChild)

			let time = document.getElementById('time'+data.idNote)
			time.innerText = 'Created: '+ data.time

			console.log(time)

			var elems = document.querySelectorAll('.dropdown-trigger');
		    var instDrop = M.Dropdown.init(elems, {'closeOnClick':false});

		}))
	})


}