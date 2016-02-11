/**
 * Module dependencies.
 */

var express = require('express'), routes = require('./routes'), price = require('./routes/price'),clearance = require('./routes/clearance'), http = require('http'), path = require('path');
var url = require('url');


var dustjs = require('adaro');

var app = express();

// all environments
app.engine('dust', dustjs.dust({
	cache : true
}));
app.set('view engine', 'dust');
app.set('views', __dirname + '/views');

app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));

// development only
if ('development' == app.get('env')) {
	app.use(express.errorHandler());
}

app.set('port', process.env.PORT || 7000);

app.get('/api/price', function(request, response) {
	var queryObject = url.parse(request.url, true).query;

	price.getProductPrices(queryObject.tpnb, queryObject.zone, function(err, data) {
		if (err) {
			console.log(err);
		} else {
			console.log(data);
		}
	});
});

app.get('/api/clearance', function(request, response) {
	var queryObject = url.parse(request.url, true).query;

	clearance.getProductClearance(queryObject.tpnb, queryObject.zone, function(err, data) {
		if (err) {
			console.log(err);
		} else {
			console.log(data);
		}
	});
});

http.createServer(app).listen(app.get('port'), function() {
	console.log('Choreography server listening on port ' + app.get('port'));
});
