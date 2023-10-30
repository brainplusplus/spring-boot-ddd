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
	var varId = $("#varId").html();
	$("#divFormData").load("/admin/master/master_laporan_mingguan_images/form/"+varId,function(data){
		$('#laporanId').val(varId);
		$("#formData").attr('action','/rest/master/master_laporan_mingguan_images/update');
		FormLoadByData(LIST_DATA[id],"formData");
		if(LIST_DATA[id].file != null){
			munculGbr();
			$('#img_gbr').attr("src",LIST_DATA[id].file);
		}
		$('#divListData').fadeOut('fast', function(){
	        $('#divFormData').fadeIn('fast');
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
	$.get("/rest/master/master_laporan_mingguan_images/remove/"+id, function (result) {
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
	var varId = $("#varId").html();
	$("#divFormData").load("/admin/master/master_laporan_mingguan_images/form/"+varId,function(data){
		$('#laporanId').val(varId);
		$("#formData").attr('action','/rest/master/master_laporan_mingguan_images/save');
		hapusGbr();
		$('#divListData').fadeOut('fast', function(){
        	$('#divFormData').fadeIn('fast');
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
        file: {
            required: true
        }
    },
    messages: {
        file: {
            required: "Silahkan memasukan Gambar!"
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
	var varId = $("#varId").html();
//	console.log(" var id");
//	console.log(varId);
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
	
	        $.get('/rest/master/master_laporan_mingguan_images/search/'+varId,{search:nama,rows: data.length,page: page + 1},function(res) {
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
				"data": "file",
				"render": function(data, type, row){
//					return "";
					return "<img src='"+data+"' alt='Mountain View' style='width:150px;height:150px;'>";
				} 
			},
                        {data: "keterangan",},
//			{
//                            "data": "keterangan",
//                            "render": function (data, type, row) {
//                                console.log(data);
//                                console.log(row);
//                                if(data){
//                                    return data;
//                                }else{
//                                    return "";
//                                }
//                            }
//                        },
			{
				"data": "id",
				"render": function (data, type, row) {
					var btnAction = '' +
                    '<div class="btn-group btn-action-datatable">' +
                    '<a class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" href="javascript:;">' +
                    'Actions <i class="fa fa-caret-down"></i>' +
                    '</a>' +
                    '<ul class="dropdown-menu">' +
                    '<li>' +
                    '<a href="javascript:;" onClick="editData(\'' + data + '\')">Edit</a>' +
                    '</li>' +
                    '<li>' +
                    '<a href="javascript:;" onClick="confirmDelete(\'' + data + '\')">Delete</a>' +
                    '</li>' +
                    '</ul>' +
                    '</div>';
					
					return btnAction;
				}
			},
		]
	});
}
/*]]>*/