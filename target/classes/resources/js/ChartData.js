var adminUserSum;
var adminWorkerNum;
var adminNum;
var adminStarterNum;
var adminMissionSum;
var adminFinishedMissionNum;
var adminOngoingMissionNum;

function AdminDataObj(adminUserSum, adminWorkerNum, adminNum, adminStarterNum, adminMissionSum, adminFinishedMissionNum, adminOngoingMissionNum) {
    this.adminUserSum = adminUserSum;
    this.adminWorkerNum = adminWorkerNum;
    this.adminNum = adminNum;
    this.adminStarterNum = adminStarterNum;
    this.adminMissionSum = adminMissionSum;
    this.adminFinishedMissionNum = adminFinishedMissionNum;
    this.adminOngoingMissionNum = adminOngoingMissionNum;
}

function loadAdminChartNumAndData(adminDataObj) {
<<<<<<< HEAD
    alert("adminDataObj: " + adminDataObj);
=======
    alert("adminDataObj: " + "adminUserSum " + adminDataObj.adminUserSum
    + "adminWorkerNum " + adminDataObj.adminWorkerNum
    + "adminNum " + adminDataObj.adminNum
    + "adminStarterNum " + adminDataObj.adminStarterNum
    + "adminMissionSum " + adminDataObj.adminMissionSum
    + "adminFinishedMissionNum " + adminDataObj.adminFinishedMissionNum
    + "adminOngoingMissionNum " + adminDataObj.adminOngoingMissionNum);

>>>>>>> 80e8a01f2e696b72978f2b8e1a8119800ff459ab
    adminUserSum = adminDataObj.adminUserSum;
    adminWorkerNum = adminDataObj.adminWorkerNum;
    adminNum = adminDataObj.adminNum;
    adminStarterNum = adminDataObj.adminStarterNum;
    adminMissionSum = adminDataObj.adminMissionSum;
    adminFinishedMissionNum = adminDataObj.adminFinishedMissionNum;
    adminOngoingMissionNum = adminDataObj.adminOngoingMissionNum;
}

var starterMissionSum;
var starterFinishedMissionNum;
var starterOngoingMissionNum;
var starterParticipantSum = [];
var starterCreditSum = [];
var starterCreditAvg = [];
var starterMissionName = [];

function StarterDataObj(starterMissionSum, starterFinishedMissionNum, starterOngoingMissionNum, starterParticipantSum, starterCreditSum, starterCreditAvg, starterMissionName) {
    this.starterMissionSum = starterMissionSum;
    this.starterFinishedMissionNum = starterFinishedMissionNum;
    this.starterOngoingMissionNum = starterOngoingMissionNum;
    this.starterParticipantSum = starterParticipantSum;
    this.starterCreditSum = starterCreditSum;
    this.starterCreditAvg = starterCreditAvg;
    this.starterMissionName = starterMissionName;
}

function loadStarterChartNumAndData(starterDataObj) {
<<<<<<< HEAD
    alert("starterDataObj: " + starterDataObj);
=======
    alert("starterDataObj: " + "starterMissionSum " + starterDataObj.starterMissionSum
    + "starterFinishedMissionNum " + starterDataObj.starterFinishedMissionNum
    + "starterOngoingMissionNum " + starterDataObj.starterOngoingMissionNum
    + "starterParticipantSum " + starterDataObj.starterParticipantSum
    + "starterCreditSum " + starterDataObj.starterCreditSum
    + "starterCreditAvg " + starterDataObj.starterCreditAvg
    + "starterMissionName " + starterDataObj.starterMissionName);

>>>>>>> 80e8a01f2e696b72978f2b8e1a8119800ff459ab
    starterMissionSum = starterDataObj.starterMissionSum;
    starterFinishedMissionNum = starterDataObj.starterFinishedMissionNum;
    starterOngoingMissionNum = starterDataObj.starterOngoingMissionNum;
    starterCreditSum = starterParticipantSum = starterDataObj.starterParticipantSum;
    starterCreditSum = starterDataObj.starterCreditSum;
    starterCreditAvg = starterDataObj.starterCreditAvg;
    starterMissionName = starterDataObj.starterMissionName;
}

var workerMissionSum;
var workerFinishedMissionNum;
var workerUnfinishedMissionNum;
var workerCredit = [];// 单个任务获得的积分
var workerMissionName = []; //每个任务的名称
var workerCreditRank;
var workerSum;//总工人数
var workerCreditSum; //积分总数

function WorkerDataObj(workerMissionSum, workerFinishedMissionNum, workerUnfinishedMissionNum, workerCredit, workerMissionName, workerCreditRank, workerSum, workerCreditSum) {
    this.workerMissionSum = workerMissionSum;
    this.workerFinishedMissionNum = workerFinishedMissionNum;
    this.workerUnfinishedMissionNum = workerUnfinishedMissionNum;
    this.workerCredit = workerCredit;
    this.workerMissionName = workerMissionName;
    this.workerCreditRank = workerCreditRank;
    this.workerSum = workerSum;
    this.workerCreditSum = workerCreditSum;
}

function loadWorkerChartNumAndData(workerDataObj) {
<<<<<<< HEAD
    alert("workerDataObj: " + workerDataObj);
=======
    alert("workerDataObj: " + "workerMissionSum " + workerDataObj.workerMissionSum
    + "workerFinishedMissionNum " + workerDataObj.workerFinishedMissionNum
    + "workerUnfinishedMissionNum " + workerDataObj.workerUnfinishedMissionNum
    + "workerCredit " + workerDataObj.workerCredit
    + "workerMissionName " + workerDataObj.workerMissionName
    + "workerCreditRank " + workerDataObj.workerCreditRank
    + "workerSum " + workerDataObj.workerSum
    + "workerCreditSum " + workerDataObj.workerCreditSum);

>>>>>>> 80e8a01f2e696b72978f2b8e1a8119800ff459ab
    workerMissionSum = workerDataObj.workerMissionSum;
    workerFinishedMissionNum = workerDataObj.workerFinishedMissionNum;
    workerUnfinishedMissionNum = workerDataObj.workerUnfinishedMissionNum;
    workerCredit = workerDataObj.workerCredit;
    workerMissionName = workerDataObj.workerMissionName;
    workerCreditRank = workerDataObj.workerCreditRank;
    workerSum = workerDataObj.workerSum;
    workerCreditSum = workerDataObj.workerCreditSum;
}