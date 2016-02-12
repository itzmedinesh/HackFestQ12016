/**
 * Module dependencies.
 */

var express = require('express'), routes = require('./routes'), home = require('./routes/home'), http = require('http'), path = require('path');

var dustjs = require('adaro');

var app = express();

var eventsocket = null;

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
if ('development' === app.get('env')) {
	app.use(express.errorHandler());
}

app.set('port', process.env.PORT || 8000);

var uiserver = http.createServer(app).listen(app.get('port'), function() {
	console.log('Smart Client available on port ' + app.get('port'));
});

var io = require('socket.io').listen(uiserver);

io.sockets.on('connection', function(socket) {
	eventsocket = socket;
	socket.emit('serverMessage', '-None-');
	socket.on('clientMessage', function(content) {
		console.log(content);
		socket.emit('serverMessage', 'You said: ' + content);
	});
});

app.get('/', home.index);
app.get('/index.html', home.index);
app.get('/mail.html', home.mail);
app.get('/price-api', function(request, response) {
	request.eventsocket = eventsocket;
	routes.index(request, response);
});
app.get('/api/monitor', routes.monindex);