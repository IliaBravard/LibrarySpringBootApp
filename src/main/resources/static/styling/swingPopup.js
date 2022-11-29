function displayPopup() {
	let subject = document.querySelector("#subject").value;
	let fullName = document.querySelector("#fullName").value;
	let email = document.querySelector("#email").value;

	if (subject != "" && fullName != "" && email.includes("@") && email != "") {
		swal({
			title: "Sent!",
			text: "The email was sent successfully!",
			icon: "success",
			button: false,
		});
	}
}