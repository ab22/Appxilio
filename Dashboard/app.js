var express = require('express'),
    exphbs  = require('express3-handlebars'),

    app = express();

app.configure(function(){
    app.engine('handlebars', exphbs({defaultLayout: 'main'}));
	app.engine('dashboard', exphbs({defaultLayout: 'main'}));
    app.set('view engine', 'handlebars');
    app.use(express.static(__dirname + '/public'));
});



app.get('/', function (req, res) {
    res.render('dashboard.handlebars');
});

app.get('/heatmap', function(req, res) {
    res.render('heatmap.handlebars');
});

app.listen(3000);