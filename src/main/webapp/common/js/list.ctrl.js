(function()
{
var module = angular.module("htracker");
module.controller("listCtrl", listCtrlCtor)

// controller used to manage views with list in table
function listCtrlCtor(
							/*DEPDENCY INJECTION*/
							getterService,
							$state) {
	
//	--------------------------
//	--- CONTROLER VARIABLE ---
//	--------------------------
	
//	var self 			= this;
//	hold an array containing the data to be display on the selected view
//	this.dataArray		= getterService.dataArray;

	this.newName = "";
	this.newType="";
	this.types = ["number","letter","boolean","image"];
//	------------------------
//	---CONTROLLER FUNCTION--
//	------------------------
	
//	--------------------------
//	--- INITIALIZATION TASK --
//	--------------------------

}
})();
