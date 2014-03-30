$(document).ready(function(){
	var selectedType = "todas";
	var selectedState = "todas";

	$('.btn-group-state button').click(function () {
		$('.btn-group-state button').each(function () {
                $(this).removeClass('btn-success');
        });
		$(this).addClass('btn-success');
		selectedState = $(this).html();
		
		filterGrid( selectedState.toLowerCase(), selectedType.toLowerCase());
		return false;
    });
	
	$('.btn-group-type button').click(function () {
		$('.btn-group-type button').each(function () {
                $(this).removeClass('btn-success');
        });
		$(this).addClass('btn-success');
		selectedType = $(this).html();
		
		filterGrid( selectedState.toLowerCase(), selectedType.toLowerCase());
		return false;		
    });
	
	

	// datatable
	var dtDenuncias =  $('#dtDenuncias').dataTable({
    "sPaginationType": "full_numbers",
    "bDestroy": true,
    "fnDrawCallback": function (oSettings) {
    },
    "oLanguage": {
        "oPaginate": {
            "sFirst": "Primera",
            "sLast": "Ultima",
            "sNext": "Siguiente",
            "sPrevious": "Anterior"
        },
        "sEmptyTable": "No hay datos que mostrar.",
        "sInfo": "Mostrando _START_/_END_ de _TOTAL_ en total.",
        "sInfoEmpty": "No hay datos que mostrar.",
        "sInfoFiltered": " - Filtrando de _MAX_ datos en total.",
        "sLengthMenu": 'Mostrar <select>' +
                        '<option value="10">10</option>' +
                        '<option value="20">20</option>' +
                        '<option value="50">50</option>' +
                        '<option value="100">100</option>' +
                        '<option value="200">200</option>' +
                        '<option value="-1">Todas</option>' +
                        '</select> entradas.',
        "sLoadingRecords": "Cargando...",
        "sProcessing": "Procesando...",
        "sSearch": "Buscar: _INPUT_",
        "sZeroRecords": "No hay datos que mostrar."
    }
});

function prettyDate(date) {
    var temp = new Date(date);
    var dateStr = padStr(temp.getDate()) + '/' + 
				  padStr(1 + temp.getMonth()) + '/' +
				  padStr(temp.getFullYear());
	return dateStr;
};

function padStr(i) {
    return (i < 10) ? "0" + i : "" + i;
};	
	
	function filterGrid(estado, denuncia){		
		jQuery.ajax({
		type: "POST",
		url: "/reporte",
		data: "{ \"estado\": \""+ estado + "\", \"tipoDenuncia\": \""+ denuncia +"\" }",
		contentType: "application/json",
		success: function (resultData) {
			var response = resultData;
			dtDenuncias.fnClearTable();
			for (var i = 0; i < response.length ; i++) {
				var data = response[i];
				dtDenuncias.fnAddData(
					[
						prettyDate(data.fecha),
						data.estado,
						data.descripcion,
						data.esAnonima
					],
					false               //Redraw: false
				);
			 dtDenuncias.fnDraw();        //Redraw when done
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log(errorThrown);
		},
		complete: function (x) {
		}
		});
	};
	filterGrid("todas", "todas");
});