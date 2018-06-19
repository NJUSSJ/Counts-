var type;
var mid;
function startJudge(tagType) {
    mid = document.getElementById("missionName").innerHTML;
    this.type = tagType;
    $.ajax({
        async: true,
        type: "POST",
        url: "/startReview",
        contentType: "application/json",
        dataType: "json",
        data: mid,
        success: function (returnData) {
            var jsonString = JSON.stringify(returnData);
            var Sample = eval("("+jsonString+")");
            if(Sample.goldMissionAllDone == 1) {
                alert("后台评估已完成！");
                document.getElementById("choices").style.display = "none";
                var sampleArea=document.getElementById("edit_area");
                sampleArea.style.display="none";
                var samplePanel=document.getElementById("samplePanel");
                var info=document.createElement("h6");
                info.innerHTML="评估已完成！";
                samplePanel.appendChild(info);
            }else{
                judge(Sample);
            }
        }
        ,
        error: function(){
                alert("提交失败!");
        }
    });

}

function judge(Sample) {
    document.getElementById("choices").style.display = "none";
    if(type == 1){
        //标签式
        var sampleArea =  document.getElementById("edit_area2")
        sampleArea.style.display = "block";
        sampleSet2(Sample, mid);
    }else{
        var sampleArea =  document.getElementById("edit_area")
        sampleArea.style.display = "block";
        sampleSet(Sample, mid);
    }
}