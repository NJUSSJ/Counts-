var submitButton=document.getElementById("submit");

submitButton.addEventListener("click", function () {
    uploadMission();
    alert("success");
})

function mission(name, startTime, endTime, description, files, fileNum){
    this.name=name;
    this.startTime=startTime;
    this.endTime=endTime;
    this.description=description;
    this.files=files;
    this.fileNum=fileNum;
}

function uploadMission() {
    var name=document.getElementById("name").value;
    var startT=document.getElementById("startTime").value;
    alert(startT);
    var endT=document.getElementById("endTime").value;
    var descrip=document.getElementById("description").value;

    var files=document.getElementsByClassName("dz-imageCode");
    var fileNum=document.getElementsByClassName("dz-imageCode").length;

    var fileArray=new Array();
    for(var i=0;i<fileNum;i++){
        fileArray[i]=files[i].src;
    }
    var missionData=new mission(name, startT, endT, descrip, fileArray, fileNum);
    MissionJASON(missionData);
}

function MissionJASON(mission) {
    $.ajax({
        type: "POST",
        url: "uploadFile",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(mission),
        //data: JSON.stringify(a),
        success: function (jsonResult) {
            if(jsonResult.success) {
                alert(jsonResult);
            }
        },
        error:function () {
            alert("fail");
        }
    })
}