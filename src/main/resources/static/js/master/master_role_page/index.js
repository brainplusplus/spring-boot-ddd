/*<![CDATA[*/
var LIST_DATA = new Object();
$(function () {
    initGrid();
    $("#divFormData").hide();
});

function reloadGrid() {
    var table = $('#grid').DataTable();

    table.ajax.reload(function (json) {

    });
}

function editData() {
	var varId = $("#varId").html();
    $("#divFormData").load("/admin/master/master_role_page/form/"+varId, function (data) {
                $("#formData").attr('action', '/rest/master/master_role_page/save_update/'+varId);
                //FormLoadByDataUsingID(LIST_DATA[id], "formData");
                $.get("/rest/master/master_role_page/get_by_role/" + varId, function (result) {
                    result = parseJson(result);
                    if (result.isSuccess) {
                    	FormLoadByData(result.data,"formData");
                    }
	                $('#divListData').fadeOut('fast', function () {
	                    $('#divFormData').fadeIn('fast');
	                });
                });
    });
}
function backToListData() {
    $('#divFormData').fadeOut('fast', function () {
        $('#divListData').fadeIn('fast', function () {
            reloadGrid();
        });
    });
}
function confirmDelete(id) {
    var message = "Do you want to delete this data?";
    showModalWarning(message, confirmDeleteAction, [id]);
}

function confirmDeleteAction(id) {
    $.get("/rest/master/master_role_page/remove/" + id, function (result) {
        result = parseJson(result);
        if (result.isSuccess) {
            hideAllModal();
            showModalSuccess("Delete data is successfully !");
            reloadGrid();

        } else {
            showModalError(result);
        }
    });
}

function showResponseForm(response, statusText, xhr, $form) {
    response = parseJson(response);
    if (response.isSuccess) {
        showModalSuccess("Save data is successfully !");
        backToListData();
    } else {
        showModalError(response);
    }
}
function showRequestForm(formData, jqForm, options) {

    return true;
}
var validateForm = {
    rules: {
        namaAset: {
            required: true
        },
        id_type_aset: {
            required: true
        },
        id_prov: {
            required: true
//        },
//        id_kab:{
//            required:true
//        },
//        id_kec:{
//            required:true
        }
    },
    messages: {}
};
var optionsForm = {
    //dataType:  'json',
    beforeSubmit: showRequestForm,
    success: showResponseForm
};
function submitForm() {
    //$("#formData").validate(validateForm);
    $('#formData').ajaxForm(optionsForm);
    $('#formData').submit();

}
var table;
function initGrid() {
	var varId = $("#varId").html();
    table = $('#grid').DataTable({
        "responsive": true,
        "drawCallback": function (settings) {
            setTimeout(function () {
                table.responsive.rebuild();
                table.responsive.recalc();
            }, 50);
        },
        "processing": true,
        "serverSide": true,
        "bFilter": false,
        "bSort": false,
        ajax: function (data, callback, settings) {
            var page = 0;
            if (data.start == 0) {
            } else {
                page = data.start / data.length;
            }
            var nama = $('#searchNama').val();

            $.get('/rest/master/master_role_page/search/'+varId, {search: nama, rows: data.length, page: page + 1}, function (res) {
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
                "render": function (data, type, row) {
                    return "<a href='#' onclick=\"confirmDelete('" + data + "');\"><span title='Hapus' class='glyphicon glyphicon-trash'></span></a>";
                }
            },
            {data: "id"},
            {data: "idPage",
                "render": function (data, type, row) {
                    return row.idPage != null ? row.idPage.nama : "";
                }
            },
            {data: "idPage",
                "render": function (data, type, row) {
                    return row.idPage != null ? row.idPage.path : "";
                }
            }
        ]
    });
}
/*]]>*/