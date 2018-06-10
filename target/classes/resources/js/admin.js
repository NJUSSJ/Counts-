var sign = 0;
function userManagement(){
    if(sign==1){
        setButtonsDown();
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

function setButtonsDown() {

}