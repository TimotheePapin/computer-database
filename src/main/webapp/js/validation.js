function highlite(champ, error) {
	if (error) {
		champ.style.backgroundColor = "#fba";
	} else {
		champ.style.backgroundColor = "";
	}
}

function checkName(champ) {
	if (champ.value.length < 1 || champ.value.legth > 255) {
		highlite(champ, true);
		return false;
	} else {
		highlite(champ, false);
		return true;
	}
}

function checkIntroduced(champ) {
	var regex = new RegExp(/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/);
	if (regex.exec(champ.value) || champ.value.length == 0) {
		highlite(champ, false);
		return true;
	} else {
		highlite(champ, true);
		return false;
	}
}

function checkDiscontinued(champ) {
	var regex = new RegExp(/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/);
	if (regex.exec(champ.value) || champ.value.length == 0) {
		highlite(champ, false);
		return true;
	} else {
		highlite(champ, true);
		return false;
	}
}


function validation(f) {
	var nameOk =checkName(f.computerName);
	var introducedOk =checkIntroduced(f.introduced);
	var discontinuedOk =checkDiscontinued(f.discontinued);
	if (nameOk && introducedOk && discontinuedOk) {
		return true;
	} else {
		alert("Some field isn't fill correctly")
		return false;
	}
}