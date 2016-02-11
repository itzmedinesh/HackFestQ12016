var PromotionCircuitBreaker = require('circuit-breaker-js');
var httpreq = require('request');

function Promotion() {

}

var tpnb;
var zone;
var promoUrl = 'http://localhost:8082/promotion/';

var promoCircuitConfig = {
	windowDuration : 10000,
	numBuckets : 10,
	timeoutDuration : 6,
	volumeThreshold : 1,
	errorThreshold : 50
};

var promoFallback = function(callback) {
	console.log('Promotion service is down');
	var promoData = {
		"promotion" : "promotion service not available at the moment"
	};
	callback(null, promoData);
};

var promoServiceBreaker = new PromotionCircuitBreaker(promoCircuitConfig);

Promotion.prototype.getPromoProduct = function(tpnb, zone, callback) {
	var promoCommand = function(success, failure) {
		
		httpreq({
			url : promoUrl + tpnb + "/"+ zone,
			method : 'GET',
			headers : {
				'Content-Type' : 'application/json'
			}
		}, function(error, resp, body) {
			if (error) {
				failure();
				callback(error, null);
			} else {
				var promoData = {
					"promotion" : body
				};
				success();
				callback(null, promoData);
			}
		});
	};
	promoServiceBreaker.run(promoCommand, promoFallback.bind(this, callback));
};

promoServiceBreaker.onCircuitOpen = function(metrics) {
	console.warn('Promotion Service Circuit open', metrics);
};

promoServiceBreaker.onCircuitClose = function(metrics) {
	console.warn('Promotion Service Circuit close', metrics);
};

module.exports = new Promotion();