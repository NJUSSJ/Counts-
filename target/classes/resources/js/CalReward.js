//var rewardBase = 0;
var reward = 0;
var txt = "";
var phoneNumber = "";
var ensureBtn = document.getElementById("ensure");
var tagTypeBtn = document.getElementById("tagType");

function loadPhone(_phoneNumber){
    phoneNumber = _phoneNumber;
}

tagTypeBtn.addEventListener("click", function () {
    if (tagTypeBtn.value === "1"){
        //document.getElementById("tag_editor").disabled="";
        document.getElementById("missionLabel").display="block";

    }else{
        document.getElementById("missionLabel").display="none";
        //document.getElementById("tag_editor").disabled="disabled";
    }
});

ensureBtn.addEventListener("click", function () {
    var difficulty = parseInt(document.getElementById("difficulty").value);
    var maxWorkerNum = parseInt(document.getElementById("maxWorkerNum").value);
/*
    if (difficulty === 1) {
        rewardBase = 0.1;
    }
    if (difficulty === 2) {
        rewardBase = 0.15;
    }
    if (difficulty === 3) {
        rewardBase = 0.2;
    }
*/
    //alert(difficulty + " "  + maxWorkerNum+ " " + phoneNumber);

    $.ajax({
        async: false,
        type: "POST",
        url: "/calReward",
        contentType: "application/json",
        dataType: "json",
        data: {"difficulty": difficulty, "maxWorker": maxWorkerNum, "uid": phoneNumber},
        success: function (returnData){
            reward=returnData;
            //alert(returnData);
        },
        error: function(){
            alert("fail14")
        }
    });

    /*if(rewardBase !== 0 && maxWorker !== 0 && picNum!== 0) {
        reward = rewardBase * Math.pow(1.1, picNum) * maxWorker;
        document.getElementById("reward").value = reward.toFixed(2);
    }*/

    var txt2 = "<span>任务奖励：</span>\n" +
        "      <input  id=\"reward\" type=\"text\" name=\"reward\" value= \"0\" disabled=\"disabled\"/>";
    document.getElementById("rewardBlank").innerHTML = txt2;
    document.getElementById("reward").value = reward.toFixed(2);
});