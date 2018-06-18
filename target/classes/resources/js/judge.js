var type;
var mid;
function startJudge(tagType) {
    mid = document.getElementById("missionName").innerHTML;
    this.type = tagType;
<<<<<<< HEAD
    //alert(mid);
=======
>>>>>>> c00b92c5ac5fb295e68687b5e0f1fe6f9ddd745d
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
                alert("评估已完成！")
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
    if(type == 1){
        //标签式

        sampleSet2(Sample, mid);
    }else{
        document.getElementById("edit_area").style.display = "block";
        sampleSet(Sample, mid);
    }
}