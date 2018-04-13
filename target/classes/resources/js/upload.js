var submitButton=document.getElementById("submit");
var indexPic=1;

submitButton.addEventListener("click", function () {
    uploadMission();
    myDropzone.processQueue();
    alert("任务发布成功！");
})

/*
dropzone setting
 */
Dropzone.autoDiscover = false;

var myDropzone = new Dropzone("#myDropzone", {
    url: "uploadPics",
    addRemoveLinks: true,
    autoProcessQueue: false,
    method: 'post',
    filesizeBase: 1024,
    parallelUploads: 100,
    acceptedFiles: ".jpg,.gif,.png,.jpeg", //上传的类型

    sending: function(file, xhr, formData) {

    },
    success: function (file, response, e) {

    },

    params: {
        name: "234"
    },

    init: function () {
        this.on("processing", function (file) {
            this.options.params={
                name: document.getElementById("name").value,
                indexPic: indexPic
            };
            indexPic++;
        });
    },

    dictDefaultMessage:'拖动文件至此或者点击上传',
    dictMaxFilesExceeded: "您最多只能上传1个文件！",
    dictResponseError: '文件上传失败!',
    dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
    dictFallbackMessage:"浏览器不受支持",
    dictFileTooBig:"文件过大上传文件最大支持.",
    dictRemoveLinks: "删除",
    dictCancelUpload: "取消"
});




function mission(name, startTime, endTime, description, workerLevel){
    this.name=name;
    this.startTime=startTime;
    this.endTime=endTime;
    this.description=description;
    this.workerLevel=workerLevel;
}

function uploadMission() {
    var name=document.getElementById("name").value;
    var startT=document.getElementById("startTime").value;
    var endT=document.getElementById("endTime").value;
    var descrip=document.getElementById("description").value;
    var workerlevel=document.getElementById("workerLevel").value;
    var missionData=new mission(name, startT, endT, descrip, workerlevel);

    MissionJASON(missionData);
    indexPic=1;

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
