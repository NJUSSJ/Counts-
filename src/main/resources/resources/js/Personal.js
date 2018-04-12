var phoneNumber;
var username;

function PersonalInfo(phoneNumber, username, password, credit, level, userType, collectionList) {
    this.phoneNumber = phoneNumber;
    this.username = username;
    this.password = password;
    this.credit = credit;
    this.level = level;
    this.userType = userType;
    this.collectionList = collectionList;
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
        data: toJsonString(phoneNumber),
        success: function takePersonalInfo(jsonResult) {
            person = JSON.stringify(jsonResult);
        }
        ,
        error: function(msg){
            alert("fail")
        }
    });
    return 0;
}

/**
 * 前端到后端储存个人信息
 * @param personalInfo
 */
function savePersonalInfo(personalInfo) {
    $.ajax({
        type: "POST",
        url: "write",
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

function loadPersonal(username, phoneNumber) {
    this.username = username;
    this.phoneNumber = phoneNumber;
    getPersonalInfo();
    var personalInfo = eval("(" + person + ")");
    document.getElementById("_username").value = personalInfo.username;
    document.getElementById("_phoneNumber").value = personalInfo.phoneNumber;
    document.getElementById("_userType").value = personalInfo.userType;
    document.getElementById("_credit").value = personalInfo.credit;
    document.getElementById("_level").value = personalInfo.level;
    document.getElementById("_password").value = personalInfo.password;
    document.getElementById("_description").value = personalInfo.collectionList;
}

function savePersonalBlanks() {
    var username = document.getElementById("_username");
    var phoneNumber = document.getElementById("_password");
    var userType = document.getElementById("_userType");
    var credit = document.getElementById("_credit");
    var level = document.getElementById("_level");
    var password = document.getElementById("_password");
    var description = document.getElementById("_description");

    var personalInfo = new PersonalInfo(phoneNumber, username, password, credit, level, userType, description);
    savePersonalInfo(personalInfo);
    alert("已储存个人信息");
}