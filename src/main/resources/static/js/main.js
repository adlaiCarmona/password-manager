function setCookie(cname, cvalue, exdays = null) {
    if(exdays == null){
        document.cookie = cname + "=" + cvalue + ";"
        return;
    }
    const d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    let expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}


async function fetchJson(url, req) {
    const response = await fetch(url, req);
    return await response.json();
}

async function fetchText(url, req) {
    const response = await fetch(url, req);
    return await response.text();
}


// TODO: Encryption and Decription in javascript & Kotlin
async function saveAccount() { // used in account.html
    fetch("http://localhost:8080/api/users/", {
        method: "GET",
        headers: { Accept: "application/json" },
    }).then((response) => console.log(typeof response));
}

async function getAccount() { // used in account.html
    const email = getCookie("email")

    return await fetchJson("http://localhost:8080/api/users/email/"+email, {
        method: "GET",
        headers: {
            Accept: "application/json"
        }
    })
}

async function getEmail() { // used in account.html
    return await fetchText("http://localhost:8080/api/users/authenticated/name", {
        method: "GET"
    });
}

async function getUserId() { // used in account.html
    const email = getCookie("email")

    return await fetchText("http://localhost:8080/api/users/userid/"+email, {
        method: "GET"
    })
}

async function getCertDuration() { // used in account.html
    const userId = getCookie("userId")

    return await fetchText(`http://localhost:8080/api/users/${userId}/setting/password-duration`, {
        method: "GET"
    })
}

// TODO: encrypt this
async function setEmailInCookies() {
    const email = await getEmail()

    setCookie("email",email)
}

// TODO: encrypt this
async function setUserIdInCookies() {
    const userId = await getUserId()

    setCookie("userId",userId)
}

async function setCertDurationInCookies() {
    const duration = await getCertDuration()

    setCookie("defaultCertsDuration",duration)
}


async function setup(){
    await setEmailInCookies();
    await setUserIdInCookies()
    await setCertDurationInCookies();
}

async function generatePassword() {
    const lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
    const upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    const numbers = "1234567890";
    const symbols = "!@#$%^&*()-_=+<>?";

    let characters = "";

    const formData = new FormData(form);

    if (formData.has("include-lowercase")) characters += lowerAlphabet;
    if (formData.has("include-uppercase")) characters += upperAlphabet;
    if (formData.has("include-numbers")) characters += numbers;
    if (formData.has("include-symbols")) characters += symbols;

    if (characters.length === 0) {
        console.log("Error: No type of character was selected.");
        return;
    }

    document.getElementById("generated-password").value = generatePass(formData.get("password-length"), characters);
    // console.log(generatedPassword);
}

function generatePass(passwordLength, characters) {

    const charactersLength = characters.length;

    let generatedPassword = "";
    for (
        let i = 0;
        i < passwordLength;
        i++
    ) {
        generatedPassword += characters.charAt(
            Math.floor(Math.random() * charactersLength)
        );
    }

    return generatedPassword
}

function copyPassword(elementId) {
    // Get the text field
    var copyText = document.getElementById(elementId);

    // Select the text field
    copyText.select();
    copyText.setSelectionRange(0, 99999); // For mobile devices

    // Copy the text inside the text field
    navigator.clipboard.writeText(copyText.value);
}