var personalMessage = ''

function getMessage(phoneNumber) {
    $.ajax({
        async: false,
        type: "GET",
        url: "getMessage",
        contentType: "application/json",
        dataType: "json",
        data: {"phoneNumber":phoneNumber},
        success: function (res) {
            personalMessage = JSON.parse(res)
            alert(personalMessage)
        },
        error:function () {
            //alert("fail");
        }
    });


}

function sendMessage(phoneNumber) {
    $.ajax({
        async: false,
        type: "GET",
        url: "getMessage",
        contentType: "application/json",
        dataType: "json",
        data: {"phoneNumber":phoneNumber},
        success: function (res) {
            personalMessage = JSON.parse(res)
            alert(personalMessage)
        },
        error:function () {
            //alert("fail");
        }
    });
}

function deleteMessage(phoneNumber) {
    $.ajax({
        async: false,
        type: "GET",
        url: "getMessage",
        contentType: "application/json",
        dataType: "json",
        data: {"phoneNumber":phoneNumber},
        success: function (res) {
            personalMessage = JSON.parse(res)
            alert(personalMessage)
        },
        error:function () {
            //alert("fail");
        }
    });
}