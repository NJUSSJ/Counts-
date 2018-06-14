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
    });

    var submissionArea = document.getElementById("");


    for(var i=0;i<submission.length();i++){
        var div1=document.createElement("div");
        div1.className="4u 12u(narrower)";
        var section1=document.createElement("section");
        var missionName1=document.createElement("h3");
        missionName1.innerHTML=missionNames[i*3].substring(0,missionNames[i*3].indexOf("^"));

        var a1_1=document.createElement("a");
        a1_1.className="image featured";
        a1_1.href="" ;
        var img1=document.createElement("img");
        img1.src="missionImages/"+""+submission[i];
        img1.className="collection";
        img1.alt="";
        a1_1.appendChild(img1);


        section1.appendChild(a1_1);
        div1.appendChild(section1);
        submissionArea.appendChild(div1);
    }
}