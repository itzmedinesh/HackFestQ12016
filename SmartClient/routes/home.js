
/*
 * GET home page.
 */

exports.index = function(request, response){
  response.render('home', { htitle: 'Tesco Foods' });
};