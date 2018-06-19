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

function setButtonsDown() {
    setButtonDown("userManagementButton");
    setButtonDown("missionManagementButton")
}

function setButtonDown(buttonname) {
    var button = document.getElementById(buttonname);
    button.style.fontSize = "0.7em";
    button.style.borderRightColor = "#442440";
    button.style.borderRightWidth = "2px";
}

function hideAllBlock() {
    document.getElementById("userManagement").style.display = "none";
    document.getElementById("missionManagement").style.display = "none";
}

function searchUser(info) {

}

function searchUser() {
    var info = document.getElementsByClassName("userSearch");
    $.ajax({
        async: false,
        type :"POST",
        url: "",
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