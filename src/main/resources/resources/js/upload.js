var submitButton=document.getElementById("submit");

submitButton.addEventListener("click", function () {
    uploadMission();
    myDropzone.processQueue();
})

function mission(name, startTime, endTime, description){
    this.name=name;
    this.startTime=startTime;
    this.endTime=endTime;
    this.description=description;

}

function uploadMission() {
    var name=document.getElementById("name").value;
    var startT=document.getElementById("startTime").value;
    var endT=document.getElementById("endTime").value;
    var descrip=document.getElementById("description").value;
    var missionData=new mission(name, startT, endT, descrip);



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

        }
    })
}
