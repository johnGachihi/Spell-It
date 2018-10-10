// $(document).ready(() => {
    var clickedBtn = "insert";
    var $audioInputChoice = $("#audio-input-choice");
    var $insertFileOption = $("#insertFileOption");
    var $recordOption = $("#recordOption");
    var $recordBtn = $("#recordBtn");
    var $stopRecordBtn = $("#stopRecordBtn");

    var revealPoints;
    var concealPoints;

    function setUpPoints() {
        revealPoints = [0, $audioInputChoice.width() + 30];
        concealPoints = [$audioInputChoice.width() + 30, 0];
        revealAnimation("#filename-div", true, [0, $audioInputChoice.width() + 30]);
    }
    $recordOption.css("backgroundColor", "rgb(0, 70, 140)");
    $insertFileOption.click(e => {
        e.preventDefault();

        if(clickedBtn !== "insert"){
            clickedBtn = "insert";
            $insertFileOption.css("backgroundColor", "");
            $recordOption.css("backgroundColor", "rgb(0, 70, 140)");

            revealAnimation("#record-controls", true, [$audioInputChoice.width() + 30, 0])
                .finished.then(r => {
                revealAnimation("#filename-div", true, [0, $audioInputChoice.width() + 30]);
            });
        }
    });

    $recordOption.click(e => {
        e.preventDefault();

        if(clickedBtn !== "record"){
            clickedBtn = "record";
            $recordOption.css("backgroundColor", "");
            $insertFileOption.css("backgroundColor", "rgb(0, 70, 140)");

            revealAnimation("#filename-div", true, [$audioInputChoice.width() + 30, 0])
                .finished.then(r => {
                revealAnimation("#record-controls", true, [0, $audioInputChoice.width() + 30]);
            });
        }
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

    var audio_stream;
    var recorder;
    var input;
    var encodingType = "mp3";
    var audioContext = new AudioContext();

    var audioFile;

    $recordBtn.click(e => {
        var constraints = {audio: true, video: false};
        navigator.mediaDevices.getUserMedia(constraints).then(stream => {
            audio_stream = stream;
            input = audioContext.createMediaStreamSource(stream);
            input.connect(audioContext.destination);
            recorder = new WebAudioRecorder(input, {
                workerDir: "/dependencies/audio-record/",
                encoding: encodingType,
                onEncoderLoading: (recorder, encoding) => {
                    console.log("Loading", encoding, "encoder.");
                    //SHOW LOADING ANIMATION
                },
                onEncoderLoaded: (recorder, encoding) => {
                    //DROP LOADING ANIMATION
                },
                onComplete: (recorder, blob) => {
                    console.log(blob);
                    audioFile = new File([blob], "a." + encodingType, {type: encodingType});
                    console.log("Audio file:", audioFile);
                }
            });

            recorder.setOptions({
                timeLimit: 20,
                encodeAfterRecord: true,
                mp3: {bitRate: 160}
            });

            recorder.startRecording();

        }).catch(err => {
            console.log(err);
        })
    });

    $stopRecordBtn.click(e => {
        audio_stream.getAudioTracks()[0].stop();
        recorder.finishRecording();
    });

    var $wordPhonetic = $("#wordPhonetic");
    $wordPhonetic.change(e => {
        audioFile = $wordPhonetic[0].files[0];
    });

    function getAudioFile(){
        return audioFile;
    }