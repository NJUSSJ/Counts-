var sign = 0;
function userManagement(){
    if(sign==1){
        setButtonsDown();
        hideAllBlock();
    }
    sign = 1;
    var button = document.getElementById("userManagementButton");
    button.style.fontSize = "0.74em";
    button.style.minWidth = "14em";
    button.style.padding = "0.5em 0";
    button.style.borderRightColor = "#96dad1";
    button.style.borderRightWidth = "2.5px";
    var userManagement = document.getElementById("userManagement");
    userManagement.style.display = "block";

}

function missionManagement() {
    if(sign==1){
        setButtonsDown();
        hideAllBlock();
    }
    sign=1;
    var button = document.getElementById("missionManagementButton");
    button.style.fontSize = "0.74em";
    button.style.minWidth = "14em";
    button.style.padding = "0.5em 0";
    button.style.borderRightColor = "#96dad1";
    button.style.borderRightWidth = "2.5px";
    var userManagement = document.getElementById("missionManagement");
    userManagement.style.display = "block";
}

function recent() {
    if(sign==1){
        setButtonsDown();
        hideAllBlock();
    }
    sign=1;
    var button = document.getElementById("recentButton");
    button.style.fontSize = "0.74em";
    button.style.minWidth = "14em";
    button.style.padding = "0.5em 0";
    button.style.borderRightColor = "#96dad1";
    button.style.borderRightWidth = "2.5px";
    var userManagement = document.getElementById("recent");
    userManagement.style.display = "block";
}

function setButtonsDown() {
    var button1 = document.getElementById("missionManagementButton");
    button1.style.fontSize = "0.7em";
    button1.style.borderRightColor = "#442440";
    button1.style.borderRightWidth = "2px";

    var button2 = document.getElementById("userManagementButton");
    button2.style.fontSize = "0.7em";
    button2.style.borderRightColor = "#442440";
    button2.style.borderRightWidth = "2px";

    var button3 = document.getElementById("recentButton");
    button3.style.fontSize = "0.7em";
    button3.style.borderRightColor = "#442440";
    button3.style.borderRightWidth = "2px";

}



function hideAllBlock() {
    document.getElementById("userManagement").style.display = "none";
    document.getElementById("missionManagement").style.display = "none";
    document.getElementById("recent").style.display = "none";

}


function para1(info) {
    this.info = info;
}

function searchUser() {
    var info = document.getElementById("userInfo").value;
    var para = new para1(info);
    $.ajax({
        async: false,
        type :"POST",
        url: "/findUserByAdmin",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(para),
        success: function (returnData) {
            if(returnData == "0"){
                alert("未找到该用户！");
            }else{
                var string = JSON.stringify(returnData);
                var user = eval("("+string+")");
                setUser(user);
            }

        },
        error: function () {
            //alert("fail");
        }
    });
}

function setUser(user) {
    var category = user.category;
    var credit = user.credit;
    var level = user.level;
    var phone = user.uid;
    var userName = user.userName;

    var tr = document.createElement("tr");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var td4 = document.createElement("td");
    var td5 = document.createElement("td");

    td1.innerHTML = phone;
    td2.innerHTML = userName;
    if(category==1){
        td3.innerHTML = "发起者";
    }else if(category==2){
        td3.innerHTML = "工人";
    }else{
        td3.innerHTML = "管理员";
    }
    td4.innerHTML = level;
    td5.innerHTML = "正常";

    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    tr.appendChild(td5);

    document.getElementById("userTable").appendChild(tr);
}

function searchMission() {
    var info = document.getElementById("missionName").value;
    var para = new para1(info);
    $.ajax({
        async: false,
        type :"POST",
        url: "/findMissionByAdmin",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(para),
        success: function (returnData) {
            if(returnData == "0"){
                alert("未找到该任务！");
            }else{
                var string = JSON.stringify(returnData);
                var mission = eval("("+string+")");
                setMission(mission);
            }

        },
        error: function () {
            //alert("fail");
        }
    });
}

function setMission(mission) {
    var mid = mission.mid;
    var type = mission.tagType;
    var starterName = mission.starterName;
    var state = mission.state;
    var tr = document.createElement("tr");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var td4 = document.createElement("td");
    var td5 = document.createElement("td");

    td1.innerHTML = mid;
    if(type == 1){
        td2.innerHTML = "标签式";
    }else{
        td2.innerHTML = "非标签式";
    }
    td3.innerHTML = starterName;
    if(state == 2){
        td4.innerHTML = "已完成";
    }else{
        td4.innerHTML = "进行中";
    }



    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);

    document.getElementById("missionTable").appendChild(tr);
}