function register() {

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    const message = document.getElementById("message");

    if (name === "" || email === "" || password === "") {

        message.innerHTML =
            "<span class='text-danger'>Please fill all fields.</span>";

        return;
    }

    fetch("/api/auth/register", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            name: name,
            email: email,
            password: password

        })

    })

    .then(response => response.text())

    .then(data => {

        message.innerHTML =
            "<span class='text-success'>" + data + "</span>";

        if (data === "Registration Successful") {

            setTimeout(() => {

                window.location.href = "login.html";

            }, 1500);

        }

    })

    .catch(error => {

        console.log(error);

        message.innerHTML =
            "<span class='text-danger'>Server Error</span>";

    });

}