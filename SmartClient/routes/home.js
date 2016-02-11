/*
 * GET home page.
 */

exports.index = function(request, response) {
	response.render('home', {
		htitle : 'Tesco Foods'
	});
};

exports.mail = function(request, response) {
	response.render('mail', {
		htitle : 'Tesco Foods'
	});
};