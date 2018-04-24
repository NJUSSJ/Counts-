var phoneNumber = "";
var userName = "";
var person = "";
var userCategory = "";
var missionNames=new Array();
var index=0;

function PersonalInfo(userName, phoneNumber, password, credit, level, category, description) {
    this.userName = userName;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.credit = credit;
    this.level = level;
    this.category = category;
    this.description = description;
}

/**
 * 后端到前端传个人信息
 * @returns {number}
 */
function getPersonalInfo() {
    $.ajax({
        async: false,
        type: "POST",
        url: "readPersonal",
        contentType: "application/json",
        dataType: "json",
        data: toJsonString(new phoneNumberObj(phoneNumber)),
        success: function takePersonalInfo(jsonResult) {
            person = JSON.stringify(jsonResult);
        },
        error: function(msg){
            alert("fail")
        }
    });
    return 0;
}
function phoneNumberObj(phoneNumber) {
    this.phoneNumber = phoneNumber;
}
function toJsonString(obj) {
    var msg=[];
    for(var key in obj){
        msg.push([key,":",obj[key]].join(""));

    }
    return "{"+msg.join(",")+"}";
}

/**
 * 前端到后端储存个人信息
 * @param personalInfo
 */
function savePersonalInfo(personalInfo) {

    $.ajax({
        type: "POST",
        url: "writePersonal",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(personalInfo),
        success: function (jsonResult) {
            if(jsonResult.success) {
                alert(jsonResult);
            }
        },
        error:function () {
            alert("fail");
        }
    });
    return 0;
}

function loadPersonal() {

    var tmp = window.location.search.split("&");
    this.userName = tmp[0].substring(tmp[0].indexOf("=")+1);
    this.phoneNumber = tmp[1].substring(tmp[1].indexOf("=")+1);
    userCategory = tmp[2].substring(tmp[2].indexOf("=")+1);
    alert(userName + " " + phoneNumber + " " + userCategory);

    getPersonalInfo();
    alert(person);
    var personalInfo = eval("(" + person + ")");

    document.getElementById("_userName").value = personalInfo.userName;
    document.getElementById("_userNameCard").innerHTML = personalInfo.userName;
    document.getElementById("_phoneNumber").value = personalInfo.phoneNumber;
    switch (personalInfo.category) {
        case 1:
            document.getElementById("_userType").value = "众包发起者";
            break;
        case 2:
            document.getElementById("_userType").value = "众包工人";
            break;
        case 3:
            document.getElementById("_userType").value = "系统管理员";
            break;
    }
    document.getElementById("_credit").value = personalInfo.credit;
    document.getElementById("_level").value = personalInfo.level;
    document.getElementById("_password").value = personalInfo.password;
    document.getElementById("_description").value = personalInfo.description;

    //加载collectionInfo
    loadPersonalCollection(personalInfo.phoneNumber, personalInfo.category);

    //获取chart相关后端数据
    getChartData(personalInfo.phoneNumber, personalInfo.category);

    //加载chart
    loadChartArea(personalInfo.category);
}

function savePersonalBlanks() {
    var userName = document.getElementById("_username").value;
    var phoneNumber = document.getElementById("_phoneNumber").value;
    switch (document.getElementById("_userType").value) {
        case "众包发起者":
            var category = 1;
            break;
        case "众包工人":
            var category = 2;
            break;
        case "系统管理员":
            var category = 3;
            break;
    }
    var credit = document.getElementById("_credit").value;
    var level = document.getElementById("_level").value;
    var password = document.getElementById("_password").value;
    var description = document.getElementById("_description").value;

    var personalInfo = new PersonalInfo(userName, phoneNumber, password, credit, level);
    savePersonalInfo(personalInfo);
    alert("已储存个人信息");
}

function TmpUser(phoneNumber,category){
    this.phoneNumber=phoneNumber;
    this.category=category;
}

function loadPersonalCollection(phoneNumber, category) {
    var tmpUser = new TmpUser(phoneNumber, category);
    $.ajax({
        async: false,
        method: "POST",
        url: "getPersonalCollectionInfo",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(tmpUser),
        success: function takePersonalCollectionInfo(returnData) {
            for (var i = 0; i < returnData.length; i++) {
                if (returnData[i] == null) {
                    break;
                }
                missionNames[i] = returnData[i];
                index++;
            }
            alert("获取personal collection数据完毕 开始加载 第一个missionName=" + missionNames[0]);
            setPersonalCollection();
        },
        error: function () {
            alert("fail");
        }
    });
    alert("loadPersonalCollection success");
}

function setPersonalCollection() {
    document.getElementById("personalCollections").innerHTML = "";
    for (var i = 0; i < missionNames.length; i++) {
        var div = document.createElement("div");
        div.className = "4u 12u$(mobile)";
        var a = document.createElement("a");
        a.className = "image fit";
        a.href = "/details.html?imageURL=" + missionNames[i] + "&userPhone=" + phoneNumber + "&userCategory=" + userCategory + "&Tagable=" + 1;
        var img = document.createElement("img");
        img.src = "../../images/" + missionNames[i] + "_1.jpg";
        img.alt = "";
        a.appendChild(img);
        div.appendChild(a);
        document.getElementById("personalCollections").appendChild(div);
    }
}