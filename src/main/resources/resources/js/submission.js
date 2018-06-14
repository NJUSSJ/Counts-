var submission;
function submissioninfo(keyword, uid) {
    this.keyword = keyword;
    this.uid = uid;
}
function load(mid,uid) {
    var info = new submissioninfo(mid, uid)
    $.ajax({
        async: true,
        type: "POST",
        url: "/MissionEvaluate/getPictureIndex",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(info),
        success: function (returnData) {
            alert(returnData);
            submission = returnData;
        },
        error:function () {
            alert("fail");
        }
    })

    for(var i=0;i<submission.length();i++){

    }
}