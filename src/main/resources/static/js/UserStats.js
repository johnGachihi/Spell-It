var $regUserStats = $("#reg-user-stats");

window.setInterval(e => {
    $.ajax({
        url: "/num-of-users",
        success: (data) => {
            $regUserStats.text(data);
            console.log(data);
        },
        error: (jqXHR, status, error) => {
            console.log(error);
        }
    })
}, 10000);