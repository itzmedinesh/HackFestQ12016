/**
 * 
 */

var app = angular.module('MyItemPrice', []);

app.config(function($interpolateProvider) {
	$interpolateProvider.startSymbol('[[').endSymbol(']]');
});

app.controller('MyCtrl', function($scope, $http) {
	$scope.price = null;
	$scope.myPrice = function(tpn) {
		// $scope.price.data = "50";
		var res = $http({
			url : "/price-api",
			method : "GET",
			params : {
				tpnb : tpn
			},
			headers : {
				'Content-Type' : 'application/json; charset=UTF-8'
			}
		});

		res.success(function(data, status, headers, config) {
			$scope.price = data;
			console.log(data);
		});
		res.error(function(data, status, headers, config) {
			$scope.price = data;
			console.log(data);
		});

	};

});
