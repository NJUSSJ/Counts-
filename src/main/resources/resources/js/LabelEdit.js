var save = document.getElementById("save");
var phoneNumber;
var index;
var tmp;
var g1;
var g2;
var count = 1;
var mid;


function getNameAndCollection() {
    tmp = window.location.search.split("&");
    var collectionName = tmp[0].substring(tmp[0].indexOf("=")+1,tmp[0].length);
    for(var i = 0;i<collectionName.length;i++) {
        if (collectionName[i] === '%') {
            collectionName = decodeURI(collectionName);
        }
    }
    var picName = tmp[1].substring(tmp[1].indexOf("=")+1,tmp[1].length);
    var phoneNumber = tmp[2].substring(tmp[2].indexOf("=")+1);
    var nameAndCollection = [collectionName,picName,phoneNumber];
    return nameAndCollection;
}

function loadLabelPhone(_phoneNumber,collection, index){
    this.phoneNumber = _phoneNumber;
    this.mid = collection;
    this.count = index;
}

function submitLabelInfo() {
    $.ajax({
        async: false,
        type: "POST",
        url: "/commit",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({"keyword":getNameAndCollection()[0], "uid":getNameAndCollection()[2]}),
        success: function (ret) {
            if(ret === 1)
                alert("提交成功!");
            else if(ret === 2)
                alert("提交失败!请勿重复提交！");
        }
        ,
        error: function () {
        }
    });
    return 0;
}

function nextImage() {
    //var picNum = tmp[3].substring(tmp[3].indexOf("=")+1);

    i++;
    if(i == 12){
        alert("已到达任务末尾！");
    }else{
        window.location.href = "test1.html?collection=" + collection  + "&imageURL=" + i + "&phoneNumber=" + phoneNumber;
    }

    var answer = loadLabelInfo()[0];
    if(answer == null){
        answer = -1;
    }

    $.ajax({
        async: false,
        type: "POST",
        url: "/updateLabelMission",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({"mid":getNameAndCollection()[0], "uid":getNameAndCollection()[2], "num":count-1, "answer":answer}),
        success: function (ret) {
            //alert("state=" + ret);
            if(ret === 1) {
                alert("已提交过，请勿重复提交！");
            }
        }
        ,
        error: function(){
            //alert("提交失败!");
        }
    });
    var tmp = window.location.search.split("&");
    var collection = tmp[0].substring(tmp[0].indexOf("=")+1);
    var i = tmp[1].substring(tmp[1].indexOf("=")+1);
    var phoneNumber = tmp[2].substring(tmp[2].indexOf("=")+1);

}
/*
function LabelData(mid, uid, missionLabel) {
    tmp = window.location.search.split("&");
    this.mid = window.location.search.split("&")[0].substring(tmp[0].indexOf("=")+1);
    this.uid = window.location.search.split("&")[2].substring(tmp[2].indexOf("=")+1);
    this.missionLabel = loadLabelInfo();
}*/