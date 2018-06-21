
var signDetails = "";
function SignDetails(phoneNumber, continuity, bonus) {
    this.phoneNumber = phoneNumber;
    this.continuity = continuity;
    this.bonus = bonus;
}

function sign(phoneNumber) {
    $.ajax({
        async: false,
        type: "POST",
        url: "/Sign",
        contentType: "application/json",
        dataType: "json",
        data: {"phoneNumber":phoneNumber},
        success: function (res) {
            //alert(res);
            if(res == 1){
                signDetails = "签到成功！";
            }else {
                signDetails = "签到失败！您今日已签过到。";
            }
        },
        error:function () {
            //alert("fail");
        }
    });
    alert(signDetails);
    //alert("签到成功！ 获得积分: " + signDetails.bonus + "您已连续签到: " + signDetails.continuity + "天");

}