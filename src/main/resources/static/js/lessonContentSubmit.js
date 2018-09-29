var lessons = [];
var $levelID = $("#levelID");

$("#lessonContentSubmit").on("click", (e) => {
   e.preventDefault();

   console.log("lessonContentSubmit clicked")
    /*var data = {"name": "Nzoia", "foods": [{name: "Gith", price: 20}, {name: "Meat", price: 25}]};

    $.post({
        url: "/tester",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8"
    });*/

    let word = $("#wordInput").val();
    let segments = $("#segmentsInput").val().split();
    let wordImage = $("#wordImage")[0].files[0];
    let wordPhonetic = $("#wordPhonetic")[0].files[0];

    let levelID = $levelID.val();

    let formData = new FormData();

    formData.append("word", word);
    formData.append("segments", segments);
    formData.append("wordImage", wordImage, wordImage.name);
    formData.append("wordPhonetic", wordPhonetic, wordPhonetic.name);
    formData.append("levelID", levelID);

    $.ajax({
        url: "/lesson-submit",
        method: "post",
        data: formData,
        contentType: false,
        processData: false,
        dataType: "json"
    });
});
