/**
 * Module dependencies.
 */

var express = require('express'), routes = require('./routes'), home = require('./routes/home'), http = require('http'), path = require('path');

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
if ('development' === app.get('env')) {
	app.use(express.errorHandler());
}

app.set('port', process.env.PORT || 8000);

var myserver = http.createServer(app).listen(app.get('port'), function() {
	console.log('Smart Client available on port ' + app.get('port'));
});

app.get('/', home.index);
app.get('/index.html', home.index);
app.get('/mail.html', home.mail);
app.get('/api/product/catalog/parallel', routes.index);