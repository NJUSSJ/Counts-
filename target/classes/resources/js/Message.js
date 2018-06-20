var personalMessage = '';

function getMessage(phoneNumber) {
    //alert("getting message");
    $.ajax({
        async: false,
        type: "POST",
        url: "GetMessage",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({"phoneNumber":phoneNumber}),
        success: function (res) {
            //personalMessage = JSON.parse(res);
            showMessage(res);

        },
        error:function () {
            //alert("fail");
        }
    });

}

function showMessage(message) {
    for(var i = 0;i<message.length;i++){
        document.getElementById("messageArea").innerHTML += "<tr id=" + i + "><td>" + i + "</td><td>" + message[i].content + "</td><td>" + message[i].senderID + "</td><td>" + message[i].keyID + "</td></tr>";
    }
}
/*
function sendMessage(phoneNumber) {
    $.ajax({
        async: false,
        type: "GET",
        url: "sendMessage",
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
}*/

function deleteMessage(phoneNumber) {
    $.ajax({
        async: false,
        type: "GET",
        url: "DeleteMessage",
        contentType: "application/json",
        dataType: "json",
        data: {"phoneNumber":phoneNumber},
        success: function (res) {
            //personalMessage = JSON.parse(res);
            //alert(res);
        },
        error:function () {
            //alert("fail");
        }
    });
}
