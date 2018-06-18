var type;
var mid;
function startJudge(tagType) {
    mid = document.getElementById("missionName").innerHTML;
    this.type = tagType;
    //alert(mid);
    $.ajax({
        async: true,
        type: "POST",
        url: "/startReview",
        contentType: "application/json",
        dataType: "json",
        data: mid,
        success: function (ret) {
            if(ret == null) {
                alert("评估已完成！")
            }else{
                var Sample = eval(ret);
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
        document.getElementById("edit_area2").style.display = "block";
        sampleSet2(Sample, mid);
    }else{
        document.getElementById("edit_area").style.display = "block";
        sampleSet(Sample, mid);
    }
}