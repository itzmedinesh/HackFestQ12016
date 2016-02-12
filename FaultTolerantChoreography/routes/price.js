var PriceCircuitBreaker = require('circuit-breaker-js');
var httpreq = require('request');

var websock = null;

function Price() {

}

var priceUrl = 'http://localhost:8084/price';

var circuitEvent = {};

var priceCircuitConfig = {
	windowDuration : 10000,
	numBuckets : 10,
	timeoutDuration : 20,
	volumeThreshold : 1,
	errorThreshold : 50
};

var priceFallback = function(callback) {
	console.log('Price service in fallback mode : price unavailable');
	if (websock) {
		circuitEvent.datetime = new Date();
		circuitEvent.metrics = null;
		circuitEvent.name = "price";
		circuitEvent.type = "fallback";
		websock.emit('serverMessage', circuitEvent);
	}
	var priceData = {
		"price" : "price not available at the moment"
	};
	callback(null, priceData);
};

var priceServiceBreaker = new PriceCircuitBreaker(priceCircuitConfig);

Price.prototype.getProductPrices = function(tpnb, zone, eventsocket, callback) {
	websock = eventsocket;
	var priceCommand = function(success, failure) {
		httpreq({
			url : priceUrl + "/" + tpnb + "/" + zone,
			method : 'GET',
			headers : {
				'Content-Type' : 'application/json'
			}
		}, function(error, resp, body) {
			if (error) {
				failure();
				callback(error, null);
			} else {
				var priceData = {
					"price" : body
				};
				success();
				callback(null, priceData);
			}
		});
	};
	priceServiceBreaker.run(priceCommand, priceFallback.bind(this, callback));
};

priceServiceBreaker.onCircuitOpen = function(metrics) {
	console.warn('Price Service Circuit open', metrics);
	if (websock) {
		circuitEvent.datetime = new Date();
		circuitEvent.metrics = metrics;
		circuitEvent.name = "price";
		circuitEvent.type = "open";
		websock.emit('serverMessage', circuitEvent);
	}
};

priceServiceBreaker.onCircuitClose = function(metrics) {
	console.warn('Price Service Circuit close', metrics);
	if (websock) {
		circuitEvent.datetime = new Date();
		circuitEvent.metrics = metrics;
		circuitEvent.name = "price";
		circuitEvent.type = "close";
		websock.emit('serverMessage', circuitEvent);
	}
};

module.exports = new Price();