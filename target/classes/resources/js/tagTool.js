var tagArea = document.getElementById("tagArea");
var nextPage = document.getElementById("more");
var save = document.getElementById("save");
var back = document.getElementById("back");
var url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521465839531&di=894d8615e5b830dffd2d3f5184ea90b1&imgtype=0&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fdc54564e9258d109241f9f09da58ccbf6d814d88.jpg"
var phoneNumber;
var imgid;
var tmp1;
var picNum;
var count = 1;
//var collectionName = getNameAndCollection()[0];

function loadPhoneNumber(_phoneNumber){
    phoneNumber = _phoneNumber;
    //imgid 格式为 collectionName + picName + phoneNumber
    imgid = getNameAndCollection()[0] + "-" + getNameAndCollection()[1] + "-" + phoneNumber;
    var imgInfo = eval("(" + tmp1 + ")");
    if(imgInfo != null){

    }
}

save.addEventListener("click", function save() {
    saveData();
    alert("标注信息已保存");
});

back.addEventListener("click", function back() {
    /*
    var tmp = confirm("返回上一页面?");
    if(tmp == true){
        history.go(-1);
    }else{
    }
    */
    count--;
    if(count == 1){
        alert("已到达任务头！");
    }else{
        window.location.href = "test1.html?collection=" + mid + "&phoneNumber=" + phoneNumber + "&index=" + (count-1);
    }
});

function nextImage() {
    /*
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var img = new Image();
    img.src = url;
    ctx.drawImage(img,0,0,canvas.width,canvas.height);
    */

    var tmp = window.location.search.split("&");
    var collection = tmp[0].substring(tmp[0].indexOf("=")+1);
    var i = tmp[1].substring(tmp[1].indexOf("=")+1);
    var phoneNumber = tmp[2].substring(tmp[2].indexOf("=")+1);

    i++;
    if(i == 13){
        alert("已到达任务末尾！");
    }else{
        window.location.href = "test1.html?collection=" + collection  + "&imageURL=" + i + "&phoneNumber=" + phoneNumber;
    }

};

//data
function imgs(sentids,imgid,sentences,filename,fixedx,fixedy,fixedwidth,fixedheight,list,missionLabel) {
    this.sentids = sentids;
    this.imgid = imgid;
    this.sentences = sentences;
    this.filename = filename;
    this.fixedx = fixedx;
    this.fixedy = fixedy;
    this.fixedwidth = fixedwidth;
    this.fixedheight = fixedheight;
    this.list = list;
    this.missionLabel = missionLabel;
}

//imgid 格式为 collectionName + picName + phoneNumber
//var imgid = getNameAndCollection()[0] + "-" + getNameAndCollection()[1] + "-" + phoneNumber;

function getSentences() {
    return sentences;
}

function getSentIds() {
    return sentids;
}

function saveData() {
    var imgData = new imgs(getSentIds(),imgid,getSentences(),getNameAndCollection()[0] + "_" + getNameAndCollection()[1],fixedX,fixedY,fixedWidth,fixedHeight,curlArray);
    ImageJson(imgData);
    alert("已储存本图片信息");
}

//test
var a = {};
a.name = "cat";
a.age = "3";
a.collectionName = "cat1";

/* ajax */
function ImageJson(imgs) {
    $.ajax({
        type: "POST",
        url: "write",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(imgs),
        //data: JSON.stringify(a),
        success: function (jsonResult) {
            if(jsonResult.success) {
                //alert(jsonResult);
            }
        },
        error:function () {
            //alert("fail");
        }
    });
}

function imgCollection(phoneNumber) {
    this.collectionName = getNameAndCollection()[0];
    this.picName = getNameAndCollection()[1];
    this.phoneNumber = phoneNumber;
}

function toJsonString(obj) {
    var msg=[];
    for(var key in obj){
        msg.push([key,":",obj[key]].join(""));

    }
    return "{"+msg.join(",")+"}";
}

var ret1 = "abc";

function getImgInfo() {
    $.ajax({
        async: false,
        type: "POST",
        url: "read",
        contentType: "application/json",
        dataType: "json",
       // data: JSON.stringify(new imgCollection()),
        data: JSON.stringify(new imgCollection(phoneNumber)),
        //data: JSON.stringify(a),
        success: function takeImgInfo(jsonResult) {
          /*if(jsonResult==""){
              alert("OJBK")
          }else{
              alert(JSON.stringify(jsonResult))
          }*/
          //alert(jsonResult);
          tmp123 = JSON.stringify(jsonResult);
          //alert("qqq" + tmp123);
          //return ret;
        }
        ,
        error: function(){
            //alert("fail")
        }
    });
    return ret1;

}
/* ---------------------------------------------------- */
function missionAndPhoneNumberObj(missionAndPhoneNumber) {
    this.missionAndPhoneNumber = missionAndPhoneNumber;
}

function submitTagInfo() {
    var missionAndPhoneNumber = getNameAndCollection()[0] + phoneNumber;
    var isSubmitted = false;
    //alert(JSON.stringify(new missionAndPhoneNumberObj(missionAndPhoneNumber)));

    $.ajax({
        async: false,
        type: "POST",
        url: "getSubmitTag",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(new missionAndPhoneNumberObj(missionAndPhoneNumber)),
        success: function (ret) {
            //alert("state=" + ret);
            if(ret === 1) {
                isSubmitted = true;
                alert("已提交过，请勿重复提交！");
            }
        }
        ,
        error: function(){
            //alert("提交失败!");
        }
    });

    if(!isSubmitted) {
        $.ajax({
            async: false,
            type: "POST",
            url: "submitTag",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(new missionAndPhoneNumberObj(missionAndPhoneNumber)),
            success: function (ret) {
                if(ret === 1)
                    alert("提交成功!");
            }
            ,
            error: function () {
                //alert("提交失败!");
            }
        });
    }
    //alert("提交成功!");
    return 0;
}