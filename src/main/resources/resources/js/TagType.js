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
        document.getElementById("info").innerHTML="<li><a id=clicked"+ missionLabel[index] +" href=\"#\" class=\"button small\" style='width=40 height=60'>"+ missionLabel[index] +"</a></li>";//多选改为单选
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
    alert("已选择标签: " + clickedMissionLabel[0]);
    return clickedMissionLabel[0];
}

function loadCanvas(url) {
    var canvas = document.getElementById("canvas");
    /*
        parameters for Pic
     */
    var Pic_width;
    var Pic_height;
    var Pic_x;
    var Pic_y;

    var image = new Image();
    image.src = url;



    image.onload = function () {
        var width = image.width;
        var height = image.height;

        var MulX = canvas.width/width;
        var MulY = canvas.height/height;



        if(MulX<= MulY){
            Pic_width = width*MulX;
            Pic_height = height*MulX;
            Pic_x = 0;
            Pic_y = 0;
        }
        else{
            Pic_width = width*MulY;
            Pic_height = height*MulY;
            Pic_y = 0;
            Pic_x = 0;
        }
        canvas.width=Pic_width;
        canvas.height=Pic_height;
        var cxt = canvas.getContext("2d");
        cxt.drawImage(image,Pic_x,Pic_y,Pic_width,Pic_height);
    };


}