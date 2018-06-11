
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
        url: "sign",
        contentType: "application/json",
        dataType: "json",
        data: {"phoneNumber":phoneNumber},
        success: function (res) {
            signDetails = JSON.parse(res)
            alert(signDetails)
        },
        error:function () {
            //alert("fail");
        }
    });

    alert("签到成功！ 获得积分: " + signDetails.bonus + "您已连续签到: " + signDetails.continuity + "天");

}