var tagArea = document.getElementById("tagArea");
var nextPage = document.getElementById("more");
var save = document.getElementById("save");
var back = document.getElementById("back");
var url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521465839531&di=894d8615e5b830dffd2d3f5184ea90b1&imgtype=0&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fdc54564e9258d109241f9f09da58ccbf6d814d88.jpg"
var phoneNumber = "";
var collectionName = getNameAndCollection()[0];

function loadPhoneNumber(phoneNumber){
    this.phoneNumber = phoneNumber;
}

function loadSubmitHref(){
    var submit = document.getElementById("submitTagInfo");
    //注意以下collectionName 参数是否能取到
    submit.setAttribute("href",'<c:url value="submit"/>?missionAndPhoneNumber=${collectionName}+${requestScope.phoneNumber}');
}

add.addEventListener("click", function add() {
    getImgInfo(imgCollection);
    alert("SUCCESS!");
})

function _delete() {
    tagArea.text = "";
}

function edit(){
}

save.addEventListener("click", function save() {
    saveData();
    alert("标注信息已保存");
})

back.addEventListener("click", function back() {
    var tmp = confirm("返回上一页面?");
    if(tmp == true){
        history.go(-1);
    }else{
    }
})

nextPage.addEventListener("click", function more() {
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var img = new Image();
    img.src = url;
    ctx.drawImage(img,0,0,canvas.width,canvas.height);
})

function tagInfoCollection(fixedx,fixedy,fixedwidth,fixedheight,list) {
    this.fixedx = fixedx;
    this.fixedy = fixedy;
    this.fixedwidth = fixedwidth;
    this.fixedheight = fixedheight;
    this.list = list;
}

//data
function imgs(sentids,imgid,sentences,filename,fixedx,fixedy,fixedwidth,fixedheight,list) {
    this.sentids = sentids;
    this.imgid = imgid;
    this.sentences = sentences;
    this.filename = filename;
    this.fixedx = fixedx;
    this.fixedy = fixedy;
    this.fixedwidth = fixedwidth;
    this.fixedheight = fixedheight;
    this.list = list;
}

//imgid 格式为 collectionName + picName + phoneNumber
var imgid = getNameAndCollection()[0] + "-" + getNameAndCollection()[1] + "-" + phoneNumber;

function getSentences() {
    return sentences;
}

function getSentIds() {
    return sentids;
}

function saveData() {
    var imgData = new imgs(getSentIds(),imgid,getSentences(),getNameAndCollection(),fixedX,fixedY,fixedWidth,fixedHeight,curlArray);
    ImageJson(imgData);
    SendTag(_sentences);
    alert("已储存本图片信息");
}

/* ajax */
function SendTag(imgs) {
    $.ajax({
        type: "POST",
        url: "tag",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(imgs),
        //data: JSON.stringify(a),
        success: function (jsonResult) {
            if(jsonResult.success) {
                alert(jsonResult);
            }
        },
        error:function () {
            alert("fail");
        }
    });
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
                alert(jsonResult);
            }
        },
        error:function () {
            alert("fail");
        }
    });
}

function imgCollection() {
    this.collectionName = getNameAndCollection()[0];
    this.id = new Number(getNameAndCollection()[1]);
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
        data: toJsonString(new imgCollection()),
        //data: JSON.stringify(a),
        success: function takeImgInfo(jsonResult) {
          /*if(jsonResult==""){
              alert("OJBK")
          }else{
              alert(JSON.stringify(jsonResult))
          }*/
          //alert(jsonResult.imgid);
          tmp123 = JSON.stringify(jsonResult);
          //alert("qqq" + tmp123);
          //return ret;
        }
        ,
        error: function(msg){
            alert("fail")
        }
    });
    return ret1;

}