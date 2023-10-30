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
    $("#divFormData").load("/admin/master/master_rel_satker_ppk/form", function (data) {
        reloadComboGET("kode_satker", "/rest/combobox/cmb_satker", "id", "namaSatker", "-- Pilih Satker --", function () {
            reloadComboGET("kode_user", "/rest/combobox/cmb_user", "id", "realName", "-- Pilih PPK --", function () {
                $("#formData").attr('action', '/rest/master/master_rel_satker_ppk/update');
                FormLoadByData(LIST_DATA[id], "formData");
                LIST_DATA[id].idSatker != null ? $('#kode_satker').val(LIST_DATA[id].idSatker.id) : "";
                LIST_DATA[id].idUser != null ? $('#kode_user').val(LIST_DATA[id].idUser.id) : "";
                $('#divListData').fadeOut('fast', function () {
                    $('#divFormData').fadeIn('fast');
                });
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
    $.get("/rest/master/master_rel_satker_ppk/remove/" + id, function (result) {
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
    $("#divFormData").load("/admin/master/master_rel_satker_ppk/form", function (data) {
        reloadComboGET("kode_satker", "/rest/combobox/cmb_satker", "id", "namaSatker", "-- Pilih Satker --", function () {
            reloadComboGET("kode_user", "/rest/combobox/cmb_user", "id", "realName", "-- Pilih PPK --", function () {
                $("#formData").attr('action', '/rest/master/master_rel_satker_ppk/save');
                $('#divListData').fadeOut('fast', function () {
                    $('#divFormData').fadeIn('fast');
                });
            });
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
        satker_id: {required: true},
        user_id: {required: true},
    },
    messages: {
        satker_id: {required: "Silahkan pilih Data!"},
        user_id: {required: "Silahkan pilih Data!"},
    },
    errorElement: 'span',
    errorClass: 'help-block help-block-error',
    focusInvalid: false,
    highlight: function (element) {
        $(element).closest('.form-group').addClass('has-error');
    },
    success: function (label) {
        label.closest('.form-group').removeClass('has-error');
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

            $.get('/rest/master/master_rel_satker_ppk/search', {
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
            {
                data: "idSatker",
                "render": function (data, type, row) {
                    return row.idSatker.namaSatker;
                }
            },
            {
                data: "idUser",
                "render": function (data, type, row) {
                    return row.idUser.realName;
                }
            },
            {
                "data": "id",
                "render": function (data, type, row) {
                    return "<a href='#' onclick=\"editData('" + data + "');\"><span title='Ubah' class='glyphicon glyphicon-edit'></span></a> " +
                            " || <a href='#' onclick='confirmDelete(\"" + data + "\");'><span title='Hapus' class='glyphicon glyphicon-trash'></span></a>";
                }
            },
        ]
    });
}
/*]]>*/