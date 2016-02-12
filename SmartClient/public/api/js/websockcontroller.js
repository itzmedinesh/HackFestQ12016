angular.module("SocketApp", []).controller("WebSocketController",
		function($scope) {
			var fsocket = io.connect('http://localhost:7000', {
				'force new connection' : true
			});
			
			var rsocket = io.connect('http://localhost:8000', {
				'force new connection' : true
			});
			
			fsocket.on('serverMessage', function(content) {
				$scope.$apply(updateAPIEvent(content));
			});
			var updateAPIEvent = function(event) {
				console.log(event);
				if (event.name === 'price') {
					console.log('price event');
					$scope.priceCircuitEvent = event;
				} else if (event.name === 'promotion') {
					console.log('promotion event');
					$scope.promotionCircuitEvent = event;
				} else if (event.name === 'clearance') {
					console.log('clearance event');
					$scope.clearanceCircuitEvent = event;
				}
			};
			
			rsocket.on('serverMessage', function(content) {
				$scope.$apply(updateWebEvent(content));
			});
			var updateWebEvent = function(event) {
				console.log(event);
				$scope.activeserver = event;
			};			

		});
