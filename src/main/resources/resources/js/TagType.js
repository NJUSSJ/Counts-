var size = 0;
var clicked;
var missionLabel = [];
var clickedMissionLabel = [];

function loadMissionLabel1(_missionLabel,url,tagType) {
    missionLabel = _missionLabel.split(",");
    loadTagType(tagType);
    loadCanvas(url);
}

function loadTagType(tagType) {

    size = missionLabel.length;
    clicked = [size];
    //对clicked初始化
    for(var clickIndex = 0;clickIndex<size;clickIndex++){
        clicked[clickIndex] = 0;
    }

    if(tagType === "1" || tagType === 1){
        var btn = "";
        for(var i =0;i<size;i++){
            btn += "<div style='margin-bottom: 10px'><input href=\"#\" style=\"width=30 height=50\" class=\"button special\" id="+ i +" value="+ missionLabel[i] +" onclick=clickLabel(" + i + ");  /></div>";
            btn += "<br>";
        }

        document.getElementById("textareaPlace").innerHTML = btn;

    }else {
        console.log("nononono!!!!!!");
    }
}

function clickLabel(i) {
    var index = parseInt(i);

    if(clicked[index] == 0){
        clicked[index] = 1;
        //alert(index + " clicked");
        document.getElementById("info").innerHTML+="<li><a id=clicked"+ missionLabel[index] +" href=\"#\" class=\"button small\" style='width=40 height=60'>"+ missionLabel[index] +"</a></li>";
    }else{
        clicked[index] = 0;
        //alert(index + " unclicked");
        var thisNode = document.getElementById("clicked" + missionLabel[index]);
        thisNode.parentNode.removeChild(thisNode);
    }
}

function loadLabelInfo() {
    var num = 0;
    for(var p = 0;p<size;p++) {
        if (clicked[p] == 1) {
            clickedMissionLabel[num] = missionLabel[p];
            num++;
        }
    }
    alert("clickedMissionLabel: " + clickedMissionLabel);
    return clickedMissionLabel;
}