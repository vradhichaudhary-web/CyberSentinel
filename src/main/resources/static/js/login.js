// ===============================
// LOGIN
// ===============================

document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("loginForm");

    form.addEventListener("submit", loginUser);

});

function loginUser(event) {

    event.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    const message = document.getElementById("loginMessage");

    if (email === "" || password === "") {

        message.innerHTML =
            "<span class='text-danger'>Please enter Email and Password.</span>";

        return;

    }

     fetch("/api/auth/login", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            email: email,
            password: password

        })

    })

    .then(response => response.json())

    .then(data => {

        if (data.success) {

            message.innerHTML =
                "<span class='text-success'>Login Successful...</span>";

            localStorage.setItem("loggedIn", "true");
            localStorage.setItem("role", data.role);

            setTimeout(() => {

                if (data.role === "ADMIN") {

                    window.location.href = "admin.html";

                } else {

                    window.location.href = "index.html";

                }

            }, 1000);

        } else {

            message.innerHTML =
                "<span class='text-danger'>" + data.message + "</span>";

        }

    })

    .catch(error => {

        console.log(error);

        message.innerHTML =
            "<span class='text-danger'>Server Error</span>";

    });

}