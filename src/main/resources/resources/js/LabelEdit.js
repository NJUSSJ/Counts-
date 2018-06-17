var save = document.getElementById("save");
var phoneNumber;
var picNum;
var tmp;

function getNameAndCollection() {
    tmp = window.location.search.split("&");
    var collectionName = tmp[0].substring(tmp[0].indexOf("=")+1,tmp[0].length);
    if (collectionName[0] === '%'){
        collectionName = decodeURI(collectionName);
    }
    var picName = tmp[1].substring(tmp[1].indexOf("=")+1,tmp[1].length);
    var phoneNumber = tmp[2].substring(tmp[2].indexOf("=")+1);
    var nameAndCollection = [collectionName,picName,phoneNumber];
    return nameAndCollection;
}

function loadLabelPhone(_phoneNumber){
    this.phoneNumber = _phoneNumber;
}

function submitLabelInfo() {
    updateData();
    alert("标注信息已保存");
}

function nextImage(picNum) {
    this.picNum = picNum;
    tmp = window.location.search.split("&");
    var collection = tmp[0].substring(tmp[0].indexOf("=")+1);
    var i = tmp[1].substring(tmp[1].indexOf("=")+1);
    var phoneNumber = tmp[2].substring(tmp[2].indexOf("=")+1);
    //var picNum = tmp[3].substring(tmp[3].indexOf("=")+1);

    if(Number(i)<picNum) {
        i = Number(i) + 1;
        window.location.href = "test1.html?collection=" + collection + "&imageURL=" + i + "&phoneNumber=" + phoneNumber + "&picNum=" + picNum;
    }else {
        alert("本图片为最后一张");
    }
};

function LabelData(mid, uid, missionLabel) {
    tmp = window.location.search.split("&");
    this.mid = window.location.search.split("&")[0].substring(tmp[0].indexOf("=")+1);
    this.uid = window.location.search.split("&")[2].substring(tmp[2].indexOf("=")+1);
    this.missionLabel = loadLabelInfo();
}

function updateData() {
    var isSubmitted = false;
    //alert(JSON.stringify(new missionAndPhoneNumberObj(missionAndPhoneNumber)));

    $.ajax({
        async: false,
        type: "POST",
        url: "/checkCommit",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({"keyword":getNameAndCollection()[0], "uid":getNameAndCollection()[2]}),
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
            url: "/updateLabelMission",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(new LabelData(getNameAndCollection()[1], getNameAndCollection()[2],loadLabelInfo())),
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