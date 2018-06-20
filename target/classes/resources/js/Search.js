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

<<<<<<< HEAD
function refreshMission() {
    getCollectionInfo();
=======
function refreshMission(phoneNumber,category) {
    var tmpUser = new TmpUser(phoneNumber,category);
    getCollectionInfo(tmpUser);
>>>>>>> 86db4a81d7c9c3d1de70aa4ecc8ce596eafcbcd0
    setCollection();
}
