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
    $("#divFormData").load("/admin/master/master_user/form", function (data) {
        reloadComboGET("role_combo", "/rest/combobox/cmb_role", "id", "nama", "-- Pilih Role --", function () {
            reloadComboGET("wilayah_combo", "/rest/combobox/cmb_wilayah", "id", "namaWilayah", "-- Pilih Wilayah --", function () {
                FormLoadByData(LIST_DATA[id], "formData");
                LIST_DATA[id].role != null ? $('#role_combo').val(LIST_DATA[id].role.id) : "";
                LIST_DATA[id].idWilayah != null ? $('#wilayah_combo').val(LIST_DATA[id].idWilayah.id) : "";
                $('#active').val(LIST_DATA[id].active.toString());
                if (LIST_DATA[id].logo != null) {
                    $('#logo').hide();
                    $('#gbr').attr('hidden',false);
                    $('#img_gambar').attr('src', LIST_DATA[id].logo);
                }
                $("#formData").attr('action', '/rest/master/master_user/update');
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
    $.get("/rest/master/master_user/remove/" + id, function (result) {
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
//	CKEDITOR.replace( 'dataUmum', {
////	    customConfig: '/config.js'
//	});
    $("#divFormData").load("/admin/master/master_user/form", function (data) {
        reloadComboGET("role_combo", "/rest/combobox/cmb_role", "id", "nama", "-- Pilih Role --", function () {
            reloadComboGET("wilayah_combo", "/rest/combobox/cmb_wilayah", "id", "namaWilayah", "-- Pilih Wilayah --", function () {
                $("#formData").attr('action', '/rest/master/master_user/save');
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
        username: {
            required: true
        },
        password: {
            required: true
        },
        realName: {
            required: true
        },
        role_combo: {
            required: true
        }
    },
    messages: {},
    errorElement: 'span',
    errorClass: 'help-block help-block-error',
    focusInvalid: false,
    highlight: function (element) {
        $(element).closest('.form-group').addClass('has-error');
    },
    unhighlight: function (element) {
        $(element).closest('.form-group').removeClass('has-error');
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

            $.get('/rest/master/master_user/search', {search: nama, rows: data.length, page: page + 1}, function (res) {
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
            {data: "username"},
            {data: "realName"},
            {data: "telepon"},
            {data: "role",
                "render": function (data, type, row) {
                    return row.role != null ? row.role.nama : "";
                }
            },
            {data: "idWilayah",
                "render": function (data, type, row) {
                    return row.idWilayah != null ? row.idWilayah.namaWilayah : "-";
                }
            },
            {data: "active",
                "render": function (data, type, row) {
                    if (data == 0) {
                        return "Tidak Aktif";
                    } else {
                        return "Aktif";
                    }
                }
            },
            {
                "data": "id",
                "render": function (data, type, row) {
                    return "<a href='#' class='btn btn-default' onclick=\"editData('" + data + "');\"><span title='Ubah' class='glyphicon glyphicon-edit'></span></a> " +
                            " <a href='#' class='btn btn-danger' onclick=\"confirmDelete('" + data + "');\"><span title='Hapus' class='glyphicon glyphicon-trash'></span></a>";
                }
            },
        ]
    });
}

function hapusGbr() {
    $('#gbr').attr('hidden',true);
    $('#logo').show();
}
/*]]>*/