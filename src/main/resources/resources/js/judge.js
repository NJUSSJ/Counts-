var type;
function startJudge(tagType) {
    this.type = tagType;
    var mid = document.getElementById("missionName").innerHTML;
    alert(mid);
    $.ajax({
        async: true,
        type: "POST",
        url: "/startReview",
        contentType: "application/json",
        dataType: "json",
        data: mid,
        success: function (ret) {
            alert(ret);
            if(ret.length == 0) {
                alert("评估已完成！")
            }else{
                judge();
            }
        }
        ,
        error: function(){
                alert("提交失败!");
        }
    });

}

function judge() {
    if(type == 1){
        //标签式
    }else{
        document.getElementById("edit_area").style.display = "block";
    }
}