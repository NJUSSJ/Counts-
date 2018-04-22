var missionNames=new Array();
var index=0;

function loadPersonalCollection(phoneNumber, category) {
    var tmpUser = new user(phoneNumber, category);
    $.ajax({
        async: false,
        method: "POST",
        url: "getCollectionInfo",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(tmpUser),
        success: function takePersonalCollectionInfo(returnData) {
            for (var i = 0; i < returnData.length; i++) {
                if (returnData[i] == null) {
                    break;
                }
                missionNames[i] = returnData[i];
                index++;
            }
            setPersonalCollection();
        },
        error: function () {
            alert("fail");
        }
    });
}

function setPersonalCollection() {
    document.getElementById("personalCollections").innerHTML="";
    for(var i = 0;i<missionNames.length;i++){
        var div = document.createElement("div");
        div.className = "4u 12u$(mobile)";
        var a = document.createElement("a");
        a.className = "image fit";
        a.href = "/details.html?imageURL="+missionNames[i];
        var img = document.createElement("img");
        img.src = "../../images/"+missionNames[i]+"_1.jpg";
        img.alt = "";
        a.appendChild(img);
        div.appendChild(a);
        document.getElementById("personalCollections").appendChild(div);
    }

}