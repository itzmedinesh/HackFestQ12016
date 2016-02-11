var async = require('async');
var httpreq = require('request');
var promotion = require('./promotion'), price = require('./price'), clearance = require('./clearance');
var url = require('url');

exports.getPriceDetails = function(request, response) {

	request.header("Access-Control-Allow-Origin", "*");
	request.header("Access-Control-Allow-Headers", "X-Requested-With");

	var finalres = {};
	var queryObject = url.parse(request.url, true).query;

	var execTasks = [];

	execTasks.push(price.getProductPrices.bind(price, queryObject.tpnb, "1"));
	execTasks.push(promotion.getPromoProduct.bind(promotion, queryObject.tpnb,
			"5"));
	execTasks.push(clearance.getProductClearance.bind(clearance,
			queryObject.tpnb, "20"));

	async.parallel(execTasks, function(err, results) {
		if (err) {
			console.log(err);
		} else {
			response.send(results);
		}
	});
};