var ClearanceCircuitBreaker = require('circuit-breaker-js');
var httpreq = require('request');

function Clearance() {

}

var clearanceUrl = 'http://localhost:8082/clearance';

var clearanceCircuitConfig = {
	windowDuration : 10000,
	numBuckets : 10,
	timeoutDuration : 1000,
	volumeThreshold : 1,
	errorThreshold : 50
};

var clearanceFallback = function(callback) {
	console.log('Clearance service is down : returning static data');
	var clearanceData = {
		"clearance" : "Clearance not available at the moment"
	};
	callback(null, clearanceData);
};

var clearanceServiceBreaker = new ClearanceCircuitBreaker(clearanceCircuitConfig);

Clearance.prototype.getProductClearance = function(tpnb,zone, callback) {
	var clearanceCommand = function(success, failure) {
		httpreq({
			url : clearanceUrl + "/" + tpnb + "/" +zone,
			method : 'GET',
			headers : {
				'Content-Type' : 'application/json'
			}
		}, function(error, resp, body) {
			if (error) {
				failure();
				callback(error, null);
			} else {
				var clearanceData = {
					"clearance" : body
				};
				success();
				callback(null, clearanceData);
			}
		});
	};
	clearanceServiceBreaker.run(clearanceCommand, clearanceFallback.bind(this, callback));
};

clearanceServiceBreaker.onCircuitOpen = function(metrics) {
	console.warn('Clearance Service Circuit open', metrics);
};

clearanceServiceBreaker.onCircuitClose = function(metrics) {
	console.warn('Clearance Service Circuit close', metrics);
};

module.exports = new Clearance();