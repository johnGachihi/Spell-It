var audioFiles = new Map();

const OptionTray = posed.div({
    revealed: {
        x: 217,
        opacity: 1,
        transition: {ease: "easeIn"}
    },
    concealed: {
        x: 0,
        opacity: 0,
        transition: {ease: "easeOut"}
    }
});

const Check = posed.div({
    reveal: {
        opacity: 1
    },
    conceal: {
        opacity: 0,
    }
});

class PhoneticInput extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            clicked: "insert",
            fileInputed: false
        };

        this.collapse = React.createRef();
        this.canvas = React.createRef();

        this.insertChoosen = this.insertChoosen.bind(this);
        this.recordChoosen = this.recordChoosen.bind(this);
        this._record = this._record.bind(this);
        this._stopRecording = this._stopRecording.bind(this);
        this._playRecording = this._playRecording.bind(this);
        this.onFileInsert = this.onFileInsert.bind(this);
    }

    insertChoosen() {
        if (this.state.clicked !== "insert") {
            this.setState({clicked: "insert"});
        }
    }

    onFileInsert(e) {
        var file = e.target.files[0];
        file = new File([file], this.props.text + "." + file.name.split(".").pop(), {type: file.type});
        audioFiles.set(this.props.text, file);
        console.log(audioFiles);
        this.setState({fileInputed: true})
    }

    recordChoosen() {
        if(this.state.clicked !== "record") {
            this.setState({clicked: "record"})
        }
    }

    _record() {
        record(this.canvas.current, this.props.text);
        $(this.collapse.current).collapse("show");
    }

    _stopRecording() {
        $(this.collapse.current).collapse("hide");
        stopRecording();
        this.setState({fileInputed: true});
    }

    _playRecording() {
        var audioFile = audioFiles.get(this.props.text);

        var audio = new Audio();
        audio.src = URL.createObjectURL(audioFile);
        console.log("Playing:", audio.src);
        audio.play();
    }

    render(){
        return (
            <div className="my-3">
                <div style={{display: "block"}}>
                    <span className="h5 mr-3">{this.props.text}</span>
                </div>
                <div className="my-2 position-relative">
                    <div id="audio-input-choice" className="btn-group" style={{zIndex: "2"}}>
                        <button id="insertFileOption"
                                className="btn btn-primary"
                                onClick={this.insertChoosen}
                                style={{backgroundColor: this.state.clicked !== "insert" ? "rgb(30,30,170)" : ""}}
                        >
                            Insert File
                        </button>
                        <button
                            id="recordOption"
                            className="btn btn-primary"
                            onClick={this.recordChoosen}
                            style={{backgroundColor: this.state.clicked !== "record" ? "rgb(30,30,170)" : ""}}
                        >
                            Record
                        </button>
                    </div>
                    <OptionTray
                        id="filename-div"
                        style={{position: "absolute", top: "0", left: "0", display: "inline", zIndex: "1"}}
                        pose={this.state.clicked === "insert" ? "revealed" : "concealed"}
                    >
                        <input id="wordPhonetic" type="file" onChange={this.onFileInsert}/>
                    </OptionTray>
                    <OptionTray
                        id="record-controls"
                        style={{position: "absolute", top: "0", left: "0", display: "inline", zIndex: "1"}}
                        pose={this.state.clicked === "record" ? "revealed" : "concealed"}
                    >
                        <a id="recordBtn" className="btn btn-primary fa fa-microphone text-light"
                           onClick={this._record}>
                        </a>
                        <a id="stopRecordBtn" className="btn btn-primary fa fa-stop text-light"
                           onClick={this._stopRecording}>
                        </a>
                        <a id="playRecordingBtn" className="btn btn-primary fa fa-play text-light"
                           onClick={this._playRecording}>
                        </a>
                    </OptionTray>
                    <Check pose={this.state.fileInputed ? "reveal" : "conceal"} style={{position: "absolute", right: "0", top: "0"}}>
                        <i className="far fa-check-circle"></i>
                    </Check>
                    <div id="audioVisualizer" className="collapse" ref={this.collapse}>
                        <canvas id="canvas" ref={this.canvas} className="mt-2 rounded" style={{width: "400px", height: "38px"}}></canvas>
                    </div>
                </div>
            </div>

        );
    }
}

class PhoneticInputForm extends React.Component {
    render() {
        console.log(this.props.absentees);
        var set = new Set(this.props.absentees);
        var texts = Array.from(set);
        return (
            <PoseGroup>
                {texts.map((t, i) => {
                    return <PhoneticInput text={t} key={t}/>
                })}
            </PoseGroup>

        );
    }
}

// var texts = ["a", "b", "c", "d", "d"];
// var textsSet = new Set(texts);
// texts = Array.from(textsSet);
//
// var audioInput = document.getElementById("audioInput");
// ReactDOM.render(<PhoneticInputForm absentees={texts}/>, audioInput);