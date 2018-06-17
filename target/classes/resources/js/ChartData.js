var adminUserSum;
var adminWorkerNum;
var adminNum;
var adminStarterNum;
var adminMissionSum;
var adminFinishedMissionNum;
var adminOngoingMissionNum;
var adminLevelName = [];
var adminLevelWorkerNum = [];
var adminUserName = [];
var adminMissionName = [];
var adminUserMissionQuality = [];

function AdminDataObj(adminUserSum, adminWorkerNum, adminNum, adminStarterNum, adminMissionSum, adminFinishedMissionNum, adminOngoingMissionNum, adminLevelName, adminLevelWorkerNum, adminUserName, adminMissionName, adminUserMissionQuality) {
    this.adminUserSum = adminUserSum;
    this.adminWorkerNum = adminWorkerNum;
    this.adminNum = adminNum;
    this.adminStarterNum = adminStarterNum;
    this.adminMissionSum = adminMissionSum;
    this.adminFinishedMissionNum = adminFinishedMissionNum;
    this.adminOngoingMissionNum = adminOngoingMissionNum;
    this.adminLevelName = adminLevelName;
    this.adminLevelWorkerNum = adminLevelWorkerNum;
    this.adminUserName = adminUserName;
    this.adminMissionName = adminMissionName;
    this.adminUserMissionQuality = adminUserMissionQuality;
}

function loadAdminChartNumAndData(adminDataObj) {
    /*alert("adminDataObj: " + "adminUserSum " + adminDataObj.adminUserSum
    + "adminWorkerNum " + adminDataObj.adminWorkerNum
    + "adminNum " + adminDataObj.adminNum
    + "adminStarterNum " + adminDataObj.adminStarterNum
    + "adminMissionSum " + adminDataObj.adminMissionSum
    + "adminFinishedMissionNum " + adminDataObj.adminFinishedMissionNum
    + "adminOngoingMissionNum " + adminDataObj.adminOngoingMissionNum
    + "adminLevelName " + adminDataObj.adminLevelName
    + "adminLevelWorkerNum " + adminDataObj.adminLevelWorkerNum
    + "adminUserName " + adminDataObj.adminUserName
    + "adminMissionName " + adminDataObj.adminMissionName
    + "adminUserMissionQuality " + adminDataObj.adminUserMissionQuality);*/

    adminUserSum = adminDataObj.adminUserSum;
    adminWorkerNum = adminDataObj.adminWorkerNum;
    adminNum = adminDataObj.adminNum;
    adminStarterNum = adminDataObj.adminStarterNum;
    adminMissionSum = adminDataObj.adminMissionSum;
    adminFinishedMissionNum = adminDataObj.adminFinishedMissionNum;
    adminOngoingMissionNum = adminDataObj.adminOngoingMissionNum;
    adminLevelName = adminDataObj.adminLevelName;
    adminLevelWorkerNum = adminDataObj.adminLevelWorkerNum;
    adminUserName = adminDataObj.adminUserName;
    adminMissionName = adminDataObj.adminMissionName;
    adminUserMissionQuality = adminDataObj.adminUserMissionQuality;
}

function getAdminData() {
    return new AdminDataObj(adminUserSum, adminWorkerNum, adminNum, adminStarterNum, adminMissionSum, adminFinishedMissionNum, adminOngoingMissionNum, adminLevelName, adminLevelWorkerNum, adminUserName, adminMissionName, adminUserMissionQuality);
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
    /*alert("starterDataObj: " + "starterMissionSum " + starterDataObj.starterMissionSum
    + "starterFinishedMissionNum " + starterDataObj.starterFinishedMissionNum
    + "starterOngoingMissionNum " + starterDataObj.starterOngoingMissionNum
    + "starterParticipantSum " + starterDataObj.starterParticipantSum
    + "starterCreditSum " + starterDataObj.starterCreditSum
    + "starterCreditAvg " + starterDataObj.starterCreditAvg
    + "starterMissionName " + starterDataObj.starterMissionName);*/

    starterMissionSum = starterDataObj.starterMissionSum;
    starterFinishedMissionNum = starterDataObj.starterFinishedMissionNum;
    starterOngoingMissionNum = starterDataObj.starterOngoingMissionNum;
    starterCreditSum = starterParticipantSum = starterDataObj.starterParticipantSum;
    starterCreditSum = starterDataObj.starterCreditSum;
    starterCreditAvg = starterDataObj.starterCreditAvg;
    starterMissionName = starterDataObj.starterMissionName;
}

function getStarterData() {
    return new StarterDataObj(starterMissionSum, starterFinishedMissionNum, starterOngoingMissionNum, starterParticipantSum, starterCreditSum, starterCreditAvg, starterMissionName);
}

var workerMissionSum;
var workerFinishedMissionNum;
var workerUnfinishedMissionNum;
var workerCredit = [];// 单个任务获得的积分
var workerMissionName = []; //每个任务的名称
var workerCreditRank;
var workerSum;//总工人数
var workerCreditSum; //积分总数
var workerAverageCredit = [];//每个任务平均分
var workerMaxCredit = [];//每个任务最大分
var workerMissionCreditQuality = []//

function WorkerDataObj(workerMissionSum, workerFinishedMissionNum, workerUnfinishedMissionNum, workerCredit, workerMissionName, workerCreditRank, workerSum, workerCreditSum, workerAverageCredit, workerMaxCredit, workerMissionCreditQuality) {
    this.workerMissionSum = workerMissionSum;
    this.workerFinishedMissionNum = workerFinishedMissionNum;
    this.workerUnfinishedMissionNum = workerUnfinishedMissionNum;
    this.workerCredit = workerCredit;
    this.workerMissionName = workerMissionName;
    this.workerCreditRank = workerCreditRank;
    this.workerSum = workerSum;
    this.workerCreditSum = workerCreditSum;
    this.workerAverageCredit = workerAverageCredit;
    this.workerMaxCredit = workerMaxCredit;
    this.workerMissionCreditQuality = workerMissionCreditQuality;
}

function loadWorkerChartNumAndData(workerDataObj) {
    /*alert("workerDataObj: " + "workerMissionSum " + workerDataObj.workerMissionSum
    + "workerFinishedMissionNum " + workerDataObj.workerFinishedMissionNum
    + "workerUnfinishedMissionNum " + workerDataObj.workerUnfinishedMissionNum
    + "workerCredit " + workerDataObj.workerCredit
    + "workerMissionName " + workerDataObj.workerMissionName
    + "workerCreditRank " + workerDataObj.workerCreditRank
    + "workerSum " + workerDataObj.workerSum
    + "workerCreditSum " + workerDataObj.workerCreditSum
    + "workerAverageCredit " + workerDataObj.workerAverageCredit
    + "workerMaxCredit" + workerDataObj.workerMaxCredit
    + "workerMissionCreditQuality " + workerDataObj.workerMissionCreditQuality);*/

    workerMissionSum = workerDataObj.workerMissionSum;
    workerFinishedMissionNum = workerDataObj.workerFinishedMissionNum;
    workerUnfinishedMissionNum = workerDataObj.workerUnfinishedMissionNum;
    workerCredit = workerDataObj.workerCredit;
    workerMissionName = workerDataObj.workerMissionName;
    workerCreditRank = workerDataObj.workerCreditRank;
    workerSum = workerDataObj.workerSum;
    workerCreditSum = workerDataObj.workerCreditSum;
    workerAverageCredit = workerDataObj.workerAverageCredit;
    workerMaxCredit = workerDataObj.workerMaxCredit;
    workerMissionCreditQuality = workerDataObj.workerMissionCreditQuality;
}

function getWorkerData() {
    return new WorkerDataObj(workerMissionSum, workerFinishedMissionNum, workerUnfinishedMissionNum, workerCredit, workerMissionName, workerCreditRank, workerSum, workerCreditSum, workerAverageCredit, workerMaxCredit, workerMissionCreditQuality);
}