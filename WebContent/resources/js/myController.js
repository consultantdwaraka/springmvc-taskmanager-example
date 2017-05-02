/**
 * 
 */
var tmApp = angular.module("myapp", []);
tmApp.controller("loginController", function($scope) {
	$scope.userName = "";
	$scope.password = "DK";
	$scope.onsubmit = function() {
		window.alert(this.userName);
	}
	$scope.names = [ {
		name : '1',
		country : 'IND'
	}, {
		name : '2',
		country : 'IND'
	}, {
		name : '3',
		country : 'IND'
	} ];
});
