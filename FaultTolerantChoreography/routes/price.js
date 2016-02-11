var PriceCircuitBreaker = require('circuit-breaker-js');
var httpreq = require('request');

function Price() {

}

var priceUrl = 'http://localhost:8084/price';

var priceCircuitConfig = {
	windowDuration : 10000,
	numBuckets : 10,
	timeoutDuration : 1000,
	volumeThreshold : 1,
	errorThreshold : 50
};

var priceFallback = function(callback) {
	console.log('Price service is down : returning static price');
	var priceData = {
		"price" : "price not available at the moment"
	};
	callback(null, priceData);
};

var priceServiceBreaker = new PriceCircuitBreaker(priceCircuitConfig);

Price.prototype.getProductPrices = function(tpnb,zone, callback) {
	var priceCommand = function(success, failure) {
		httpreq({
			url : priceUrl + "/" + tpnb + "/" +zone,
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
};

priceServiceBreaker.onCircuitClose = function(metrics) {
	console.warn('Price Service Circuit close', metrics);
};

module.exports = new Price();