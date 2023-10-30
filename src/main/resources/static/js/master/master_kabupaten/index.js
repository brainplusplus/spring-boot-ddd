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
    $("#divFormData").load("/admin/master/master_kabupaten/form", function (data) {
        loadProvince();
        $("#formData").attr('action', '/rest/master/master_kabupaten/update');
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
    $.get("/rest/master/master_kabupaten/remove/" + id, function (result) {
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
    $("#divFormData").load("/admin/master/master_kabupaten/form", function (data) {
        loadProvince();
        $("#formData").attr('action', '/rest/master/master_kabupaten/save');
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

function loadProvince() {
    window.console.log('hello');
    // load province
    $.ajax({
        url: '/rest/combobox/cmb_prov',
        success: function (data) {
            var comboProvinsi = $('#kode_prov');
            comboProvinsi.empty();
            $.each(data, function (key, value) {
                $("<option />", {
                    val: value.kode,
                    text: value.nama
                }).appendTo(comboProvinsi);
            });
        },
        complete: function () {
        }
    });
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

            $.get('/rest/master/master_kabupaten/search', {
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
            {data: "kodeProv.nama"},
            {data: "kodeKab"},
            {data: "namaKab"},
            {
                "data": "id",
                "render": function (data, type, row) {
                    return "<a href='#' class='btn btn-default' onclick=\"editData('" + data + "');\"><i class=\"glyphicon glyphicon-edit\"></i> Edit</a> " +
                        "<a href='#' class='btn btn-danger' onclick='confirmDelete(\"" + data + "\");'><i class=\"glyphicon glyphicon-trash\"></i> Delete</a>";
                }
            },
        ]
    });
}
/*]]>*/