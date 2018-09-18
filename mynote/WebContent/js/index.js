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
				note.setAttribute("style", "color:white");
				note.innerText = data.noteText
				let noteInfo = document.getElementById("notinha" + idNote)

				card = createNote(data)
				noteInfo.innerHTML = card

				noteContainer.insertBefore(noteInfo, document.getElementById("notinha" + (data.idNote - 1)))

				M.toast({
					html: 'Note updated!!'
				})

			}))
		})
	}, 1000 * 2), false);
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
//					var grid = document.querySelector('.grid');
//					var pckry = new Packery(grid, {
//						// options
//						itemSelector: '.grid-item',
//						percentPosition:true
//					})
//					grid.appendChild(noteInfo)
//					pckry.prepended(noteInfo)

												
					noteContainer.appendChild(noteInfo);
												

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

				card = createNote(data)
				noteInfo.innerHTML = card

				noteContainer.insertBefore(noteInfo, document.getElementById("notinha" + (data.idNote - 1)))

				M.toast({
					html: 'Tag updated!!'
				})

				// let url = window.location.pathname + window.location.search +
				// window.location.hash
				// load(url, document.getElementById("tag"+idNote));

			}))
		})
	}, 1000 * 2), false)
}

function onColorPicker(idNote) {
	let colorPicker = document.getElementById("colorPicker" + idNote)
	let note = document.getElementById('note' + idNote)
	let noteContainer = document.getElementById("alou")
	let noteInfo = document.getElementById("notinha" + idNote)


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

				card = createNote(data)
				noteInfo.innerHTML = card

				noteContainer.insertBefore(noteInfo, document.getElementById("notinha" + (data.idNote - 1)))

				M.toast({
					html: 'Color updated!!'
				})
				// let url = window.location.pathname + window.location.search +
				// window.location.hash
				// load(url, document.getElementById("tag"+idNote));

			}))
		})
	}, 500), false)
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
		`<div>
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
						class="card-panel"
						onfocus="onNoteTextChange(${note.idNote})" style="background-color:${note.color};">

						<span id='noteText${note.idNote}' class="white-text">${note.noteText}
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
								<div class="collapsible-header" id="deleteButton">
									<i class="material-icons">color_lens</i> <input type="color"
										id="colorPicker${note.idNote}" name="color" value="${note.color}"
										onclick='onColorPicker(${note.idNote})'>

								</div>
								
							</li>
							<li>
								<div class="collapsible-header" id="deleteButton" onClick='removeNote(${note.idNote})' style="display:flex;justify-content:flex-start;align-items:center;">
									<i class="material-icons">delete</i>Delete
								</div>
							</li>
						</ul>



					</div>
				</div>

				<div contenteditable="true" id="tag${note.idNote}"
					class="card-panel"
					onfocus="onTagChange(${note.idNote})" style="background-color:${note.color};">

					<span id='note${note.idNote}' class="white-text">#${note.tag}
					</span>
				</div>
				
										<form>
						<div class="row">
						<div class="chips">${note.tag}</div>
<!-- 							<div >
								
							</div> -->

						</div>
						</form>
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

//			var grid = document.querySelector('.grid');
//			var pckry = new Packery(grid, {
//				// options
//				itemSelector: '.grid-item',
//				percentPosition:true
//			})
//			grid.appendChild(noteInfo)
//			pckry.prepended(noteInfo)
			let firstChild = document.getElementById("alou").firstChild.innerHTML;
			noteContainer.insertBefore(noteInfo,document.getElementById("notinha"+(data.idNote-1)))

		}))
	})


}