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
	$("#divFormData").load("/admin/master/master_laporan_mingguan/form/"+varId,function(data){
		$('#lokasiId').val(varId);
		$("#formData").attr('action','/rest/master/master_laporan_mingguan/update');
		FormLoadByData(LIST_DATA[id],"formData");
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
	$.get("/rest/master/master_laporan_mingguan/remove/"+id, function (result) {
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
	$("#divFormData").load("/admin/master/master_laporan_mingguan/form/"+varId,function(data){
		$('#lokasiId').val(varId);
		$("#formData").attr('action','/rest/master/master_laporan_mingguan/save');
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
var previledge = $('#prev').val();
if (previledge == "[REKANAN]"){
    $('#createBtn').hide();
 } else {
    $('#createBtn').show();
 }
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
	
	        $.get('/rest/master/master_laporan_mingguan/search/'+varId,{search:nama,rows: data.length,page: page + 1},function(res) {
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
			
			{data: "laporanPeriodeMingguKe"},
			{
                            "data": "tanggalDari",
                            "render":function(data, type, row) {
                                return tanggalIndonesia(data);
                            }
                        },
			{
                            "data": "tanggalSampai",
                        "render":function(data, type, row) {
                                return tanggalIndonesia(data);
                            }
                        },
			{data: "rencanaProgressMingguIni"},
			{data: "progressMingguIni"},
			{data: "totalSerapan"},
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
                                        '<a href="javascript:;" onClick="editData(\'' + data + '\')"><i class="fa fa-pencil"></i>Edit</a>' +
                                        '</li>' +
                                        '<li>' +
                                        '<a href="javascript:;" onClick="confirmDelete(\'' + data + '\')"><i class="fa fa-trash"></i> Delete</a>' +
                                        '</li>' +
                                        '<li>' +
                                        '<a href="javascript:;" onClick="laporanMingguanImage(\'' + data + '\')"><i class="fa fa-picture-o"></i> Gambar Progress Laporan</a>' +
                                        '</li>' +
                    //                    '<li>' +
                    //                    '<a href="javascript:;" onClick="laporanMingguanTampakImage(\'' + data + '\')"><i class="fa fa-tasks"></i> Gambar Progress Tampak Laporan</a>' +
                    //                    '</li>' +
                                        '<li>' +
                                        '<a href="javascript:;" onClick="laporanMingguanMarking(\'' + data + '\')"><i class="fa fa-check-square"></i> Bukti Lapangan</a>' +
                                        '</li>' +
                                        '<li>' +
                                        '<a href="javascript:;" onClick="laporan(\'' + data + '\')"><i class="fa fa-print"></i> Cetak Laporan</a>' +
                                        '</li>' +
                                        '</ul>' +
                                        '</div>';
                                    var btnActionRekanan = '' +
                                        '<div class="btn-group btn-action-datatable">' +
                                        '<a class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" href="javascript:;">' +
                                        'Actions <i class="fa fa-caret-down"></i>' +
                                        '</a>' +
                                        '<ul class="dropdown-menu">' +
                                        '<li>' +
                                        '<a href="javascript:;" onClick="laporan(\'' + data + '\')"><i class="fa fa-print"></i> Cetak Laporan</a>' +
                                        '</li>' +
                                        '</ul>' +
                                        '</div>';
                                    if (previledge == "[REKANAN]"){
                                        return btnActionRekanan;
                                     } else {
                                         return btnAction;
                                     }
                                    
				}
			},
		]
	});
}

function laporanMingguanImage(id) {
    location.href = "/admin/master/master_laporan_mingguan_images/index/" + id;
}
function laporanMingguanTampakImage(id) {
    location.href = "/admin/master/master_laporan_mingguan_tampak_images/index/" + id;
}
function laporanMingguanMarking(id) {
    location.href = "/admin/master/master_laporan_mingguan_marking/index/" + id;
}
function laporan(id) {
    location.href = "/admin/master/master_laporan_mingguan/laporan/" + id;
}

/*]]>*/