var returnSample;
var picIndex;
var imageInfo;
var quality;
var userId;
var indexForSample=0;
var lastIndex;
var picName;
var grade;


var scale = function (btn,bar,title){

    this.btn = document.getElementById(btn);

    this.bar = document.getElementById(bar);

    this.title = document.getElementById(title);

    this.step = this.bar.getElementsByTagName("div")[0];



    this.init = function (){

        var f=this,g=document,b=window,m=Math;

        f.btn.onmousedown=function (e){

            var x=(e||b.event).clientX;

            var l=this.offsetLeft;

            var max=f.bar.offsetWidth-this.offsetWidth;

            g.onmousemove=function (e){

                var thisX=(e||b.event).clientX;

                var to=m.min(max,m.max(-2,l+(thisX-x)));

                f.btn.style.left=to+'px';

                f.ondrag(m.round(m.max(0,to/max)*100),to);

                b.getSelection ? b.getSelection().removeAllRanges() : g.selection.empty();

            };

            g.onmouseup=new Function('this.onmousemove=null');

        };

    };

    this.ondrag = function (pos,x){

        this.step.style.width=Math.max(0,x)+'px';

        this.title.innerHTML='分数：'+pos+'';

        grade = pos;
    };

    this.init();

}



new scale('btn','bar','title'); //实例化一个拖拽


function loadDetails(picNum) {
    /*
    加载任务图片
     */
    var missionName=document.getElementById("Name").innerText;

    loadChart1(missionName);//

    var picSection=document.getElementById("picSection");
    for(var i=1;i<=picNum;i++){
        var div=document.createElement("div");
        div.className="4u 12u$(mobile)";
        var a=document.createElement("a");
        a.href="#";
        a.className="image fit picture";
        var img=document.createElement("img");
        img.src="missionImages/"+missionName+"_"+i+".jpg";
        img.style.height = "150px";
        img.style.width = "242.7px";
        a.appendChild(img);
        div.appendChild(a);
        picSection.appendChild(div);
    }


}

/*
parameters for canvas
 */
var canvas = document.getElementById("canvasForSample");
var cxt = canvas.getContext("2d");

//已确定的矩形框
var fixedX = [];
var fixedY = [];
var fixedWidth = [];
var fixedHeight = [];
var index1 = 0;
var index2 = 0;
var colorArray = ["#000000","#66a3ff","#ff4d4d","#33cc33","#ffa31a","#cc33ff","#ff0000","#006600","#0000b3"];
var curlArray=[];

//data list
var sentences = [];
var sentids = [];


var Pic_width;
var Pic_height;
var Pic_x;
var Pic_y;
var image = new Image();

function sampleSet(sample, missionName) {
    picIndex=sample.picIndex;
    imageInfo=sample.imageInfo;
    quality=sample.quality;
    userId=sample.userId;
    lastIndex=userId.length-1;
    loadOneSample(indexForSample,missionName);
}

function loadOneSample(index,missionName) {
    image.src="";
    document.getElementById("textareaPlace").innerHTML="";


    var imgInfo=eval(imageInfo[index]);
    //alert(imgInfo.imgid);

    /*
    load rect and curl
     */
    if(imgInfo!=null){
        if(imgInfo.fixedx!=null||imgInfo.list!=null) {

            fixedX = imgInfo.fixedx;
            fixedY = imgInfo.fixedy;
            fixedWidth = imgInfo.fixedwidth;
            fixedHeight = imgInfo.fixedheight;
            curlArray = imgInfo.list;

            index1=fixedX.length;
            index2=curlArray.length;
            sentences=imgInfo.sentences;
            sentids=imgInfo.sentids;

        }
    }

    /*
    load overall
     */
    if(imgInfo!=null){
        if(imgInfo.fixedx!=null||imgInfo.list!=null||imgInfo.sentences!=null){
            var OverallIndex=0;
            while(imgInfo.sentences[OverallIndex].status!=2){
                OverallIndex++;
                if(OverallIndex==imgInfo.sentences.length){
                    break;
                }
            }
            if(OverallIndex<imgInfo.sentences.length){
                document.getElementById("info").innerHTML=imgInfo.sentences[OverallIndex].raw;
            }

        }
    }

    image.src ="missionImages/"+missionName+"_"+picIndex[indexForSample]+".jpg";


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

    drawImage();

    var indexOfRectSentence=0;
    var indexOfCurlSentence=0;


    var parent=document.getElementById("textareaPlace");

    /*
    add rect info
     */
    for(var i=0;i<fixedX.length;i++){
        if(fixedWidth[i]==0){
            continue;
        }
        var textarea  =  document.createElement("textarea");
        while(imgInfo.sentences[indexOfRectSentence].status!=0){
            indexOfRectSentence++;
            if(indexOfRectSentence==imgInfo.sentences.length){
                break;
            }
        }
        textarea.innerHTML=imgInfo.sentences[indexOfRectSentence].raw;
        indexOfRectSentence++;
        textarea.style.borderColor = colorArray[i];
        textarea.style.margin = "5px";
        textarea.style.width = "90px";
        textarea.id = "rectArea"+i;
        parent.appendChild(textarea);
    }

    for(var i=0;i<curlArray.length;i++){
        if(curlArray[i].length==1){
            continue;
        }

        var textarea=document.createElement("textarea");

        while(imgInfo.sentences[indexOfCurlSentence].status!=1){
            indexOfCurlSentence++;
            if(indexOfCurlSentence==imgInfo.sentences.length){
                break;
            }
        }

        textarea.innerHTML=imgInfo.sentences[indexOfCurlSentence].raw;

        indexOfCurlSentence++;

        textarea.style.borderColor = colorArray[i];
        textarea.style.margin = "5px";
        textarea.style.width = "90px";
        textarea.id="curlArea"+i;

        textarea.style.borderStyle = "dotted";
        parent.appendChild(textarea);

    }


}

/*
绘制已有Rect
 */
function drawRects() {
    for(var i = 0;i<index1;i++){
        cxt.strokeStyle = (colorArray[i]);
        cxt.strokeRect(fixedX[i],fixedY[i],fixedWidth[i],fixedHeight[i]);
    }
    cxt.strokeStyle = (colorArray[index1]);
}

function drawCurl() {
    for(var i=0;i<curlArray.length;i++){
        cxt.moveTo(curlArray[i][0],curlArray[i][1]);
        for(var j=2;j<curlArray[i].length-1;j=j+2){
            cxt.lineTo(curlArray[i][j],curlArray[i][j+1]);
        }
        cxt.strokeStyle=colorArray[i];
        cxt.stroke();
        cxt.beginPath();
    }
}

/*
functions for canvas rect
 */
function drawImage() {
    cxt.drawImage(image,Pic_x,Pic_y,Pic_width,Pic_height);
    drawRects();
    drawCurl();
}



function good() {
    if(!judgeLast()){
        quality[indexForSample]=3;
        indexForSample++;
        loadOneSample(indexForSample,picName);
    }else{
        quality[indexForSample]=3;
        sendJudgeResult();
    }
}

function fair() {
    if(!judgeLast()){
        quality[indexForSample]=2;
        indexForSample++;
        loadOneSample(indexForSample,picName);
    }else{
        quality[indexForSample]=2;
        sendJudgeResult();
    }
}

function middle() {
    if(!judgeLast()){
        quality[indexForSample]=1;
        indexForSample++;
        loadOneSample(indexForSample,picName);
    }else{
        quality[indexForSample]=1;
        sendJudgeResult();
    }
}

function bad() {
    if(!judgeLast()){
        quality[indexForSample]=0;
        indexForSample++;
        loadOneSample(indexForSample,picName);
    }else{
        quality[indexForSample]=0;
        sendJudgeResult();
    }
}

function judgeLast(){
    if(indexForSample==lastIndex){
        alert("评估完成！")
        var sampleArea=document.getElementById("edit_area");
        sampleArea.style.display="none";
        var samplePanel=document.getElementById("samplePanel");
        var info=document.createElement("h6");
        info.innerHTML="评估已完成！";
        samplePanel.appendChild(info);
        return true;

    }else{
        return false;
    }
}

function sendJudgeResult() {
    //alert(quality);
    returnSample.imageInfo=imageInfo;
    returnSample.userId=userId;
    returnSample.picIndex=picIndex;
    returnSample.quality=quality;
    returnSample.missionName=picName;

    $.ajax({
        type: "POST",
        url: "getSampleResult",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(returnSample),
        success: function() {

        },
        error: function(){

        }
    });
}

var step=-1;

function addOne() {
    step--;
}

function back() {
    window.history.go(step);
}

var number0;
var number1;
var number2;
var number3;
var numberSum;
var credit;
var level;

function SingleMissionData(number0, number1, number2, number3, numberSum, credit, level) {
    this.number0=number0;
    this.number1=number1;
    this.number2=number2;
    this.number3=number3;
    this.numberSum=numberSum;
    this.credit=credit;
    this.level=level;
}
function loadChartsData() {

}


