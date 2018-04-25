var returnSample;
var picIndex;
var imageInfo;
var quality;
var userId;
function loadDetails(picNum) {
    /*
    加载任务图片
     */
    var missionName=document.getElementById("Name").innerText;
    var picSection=document.getElementById("picSection");
    for(var i=1;i<=picNum;i++){
        var div=document.createElement("div");
        div.className="4u 12u$(mobile)";
        var a=document.createElement("a");
        a.href="#";
        a.className="image fit picture";
        var img=document.createElement("img");
        img.src="../../images/"+missionName+"_"+i+".jpg";
        a.appendChild(img);
        div.appendChild(a);
        picSection.appendChild(div);
    }

    loadSample(missionName);


}

/*
加载抽样
 */
function loadSample(missionName) {
    $.ajax({
        type: "POST",
        url: "/getSample",
        contentType: "application/json",
        dataType: "json",
        data: missionName,
        success: function(returnData) {
            if(returnData=="0"){
                var sampleArea=document.getElementById("edit_area");
                sampleArea.style.display="none";
                var samplePanel=document.getElementById("samplePanel");
                var info=document.createElement("h6");
                info.innerHTML="任务还未截止！请在任务结束后的三个工作日内进行评估。";
                samplePanel.appendChild(info);
            }else{
                var jsonString=returnData;
                var Sample=eval(jsonString);
                returnSample=Sample;
                alert(Sample.missionName);
                sampleSet(Sample);
            }
        },
        error: function(){
            alert("fail")
        }
    });
}

function sampleSet(sample) {
    picIndex=sample.picIndex;
    imageInfo=sample.imageInfo;
    quality=sample.quality;
    userId=sample.userId;
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