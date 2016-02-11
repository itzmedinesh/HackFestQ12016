var async = require('async');
var httpreq = require('request');

exports.getPrice = function(request, response) {

	request.header("Access-Control-Allow-Origin", "*");
	request.header("Access-Control-Allow-Headers", "X-Requested-With");

	var promotion;
	console.log("POST: " + request.body);

	var finalres = {};

	async.parallel([
			function(callback) {
				httpreq(
						{
							url : 'http://localhost:8084/price/list',
							method : 'POST',
							headers : {
								'Content-Type' : 'application/json'
							},
							json : [ '074773141', '075077445', '074951909',
									'075620534', '075620586', '076742716',
									'075997220' ]
						}, function(error, resp, body) {
							if (error) {
								callback(error);
							} else {
								finalres.price = body;
								callback();
							}
						});

			},
			function(callback) {
				httpreq(
						{
							url : 'http://localhost:8082/promotion/list',
							method : 'POST',
							headers : {
								'Content-Type' : 'application/json'
							},
							json : [ '074773141', '075077445', '074951909',
									'075620534', '075620586', '076742716',
									'075997220' ]
						}, function(error, resp, body) {
							if (error) {
								callback(error);
							} else {
								finalres.promotion = body;
								callback();
							}
						});
			},
			function(callback) {
				httpreq(
						{
							url : 'http://localhost:8082/clearance/list', // URL
																			// to
							// hit
							method : 'POST',
							headers : {
								'Content-Type' : 'application/json'
							},
							json : [ '074773141', '075077445', '074951909',
									'075620534', '075620586', '076742716',
									'075997220' ]
						}, function(error, resp, body) {
							if (error) {
								callback(error);
							} else {
								finalres.clearance = body;
								callback();
							}
						});
			} ], function(err, results) {
		if (err) {
			console.log(err);
		} else {
			response.send(finalres);

		}

	});
};