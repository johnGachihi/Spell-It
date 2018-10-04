// import Chart from "chart.js";
const Chart = require("../../../node_modules/chart.js/src/chart");

var days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
var dayData = [];
var monthData = [];

$.ajax({
    url: "/reg-user-count-data",
    success: (data) => {
        console.log(data);
        monthData = data;
        var ctx = $("#home-stats-chart");
        var lineChart = new Chart(ctx, {
            type: "line",
            data: {
                labels: months,
                datasets:[{
                    label: "Registered users",
                    data: data,
                    borderColor: "#007bff",
                    lineTension: 0
                },{
                    label: "Current users",
                    data: [87,67,56,45,34,23,45,67,34,67,34,78],
                    borderColor: "#343a40",
                    lineTension: 0
                }]
            }
        });
    },
    error: (jqXHR, status, error) => {
        console.log(error);
    }
})


