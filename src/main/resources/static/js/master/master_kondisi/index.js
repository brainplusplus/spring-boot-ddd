/*<![CDATA[*/
var LIST_DATA = new Object();
$(function(){
	initGrid();
	$("#divFormData").hide();
});
function reloadGrid(){
    var table = $('#grid').DataTable();

    table.ajax.reload( function ( json ) {

    } );
}

function editData(id){
	$("#divFormData").load("/admin/master/master_kondisi/form",function(data){
			reloadComboGET("jenis_sta","/rest/combobox/jenis_sta","id","nama","-- Pilih Jenis Status--",function(){
				$("#formData").attr('action','/rest/master/master_kondisi/update');
				FormLoadByData(LIST_DATA[id],"formData");
				$('#divListData').fadeOut('fast', function(){
			        $('#divFormData').fadeIn('fast');
			    });	
			});
	});
}
function backToListData(){
	$('#divFormData').fadeOut('fast', function(){
        $('#divListData').fadeIn('fast',function(){
        	reloadGrid();
        });
    });
}
function confirmDelete(id){
    var message = "Do you want to delete this data?";
    showModalWarning(message,confirmDeleteAction,[id]);
}

function confirmDeleteAction(id){
	$.get("/rest/master/master_kondisi/remove/"+id, function (result) {
        result = parseJson(result);
        if(result.isSuccess) {
        	hideAllModal();
        	showModalSuccess("Delete data is successfully !");            
            reloadGrid();   
                    	         
        }else{
            showModalError(result);
        }
    });
}

function gotoNew(){
	$("#divFormData").load("/admin/master/master_kondisi/form",function(data){
		reloadComboGET("jenis_sta","/rest/combobox/jenis_sta","id","nama","-- Pilih Jenis Status--",function(){
			$("#formData").attr('action','/rest/master/master_kondisi/save');
			$('#divListData').fadeOut('fast', function(){
	        	$('#divFormData').fadeIn('fast');
	    	});
		});
	});
}
function showResponseForm(response, statusText, xhr, $form)  {
    response = parseJson(response);
    if(response.isSuccess){
    	showModalSuccess("Save data is successfully !");
    	backToListData();
    }else{
        showModalError(response);
    }
}
function showRequestForm(formData, jqForm, options) {

    return true;
}
var validateForm = {
    rules: {
        nama: {
            required: true
        }
    },
    messages: {
        nama: {
            required: "Silahkan memasukan nama!"
        }
    }
};
var optionsForm = {
    //dataType:  'json',
    beforeSubmit:  showRequestForm,
    success: showResponseForm
};
function submitForm(){
	$("#formData").validate(validateForm);
    $('#formData').ajaxForm(optionsForm);
    $('#formData').submit();
	 
}
var table;
function initGrid(){
	table = $('#grid').DataTable({
	    "responsive": true,
	    "drawCallback": function ( settings ) {
	    	setTimeout(function(){
	    		table.responsive.rebuild();
	    		table.responsive.recalc();
	    	},50);
	    },
	    "processing": true,
	    "serverSide": true,
	    "bFilter": false,
	    "bSort" : false,
	     ajax: function(data,callback, settings) {
	        var page = 0;
	        if (data.start == 0) {
	        } else {
	            page = data.start/data.length;
	        }
	        var nama = $('#searchNama').val();
	
	        $.get('/rest/master/master_kondisi/search',{search:nama,rows: data.length,page: page + 1},function(res) {
	        	LIST_DATA = new Object();
	        	$.each(res.rows, function (keyResult, valueResult) {
	        		LIST_DATA[valueResult.id] = valueResult;
	        	});
	        	callback({
	                    recordsTotal: res.recordsTotal,
	                    recordsFiltered: res.recordsFiltered,
	                    data: res.rows
	                });
	            });
	    },
	
	    "columns": [
			{ 
				"data": "id", 
				"render": function ( data, type, row ) 
							{ 
								return "<a class='btn btn-warning' href='#' onclick=\"editData('"+data+"');\"><i class='fa fa-edit'></i></a> "+
								" <a href='#' class='btn btn-danger' onclick='confirmDelete(\""+data+"\");'><i class='fa fa-trash-o'></i></a>";
							}  
			},        
	        {
	            data: "nama"
	        }
	    ]
	});
}

 ( function( $ ) {
$( document ).ready(function() {
$('#cssmenu > ul > li > a').click(function() {
  $('#cssmenu li').removeClass('active');
  $(this).closest('li').addClass('active');	
  var checkElement = $(this).next();
  if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
    $(this).closest('li').removeClass('active');
    checkElement.slideUp('normal');
  }
  if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
    $('#cssmenu ul ul:visible').slideUp('normal');
    checkElement.slideDown('normal');
  }
  if($(this).closest('li').find('ul').children().length == 0) {
    return true;
  } else {
    return false;	
  }		
});
});
} )( jQuery );
 init.push(function () {
		$('#grid').dataTable();
		$('#jq-datatables-example_wrapper .table-caption').text('Some header text');
		$('#jq-datatables-example_wrapper .dataTables_filter input').attr('placeholder', 'Search...');
	});
/*]]>*/