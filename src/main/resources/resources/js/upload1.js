var submitButton=document.getElementById("submit");
var indexPic=1;
var requestorPhone;

function load(userPhone) {
    requestorPhone=userPhone;
}

submitButton.addEventListener("click", function () {
    if(!veriInput()){
        return;
    }
    myDropzone.processQueue();
    alert("发布成功！");
    $.ajax({
        async: false,
        type: "POST",
        url: "uploadFinish",
        contentType: "application/json",
        dataType: "json",
        data: requestorPhone,
        success: function() {

        },
        error: function(msg){
            alert("fail")
        }
    });
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
    success: function (returnData) {
    },

    params: {
        name: "234"
    },

    init: function () {
        this.on("processing", function (file) {
            this.options.params={
                name: document.getElementById("name").value,
                startTime: document.getElementById("startTime").value,
                endTime: document.getElementById("endTime").value,
                workLevel: document.getElementById("workerLevel").value,
                description: document.getElementById("description").value,
                expectedNum: document.getElementById("maxNum").value,
                reward: document.getElementById("reward").value,
                indexPic: indexPic,
                requestorPhone: requestorPhone
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


function veriInput() {
    var name=document.getElementById("name").value;
    var startTime=document.getElementById("startTime").value;
    var endTime=document.getElementById("endTime").value;
    var workLevel=document.getElementById("workerLevel").value;
    var description=document.getElementById("description").value;
    var expectedNum=document.getElementById("maxNum").value;
    var reward=document.getElementById("reward").value;



    if(name==null||name==""){
        alert("请填写任务名称！");
        return false;
    }
    if(name.length>=15){
        alert("任务名称不能大于15!");
        return false;
    }

    var existed;

    $.ajax({
        async: false,
        type: "POST",
        url: "/findMission",
        contentType: "application/json",
        dataType: "json",
        data: name,
        success: function (returnData){
            existed=returnData;
        },
        error: function(msg){
            alert("fail12")
        }
    });




    if(startTime==null||startTime==null||endTime==null||endTime==""||startTime>endTime){
        alert("请正确设置起止时间！");
        return false;
    }
    if(workLevel==null||workLevel==""){
        alert("请设置工人等级限制!");
        return false;
    }
    if(description==""||description==null){
        alert("请输入任务描述!");
        return false;
    }
    if(expectedNum==null||expectedNum==""){
        alert("请设置期待标注人数！");
        return false;
    }
    if(reward==null||reward==""){
        alert("请设置奖励！");
        return false;
    }


    if(document.getElementsByClassName("dz-image")==[]){
        alert("请选择任务文件！");
        return false;
    }

    if(existed){
        alert("任务名已被占用！");
        return false;
    }

    return true;



}


