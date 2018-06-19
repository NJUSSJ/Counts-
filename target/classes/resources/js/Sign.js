
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
            signDetails = res;
        },
        error:function () {
            //alert("fail");
        }
    });
    if(signDetails == "") {
        alert("您今日已经签过到了！");
    }else {
        alert(signDetails);
    }
    //alert("签到成功！ 获得积分: " + signDetails.bonus + "您已连续签到: " + signDetails.continuity + "天");

}