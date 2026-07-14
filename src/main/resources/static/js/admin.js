// =====================================
// CYBER SENTINEL ADMIN PANEL
// =====================================

document.addEventListener("DOMContentLoaded", () => {

    loadDashboard();
    loadUsers();
    loadThreats();

});

// =====================================
// LOAD DASHBOARD
// =====================================

function loadDashboard() {

    fetch("/api/dashboard")

        .then(response => response.json())

        .then(data => {

            document.getElementById("totalUsers").innerText = data.totalUsers;
            document.getElementById("totalThreats").innerText = data.totalThreats;
            document.getElementById("totalScans").innerText = data.totalScans;
            document.getElementById("highRisk").innerText = data.highRisk;

        })

        .catch(error => console.log(error));

}

// =====================================
// LOAD USERS
// =====================================

function loadUsers() {

    fetch("/api/users")

        .then(response => response.json())

        .then(data => {

            const table = document.getElementById("userTable");

            table.innerHTML = "";

            if (data.length === 0) {

                table.innerHTML = `

                    <tr>
                        <td colspan="6" class="text-center">
                            No Users Found
                        </td>
                    </tr>

                `;

                return;

            }

            data.forEach(user => {

                table.innerHTML += `

                    <tr>

                        <td>${user.id}</td>

                        <td>${user.name}</td>

                        <td>${user.email}</td>

                        <td>${user.role}</td>

                        <td>${user.createdAt ?? "-"}</td>

                        <td>

                            <button
                                class="btn btn-danger btn-sm"
                                onclick="deleteUser(${user.id})">

                                Delete

                            </button>

                        </td>

                    </tr>

                `;

            });

        })

        .catch(error => console.log(error));

}

// =====================================
// LOAD THREATS
// =====================================

function loadThreats() {

    fetch("/api/threats")

        .then(response => response.json())

        .then(data => {

            const table = document.getElementById("threatTable");

            table.innerHTML = "";

            if (data.length === 0) {

                table.innerHTML = `

                    <tr>
                        <td colspan="6" class="text-center">
                            No Threats Found
                        </td>
                    </tr>

                `;

                return;

            }

            data.forEach(threat => {

                table.innerHTML += `

                    <tr>

                        <td>${threat.id}</td>

                        <td>${threat.title}</td>

                        <td>${threat.type}</td>

                        <td>${threat.riskLevel}</td>

                        <td>${threat.description}</td>

                        <td>

                            <button
                                class="btn btn-danger btn-sm"
                                onclick="deleteThreat(${threat.id})">

                                Delete

                            </button>

                        </td>

                    </tr>

                `;

            });

        })

        .catch(error => console.log(error));

}

// =====================================
// ADD THREAT
// =====================================

function addThreat() {

    const title = document.getElementById("title").value;
    const type = document.getElementById("type").value;
    const riskLevel = document.getElementById("riskLevel").value;
    const description = document.getElementById("description").value;

    if (title === "" || type === "" || description === "") {

        alert("Please fill all fields.");

        return;

    }

    fetch("/api/threats", {

        method: "POST",

        headers: {

            "Content-Type": "application/json"

        },

        body: JSON.stringify({

            title: title,
            type: type,
            riskLevel: riskLevel,
            description: description

        })

    })

    .then(() => {

        alert("Threat Added Successfully.");

        document.getElementById("title").value = "";
        document.getElementById("type").value = "";
        document.getElementById("description").value = "";

        loadThreats();
        loadDashboard();

    })

    .catch(error => console.log(error));

}

// =====================================
// DELETE THREAT
// =====================================

function deleteThreat(id) {

    if (!confirm("Delete this Threat?")) {

        return;

    }

    fetch("/api/threats/" + id, {

        method: "DELETE"

    })

    .then(() => {

        loadThreats();
        loadDashboard();

    })

    .catch(error => console.log(error));

}

// =====================================
// DELETE USER
// =====================================

function deleteUser(id) {

    if (!confirm("Delete this User?")) {

        return;

    }

    fetch("/api/users/" + id, {

        method: "DELETE"

    })

    .then(() => {

        loadUsers();
        loadDashboard();

    })

    .catch(error => console.log(error));

}

// =====================================
// LOGOUT
// =====================================

function logout() {

    localStorage.clear();

    window.location.href = "login.html";

}

// =====================================
// AUTO REFRESH
// =====================================

setInterval(() => {

    loadDashboard();
    loadUsers();
    loadThreats();

}, 10000);