var lessons = [];
var $levelID = $("#levelID");
var $levelNo = $("#levelNo");
var $checkpointWordImageInput = $("#checkpointWordImage");
var $addLevelBtn = $("#addLevelBtn");

$("#lessonContentSubmit").on("click", (e) => {
   e.preventDefault();

   console.log($levelID.val());

    let word = $("#wordInput").val();
    let segments = $("#segmentsInput").val().split("_");
    let wordImage = $("#wordImage")[0].files[0];
    // let wordPhonetic = $("#wordPhonetic")[0].files[0];

    var text = word.split("").concat(segments);
    console.log(text);

    $(".spinner").slideDown();

    console.log("And in lessonContentSubmit", getAudioFile());

    var numOfAbsentTexts = 0;
    $.ajax({
        url: "/phonetics/check-availability",
        data: {texts: text},
        dataType: "json"
    }).then(
        //.done()
        data => {
            numOfAbsentTexts = new Set(data).size;

            if(data.length > 0){
                $(".spinner").slideUp();
                $("#textSoundSubmitModal").modal("show");

                const i = document.getElementById("neededInputs");
                ReactDOM.render(<PhoneticInputForm absentees={data}/>, i);

                console.log("And in lessonSubmit, the audioFiles:", audioFiles);
                attachEventListenerToSaveBtn();
            }
        },
        (jqXHR, status, err) => {
            console.log(err);
        }
    );

    function attachEventListenerToSaveBtn() {
        $("#textsSoundSubmitBtn").click(e => {
            if(audioFiles.size === numOfAbsentTexts) {
                let formData = insertFilesIntoFormData(audioFiles);
                console.log(formData.getAll("files"));
                $.ajax({
                    url: "/phonetics/saveAllPhonetics",
                    data: formData,
                    method: "post",
                    processData: false,
                    contentType: false
                }).then(
                    () => {
                        $("#textSoundSubmitModal").modal("hide");
                        submitLessonContent();
                    }
                )
            }
        })
    }

    function insertFilesIntoFormData(audioFiles) {
        let audioFilesArray = Array.from(audioFiles.values());
        let formData = new FormData();
        for(let file of audioFilesArray) {
            formData.append("files", file, file.name);
        }
        return formData;
    }

    function submitLessonContent(){
        let levelID = $levelID.val();
        let levelNo = $levelNo.val();

        let formData = new FormData();

        formData.append("word", word);
        formData.append("segments", segments);
        formData.append("wordImage", wordImage, wordImage.name);
        formData.append("wordPhonetic", getAudioFile(), getAudioFile().name);
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
                $("#no-lessons-message").attr("style", "display: none;");
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
            }
        });
    }


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

    window.location = "/edit-content"
});

function revealAnimation(element, autoplay, translationPoints){
    return anime({
        targets: element,
        translateX: translationPoints,
        duration: 500,
        easing: "easeOutQuad",
        autoplay: autoplay,
    });
}