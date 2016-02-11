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

	execTasks.push(price.getProductPrices.bind(price, queryObject.tpnb, "Z1"));
	execTasks.push(promotion.getPromoProduct.bind(promotion, queryObject.tpnb,
			"Z5"));
	execTasks.push(clearance.getProductClearance.bind(clearance,
			queryObject.tpnb, "Z20"));

	async
			.parallel(
					execTasks,
					function(err, results) {
						if (err) {
							console.log(err);
						} else {

							var pricedata = {};

							pricedata.tpnb = queryObject.tpnb;

							for (var i = 0; i < results.length; i++) {
								var obj = results[i];
								if (obj.price && obj.price !== null
										&& obj.price !== undefined) {
									var priceobj = JSON.parse(obj.price);
									var tpnckey = Object
											.keys(priceobj.tpncToProductVariant)[0];
									var tpncobj = priceobj.tpncToProductVariant[tpnckey];
									var effdtkey = Object
											.keys(tpncobj.effective_date)[0];
									var effdtobj = tpncobj.effective_date[effdtkey];
									pricedata.regprice = effdtobj.regPrice;
								} else if (obj.promotion
										&& obj.promotion !== null
										&& obj.promotion !== undefined
										&& obj.promotion !== 'promotion service not available at the moment') {
									pricedata.promoprice = "Item part of meal deal promotion";
								} else if (obj.clearance
										&& obj.clearance !== null
										&& obj.clearance !== undefined) {
									var clrobj = JSON.parse(obj.clearance);
									var effdtkey = Object
											.keys(clrobj.effective_date)[0];
									var effdtobj = clrobj.effective_date[effdtkey];
									pricedata.clearanceprice = effdtobj.clrPrice;
								}
							}
							console.log(pricedata);
							response.send(pricedata);
						}
					});
};