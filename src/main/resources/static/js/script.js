// ===============================
// LOGIN CHECK
// ===============================

if (localStorage.getItem("loggedIn") !== "true") {

    window.location.href = "login.html";

}
// ===============================
// DASHBOARD DATA
// ===============================

document.addEventListener("DOMContentLoaded", () => {
const role = localStorage.getItem("role");

document.getElementById("userRole").innerText = role;
    loadDashboard();
    loadCharts();
    loadHistory();

});

// ===============================
// LOAD DASHBOARD
// ===============================

function loadDashboard() {

    fetch("/api/dashboard")
        .then(response => response.json())
        .then(data => {

            document.getElementById("totalUsers").innerText = data.totalUsers;
            document.getElementById("totalScans").innerText = data.totalScans;
            document.getElementById("fakeJobs").innerText = data.fakeJobScans;
            document.getElementById("urlScans").innerText = data.urlScans;
            document.getElementById("highRisk").innerText = data.highRisk;
            document.getElementById("mediumRisk").innerText = data.mediumRisk;
            document.getElementById("lowRisk").innerText = data.lowRisk;

        })
        .catch(error => console.log(error));

}

// ===============================
// FAKE JOB DETECTOR
// ===============================

function checkJob() {

    const description = document.getElementById("jobDescription").value;

    if (description === "") {

        alert("Enter Job Description");
        return;

    }

    fetch("/api/job/check", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            jobDescription: description
        })

    })

    .then(response => response.json())

    .then(data => {

        document.getElementById("jobResult").innerHTML = `

            <h5>Risk : ${data.riskLevel}</h5>
            <h5>Score : ${data.scamScore}</h5>
            <p>${data.result}</p>

        `;

        loadDashboard();
        loadHistory();
        loadCharts();

    })

    .catch(error => console.log(error));

}

// ===============================
// URL SCANNER
// ===============================

function checkUrl() {

    const url = document.getElementById("url").value;

    if (url === "") {

        alert("Enter URL");
        return;

    }

    fetch("/api/url/check", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            url: url
        })

    })

    .then(response => response.json())

    .then(data => {

        document.getElementById("urlResult").innerHTML = `

            <h5>Risk : ${data.riskLevel}</h5>
            <h5>Score : ${data.threatScore}</h5>
            <p>${data.result}</p>

        `;

        loadDashboard();
        loadHistory();
        loadCharts();

    })

    .catch(error => console.log(error));

}

// ===============================
// BAR + PIE CHART
// ===============================
// ===============================
// LOAD CHARTS
// ===============================

let barChart;
let pieChart;

function loadCharts() {

    fetch("/api/dashboard")

        .then(response => response.json())

        .then(data => {

            if (barChart) {
                barChart.destroy();
            }

            if (pieChart) {
                pieChart.destroy();
            }

            // BAR CHART

            const barCtx = document.getElementById("barChart");

            if (barCtx) {

                barChart = new Chart(barCtx, {

                    type: "bar",

                    data: {

                        labels: [
                            "Users",
                            "Scans",
                            "Fake Jobs",
                            "URL Scans"
                        ],

                        datasets: [{

                            label: "Cyber Sentinel",

                            data: [

                                data.totalUsers,
                                data.totalScans,
                                data.fakeJobScans,
                                data.urlScans

                            ],

                            borderWidth: 1

                        }]

                    },

                    options: {

                        responsive: true,

                        scales: {

                            y: {

                                beginAtZero: true

                            }

                        }

                    }

                });

            }

            // PIE CHART

            const pieCtx = document.getElementById("pieChart");

            if (pieCtx) {

                pieChart = new Chart(pieCtx, {

                    type: "doughnut",

                    data: {

                        labels: [
                            "High",
                            "Medium",
                            "Low"
                        ],

                        datasets: [{

                            data: [

                                data.highRisk,
                                data.mediumRisk,
                                data.lowRisk

                            ]

                        }]

                    },

                    options: {

                        responsive: true

                    }

                });

            }

        })

        .catch(error => console.log(error));

}

// ===============================
// LOAD HISTORY
// ===============================

function loadHistory() {

    fetch("/api/history")

        .then(response => response.json())

        .then(data => {

            const table = document.getElementById("historyTable");

            table.innerHTML = "";

            if (data.length === 0) {

                table.innerHTML = `

                    <tr>
                        <td colspan="6" class="text-center">
                            No Scan History Found
                        </td>
                    </tr>

                `;

                return;

            }

            data.reverse().forEach((scan, index) => {

                let badge = "bg-success";

                if (scan.riskLevel === "HIGH") {

                    badge = "bg-danger";

                } else if (scan.riskLevel === "MEDIUM") {

                    badge = "bg-warning text-dark";

                }

                table.innerHTML += `

                    <tr>

                        <td>${index + 1}</td>

                        <td>${scan.scanType}</td>

                        <td>${scan.inputData}</td>

                        <td>

                            <span class="badge ${badge}">
                                ${scan.riskLevel}
                            </span>

                        </td>

                        <td>${scan.score}</td>

                      <td>

    ${
        localStorage.getItem("role") === "ADMIN"
        ?
        `<button
            class="btn btn-sm btn-danger"
            onclick="deleteHistory(${scan.id})">

            Delete

        </button>`
        :
        `<span class="badge bg-secondary">
            View Only
        </span>`
    }

</td> 
                    </tr>

                `;

            });

        })

        .catch(error => console.log(error));

}

// ===============================
// DELETE HISTORY
// ===============================

function deleteHistory(id) {

    if (!confirm("Delete this scan?")) {

        return;

    }

    fetch("/api/history/" + id, {

        method: "DELETE"

    })

    .then(() => {

        loadHistory();
        loadDashboard();
        loadCharts();

    })

    .catch(error => console.log(error));

}

// ===============================
// AUTO REFRESH
// ===============================

setInterval(() => {

    loadDashboard();
    loadHistory();
    loadCharts();

}, 5000);

// ===============================
// SEARCH HISTORY
// ===============================

function searchHistory() {

    let input = document.getElementById("historySearch").value.toLowerCase();

    let table = document.getElementById("historyTable");

    let rows = table.getElementsByTagName("tr");

    for (let i = 0; i < rows.length; i++) {

        let text = rows[i].innerText.toLowerCase();

        if (text.indexOf(input) > -1) {

            rows[i].style.display = "";

        } else {

            rows[i].style.display = "none";

        }

    }

}
// ===============================
// LOGOUT
// ===============================

function logout() {

    if (confirm("Do you want to logout?")) {

        localStorage.removeItem("loggedIn");
        localStorage.removeItem("role");

        window.location.href = "login.html";

    }

}
// ===============================
// DOWNLOAD PDF REPORT
// ===============================

function downloadPdf() {

    window.open("/api/report/pdf", "_blank");

}
// ===============================
// DOWNLOAD EXCEL
// ===============================

function downloadExcel() {

    window.open("/api/report/excel", "_blank");

}
