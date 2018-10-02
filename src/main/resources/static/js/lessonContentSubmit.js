var lessons = [];
var $levelID = $("#levelID");
var $levelNo = $("#levelNo");
var $checkpointWordImageInput = $("#checkpointWordImage");
var $addLevelBtn = $("#addLevelBtn");

$("#lessonContentSubmit").on("click", (e) => {
   e.preventDefault();

   console.log($levelID.val());
    /*var data = {"name": "Nzoia", "foods": [{name: "Gith", price: 20}, {name: "Meat", price: 25}]};

    $.post({
        url: "/tester",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8"
    });*/

    let word = $("#wordInput").val();
    let segments = $("#segmentsInput").val().split("_");
    let wordImage = $("#wordImage")[0].files[0];
    let wordPhonetic = $("#wordPhonetic")[0].files[0];

    let levelID = $levelID.val();
    let levelNo = $levelNo.val();

    let formData = new FormData();

    formData.append("word", word);
    formData.append("segments", segments);
    formData.append("wordImage", wordImage, wordImage.name);
    formData.append("wordPhonetic", wordPhonetic, wordPhonetic.name);
    formData.append("levelID", levelID);
    formData.append("levelNo", levelNo);

    $.ajax({
        url: "/lesson-submit",
        method: "post",
        data: formData,
        contentType: false,
        processData: false,
        dataType: "json",
        success: (data) => {
            console.log(data.lessonNumber);
            $("#addLessonArea").slideUp();
            $("#no-lessons-message").attr("style", "display: none;")
            $("#lessons-area").append(
                "<div class='card m-2 border-primary w-50' style='width: 100px; height: 100px'>" + "" +
                    "<div class='card-header'>Lesson " + data.lessonNumber + "</div>" +
                    "<img  class='card-img-top' src=''>" +
                    "<div class='card-text mx-1'>Word: " +
                        "<span style='font-weight: bold;'>" + data.text + "</span>" +
                        ". Segments:" +
                        "<span style='font-weight: bold;'>" + data['wordSegments'].map((seg) => " " + seg)+ "</span>" +
                    "</div>"  +
                "</div>"

            );
            /*$.ajax({
                url: "/content",
                data: {imageName: "SIT3070320216704198359.png"},
                success: (data) => {
                    /!*console.log("Start of success")
                    var reader = FileReader();
                    reader.onload = (e) => {
                        console.log("asdfsadfasdfasdf");
                        $("#imaage")
                            .attr("src", e.target.result)
                            .width(100)
                            .height(150)
                    }
                    reader.readAsArrayBuffer(data);*!/

                }
            });*/
        }
    });
});

$checkpointWordImageInput.change(ev => {
    if($checkpointWordImageInput[0].files && $checkpointWordImageInput[0].files[0]){
        console.log("input changed", $checkpointWordImageInput[0].files[0]);
        let reader = new FileReader();
        reader.onload = e => {
            $("#checkpointWordImageViewer").attr("src", e.target.result);
            $("#checkpointWordImageViewer").css("display", "block");
        }
        reader.readAsDataURL($checkpointWordImageInput[0].files[0]);
    }
});

$addLevelBtn.click(e => {
    e.preventDefault();

    let formData = new FormData();
    formData.append("checkpointWordImage", $checkpointWordImageInput[0].files[0], $checkpointWordImageInput[0].files[0].name);
    formData.append("quizWord", $("#quizWord").val());
    formData.append("incompleteQuizWord", $("#incompleteQuizWord").val());
    formData.append("levelId", $levelID.val());
    formData.append("levelNo", $levelNo.val());

    $.ajax({
        url: "/checkpoint-content",
        method: "post",
        data: formData,
        contentType: false,
        processData: false,
        dataType: "json"
    })
});