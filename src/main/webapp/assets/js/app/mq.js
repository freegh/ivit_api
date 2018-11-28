
function showLog(data){
	$("#result").html(data);
	$("#modal_large").modal("show");
}
var table;
$(function() {


    // Table setup
    // ------------------------------

    // Setting datatable defaults
    $.extend( $.fn.dataTable.defaults, {
        autoWidth: false,
        responsive: true,
        columnDefs: [{ 
            orderable: false,
            width: '100px',
            targets: [ 5 ]
        }],
        dom: '<"datatable-header"fl><"datatable-scroll-wrap"t><"datatable-footer"ip>',
        language: {
            search: '<span>Filter:</span> _INPUT_',
            searchPlaceholder: 'Type to filter...',
            lengthMenu: '<span>Show:</span> _MENU_',
            paginate: { 'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;' }
        },
        drawCallback: function () {
            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').addClass('dropup');
        },
        preDrawCallback: function() {
            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').removeClass('dropup');
        }
    });


    // Basic responsive configuration
    table = $('.datatable-responsive').DataTable( {
        "processing": true,
        "serverSide": true,
        "columns" : [ 
        	{ "data" : 'vdlTxnCodeTran'},
        	{ "data" : 'vdlResp'},
        	{ "data" : 'vdlFromAcct'},
        	{ "data" : 'vdlToAcct'},
        	{ "data" : 'vdlSysTime'},
        	{ "data" : null},
        ],
        "ajax": {
            "url": '/api/listVdo',
             "dataSrc": 'data',
        },
        "aoColumnDefs": [
/*            {
                "mRender": function ( data, type, row ) {
                    return data +'ddd';
                },
                "aTargets": [ 0 ]
            },*/
            {
                "mRender": function ( data, type, row ) {
                	var dat = data.rawData.replace(/(?:\r\n|\r|\n)/g, '<br>');
                	dat = dat.replace(/ /g, '&nbsp;');
			var out = '<ul class="icons-list">'+
					'<li class="dropdown">'+
					'	<a href="#" class="dropdown-toggle" data-toggle="dropdown">'+
					'		<i class="icon-menu9"></i>'+
					'	</a>'+

					'	<ul class="dropdown-menu dropdown-menu-right">'+
					'		<li><a href="javascript:showLog(\''+dat +'\')"><i class="icon-glasses-3d2"></i> View</a></li>'+
					//'		<li><a href="#"><i class="icon-floppy-disk"></i> Save</a></li>'+
					//'		<li><a href="#"><i class="icon-bin"></i> Delete</a></li>'+
					'	</ul>'+
					'</li>'+
				'</ul>';
                    return out;
                },
                "aTargets": [ 5 ]
            },            
            //{ "bVisible": false,  "aTargets": [ 3 ] },
            { "sClass": "text-center", "aTargets": [ 5 ] }
        ],
    } );
    
    // Column controlled child rows
    $('.datatable-responsive-column-controlled').DataTable({
        responsive: {
            details: {
                type: 'column'
            }
        },
        columnDefs: [
            {
                className: 'control',
                orderable: false,
                targets:   0
            },
            { 
                width: "100px",
                targets: [6]
            },
            { 
                orderable: false,
                targets: [6]
            }
        ],
        order: [1, 'asc']
    });


    // Control position
    $('.datatable-responsive-control-right').DataTable({
        responsive: {
            details: {
                type: 'column',
                target: -1
            }
        },
        columnDefs: [
            {
                className: 'control',
                orderable: false,
                targets: -1
            },
            { 
                width: "100px",
                targets: [5]
            },
            { 
                orderable: false,
                targets: [5]
            }
        ]
    });


    // Whole row as a control
    $('.datatable-responsive-row-control').DataTable({
        responsive: {
            details: {
                type: 'column',
                target: 'tr'
            }
        },
        columnDefs: [
            {
                className: 'control',
                orderable: false,
                targets:   0
            },
            { 
                width: "100px",
                targets: [6]
            },
            { 
                orderable: false,
                targets: [6]
            }
        ],
        order: [1, 'asc']
    });



    // External table additions
    // ------------------------------

    // Enable Select2 select for the length option
    $('.dataTables_length select').select2({
        minimumResultsForSearch: Infinity,
        width: 'auto'
    });
    
});

function MQControl(elem) {
	  this.onreload = function() { table.ajax.reload( null, false );}
	  
	  var self = this
	  elem.onclick = function(e) {
	    var target = e && e.target || event.srcElement
	    var action = target.getAttribute('data-action')
	    if (action) {
	      self["on"+action]()
	    }
	  }
	}
	new MQControl(document.getElementById('mqControl'))
