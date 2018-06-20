var searchBtn = document.getElementById("searchBtn");
var index = 0;

searchBtn.addEventListener("click", function () {
    var searchType = document.getElementById("searchType").value;
    var searchContent = document.getElementById("searchContent").value;
    $.ajax({
        async: false,
        method: "POST",
        url: "/MissionTake/SearchInHall",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({"range":searchType, "keyword":searchContent}),
        success: function takePersonalInfo(returnData) {
            /*for (var i = 0; i < returnData.length; i++) {
                if (returnData[i].name == null) {
                    break;
                }
                missionNames[i] = returnData[i].name;
                alert(missionNames[i]);
                index++;
            }*/

            for (var i = 0; i < returnData.length; i++) {
                if (returnData[i] == null) {
                    break;
                }
                missionNames[i] = returnData[i];
                index++;
            }
            setMissionNames(missionNames, index);
            setCollection();
        },
        error: function () {
            //alert("fail")
        }
    });

});

function refreshMission(phoneNumber) {
    $.ajax({
        async: false,
        method: "POST",
        url: "/MissionTake/SearchInHall",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({"range":"refresh", "keyword":phoneNumber}),
        success: function takePersonalInfo(returnData) {
            /*for (var i = 0; i < returnData.length; i++) {
                if (returnData[i].name == null) {
                    break;
                }
                missionNames[i] = returnData[i].name;
                alert(missionNames[i]);
                index++;
            }*/

            for (var i = 0; i < returnData.length; i++) {
                if (returnData[i] == null) {
                    break;
                }
                missionNames[i] = returnData[i];
                index++;
            }
            setMissionNames(missionNames, index);
            setCollection();
        },
        error: function () {
            //alert("fail")
        }
    });
}
