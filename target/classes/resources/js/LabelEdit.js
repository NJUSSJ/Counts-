var nextPage = document.getElementById("more");
var save = document.getElementById("save");
var back = document.getElementById("back");
var phoneNumber;

function getNameAndCollection() {
    var tmp = window.location.search.split("&");
    var collectionName = tmp[0].substring(tmp[0].indexOf("=")+1,tmp[0].length);
    if (collectionName[0] === '%'){
        collectionName = decodeURI(collectionName);
    }
    var picName = tmp[1].substring(tmp[1].indexOf("=")+1,tmp[1].length);
    var nameAndCollection = [collectionName,picName];
    return nameAndCollection;
}

function loadLabelPhone(_phoneNumber){
    this.phoneNumber = _phoneNumber;
}

save.addEventListener("click", function save() {
    saveData();
    alert("标注信息已保存");
});

back.addEventListener("click", function back() {

    var tmp = window.location.search.split("&");
    var collection = tmp[0].substring(tmp[0].indexOf("=")+1);
    var i = tmp[1].substring(tmp[1].indexOf("=")+1);
    var phoneNumber = tmp[2].substring(tmp[2].indexOf("=")+1);
    var picNum = tmp[3].substring(tmp[3].indexOf("=")+1);

    if(Number(i) > 1) {
        i = Number(i) - 1;
        window.location.href = "test1.html?collection=" + collection + "&imageURL=" + i + "&phoneNumber=" + phoneNumber + "&picNum=" + picNum;
    }else {
        alert("本图片为第一张");
    }
});

nextPage.addEventListener("click", function more() {
    var tmp = window.location.search.split("&");
    var collection = tmp[0].substring(tmp[0].indexOf("=")+1);
    var i = tmp[1].substring(tmp[1].indexOf("=")+1);
    var phoneNumber = tmp[2].substring(tmp[2].indexOf("=")+1);
    var picNum = tmp[3].substring(tmp[3].indexOf("=")+1);

    if(Number(i)<picNum) {
        i = Number(i) + 1;
        window.location.href = "test1.html?collection=" + collection + "&imageURL=" + i + "&phoneNumber=" + phoneNumber + "&picNum=" + picNum;
    }else {
        alert("本图片为最后一张");
    }
});

//data
function LabelData(mid, uid, missionLabel) {
    this.mid = window.location.search.split("&")[0].substring(tmp[0].indexOf("=")+1);
    this.uid = window.location.search.split("&")[2].substring(tmp[2].indexOf("=")+1);
    this.missionLabel = loadLabelInfo();
}

//imgid 格式为 collectionName + picName + phoneNumber
//var imgid = getNameAndCollection()[0] + "-" + getNameAndCollection()[1] + "-" + phoneNumber;

function saveData() {
    $.ajax({
        type: "POST",
        url: "write",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(new LabelData()),
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

/* ---------------------------------------------------- */
function missionAndPhoneNumberObj(missionAndPhoneNumber) {
    this.missionAndPhoneNumber = missionAndPhoneNumber;
}

function submitLabelInfo() {
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