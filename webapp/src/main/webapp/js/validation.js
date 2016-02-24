function highlight(champ, error) {
	if (error) {
		champ.style.backgroundColor = "#fba";
	} else {
		champ.style.backgroundColor = "";
	}
}

function checkName(champ) {
	console.log(checkName)
	if (champ.value.length < 1 || champ.value.legth > 255) {
		highlight(champ, true);
		return false;
	} else {
		highlight(champ, false);
		return true;
	}
}

function checkIntroduced(champ) {
	console.log(checkIntroduced)
	var regex = new RegExp(regex_date);
	console.log(regex)
	if (champ.value.match(regex) || champ.value.length == 0) {
		highlight(champ, false);
		return true;
	} else {
		highlight(champ, true);
		return false;
	}
}

function checkDiscontinued(champ) {
	console.log(checkDiscontinued)
	var regex = new RegExp(regex_date);
	console.log(regex)
	if (champ.value.match(regex) || champ.value.length == 0) {
		highlight(champ, false);
		return true;
	} else {
		highlight(champ, true);
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
		alert(warning)
		return false;
	}
}