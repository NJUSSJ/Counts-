//var rewardBase = 0;
var reward = 0;
var txt = "";
var phoneNumber = "";
var ensureBtn = document.getElementById("ensure");
var typeBtn = document.getElementById("type");

function loadPhone(_phoneNumber){
    alert(_phoneNumber);
    phoneNumber = _phoneNumber;
}

typeBtn.addEventListener("click", function () {
    if (typeBtn.value === "1"){
        txt = "<span>标签名称（不同标签请用空格隔开）：<input  type=\"text\"/></span>";
        document.getElementById("tagNames").innerHTML = txt;
    }else{
        document.getElementById("tagNames").innerHTML = "";
    }
});

ensureBtn.addEventListener("click", function () {
    var difficulty = parseInt(document.getElementById("difficulty").value);
    var maxWorker = parseInt(document.getElementById("maxNum").value);
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
    alert(difficulty + " "  + maxWorker+ " " + phoneNumber);

    $.ajax({
        async: false,
        type: "POST",
        url: "/calReward",
        contentType: "application/json",
        dataType: "json",
        data: {"difficulty": difficulty, "maxWorker": maxWorker, "phoneNumber": phoneNumber},
        success: function (returnData){
            reward=returnData;
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