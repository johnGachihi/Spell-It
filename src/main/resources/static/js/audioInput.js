// $(document).ready(() => {
    var clickedBtn = "insert";
    var $audioInputChoice = $("#audio-input-choice");
    var $insertFileOption = $("#insertFileOption");
    var $recordOption = $("#recordOption");
    var $recordBtn = $("#recordBtn");
    var $stopRecordBtn = $("#stopRecordBtn");
    var $playRecordingBtn = $("#playRecordingBtn");

    var revealPoints;
    var concealPoints;

    //Sets up translation points
    //This can only happen when addLessonArea is displayed by clicking the addLesson button.
    function setUpPoints() {
        revealPoints = [0, $audioInputChoice.width() + 30];
        concealPoints = [$audioInputChoice.width() + 30, $audioInputChoice.width() - $("#wordPhonetic").width()];
        revealAnimation("#filename-div", true, [0, $audioInputChoice.width() + 30]);
    }
    $recordOption.css("backgroundColor", "rgb(0, 70, 140)");
    $insertFileOption.click(e => {
        e.preventDefault();

        if(clickedBtn !== "insert"){
            clickedBtn = "insert";
            $insertFileOption.css("backgroundColor", "");
            $recordOption.css("backgroundColor", "rgb(0, 70, 140)");

            revealAnimation("#record-controls", true, concealPoints)
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

            revealAnimation("#filename-div", true, concealPoints)
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
    var analyser = audioContext.createAnalyser();

    var canvas = document.getElementById("canvas");
    var canvasCtx = canvas.getContext("2d");
    var WIDTH = canvas.width;
    var HEIGHT = canvas.height;

    var audioFile;

    $recordBtn.click(e => {
        var constraints = {audio: true, video: false};
        navigator.mediaDevices.getUserMedia(constraints).then(stream => {
            audio_stream = stream;
            input = audioContext.createMediaStreamSource(stream);

            input.connect(analyser);
            analyser.fftSize = 256;
            var bufferLength = analyser.frequencyBinCount;
            var dataArray = new Uint8Array(bufferLength);
            canvasCtx.clearRect(0, 0, WIDTH, HEIGHT);
            function draw(){
                requestAnimationFrame(draw);
                analyser.getByteFrequencyData(dataArray);

                canvasCtx.fillStyle = "#007bff";
                canvasCtx.fillRect(0, 0, WIDTH, HEIGHT);

                var barWidth = (WIDTH/bufferLength) * 2;
                var barHeight;
                var x = 0;

                for(var i=0; i<bufferLength; i++){
                    barHeight = dataArray[i]/2;

                    canvasCtx.fillStyle = "#fff";
                    canvasCtx.fillRect(x, HEIGHT-barHeight/2, barWidth, barHeight);

                    x += barWidth + 1;
                }
            }
            draw();

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

    $playRecordingBtn.click(e => {
        var audio = new Audio();
        audio.src = URL.createObjectURL(audioFile);
        audio.play().then(r => {
            console.log("RECORDED AUDIO FINISHED");
        }).catch(err => {
            console.log(err);
        });
    });

    var $wordPhonetic = $("#wordPhonetic");
    $wordPhonetic.change(e => {
        audioFile = $wordPhonetic[0].files[0];
    });

    function getAudioFile(){
        return audioFile;
    }