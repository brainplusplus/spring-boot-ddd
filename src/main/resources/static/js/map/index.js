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
    var varId = $("#varId").html();
    $("#divFormData").load("/admin/master/master_gambar_lokasi/form/" + varId, function (data) {
        $('#lokasiId').val(varId);
        $("#formData").attr('action', '/rest/master/master_gambar_lokasi/update');
        FormLoadByData(LIST_DATA[id], "formData");
        if (LIST_DATA[id].file != null) {
            $('#file_gambar').hide();
            $('#gbr').attr('hidden',false);
            $('#img_gambar').attr('src', LIST_DATA[id].file);
        }
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
    $.get("/rest/master/master_gambar_lokasi/remove/" + id, function (result) {
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
    var varId = $("#varId").html();
    $("#divFormData").load("/admin/master/master_gambar_lokasi/form/" + varId, function (data) {
        $('#gbr').hide();
        $('#lokasiId').val(varId);
        $("#formData").attr('action', '/rest/master/master_gambar_lokasi/save');
        $('#divListData').fadeOut('fast', function () {
            $('#divFormData').fadeIn('fast');
        });
    });
}

var table;
function initGrid() {
    var varId = $("#varId").html();
//	console.log(" var id");
//	console.log(varId);
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

            $.get('/rest/master/master_gambar_lokasi/search/' + varId, {search: nama, rows: data.length, page: page + 1}, function (res) {
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
            {data: "namaFile"},
            {
                data: "file",
                "render": function (data, type, row) {
                    return '<img type="file" src="' + data + '" style="width: 150px; height: 150px"/>'
                }
            },
            {data: "keterangan"},
            {
                data: "type",
                "render": function (data, type, row) {
                    if (data == 1) {
                        return "Kondisi Lahan";
                    } else if (data == 2) {
                        return "Kondisi Jalan Akses, Drainase";
                    } else {
                        return "Kondisi sumber air dan listrik"
                    }
                }
            },
            {
                "data": "id",
                "render": function (data, type, row) {
                    return "<a href='#' class='btn btn-sm btn-default update-item' title='Edit Gambar' onclick=\"editData('" + data + "');\"><i class='glyphicon glyphicon-edit'></i></a> " +
                            "<a href='#' class='btn btn-sm btn-danger delete-item' title='Hapus Gambar' onclick='confirmDelete(\"" + data + "\");'><i class='glyphicon glyphicon-trash'></i></a>";
                }
            },
        ]
    });
}

function hapusGbr() {
    $('#gbr').attr('hidden',true);
    $('#file_gambar').show();
}
/*]]>*/