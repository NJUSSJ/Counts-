/**
 *@author fortune
 */
var userCategory1;
var Tagable1;

function load(num,url,collection,userCategory,Tagable,phoneNumber,picNum) {
    if(userCategory === 3){
        document.getElementById("addMissionBtn").innerHTML = "";
        document.getElementById("addMissionBtn").style.display = "none";
    }

    userCategory1=userCategory;
    Tagable1=Tagable;



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
        url: "/addJudgeMissionToUser",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(collectionData),
        success: function (returnData) {
            if(returnData!="0"){
                alert(returnData);
            }
        },
        error: function () {

        }
    });
}

