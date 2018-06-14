var searchBtn = document.getElementById("searchBtn");

searchBtn.addEventListener("click", function () {
    var searchType = document.getElementById("searchType");
    var searchContent = document.getElementById("searchContent");
    $.ajax({
        async: false,
        method: "POST",
        url: "/MissionTake/SearchInHall",
        contentType: "application/json",
        dataType: "json",
        data: {"searchType":searchType, "searchContent":searchContent},
        success: function takePersonalInfo(returnData) {
            for (var i = 0; i < returnData.length; i++) {
                if (returnData[i] == null) {
                    break;
                }
                missionNames[i] = returnData[i];
                index++;
            }
            setCollection();
        },
        error: function () {
            //alert("fail")
        }
    });

});
