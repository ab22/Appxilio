var express = require('express'),
    exphbs  = require('express3-handlebars'),
	fs = require('fs'),
	url = require('url'),
	path = require('path'),

    app = express();
	
var Hashids = require("hashids"),
    hashids = new Hashids("this is my salt",6);


app.configure(function(){
    app.engine('handlebars', exphbs({defaultLayout: 'main'}));
	app.engine('dashboard', exphbs({defaultLayout: 'main'}));
	app.engine('statistics', exphbs({defaultLayout: 'main'}));
    app.set('view engine', 'handlebars');
    app.use(express.static(__dirname + '/public'));
	app.use(express.bodyParser());

// or, as `req.files` is only provided by the multipart middleware, you could 
// add just that if you're not concerned with parsing non-multipart uploads, 
// like:
app.use(express.multipart());
});

app.get('/heatmap', function(req, res) {
    res.render('heatmap.handlebars');
});

app.get('/', function (req, res) {
    res.render('dashboard.handlebars');
});

app.get('/statistics', function(req, res) {
    res.render('statistics.handlebars');
});

app.get('/image/:id', function(req, res) {

	var request = url.parse(req.url, true);
	var action = req.params.id;
	console.log(action);

	var img = fs.readFileSync('./images/'+action);
    res.writeHead(200, {'Content-Type': 'image/jpeg' });
    res.end(img, 'binary');
	
});

app.post('/image/add', function(req, res) {

	var tempPath = req.files.file.path,
		imageName = req.files.file.name,
        targetPath = path.resolve('./images/'+imageName);
    if (path.extname(req.files.file.name).toLowerCase() === '.jpg') {
        fs.readFile(tempPath, function (err, data) {

		  fs.writeFile(targetPath, data, function (err) {

		  	/// let's see it
		  	res.redirect("/image/" + imageName);

		  });
	});
    } 	
});

app.get('/denuncia/getAll',function(req, res) {

fs.readFile('./json/denuncias.json', 'utf8', function (err, data) {
  if (err) {
    console.log('Error: ' + err);
    return;
  }
  //data = JSON.parse(data);
 
  res.writeHead(200,{'Content-Type': 'application/json'});
  res.end(data);
});

});


app.post('/denuncia/addPanico',function(req, res) {

fs.readFile('./json/denuncias.json', 'utf8', function (err, data) {
  if (err) {
    console.log('Error: ' + err);
    return;
  }
  data = JSON.parse(data);
  
  var date = new Date().valueOf();
  
  console.log(req.body);
  
  var hash = hashids.encrypt(date),
      numbers = hashids.decrypt(hash);
	  
	console.log(numbers);
  
  data.push(
   {
   "latitud":req.body.latitud,
   "longitud":req.body.longitud,
   "infoUsuario":req.body.infoUsuario,
  	"boton":req.body.boton,
  	"esAnonima":req.body.esAnonima,
  	"esDenuncia":false,
	"estado":"reportado",
  	"fecha":date,
  	"numeroCaso":hash,
	"comentarios":"",
	"descripcion":"",
  	"imagenes":[]
	}
  
  );
  
  fs.writeFile('./json/denuncias.json', JSON.stringify(data, null, 4), function(err) {
    if(err) {
        console.log(err);
    } else {
        console.log("The file was saved!");
		res.writeHead(200,{'Content-Type': 'application/json'});
		res.end(hash);
    }
}); 
  
  
});

});

app.post('/denuncia/addDenuncia',function(req, res) {

fs.readFile('./json/denuncias.json', 'utf8', function (err, data) {
  if (err) {
    console.log('Error: ' + err);
    return;
  }
  data = JSON.parse(data);
  
  var date = new Date().valueOf();
  
  console.log(req.body);
  
  var hash = hashids.encrypt(date),
      numbers = hashids.decrypt(hash);
	  
	console.log(numbers);
  
  data.push(
   {
   "latitud":req.body.latitud,
   "longitud":req.body.longitud,
   "infoUsuario":req.body.infoUsuario,
  	"boton":req.body.boton,
  	"esAnonima":req.body.esAnonima,
  	"esDenuncia":true,
	"estado":"reportado",
  	"fecha":date,
  	"numeroCaso":hash,
	"comentarios":"",
	"descripcion":"",
  	"imagenes":req.body.imagenes
	}
  
  );
  
  fs.writeFile('./json/denuncias.json', JSON.stringify(data, null, 4), function(err) {
    if(err) {
        console.log(err);
    } else {
        console.log("The file was saved!");
		res.writeHead(200,{'Content-Type': 'application/json'});
		res.end(hash);
    }
}); 
  
  
});

});

app.get('/denuncia/find/:id',function(req, res) {

fs.readFile('./json/denuncias.json', 'utf8', function (err, data) {
  if (err) {
    console.log('Error: ' + err);
    return;
  }
  data = JSON.parse(data);
  var list = [];
  for(var i=0; i<data.length; i++)
  {
	if(!data[i].esAnonima && data[i].infoUsuario.correoElectronico === req.params.id){
		list.push(data[i]);
	}
  }
 
  res.writeHead(200,{'Content-Type': 'application/json'});
  res.end(JSON.stringify(list));
});

});


app.post('/denuncia/actualizarCaso',function(req, res) {

fs.readFile('./json/denuncias.json', 'utf8', function (err, data) {
  if (err) {
    console.log('Error: ' + err);
    return;
  }
  data = JSON.parse(data);
  var item = {};
  for(var i=0; i<data.length; i++)
  {
	if(data[i].numeroCaso === req.body.numeroCaso){
		data[i].estado = req.body.estado;
		data[i].comentarios = req.body.comentarios;
		item = data[i];
		break;
	}
  }
  
  fs.writeFile('./json/denuncias.json', JSON.stringify(data, null, 4), function(err) {
    if(err) {
        console.log(err);
    } else {
        console.log("The file was saved!");
		
  res.writeHead(200,{'Content-Type': 'application/json'});
  res.end(JSON.stringify(item));
    }
}); 
 
});

});

app.post('/denuncia/agregarTipoDenuncia',function(req, res) {

fs.readFile('./json/denuncias.json', 'utf8', function (err, data) {
  if (err) {
    console.log('Error: ' + err);
    return;
  }
  data = JSON.parse(data);
  var item = {};
  for(var i=0; i<data.length; i++)
  {
	if(data[i].numeroCaso === req.body.numeroCaso){
		data[i].tipoDenuncia = req.body.tipoDenuncia;
		item = data[i];
		break;
	}
  }
  
  fs.writeFile('./json/denuncias.json', JSON.stringify(data, null, 4), function(err) {
    if(err) {
        console.log(err);
    } else {
        console.log("The file was saved!");
		
  res.writeHead(200,{'Content-Type': 'application/json'});
  res.end(JSON.stringify(item));
    }
}); 
 
});

});

app.post('/denuncia/agregarImagenesDescripcion',function(req, res) {

fs.readFile('./json/denuncias.json', 'utf8', function (err, data) {
  if (err) {
    console.log('Error: ' + err);
    return;
  }
  data = JSON.parse(data);
  var item = {};
  for(var i=0; i<data.length; i++)
  {
	if(data[i].numeroCaso === req.body.numeroCaso){
		for(var j=0; j<req.body.imagenes.length; j++) {
			data[i].imagenes.push(req.body.imagenes[j]);	
		}
		data[i].descripcion = req.body.descripcion;
		item = data[i];
		break;
	}
  }
  
  fs.writeFile('./json/denuncias.json', JSON.stringify(data, null, 4), function(err) {
    if(err) {
        console.log(err);
    } else {
        console.log("The file was saved!");
		
  res.writeHead(200,{'Content-Type': 'application/json'});
  res.end(JSON.stringify(item));
    }
}); 
 
});

});

app.post('/reporte',function(req, res) {

fs.readFile('./json/denuncias.json', 'utf8', function (err, data) {
  if (err) {
    console.log('Error: ' + err);
    return;
  }
  data = JSON.parse(data);
  var list = [];
  for(var i=0; i<data.length; i++)
  {
	if(data[i].estado === req.body.estado || req.body.estado==="todas" ){
		if(data[i].tipoDenuncia===req.body.tipoDenuncia || req.body.tipoDenuncia==="todas")
			{
				list.push(data[i]);
			}
		}
  }
  
 		
  res.writeHead(200,{'Content-Type': 'application/json'});
  res.end(JSON.stringify(list));
    
});

});



app.listen(3000);
