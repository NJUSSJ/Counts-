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
        $("tag_editor").tagEditor({ placeholder: 'Enter tags ...' });
        //document.getElementById("tag_editor").disabled="";
        //document.getElementById("missionLabel").display="block";
        document.getElementById("evaluatePlace").innerHTML ="<span>评估策略： </span>\n" +
            "                            <select id=\"evaluateStrategy\" >\n" +
            "                                <option value=\"2\">手动评估</option>\n" +
            "                                <option value=\"3\">雇佣工人评估</option>\n" +
            "                            </select>";

        document.getElementById("bonusPlace").innerHTML = "<span>奖励分配策略： </span>\n" +
            "                            <select id=\"bonusStrategy\" >\n" +
            "                                <option value=\"2\">double or nothing</option>\n" +
            "                                <option value=\"3\">彩票策略</option>\n" +
            "                            </select>";


    }else{
        $("tag_editor").tagEditor('destroy');
        //document.getElementById("missionLabel").display="none";
        //document.getElementById("tag_editor").disabled="disabled";
        document.getElementById("evaluatePlace").innerHTML = "<span>评估策略： </span>\n" +
            "                            <select id=\"evaluateStrategy\" >\n" +
            "                                <option value=\"1\">系统自动评估</option>\n" +
            "                                <option value=\"2\">手动评估</option>\n" +
            "                            </select>";
        document.getElementById("bonusPlace").innerHTML = "<span>奖励分配策略： </span>\n" +
            "                            <select id=\"bonusStrategy\" >\n" +
            "                                <option value=\"1\">平均分配</option>\n" +
            "                            </select>";
    }
});

ensureBtn.addEventListener("click", function () {
    var difficulty = parseInt(document.getElementById("difficulty").value);
    var maxWorkerNum = parseInt(document.getElementById("maxWorkerNum").value);
    var picNum = document.getElementsByClassName("dz-image").length;
    var bonusStrategy = parseInt(document.getElementById("bonusStrategy").value);
    var evaluateStrategy = parseInt(document.getElementById("evaluateStrategy").value);

    $.ajax({
        async: false,
        type: "POST",
        url: "/calReward",
        contentType: "application/json",
        dataType: "json",
        data: {"difficulty": difficulty, "maxWorker": maxWorkerNum, "picNum": picNum,"uid": phoneNumber,"bonusStrategy": bonusStrategy,"evaluateStrategy": evaluateStrategy},
        success: function (returnData){
            reward=returnData;
            //alert(returnData);
        },
        error: function(){
            alert("fail14")
        }
    });

    var txt2 = "<span>任务奖励：</span>\n" +
        "      <input  id=\"reward\" type=\"text\" name=\"reward\" value= \"0\" disabled=\"disabled\" style=\"display=none\" />";
    document.getElementById("rewardBlank").innerHTML = txt2;
    document.getElementById("reward").value = reward.toFixed(2);

    if(confirm("发布本任务需要 " + reward.toFixed(2) + " 积分，您想继续发布吗？")){
        submitMission();
    }

});