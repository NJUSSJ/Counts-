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



function searchUser() {
    var info = document.getElementsByClassName("userSearch").value;
    $.ajax({
        async: false,
        type :"POST",
        url: "/findUser",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(info),
        success: function (returnData) {
            //alert(returnData);
        },
        error: function () {
            //alert("fail");
        }
    });
}