console.log("importou o js")


function debounce(func, wait, immediate) {
	var timeout;
	return function() {
		var context = this, args = arguments;
		var later = function() {
			timeout = null;
			if (!immediate) func.apply(context, args);
		};
		var callNow = immediate && !timeout;
		clearTimeout(timeout);
		timeout = setTimeout(later, wait);
		if (callNow) func.apply(context, args);
	};
};
function alou(idNote){
	let note = document.getElementById("editor"+idNote)
	note.addEventListener("input", debounce(()=>{
		console.log('atualizando o bd')
		let xhr = new XMLHttpRequest();
		const url = "/mynote/EditNoteText"
		let noteText = note.innerText
//		let params = "noteText=" + encodeURIComponent(noteText)+"idNote=" + encodeURIComponent(idNote)
		let params = {
				"noteText":encodeURIComponent(noteText),
				"idNote":encodeURIComponent(idNote)
		}
		console.log(idNote)
//		fetch(url, {
//		    method : "POST",
//		    body: params,
//
//		}).then(
//			    response => response.text() // .json(), etc.
//			    // same as function(response) {return response.text();}
//			).then(
//			    html => console.log(html)
//			);

		xhr.open("POST", url);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send('note='+(JSON.stringify(params)));
		

//		console.log(document.getElementByClassName('noteText')[0].innerText)
	},1000*2), false);
}
document.addEventListener('DOMContentLoaded', function() {
	console.log("importou o js")
	var elems = document.querySelectorAll('.tooltipped');
	var instances = M.Tooltip.init(elems, {});
	document.getElementById('editor1').addEventListener('input',()=>{
		console.log('to clicando na tecla')
	},false)


});
