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

function editData(id) {
    $("#divFormData").load("/admin/master/master_satker/form", function (data) {
        $("#formData").attr('action', '/rest/master/master_satker/update');
        FormLoadByData(LIST_DATA[id], "formData");
        $('#divListData').fadeOut('fast', function () {
            $('#divFormData').fadeIn('fast');
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
    $.get("/rest/master/master_satker/remove/" + id, function (result) {
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

function gotoNew() {
    $("#divFormData").load("/admin/master/master_satker/form", function (data) {
        $("#formData").attr('action', '/rest/master/master_satker/save');
        $('#divListData').fadeOut('fast', function () {
            $('#divFormData').fadeIn('fast');
        });
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
        nama: {required: true},
        kode: {required: true},
        namaKaSatker: {required: true}
    },
    messages: {
        nama: {required: "Silahkan memasukan Data!"},
        kode: {required: "Silahkan memasukan Data!"},
        namaKaSatker: {required: "Silahkan memasukan Data!"},
    }
};
var optionsForm = {
    //dataType:  'json',
    beforeSubmit: showRequestForm,
    success: showResponseForm
};
function submitForm() {
    $("#formData").validate(validateForm);
    $('#formData').ajaxForm(optionsForm);
    $('#formData').submit();
}
var table;
function initGrid() {
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

            $.get('/rest/master/master_satker/search', {
                search: nama,
                rows: data.length,
                page: page + 1
            }, function (res) {
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
            {data: "kodeSatker"},
            {data: "namaSatker"},
            {data: "kepalaSatker"},
            {
                "data": "id",
                "render": function (data, type, row) {
                    return "<a href='#' class='btn btn-default' onclick=\"editData('" + data + "');\"><span title='Ubah' class='glyphicon glyphicon-edit'></span></a> " +
                        "<a href='#' class='btn btn-danger' onclick='confirmDelete(\"" + data + "\");'><span title='Hapus' class='glyphicon glyphicon-trash'></span></a>";
                }
            },
        ]
    });
}
/*]]>*/