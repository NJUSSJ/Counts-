var rewardBase = 0;
var reward = 0;
var ensureBtn = document.getElementById("ensure");

ensureBtn.addEventListener("click", function () {
    var difficulty = parseInt(document.getElementById("difficulty").value);
    var maxWorker = parseInt(document.getElementById("maxNum").value);
    var picNum = parseInt(document.getElementById("picNum").value);

    if (difficulty === 1) {
        rewardBase = 0.1;
    }
    if (difficulty === 2) {
        rewardBase = 0.15;
    }
    if (difficulty === 3) {
        rewardBase = 0.2;
    }

    alert(difficulty + " " + rewardBase + " " + maxWorker+ " " + picNum);

    $.ajax({
        async: false,
        type: "POST",
        url: "/calReward",
        contentType: "application/json",
        dataType: "json",
        data: {"rewardBase": rewardBase, "maxWorker": maxWorker, "picNum": picNum},
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

    document.getElementById("reward").value = reward.toFixed(2);
});