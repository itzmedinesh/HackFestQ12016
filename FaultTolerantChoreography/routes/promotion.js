var PromotionCircuitBreaker = require('circuit-breaker-js');
var httpreq = require('request');
var websock = null;
function Promotion() {

}
var circuitEvent = {};
var promoUrl = 'http://localhost:8082/promotion/';

var promoCircuitConfig = {
	windowDuration : 10000,
	numBuckets : 10,
	timeoutDuration : 6,
	volumeThreshold : 20,
	errorThreshold : 50
};

var promoFallback = function(callback) {
	var promoData = {
		"promotion" : "promotion service not available at the moment"
	};
	console.log('Promotion service in fallback mode : promo data unavailable');
	if (websock) {
		circuitEvent.datetime = new Date();
		circuitEvent.metrics = null;
		circuitEvent.name = "promotion";
		circuitEvent.type = "fallback";
		websock.emit('serverMessage', circuitEvent);
	}
	callback(null, promoData);
};

var promoServiceBreaker = new PromotionCircuitBreaker(promoCircuitConfig);

Promotion.prototype.getPromoProduct = function(tpnb, zone, eventsocket,
		callback) {
	websock = eventsocket;
	var promoCommand = function(success, failure) {

		httpreq({
			url : promoUrl + tpnb + "/" + zone,
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
	if (websock) {
		circuitEvent.datetime = new Date();
		circuitEvent.metrics = metrics;
		circuitEvent.name = "promotion";
		circuitEvent.type = "open";
		websock.emit('serverMessage', circuitEvent);
	}
};

promoServiceBreaker.onCircuitClose = function(metrics) {
	console.warn('Promotion Service Circuit close', metrics);
	if (websock) {
		circuitEvent.datetime = new Date();
		circuitEvent.metrics = metrics;
		circuitEvent.name = "promotion";
		circuitEvent.type = "close";
		websock.emit('serverMessage', circuitEvent);
	}
};

module.exports = new Promotion();