var size = 0;
var clicked = [];
var missionLabel = [];
var clickedMissionLabel = [];

function loadTagType(tagType, _missionLabel) {
    missionLabel = _missionLabel;
    size = missionLabel.size();
    clicked = [size];
    //对clicked初始化
    for(var clickIndex = 0;clickIndex<size;clickIndex++){
        clicked[clickIndex] = 0;
    }

    if(tagType === 1){
        document.getElementById("picTools").innerHTML = "";
        document.getElementById("textareaPlace").innerHTML = "";
        //String testBtn = "<li><a href=\"#\" class=\"button special\" id=\"123\"/> 测试标签</li>";
        var btn = "";
        for(var i =0;i<size;i++){
            btn += "<li><a href=\"#\" class=\"button special\" id=missionLabel_"+ i +"/> missionLabel[i]</li>";
        }

        document.getElementById("missionLabel").innerHTML = btn;

        //加单击标签后的响应
        for(var index = 0;index<size;index++) {
            document.getElementById("missionLabel_" + i).addEventListener("click", function () {
                clicked[index] = !clicked[index];
                if (clicked[index]) {
                    document.getElementById("missionLabel_" + i).style = "background-color: #44BB8C";//改背景颜色

                } else {
                    document.getElementById("missionLabel_" + i).style = "";
                }
            });
        }

        var num = 0;
        for(var p = 0;p<size;p++){
            if(clicked[p]) {
                clickedMissionLabel[num] = missionLabel[p];
                num++;
            }
        }
    }
}
