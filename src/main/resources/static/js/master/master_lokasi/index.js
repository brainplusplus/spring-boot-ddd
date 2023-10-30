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
    $("#divFormData").load("/admin/master/master_lokasi_survey/form", function (data) {
        reloadComboGET("kode_prov", "/rest/combobox/cmb_prov", "kode", "nama", "-- Pilih Paket --", function () {
            reloadComboGET("kode_kab", "/rest/combobox/cmb_kab/" + LIST_DATA[id].kodeProv.kode, "kodeKab", "namaKab", "-- Pilih Kabupaten --", function () {
                reloadComboGET("paket", "/rest/combobox/cmb_paket", "id", "namaPaket", "-- Pilih Paket --", function () {
                    reloadComboGET("pengawas", "/rest/combobox/cmb_pengawas", "id", "realName", "-- Pilih Pengawas --", function () {
                        reloadComboGET("rekanan", "/rest/combobox/cmb_rekanan", "id", "realName", "-- Pilih Rekanan --", function () {
                            $("#formData").attr('action', '/rest/master/master_lokasi_survey/update');
                            FormLoadByData(LIST_DATA[id], "formData");
                            LIST_DATA[id].idPaket != null ? $('#paket').val(LIST_DATA[id].idPaket.id) : "";
                            LIST_DATA[id].kodeProv != null ? $('#kode_prov').val(LIST_DATA[id].kodeProv.kode) : "";
                            LIST_DATA[id].kodeKab != null ? $('#kode_kab').val(LIST_DATA[id].kodeKab.kodeKab) : "";
                            LIST_DATA[id].idUserPengawas != null ? $('#pengawas').val(LIST_DATA[id].idUserPengawas) : "";
                            LIST_DATA[id].idUserRekanan != null ? $('#rekanan').val(LIST_DATA[id].idUserRekanan) : "";
                            LIST_DATA[id].id != null ? $('#id').val(LIST_DATA[id].id) : "";
                            if (LIST_DATA[id].kurvaSRencana != null) {
                                $('#kurva_s_rencana').hide();
                                $('#krv').attr('hidden', false);
                                $('#img_krv').attr('src', LIST_DATA[id].kurvaSRencana);
                            } else {
                                $('#krv').hide();
                            }
                            if (LIST_DATA[id].gambarRencana != null) {
                                $('#gambar_rencana').hide();
                                $('#gbr').attr('hidden', false)
                                $('#img_gbr').attr('src', LIST_DATA[id].gambarRencana);
                            } else {
                                $('#gbr').hide();
                            }
                            $('#divListData').fadeOut('fast', function () {
                                $('#divFormData').fadeIn('fast');
                            });
                        });
                    });
                });
            });
        });
    });
}

function getKabupaten() {
    var kodeProvinsi = $("#kode_prov").val();
    reloadComboGET("kode_kab", "/rest/combobox/cmb_kab/" + kodeProvinsi, "kodeKab", "namaKab", "-- Pilih Kabupaten --", function () {});
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
    $.get("/rest/master/master_lokasi_survey/remove/" + id, function (result) {
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
    $("#divFormData").load("/admin/master/master_lokasi_survey/form", function (data) {
        reloadComboGET("kode_prov", "/rest/combobox/cmb_prov", "kode", "nama", "-- Pilih Provinsi --", function () {
            reloadComboGET("paket", "/rest/combobox/cmb_paket", "id", "namaPaket", "-- Pilih Paket --", function () {
                reloadComboGET("pengawas", "/rest/combobox/cmb_pengawas", "id", "realName", "-- Pilih Pengawas --", function () {
                    reloadComboGET("rekanan", "/rest/combobox/cmb_rekanan", "id", "realName", "-- Pilih Rekanan --", function () {
                        $("#formData").attr('action', '/rest/master/master_lokasi_survey/save');
                        $('#divListData').fadeOut('fast', function () {
                            $('#divFormData').fadeIn('fast');
                        });
                    });
                });
            });
        });
    });
}

function laporanMingguan(id) {
    location.href = "/admin/master/master_laporan_mingguan/index/" + id;
}

function gambar(id) {
    location.href = "/admin/master/master_gambar_lokasi/index/" + id;
}

function realisasi(id) {
    window.location.href = "/admin/master/realisasi/index/" + id;
}

function laporanDataUmum(id) {
    window.location.href = "/admin/master/master_laporan_data_umum/index/" + id;
}

var previledge = $('#prev').val();
//onsole.log(previledge);
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
            var tahun = $('#tahun').val();

            $.get('/rest/master/master_lokasi_survey/search', {tahun: tahun, search: nama, rows: data.length, page: page + 1}, function (res) {
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
            {data: "kodeProv",
                "render": function (data, type, row) {
                    return row.kodeProv != null ? row.kodeProv.nama : "-";
                }
            },
            {data: "kodeKab",
                "render": function (data, type, row) {
                    return row.kodeKab != null ? row.kodeKab.namaKab : "-";
                }
            },
            {data: "idPaket",
                "render": function (data, type, row) {
                    return row.idPaket != null ? row.idPaket.namaPaket : "-";
                }
            },
            {data: "namaLokasi"},
            {data: "alamat"},
            {data: "noKontrak"},
            {
                "data": "id",
                "render": function (data, type, row) {
                    var btnAdmin = '' +
                            '<div class="btn-group btn-action-datatable">' +
                            '<a class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" href="javascript:;">' +
                            'Action <i class="fa fa-caret-down"></i>' +
                            '</a>' +
                            '<ul class="dropdown-menu">' +
                            '<li>' +
                            '<a href="javascript:;" onclick="editData(\'' + data + '\')"><i class="glyphicon glyphicon-edit"></i>&nbsp;Sunting</a>' +
                            '</li>' +
                            '<li>' +
                            '<a href="javascript:;" onclick="confirmDelete(\'' + data + '\')"><i class="glyphicon glyphicon-trash"></i>&nbsp;Hapus</a>' +
                            '</li>' +
                            '<li>' +
                            '<a href="javascript:;" onClick="laporanMingguan(\'' + data + '\')"><i class="fa fa-print"></i> Laporan Mingguan</a>' +
                            '</li>' +
                            '<li>' +
                            '<a href="javascript:;" onClick="realisasi(\'' + data + '\')"><i class="fa fa-arrow-right"></i> Realisasi Mingguan</a>' +
                            '</li>' +
                            '<li>' +
                            '<a href="javascript:;" onClick="gambar(\'' + data + '\')"><i class="fa fa-picture-o"></i> Gambar Lokasi</a>' +
                            '</li>' +
                            '<li>' +
                            '<a href="javascript:;" onClick="laporanDataUmum(\'' + data + '\')"><i class="fa fa-database"></i> Laporan Data Umum</a>' +
                            '</li>' +
                            '</ul>' +
                            '</div>';
                    var btnPengawas = '' +
                            '<div class="btn-group btn-action-datatable">' +
                            '<a class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" href="javascript:;">' +
                            'Action <i class="fa fa-caret-down"></i>' +
                            '</a>' +
                            '<ul class="dropdown-menu">' +
                            '<li>' +
                            '<a href="javascript:;" onClick="laporanMingguan(\'' + data + '\')"><i class="fa fa-print"></i> Laporan Mingguan</a>' +
                            '</li>' +
                            '<li>' +
                            '<a href="javascript:;" onClick="realisasi(\'' + data + '\')"><i class="fa fa-arrow-right"></i> Realisasi Mingguan</a>' +
                            '</li>' +
                            '<li>' +
                            '<a href="javascript:;" onClick="gambar(\'' + data + '\')"><i class="fa fa-picture-o"></i> Gambar Lokasi</a>' +
                            '</li>' +
                            '<li>' +
                            '<a href="javascript:;" onClick="laporanDataUmum(\'' + data + '\')"><i class="fa fa-database"></i> Laporan Data Umum</a>' +
                            '</li>' +
                            '</ul>' +
                            '</div>';
                    var btnRekanan = '' +
                            '<div class="btn-group btn-action-datatable">' +
                            '<a class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" href="javascript:;">' +
                            'Action <i class="fa fa-caret-down"></i>' +
                            '</a>' +
                            '<ul class="dropdown-menu">' +
                            '<li>' +
                            '<a href="javascript:;" onClick="laporanMingguan(\'' + data + '\')"><i class="fa fa-print"></i> Laporan Mingguan</a>' +
                            '</li>' +
                            '<li>' +
                            '<li>' +
                            '<a href="javascript:;" onClick="laporanDataUmum(\'' + data + '\')"><i class="fa fa-database"></i> Laporan Data Umum</a>' +
                            '</li>' +
                            '</ul>' +
                            '</div>';
                    if (previledge == "[PENGAWAS]") {
                        btnAction = btnPengawas;
                    } else if (previledge == "[REKANAN]"){
                        btnAction = btnRekanan;
                    } else {
                        btnAction = btnAdmin;
                    }
                    return btnAction;
                }
            },
        ]
    });
}

function hapusGbr() {
    $('#gbr').hide();
    $('#gambar_rencana').show();
}
function hapusKrv() {
    $('#krv').hide();
    $('#kurva_s_rencana').show();
}
/*
 * 
 * Untuk combobox tahun
 */
var min = 2015,
        max = new Date().getFullYear() + 5,
        select = document.getElementById('tahun');

for (var i = min; i <= max; i++) {
    var opt = document.createElement('option');
    opt.value = i;
    opt.innerHTML = i;
    select.appendChild(opt);
}
//////////////////////////////////////////
$('#tahun').on('change', function () {
    reloadGrid();
});
/*]]>*/