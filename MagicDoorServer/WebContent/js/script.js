function checkLogin(isLoggedIn) {
	if (isLoggedIn == true) {
		return true;
	} else {
		alert("Please log in first!");
		return false;
	}
};

// get the password of the user
function getPasswordOfTheUser(formId) {
	var xmlhttp = null;
	// we have to write this
	if (window.XMLHttpRequest) {
		// for ie7 & above, chrome, firefox
		// alert("request object created");
		xmlhttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		// for ie5 & ie6
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		alert("XMLHttpRequest is not supported by Browser");
	}
	// creation of xml http request object

	// ajax
	var userName = document.getElementById(formId).userName.value;
	xmlhttp.open("POST", "./GetPasswordAjax.ajax?userName=" + userName, true);

	xmlhttp.onreadystatechange = function a() {
		// alert("state " +xmlhttp.readyState);
		if (xmlhttp.readyState == 4) {
			document.getElementById(formId).password.value = xmlhttp.responseText;
			document.getElementById(formId).confirmPassword.value = xmlhttp.responseText;
			return false;
		}
	};
	xmlhttp.send(null);
}

// Check if ID and password field is blank
function checkLoginFormBlank(loginForm) {
	if (document.getElementById(loginForm).userName.value.length < 1
			|| !document.getElementById(loginForm).userName.value.match(/\S/)) {
		// user name
		alert("Please insert user name!");
		document.getElementById(loginForm).userName.focus();
		return false;
	} else if (document.getElementById(loginForm).password.value.length < 1
			|| !document.getElementById(loginForm).password.value.match(/\S/)) {
		// password
		alert("Please password!");
		document.getElementById(loginForm).password.focus();
		return false;
	}
}

// Check if any fields in sign up form are blank
function checkSignUpFormBlank(divUserName, divPassword) {
	// blank check
	if (document.signUpForm.userName.value.length < 1
			|| !document.signUpForm.userName.value.match(/\S/)) {
		// user name
		alert("Please insert user name!");
		document.signUpForm.userName.focus();

		return false;
	} else if (document.signUpForm.password.value.length < 1
			|| !document.signUpForm.password.value.match(/\S/)) {
		// password
		alert("Please password!");
		document.signUpForm.password.focus();
		return false;
	} else if (document.signUpForm.confirmPassword.value.length < 1
			|| !document.signUpForm.confirmPassword.value.match(/\S/)) {
		// confirm password
		alert("Please corfirm password!");
		document.signUpForm.confirmPassword.focus();
		return false;
	} else if (document.signUpForm.firstName.value.length < 1
			|| !document.signUpForm.firstName.value.match(/\S/)) {
		// first name
		alert("Please first name!");
		document.signUpForm.firstName.focus();
		return false;
	} else if (document.signUpForm.lastName.value.length < 1
			|| !document.signUpForm.lastName.value.match(/\S/)) {
		// last name
		alert("Please last name!");
		document.signUpForm.firstName.focus();
		return false;
	} else if (document.signUpForm.eMailAddress.value.length < 1
			|| !document.signUpForm.eMailAddress.value.match(/\S/)) {
		// email name
		alert("Please E-Mail Address!");
		document.signUpForm.eMailAddress.focus();
		return false;
	} else if (document.getElementById(divUserName).innerText.length > 0) {
		// check if user name is taken, if so do not proceed to the next
		return false;
	} else if (document.getElementById(divPassword).innerText.length > 0) {
		// check if password is matched, if so do not proceed to the next
		return false;
	}
}

// check if new user name is already exsiting
function checkDuplicateUserName(formId, divId) {
	var xmlhttp = null;
	// we have to write this
	if (window.XMLHttpRequest) {
		// for ie7 & above, chrome, firefox
		// alert("request object created");
		xmlhttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		// for ie5 & ie6
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		alert("XMLHttpRequest is not supported by Browser");
	}
	// creation of xml http request object

	// ajax
	var userName = document.getElementById(formId).userName.value;
	xmlhttp.open("POST", "./CheckDuplicateUserNameAjax.ajax?userName="
			+ userName, true);

	xmlhttp.onreadystatechange = function a() {
		// alert("state " +xmlhttp.readyState);
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.responseText == "ok") {
				// document.forms["signUpForm"].submit();
				// document.getElementById(divId).innerHTML = "";
			} else {
				document.getElementById(divId).innerHTML = xmlhttp.responseText
						.fontcolor("red");
				return false;
			}
		}
	};
	xmlhttp.send(null);
}

// check if inserted password is matched to confirm password
function checkPasswordMatch(formId, divId) {
	var password = document.getElementById(formId).password.value;
	var confirmPassword = document.getElementById(formId).confirmPassword.value;

	if (password == confirmPassword) {
		document.getElementById(divId).innerHTML = "";
	} else {
		document.getElementById(divId).innerHTML = "Password is not matched"
				.fontcolor("red");
		return false;
	}
}

// Double check if the user really want to close the account
function doubleCheckToCloseAccount() {
	return confirm('Are you sure to close the account?');
}

// Check if there is any blank filed in Update Account form
function checkUpdateAccountFormBlank(divPassword) {
	if (document.updateAccountInfoForm.password.value.length < 1
			|| !document.updateAccountInfoForm.password.value.match(/\S/)) {
		// password
		alert("Please password!");
		document.updateAccountInfoForm.password.focus();
		return false;
	} else if (document.updateAccountInfoForm.confirmPassword.value.length < 1
			|| !document.updateAccountInfoForm.confirmPassword.value
					.match(/\S/)) {
		// confirm password
		alert("Please corfirm password!");
		document.updateAccountInfoForm.confirmPassword.focus();
		return false;
	} else if (document.updateAccountInfoForm.firstName.value.length < 1
			|| !document.updateAccountInfoForm.firstName.value.match(/\S/)) {
		// first name
		alert("Please first name!");
		document.updateAccountInfoForm.firstName.focus();
		return false;
	} else if (document.updateAccountInfoForm.lastName.value.length < 1
			|| !document.updateAccountInfoForm.lastName.value.match(/\S/)) {
		// last name
		alert("Please last name!");
		document.updateAccountInfoForm.firstName.focus();
		return false;
	} else if (document.updateAccountInfoForm.eMailAddress.value.length < 1
			|| !document.updateAccountInfoForm.eMailAddress.value.match(/\S/)) {
		// email name
		alert("Please E-Mail Address!");
		document.updateAccountInfoForm.eMailAddress.focus();
		return false;
	} else if (document.getElementById(divPassword).innerText.length > 0) {
		// check if password is matched, if so do not proceed to the next
		return false;
	}
}
