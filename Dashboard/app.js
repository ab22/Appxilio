var express = require('express'),
    exphbs  = require('express3-handlebars'),

    app = express();

app.configure(function(){
    app.engine('handlebars', exphbs({defaultLayout: 'main'}));
    app.set('view engine', 'handlebars');
    app.use(express.static(__dirname + '/public'));
});



app.get('/', function (req, res) {
    res.render('home');
});

app.get('/heatmap', function(req, res) {
    res.render('heatmap.handlebars');
});

app.get('/heattest', function(req, res) {
    res.render('heattest.handlebars');
});

app.listen(3000);