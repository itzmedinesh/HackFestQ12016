var Resilient = require('resilient');
var url = require('url');

exports.index = function(request, response) {

	var queryObject = url.parse(request.url, true).query;

	var serviceServers = [ 'http://localhost:7000', 'http://localhost:6000' ];
	var discoveryServers = [ 'http://localhost:9080/priceapi' ];

	var client = Resilient({
		service : {
			basePath : '/api/product/catalog'
		},
		discovery : {
			servers : discoveryServers,
			parallel : false,
			timeout : 1000,
		}
	});

	client.on('request:outgoing', function(options) {
		console.log('Outgoing request:', options);
	});

	client.on('request:incoming', function(options) {
		console.log('Incoming response:', options);
	});

	client.on('request:fallback', function(options, res) {
		console.log('Request fallback request cycle was dispached!');
		console.log('Using the following options:', options);
		console.log('With the failed response:', res);
	});

	client.on('request:retry', function(options, servers) {
		console.log('Retry request cycle was dispached!');
		console.log('Using the following options:', options);
		console.log('Using the following servers:', servers);
	});

	client.on('servers:refresh', function(servers) {
		console.log('New servers list:', servers.join(', '));
	});

	client.on('servers:cache', function(servers) {
		console.log('Cached servers list:', servers.join(', '));
	});

	client.on('discovery:refresh', function(servers) {
		console.log('New up-to-date server list:', servers.join(', '));
	});
	// client.setServers(servers);

	var urlstr = '/parallel';

	if (queryObject && queryObject !== null && queryObject !== undefined
			&& Object.keys(queryObject).length !== 0) {
		urlstr = urlstr + "?";
		for ( var property in queryObject) {
			urlstr = urlstr + property + "=" + queryObject[property] + "&";
		}
		urlstr = urlstr.substring(0, urlstr.length - 1);
	}

	console.log(urlstr);

	client.get(urlstr, function(err, res) {
		response.send(res.data);
	});

};