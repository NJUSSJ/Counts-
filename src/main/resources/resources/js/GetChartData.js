var data = "";
var starterData = "";
var workerData = "";
var adminData = "";

function userPhoneNumberObj(phoneNumber) {
    this.phoneNumber = phoneNumber;
}

function para(uid) {
    this.uid = uid;
}

function getChartData(phoneNumber, category) {



    //alert("category " + category);

    switch (category) {
        //发起者
        case 1:
            $.ajax({
                async: false,
                type: "POST",
                url: "starterChartData",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(new userPhoneNumberObj(phoneNumber)),
                success: function takeStarterPersonalInfo(jsonResult) {
                    starterData = JSON.stringify(jsonResult);
                },
                error: function(msg){
                    //alert("fail")
                }
            });
            var starterDataObj = eval("(" + starterData + ")");
            loadStarterChartNumAndData(starterDataObj);
            break;

        //工人
        case 2:
            $.ajax({
                async: false,
                type: "POST",
                url: "workerChartData",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(new userPhoneNumberObj(phoneNumber)),
                success: function takeWorkerPersonalInfo(jsonResult) {
                    workerData = JSON.stringify(jsonResult);
                },
                error: function(msg){
                    //alert("fail")
                }
            });
            var workerDataObj = eval("(" + workerData + ")");
            loadWorkerChartNumAndData(workerDataObj);
            break;

        //管理员
        case 3:
            $.ajax({
                async: false,
                type: "POST",
                url: "adminChartData",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(new userPhoneNumberObj(phoneNumber)),
                success: function takeAdminPersonalInfo(jsonResult) {
                    adminData = JSON.stringify(jsonResult);
                },
                error: function(msg){
                    //alert("fail")
                }
            });
            var adminDataObj = eval("(" + adminData + ")");
            loadAdminChartNumAndData(adminDataObj);
            break;
    }
    //alert("loadChartData Success");
    return 0;
}