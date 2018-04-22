
var phoneNumber = "";
var userName = "";
var person = "";

function PersonalInfo(userName, phoneNumber, password, credit, level) {
    this.userName = userName;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.credit = credit;
    this.level = level;
    //this.userType = userType;
    //this.collectionList = collectionList;
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
    alert(userName + " " + phoneNumber);

    getPersonalInfo();
    alert(person);
    var personalInfo = eval("(" + person + ")");
    document.getElementById("_userName").value = personalInfo.userName;
    document.getElementById("_userNameCard").innerHTML = personalInfo.userName;
    document.getElementById("_phoneNumber").value = personalInfo.phoneNumber;
    //document.getElementById("_userType").value = personalInfo.userType;
    document.getElementById("_credit").value = personalInfo.credit;
    document.getElementById("_level").value = personalInfo.level;
    document.getElementById("_password").value = personalInfo.password;
    //document.getElementById("_description").value = personalInfo.collectionList;
}

function savePersonalBlanks() {
    var userName = document.getElementById("_username").value;
    var phoneNumber = document.getElementById("_phoneNumber").value;
    var userType = document.getElementById("_userType").value;
    var credit = document.getElementById("_credit").value;
    var level = document.getElementById("_level").value;
    var password = document.getElementById("_password").value;
    var description = document.getElementById("_description").value;

    var personalInfo = new PersonalInfo(userName, phoneNumber, password, credit, level);
    savePersonalInfo(personalInfo);
    alert("已储存个人信息");
}