var picIndex;
var imageInfo;
var answers = [];
var uid;
var indexForSample=0;
var lastIndex;
var picName;
var grade;
var labels = [];
var labelIndex = 0;

function finishReviewPara(mid, indexs, answers, uid) {
    this.mid = mid;
    this.indexs = indexs;
    this.answers = answers;
    this.uid = uid;
}
var scale = function (btn,bar,title){

    this.btn = document.getElementById("btn");

    this.bar = document.getElementById("bar");

    this.title = document.getElementById("title");

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


/*
加载抽样
 */
function loadSample() {
    var tagType = document.getElementById("tagType");
    if(tagType.innerHTML == "1"){
        tagType.innerHTML = "标签式";
    }else{
        tagType.innerHTML = "非标签式";
    }

    var bonusStrategy = document.getElementById("evaluateStrategy");
    if(bonusStrategy.innerHTML == "1"){
        bonusStrategy.innerHTML = "自动评估";
    }else if(bonusStrategy.innerHTML == "2"){
        bonusStrategy.innerHTML = "手动评估";
    }else {
        bonusStrategy.innerHTML = "雇佣评估";
    }

    var evaluateStrategy = document.getElementById("bonusStrategy");
    if(evaluateStrategy.innerHTML == "1"){
        evaluateStrategy.innerHTML = "平均分配";
    }else if(evaluateStrategy.innerHTML == "2"){
        evaluateStrategy.innerHTML = "Double or nothing";
    }else{
        evaluateStrategy.innerHTML = "双色球分配";
    }
    $.ajax({
        type: "POST",
        url: "/getSample",
        contentType: "application/json",
        dataType: "json",
        data: document.getElementById("missionName").innerHTML,
        success: function(returnData) {
            if(returnData=="0"){
                var sampleArea=document.getElementById("choices");
                sampleArea.style.display="none";
                var samplePanel=document.getElementById("samplePanel");
                var info=document.createElement("h6");
                info.innerHTML="任务还未截止！请在任务结束后的三个工作日内进行评估。";
                samplePanel.appendChild(info);
            }else if(returnData=="2"){
                var sampleArea=document.getElementById("choices");
                sampleArea.style.display="none";
                var samplePanel=document.getElementById("samplePanel");
                var info=document.createElement("h6");
                info.innerHTML="该任务已完成过评估！";
                samplePanel.appendChild(info);
            } else{
            }
        },
        error: function(){
            //alert("fail")
        }
    });
}

/*
parameters for canvas
 */
var canvas = document.getElementById("canvas1");
var cxt = canvas.getContext("2d");
var image = new Image();

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


function sampleSet(sample, missionName) {
    picIndex=sample.picIndex;
    imageInfo=sample.info;

    uid=sample.uid;
    lastIndex=uid.length-1;
    loadOneSample(indexForSample,missionName);
}

function loadOneSample(index,missionName) {

    image.src="";
    document.getElementById("textareaPlace").innerHTML="";
    var jsonString = JSON.stringify(imageInfo[index]);
    var imgInfo=eval("("+jsonString+")");
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

    image.src = "missionImages/"+missionName+"_"+(picIndex[indexForSample]+1)+".jpg";

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

        drawImage();
    };


    var indexOfRectSentence=0;
    var indexOfCurlSentence=0;


    var parent=document.getElementById("textareaPlace");

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

    if(imgInfo!=null){
        if(imgInfo.fixedx!=null||imgInfo.list!=null||imgInfo.sentences!=null){
            var OverallIndex=0;
            while(imgInfo.sentences[OverallIndex].status!=2){
                OverallIndex++;
                if(OverallIndex==imgInfo.sentences.length){
                    break;
                }
            }
            if(imgInfo.sentences[OverallIndex]!=null){
                document.getElementById("info").innerHTML=imgInfo.sentences[OverallIndex].raw;
            }

        }
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
        answers[indexForSample]= grade;
        indexForSample++;
        loadOneSample(indexForSample,picName);
    }else{
        answers[indexForSample]=grade;
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
    var returnInfo = new finishReviewPara(mid, picIndex, answers, uid);
    //alert(quality);
    $.ajax({
        type: "POST",
        url: "/finishReview",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(returnInfo),
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


function sampleSet2(Sample, mid) {
    picIndex = Sample.picIndex;
    labels = Sample.label[0].split(",");
    lastIndex=picIndex.length;

    var btn = "";
    for(var i =0;i<labels.length;i++){
        btn += "<div><input href=\"#\" style=\"width=30 height=50\" class=\"button special\" id="+ i +" value="+ labels[i] +" onclick=clickLabel(" + i + ");  /></div>";
        btn += "<br>";
    }

    document.getElementById("textareaPlace2").innerHTML = btn;

    loadOneSample2(indexForSample, mid);

}

function loadOneSample2(index, mid) {
    var url = "missionImages/"+mid+"_"+picIndex[index]+".jpg";
    loadCanvas(url);
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
        var cxt = canvas.getContext("2d");
        cxt.clearRect(0,0,canvas.width,canvas.height);
        cxt.drawImage(image,Pic_x,Pic_y,Pic_width,Pic_height);
    };


}



function clickLabel(i) {
    if(labelIndex == lastIndex-1){
        answers[labelIndex] = i;
        alert(answers);
        document.getElementById("choices").style.display = "none";
        var sampleArea=document.getElementById("edit_area2");
        sampleArea.style.display="none";
        var samplePanel=document.getElementById("samplePanel");
        var info=document.createElement("h6");
        info.innerHTML="评估已完成！";
        samplePanel.appendChild(info);
        finishReview();
    }else{
        answers[labelIndex] = i;
        labelIndex++;
        loadOneSample2(labelIndex, mid);
    }
}
function finishReview() {
    var para = new finishReviewPara(mid, picIndex, answers, uid);
    $.ajax({
        async: true,
        type: "POST",
        url: "/finishReview",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(para),
        success: function (returnData) {

        }
        ,
        error: function(){
            //alert("提交失败!");
        }
    });
}