// import Chart from "chart.js";
const Chart = require("../../../node_modules/chart.js/src/chart");

var days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

var ctx = $("#home-stats-chart");
var lineChart = new Chart(ctx, {
    type: "line",
    data: {
        labels: months,
        datasets:[{
            label: "Registered users",
            data: [12,23,34,45,56,67,78,23,45,12,23,45],
            borderColor: "#007bff",
            lineTension: 0
        },{
            label: "Current users",
            data: [87,67,56,45,34,23,45,67,34,67,34,78],
            borderColor: "#343a40",
            lineTension: 0
        }]
    }
})
