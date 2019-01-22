(function()
		{

var module = angular.module("htracker");

// http://stackoverflow.com/questions/41211875/angularjs-1-6-0-latest-now-routes-not-working
module.config(['$locationProvider', function($locationProvider) {
    $locationProvider.hashPrefix('');
}]);

//another example doing it using text/ng-template
module.config(function($stateProvider, $urlRouterProvider) {

    $stateProvider
        .state("userView", {
            url: "/userView",
            templateUrl: "viewPage.html",
            controller: "listCtrl as listCtrl",
        })
        .state("editPage", {
        	url: "/editPage",
        	templateUrl: "editPage.html",
        	controller: "listCtrl as listCtrl",
        })
        .state("newParameter", {
        	url: "/newParameter",
        	templateUrl: "newParameter.html",
        	controller: "listCtrl as listCtrl",
        })
        .state("mainAdmin", {
        	url: "/mainAdmin",
        	templateUrl: "adminView/mainAdmin.html",
        	controller: "listCtrl as listCtrl",
        })


    $urlRouterProvider.when("", "userView");
    $urlRouterProvider.otherwise('viewPage.html');
});
})()