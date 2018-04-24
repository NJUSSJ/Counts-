var chartData = "";

function userPhoneNumberObj(phoneNumber) {
    this.phoneNumber = phoneNumber;
}

function getChartData(phoneNumber, category) {
    switch (category) {
        //发起者
        case 1:
            $.ajax({
                async: false,
                type: "POST",
                url: "starterChartData",
                contentType: "application/json",
                dataType: "json",
                data: toJsonString(new userPhoneNumberObj(phoneNumber)),
                success: function takePersonalInfo(jsonResult) {
                    chartData = JSON.stringify(jsonResult);
                },
                error: function(msg){
                    alert("fail")
                }
            });
            break;

        //工人
        case 2:
            $.ajax({
                async: false,
                type: "POST",
                url: "workerChartData",
                contentType: "application/json",
                dataType: "json",
                data: toJsonString(new phoneNumberObj(phoneNumber)),
                success: function takePersonalInfo(jsonResult) {
                    chartData = JSON.stringify(jsonResult);
                },
                error: function(msg){
                    alert("fail")
                }
            });
            break;

        //管理员
        case 3:
            $.ajax({
                async: false,
                type: "POST",
                url: "adminChartData",
                contentType: "application/json",
                dataType: "json",
                data: toJsonString(new phoneNumberObj(phoneNumber)),
                success: function takePersonalInfo(jsonResult) {
                    chartData = JSON.stringify(jsonResult);
                },
                error: function(msg){
                    alert("fail")
                }
            });
            break;
    }

    return 0;
}