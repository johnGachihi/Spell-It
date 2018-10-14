
var audioStream;
var audioFile;
var input;
var encodingType = "mp3";
var audioContext = new AudioContext();
var analyser = audioContext.createAnalyser();

function record(canvas, key) {
    var mediaAccessConstraints = {audio: true, video: false};
    navigator.mediaDevices.getUserMedia(mediaAccessConstraints).then(stream => {
        audioStream = stream;
        console.log("The stream: ", stream);
        input = audioContext.createMediaStreamSource(stream);
        input.connect(analyser);
        input.connect(audioContext.destination);

        analyser.fftSize = 256;
        var bufferLength = analyser.frequencyBinCount;
        var dataArray = new Uint8Array(bufferLength);

        var canvasCtx = canvas.getContext("2d");
        canvasCtx.clearRect(0, 0, canvas.width, canvas.height);

        function draw(){
            requestAnimationFrame(draw);
            analyser.getByteFrequencyData(dataArray);

            canvasCtx.fillStyle = "#007bff";
            canvasCtx.fillRect(0, 0, canvas.width, canvas.height);

            var barWidth = (canvas.width/bufferLength) * 2;
            var barHeight;
            var x = 0;

            for (var i=0; i<bufferLength; i++){
                barHeight = dataArray[i]/2;

                canvasCtx.fillStyle = "#fff";
                canvasCtx.fillRect(x, canvas.height-barHeight/1.5, barWidth, barHeight);
                x += barWidth + 1;
            }
        }
        draw();

        recorder = new WebAudioRecorder(input, recorderConfigs(key));
        recorder.setOptions(recorderOptions);

        recorder.startRecording();
    }).catch(err => {
        console.log(err);
    });
}

function stopRecording() {
    audioStream.getAudioTracks()[0].stop();
    recorder.finishRecording();
}

function playRecording() {
    var audio = new Audio();
    audio.src = URL.createObjectURL(audioFile);
    console.log("Playing:", audio.src);
    audio.play();
}

function recorderConfigs(key){
    return {
        workerDir: "/dependencies/audio-record/",
        encoding: encodingType,
        onEncoderLoading: (recorder, encoding) => {
            console.log("Loading", encodingType, "encoder")
        },
        onEncoderLoaded: (recorder, encoding) => {
            console.log("Loaded", encodingType, "encoder");
        },
        onComplete: (recorder, blob) => {
            console.log("Encoding complete");

            audioFile = new File([blob], (key + "." + encodingType), {type: blob.type});
            audioFiles.set(key, audioFile);
            console.log("The audioFiles:", audioFiles);
        }
    }
}

var recorderOptions = {
    timeLimit: 20,
    encodeAfterRecord: true,
    mp3: {bitRate: 160}
};

