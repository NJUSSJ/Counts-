var submission;
var missionName;
function submissioninfo(keyword, uid) {
    this.keyword = keyword;
    this.uid = uid;
}
function load(mid,uid) {
    var info = new submissioninfo(mid, uid)
    missionName = mid;
    $.ajax({
        async: false,
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




    var rowNum=Math.floor(submission.length/3);
    var left=submission.length%3;


    for(var i=0;i<rowNum;i++){
        var rowDiv=document.createElement("div");
        rowDiv.className="row";

        var div1=document.createElement("div");
        div1.className="4u 12u(narrower)";
        var section1=document.createElement("section");
        var a1_1=document.createElement("a");
        a1_1.className="image featured";
        a1_1.href = "";
        var img1=document.createElement("img");
        img1.src="missionImages/"+missionName+"_"+submission[i*3+1]+".jpg";
        img1.className="collection";
        img1.alt="";
        a1_1.appendChild(img1);
        section1.appendChild(a1_1);
        div1.appendChild(section1);
        rowDiv.appendChild(div1);

        var div2=document.createElement("div");
        div2.className="4u 12u(narrower)";
        var section2=document.createElement("section");
        var a2_1=document.createElement("a");
        a2_1.className="image featured";
        a2_1.href="";
        var img2=document.createElement("img");
        img2.src="missionImages/"+missionName+"_"+submission[i*3+2]+".jpg";
        img2.className="collection";
        img2.alt="";
        a2_1.appendChild(img2);
        section2.appendChild(a2_1);
        div2.appendChild(section2);
        rowDiv.appendChild(div2);





        var div3=document.createElement("div");
        div3.className="4u 12u(narrower)";
        var section3=document.createElement("section");
        var a3_1=document.createElement("a");
        a3_1.className="image featured";
        a3_1.href="";
        var img3=document.createElement("img");
        img3.src="missionImages/"+missionName+"_"+submission[i*3+3]+".jpg";
        img3.className="collection";
        img3.alt="";
        a3_1.appendChild(img3);
        section3.appendChild(a3_1);
        div3.appendChild(section3);
        rowDiv.appendChild(div3);

        document.getElementById("submissionArea").appendChild(rowDiv);
    }
    var rowDiv=document.createElement("div");
    rowDiv.className="row";
    for(var i=0;i<left;i++){
        var div1=document.createElement("div");
        div1.className="4u 12u(narrower)";
        var section1=document.createElement("section");
        var a1_1=document.createElement("a");
        a1_1.className="image featured";
        a1_1.href="";
        var img1=document.createElement("img");
        img1.src="missionImages/"+missionName+"_"+submission[3*rowNum+i+1]+".jpg";
        img1.className="collection";
        img1.alt="";
        a1_1.appendChild(img1);
        section1.appendChild(a1_1);
        div1.appendChild(section1);
        rowDiv.appendChild(div1);
    }

    document.getElementById("submissionArea").appendChild(rowDiv);
}