var searchBtn = document.getElementById("searchBtn");
var index = 0;
var missionNames = [];

searchBtn.addEventListener("click", function () {
    var searchType = document.getElementById("searchType").value;
    var searchContent = document.getElementById("searchContent").value;
    searchInHall(searchType, searchContent);

});

function refreshMission(phoneNumber) {
    searchInHall("refresh",phoneNumber);
}
