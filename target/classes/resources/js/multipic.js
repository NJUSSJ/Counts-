/**
 *@author fortune
 */
var userCategory1;
var Tagable1;

function load(num,url,collection,userCategory,Tagable,phoneNumber,picNum,picType) {

    if(userCategory === 1){
        document.getElementById("addMissionBtn").innerHTML = "";
        document.getElementById("addMissionBtn").style.display = "none";
        document.getElementById("judgeMissionBtn").innerHTML = "";
        document.getElementById("judgeMissionBtn").style.display = "none";
    }

    userCategory1=userCategory;
    Tagable1=Tagable;

    var cover = document.getElementById("cover");
    cover.src = url+"1.jpg";

    var tagType = document.getElementById("tagType");
    if(tagType.innerHTML == "1"){
        tagType.innerHTML = "标签式";
    }else{
        tagType.innerHTML = "非标签式";
        document.getElementById("judgeMissionBtn").style.display ="none";
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
}

function setButtons(num) {
    if(Tagable1=="0"){
        var buttons=document.getElementsByClassName("button special");
        for(var i=0;i<=num;i++){
            buttons[i].style.display="none";
        }
    }else {
        var submenu=document.getElementById("submenu");
        submenu.style.display="none";
    }
    if(userCategory1=="1"){
        var submenu=document.getElementById("submenu");
        submenu.style.display="none";
    }
}

function collection(uid, mid){
    this.uid=uid;
    this.mid=mid;
}
function addMissionToUser(missionName, userPhone) {
        //alert(missionName);
        //alert(userPhone);
        var collectionData=new collection(userPhone,missionName);
        $.ajax({
           type: "POST",
           async: false,
           url: "/addMissionToUser",
           contentType: "application/json",
           dataType: "json",
           data: JSON.stringify(collectionData),
           success: function (returnData) {
               if(returnData=="0"){
                   alert("您的等级未达到任务要求！");
               }else if(returnData=="1"){
                   alert("该任务已截止！");
               }
           },
           error: function () {

           }
        });
}


function addJudgeMissionToUser(missionName, userPhone) {
    //alert(missionName);
    //alert(userPhone);
    var collectionData=new collection(userPhone,missionName);
    $.ajax({
        type: "POST",
        async: false,
        url: "/getGoldMission",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(collectionData),
        success: function (returnData) {
            if(returnData=="0"){
                alert("该任务评估入口已关闭！");
            }
        },
        error: function () {

        }
    });
}

