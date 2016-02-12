var ClearanceCircuitBreaker = require('circuit-breaker-js');
var httpreq = require('request');
var websock = null;
function Clearance() {

}
var circuitEvent = {};
var clearanceUrl = 'http://localhost:8082/clearance';

var clearanceCircuitConfig = {
	windowDuration : 10000,
	numBuckets : 10,
	timeoutDuration : 20,
	volumeThreshold : 1,
	errorThreshold : 50
};

var clearanceFallback = function(callback) {
	var clearanceData = {
			"clearance" : "clearance service not available at the moment"
		};
	console.log('Clearance service in fallback mode : clearance data unavailable');
	if (websock) {
		circuitEvent.datetime = new Date();
		circuitEvent.metrics = null;
		circuitEvent.name = "clearance";
		circuitEvent.type = "fallback";
		websock.emit('serverMessage', circuitEvent);
	}
	callback(null, clearanceData);
};

var clearanceServiceBreaker = new ClearanceCircuitBreaker(
		clearanceCircuitConfig);

Clearance.prototype.getProductClearance = function(tpnb, zone, eventsocket,
		callback) {
	websock = eventsocket;
	var clearanceCommand = function(success, failure) {
		httpreq({
			url : clearanceUrl + "/" + tpnb + "/" + zone,
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
	clearanceServiceBreaker.run(clearanceCommand, clearanceFallback.bind(this,
			callback));
};

clearanceServiceBreaker.onCircuitOpen = function(metrics) {
	console.warn('Clearance Service Circuit open', metrics);
	if (websock) {
		circuitEvent.datetime = new Date();
		circuitEvent.metrics = metrics;
		circuitEvent.name = "clearance";
		circuitEvent.type = "open";
		websock.emit('serverMessage', circuitEvent);
	}
};

clearanceServiceBreaker.onCircuitClose = function(metrics) {
	console.warn('Clearance Service Circuit close', metrics);
	if (websock) {
		circuitEvent.datetime = new Date();
		circuitEvent.metrics = metrics;
		circuitEvent.name = "clearance";
		circuitEvent.type = "close";
		websock.emit('serverMessage', circuitEvent);
	}
};

module.exports = new Clearance();