function setCenterKabupaten() {
    $('#dvLoading').fadeIn(1000);
    var bounds = new google.maps.LatLngBounds();
    $.ajax({
        type: "GET",
        dataType: "json",
        url: '/rest/map/center_kabupaten/' + $("#cmbkabupaten").val(),
        //data: {kode_kab: $("#cmbkabupaten").val()},
        success: function (result)
        {
            var val = result.data;
            var items = [];
            //$.each( result, function( key, val ) {
            if (val.xCoordinate != null) {
                var latLng = new google.maps.LatLng(val.xCoordinate, val.yCoordinate);
                //map.setZoom(11);
                //map.setCenter(latLng);	
                bounds.extend(latLng);
            } else {
                alert('Maaf,kabupaten yang anda pilih titik koordinatnya masih kosong.');
            }

           // });

        }

    });
    
    deleteMarkers();



//    $.ajax({
//        type: "POST",
//        dataType: "html",
//        url: "/rest/map/get_kecamatan",
//        data: {
//            kode_kab: $("#cmbkabupaten").val(),
//        },
//        success: function (msg) {
//            if (msg == '') {
//                $("select#cmbkecamatan").html('<option value="">--Kecamatan--</option>');
//
//
//            } else {
//                $("select#cmbkecamatan").html(msg);
//            }
//
//
//        }
//
//    });



    var tahun = $('#tahun_combo').val();
//    console.log("masuk kecamatan marker");
//    console.log($("#cmbkabupaten").val());
    $.ajax({
        type: "POST",
        dataType: "json",
        url: '/rest/map/kecamatan_marker',
        data: {kode_kab: $("#cmbkabupaten").val()},
        success: function (data)
        {
//            console.log("masuk kecamatan marker success");
//            console.log(data);
            if (data.isSuccess == true) {
                var items = [];
                $.each(data.data, function (key, val) {
                    //console.log(val);
                    var blueIcon = "http://www.google.com/intl/en_us/mapfiles/ms/micons/red-dot.png";
                    var greenIcon = "http://www.google.com/intl/en_us/mapfiles/ms/micons/green-dot.png";
                    var blackIcon = "http://www.google.com/intl/en_us/mapfiles/ms/micons/purple-dot.png";
                    var IconLocation = "/templates/assets/house.png";
                    var dataIcon;
                    if (val.type == 'kabupaten')
                        dataIcon = blueIcon;
                    //if(val.type=='kecamatan')dataIcon=greenIcon;
                    if (val.type == 'lokasiSurvey')
                        dataIcon = IconLocation;

                    //if(val.type=='kecamatan_null')dataIcon=blackIcon;


                    var drilldown = '';
                    var strview = '';
                    var kode = $("#cmbkabupaten").val();
                    if (val.type == 'kabupaten') {
                        strview = 'Kabupaten';
                        drilldown = '<br/><a href="#" onclick="lnkdrilldown_click(\'kabupaten\',\'' + $.trim(kode) + '\')">Drill Down Wilayah</a>';
                    } 
//                    else if (val.type == 'kecamatan') {
//                        strview = 'Kecamatan';
//                        drilldown = '<br/><a href="#" onclick="lnkdrilldown_click(\'kecamatan\',\'' + $.trim(kode) + '\')">Drill Down Wilayah</a>';
//                    } else {
//                        strview = 'Desa';
//                    }

                    if (val.type == 'lokasiSurvey') {
                        var latLng = new google.maps.LatLng(val.x_coord, val.y_coord);
                        bounds.extend(latLng);
                        var marker = new google.maps.Marker({
                            position: latLng,
                            map: map,
                            icon: dataIcon});

                        markers.push(marker);
                        if (val.type == 'lokasiSurvey') {
                            $type = 'lokasiSurvey';
                        }

                        bindInfoWindowAjax(val.kode, val.nama, marker, map, infowindow, val.type, val.id);
                    }
                });
            }
            map.fitBounds(bounds);
            map.panToBounds(bounds);
            $('#dvLoading').fadeOut(2000);
        }
    });
}	 