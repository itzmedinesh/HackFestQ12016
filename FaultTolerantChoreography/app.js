/**
 * Module dependencies.
 */

var express = require('express'), routes = require('./routes'), http = require('http'), path = require('path');
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

var eventsocket = null;

app.set('port', process.env.PORT || 7000);

app.get('/api/finalprice', function(request, response) {
	request.eventsocket = eventsocket;
	routes.getPriceDetails(request, response);
});


var apiserver = http.createServer(app).listen(app.get('port'), function() {
	console.log('Choreography server listening on port ' + app.get('port'));
});

var io = require('socket.io').listen(apiserver);

io.sockets.on('connection', function(socket) {
	eventsocket = socket;
	socket.emit('serverMessage', 'client connected');
	socket.on('clientMessage', function(content) {
		console.log(content);
		socket.emit('serverMessage', 'You said: ' + content);
	});
});
