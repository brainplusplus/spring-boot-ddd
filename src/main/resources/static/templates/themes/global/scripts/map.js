//accounting.settings = {
//	currency: {
//		symbol : "",   // default currency symbol is '$'
//		format: "%v", // controls output: %s = symbol, %v = value/number (can be object: see below)
//		decimal : ",",  // decimal point separator
//		thousand: ".",  // thousands separator
//		precision : 3   // decimal places
//	},
//	number: {
//		precision : 0,  // default precision on numbers is 0
//		thousand: ".",
//		decimal : ","
//	}
//}
//
//		function format_money(curr) {
//			if(curr>=1000000000) {
//				return accounting.formatMoney(curr/1000000000)+" M";
//			}
//			else {
//				return accounting.formatMoney(curr)+" Jt";
//			}
//		}
function lnkdrilldown_click(otype, okode) {
    if (otype == 'propinsi') {
        $('#propinsi').val(okode);
        $('#propinsi').change();
    } else if (otype == 'kabupaten') {
        $('#cmbkabupaten').val(okode);
        $('#cmbkabupaten').change();
    } 
//    else if (otype == 'kecamatan') {
//        $('#cmbkecamatan').val(okode);
//        $('#cmbkecamatan').change();
//    }
}

reloadComboGET("propinsi", "/rest/combobox/cmb_prov", "kode", "nama", "-- Pilih Propinsi --", function () {

});
var map;
var markers = [];
function initialize() {
    var mapDiv = document.getElementById('map-canvas');
    var bounds = new google.maps.LatLngBounds();
    map = new google.maps.Map(mapDiv, {
        center: new google.maps.LatLng(-0.263671, 114.419861),
        zoom: 5,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var no = 0;
    infowindow = new google.maps.InfoWindow({maxWidth: 520});

    $.ajax({
        type: "POST",
        dataType: "json",
        url: '/rest/map/propinsi_marker',
        success: function (data)
        {
            //console.log("propinsi marker");
            //console.log(data);
            var items = [];
            $.each(data.data, function (key, val) {

                var blueIcon = "http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png";

                var dataIcon;
                dataIcon = blueIcon;
                var latLng = new google.maps.LatLng(val.xcoord, val.ycoord);
                bounds.extend(latLng);
                /*var marker = new google.maps.Marker({
                 position: latLng,
                 map: map,
                 icon:dataIcon});
                 markers.push(marker);*/


                //}
                //bindInfoWindowAjax(val.kode,val.nama_propinsi,marker, map, infowindow,'propinsi',val.id);		
            });
            map.fitBounds(bounds);
            map.panToBounds(bounds);

        }

    });

}


function bindInfoWindowAjax(kode, nama_propinsi, marker, map, infowindow, type, id) {
    google.maps.event.addListener(marker, 'click', function () {
//        console.log("detail icon");
//        console.log(type);
//        console.log(id);
        var url_data;
        url_data = '/rest/map/get_count_data';
        var tahun = $('#tahun_combo').val();
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url_data,
            data: {
                type: type,
                id: id
            },
            success: function (result)
            {
//                console.log("data getcount data");
//                console.log(result);
                //$.each(data, function (key, val) {
//                    console.log(result);
                    if (type != 'kabupaten_null') {

                    var val = result.data;
                        var drilldown = '';
                        var strview = '';
                        if (type == 'propinsi') {
                            strview = 'Propinsi';
                            data = '';
                            drilldown = '<br/><a href="#" onclick="lnkdrilldown_click(\'propinsi\',\'' + $.trim(kode) + '\')">Drill Down Wilayah</a> ';
                        } else if (type == 'kabupaten') {
                            strview = 'Kabupaten';
                            data = '';
                            drilldown = '<br/><a href="#" onclick="lnkdrilldown_click(\'kabupaten\',\'' + $.trim(kode) + '\')">Drill Down Wilayah</a>';
                        } else if (type == 'kecamatan') {
                            strview = 'Kecamatan';
                            data = '';
                            drilldown = '<br/><a href="#" onclick="lnkdrilldown_click(\'kecamatan\',\'' + $.trim(kode) + '\')">Drill Down Wilayah</a>';
                        } else if (type == 'lokasiSurvey') {
                            strview = 'Lokasi';
                            //data='<tr><td>Nama Surveyor</td><td>:</td><td>'+val.nama_surveyor+'</td></tr><tr><td>Nama Konsultan MK</td><td>:</td><td>'+val.nama_konsultan_mk+'</td></tr><tr><td>Tanggal Survey</td><td>:</td><td>'+val.tanggal_survey+'</td></tr><tr><td>Nama Lembaga Pengusul</td><td>:</td><td>'+val.nama_lembaga_pengusul+'</td></tr><tr><td>Kontak Person</td><td>:</td><td>'+val.kontak_person+'</td></tr><tr><td>Target Grup</td><td>:</td><td>'+val.target_grup+'</td></tr><tr><td>Jml Tb</td><td>:</td><td>'+val.jml_tb+'</td></tr><tr><td>Lebar Jalan Kondisi Akses</td><td>:</td><td>'+val.lebar_jalan_kondisi_akses+'</td></tr><tr><td>Sumber LIstrik Jalan Lokasi</td><td>:</td><td>'+val.sumber_listrik_jarak_lokasi+'</td></tr><tr><td>Sumber Listrik Jarak Lokasi</td><td>:</td><td>'+val.sumber_listrik_jarak_lokasi+'</td></tr><tr><td>Sumber Air Jarak Lokasi</td><td>:</td><td>'+val.sumber_air_jarak_lokasi+'</td></tr><tr><td>Jarak Dengan Pusat Kegiatan</td><td>:</td><td>'+val.jarak_dengan_pusat_kegiatan+'</td></tr><tr><td>Lusa Lahan Usulan</td><td>:</td><td>'+val.luas_lahan_usulan+'</td></tr><tr><td>Status Tanah</td><td>:</td><td>'+val.status_tanah+'</td></tr><tr><td>Jenis Tanah</td><td>:</td><td>'+val.jenis_tanah+'</td></tr><tr><td>Kondisi Fisik</td><td>:</td><td>'+val.kondisi_fisik+'</td></tr><tr><td colspan="3">Kondisi Lahan</td></tr><tr><td colspan="3">'+val.foto+'</td></tr><tr><td colspan="3">Kondisi jalan akses,drainase</td></tr><tr><td colspan="3">'+val.foto2+'</td></tr><tr><td colspan="3">Kondisi sumber air dan listrik </td></tr><tr><td colspan="3">'+val.foto3+'</td></tr><tr><td>Catatan Khusus</td><td>:</td><td>'+val.keterangan+'</td></tr><tr><td>Rekomendasi</td><td>:</td><td>'+val.rekomendasi+'</td></tr><tr><td colspan="3"><b>Progress Pekerjaan</b></td><td></td><td></td></tr><tr><td>Rencana</td><td>:</td><td>'+val.rencana+' %</td></tr><tr><td>Realisasi</td><td>:</td><td>'+val.realisasi+' %</td></tr><tr><td>Pekerjaan Tambah kurang</td><td></td><td></td></tr><tr><td>Pekerjaan Minggu Ini</td><td>:</td><td>'+val.pekerjaan_minggu_ini+' </td></tr><tr><td>Hambatan</td><td>:</td><td>'+val.permasalahan_hambatan+'</td></tr><tr><td colspan="3">'+val.link_laporan+'</td></tr><tr><td colspan="4"><b>Foto Progress</b></td></tr><tr><td colspan="4">'+val.foto_progress+'</td></tr><tr><td colspan="4">'+val.foto_progress2+'</td></tr>';	
                            data = '<tr><td colspan="3"><b>Progress Pekerjaan</b></td><td></td><td></td></tr><tr><td>Rencana</td><td>:</td><td>' + val.rencana + ' %</td></tr><tr><td>Realisasi</td><td>:</td><td>' + val.realisasi + ' %</td></tr><tr><td>Deviasi</td><td>:</td><td>' + val.deviasi + ' %</td></tr><tr><td>Pekerjaan Tambah kurang</td><td></td><td></td></tr><tr><td>Pekerjaan Minggu Ini</td><td>:</td><td>' + val.pekerjaan_minggu_ini + ' </td></tr><tr><td>Hambatan</td><td>:</td><td>' + val.permasalahan_hambatan + '</td></tr><tr><td colspan="3">' + val.link_laporan + '</td></tr><tr><td colspan="4"><b>Foto Progress</b></td></tr><tr><td colspan="4">' + val.foto_progress + '</td></tr>';
                            drilldown = '';
                        } else
                            strview = 'Desa';
                        //"<td>KN Null Lokasi</td><td>:</td><td>.'+val.get_kn_null+'</td>"
                        var strDescription = '<table><tr><td colspan="3"><b>Data Umum</b></td></tr><tr style="width:180px;"><td>' + strview + '</td><td>:</td><td>' + val.nama_lokasi + '</td></tr><tr><td>Alamat</td><td>:</td><td>' + val.alamat + '</td></tr><tr><td>Kontraktor</td><td>:</td><td>' + val.kontraktor_pelaksana + '</td></tr><tr><td colspan="3"><a href="/admin/master/master_laporan_data_umum/index/'+val.id_lokasi+'" target=_blank>Detail Data Umum</a></td></tr>' + data + '<tr style="background-color:#ECECFF"></tr></table>' + drilldown;
                    } else {
                        var strDescription = val.nama;
                    }

                    infowindow.setContent(strDescription);
                    infowindow.open(map, marker);
               // });
            }


        });
    });
    google.maps.event.addListener(marker, 'click', function () {
        // event click
    });
}





function bindInfoWindow(marker, map, infowindow, strDescription) {
    google.maps.event.addListener(marker, 'click', function () {
        infowindow.setContent(strDescription);
        infowindow.open(map, marker);
    });
}

function deleteMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

google.maps.event.addDomListener(window, 'load', initialize);

$(document).ajaxStart(function () {
    $("#loading").show();
});
$(document).ajaxStop(function () {
    $("#loading").hide();
});
