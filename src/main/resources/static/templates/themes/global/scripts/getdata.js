

function setCenterPropinsi() {
    //console.log(":masusk center propinsi");
    var bounds = new google.maps.LatLngBounds();
    //deleteMarkers();
    //var id = $("#propinsi").val();
    //console.log(id);
    $.ajax({
        type: "POST",
        dataType: "json",
        url: '/rest/map/get_propinsi',
        data: {
            kode_prov: $("#propinsi").val()
        },
        success: function (data)
        {
            //console.log(data.data.x_coord);
            var items = [];
            var latLng = new google.maps.LatLng(data.data.x_coord, data.data.y_coord);
            //map.setZoom(8);
            //map.setCenter(latLng);	
            bounds.extend(latLng);
//            $.each(data, function (key, val) {
//                console.log(val);
//                
//                var latLng = new google.maps.LatLng(val.x_coord,val.y_coord);
//                //map.setZoom(8);
//                //map.setCenter(latLng);	
//                bounds.extend(latLng);
//            });

        }

    });

    //console.log("masuk cmb kab");
    var kodeProvinsi = $("#propinsi").val();
    reloadComboGET("cmbkabupaten", "/rest/combobox/cmb_kab/" + kodeProvinsi, "kodeKab", "namaKab", "-- Pilih Kabupaten --", function () {});

//		$.ajax({
//		   type: "POST",
//		   dataType: "html",
//		   url: "rest/map/get_kabupaten",
//		   data:  {
//               kode: $("#propinsi").val()
//			  
//            },
//		   success: function(msg){
//			  if(msg == ''){
//					$("select#cmbkabupaten").html('<option value="">--Kabupaten--</option>');
//					
//					
//			  }else{
//			  		$("select#cmbkabupaten").html(msg);						     			
//			  }
//
//			  			  		   			     
//		   }
//		   
//		});	
    deleteMarkers();


    var tahun = $('#tahun_combo').val();
    $.ajax({
        type: "POST",
        dataType: "json",
        url: '/rest/map/kabupaten_marker',
        data: {
            kode_prov: $("#propinsi").val()
//            a: kn,
//            b: kub,
//            c: sehat,
//            d: pump,
//            e: rumah,
//            f: listrik,
//            g: modal,
//            tahun: tahun
        },
        success: function (result)
        {
//            console.log("data kabupaten_marker");
//            console.log(result);
            if (result.isSuccess == true) {
                var items = [];
                $.each(result.data, function (key, val) {
//                    console.log(val);
                    var blueIcon = "http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png";
                    var redIcon = "http://www.google.com/intl/en_us/mapfiles/ms/micons/red-dot.png";
                    var blackIcon = "http://www.google.com/intl/en_us/mapfiles/ms/micons/purple-dot.png";
                    var IconLocation = "/templates/assets/house.png";
                    var dataIcon;
                    if (val.type == 'propinsi') {
//                        console.log("ada data propinsi");
                        dataIcon = blueIcon;
                    }
                    //if(val.type=='kabupaten')dataIcon=redIcon;
                    if (val.type == 'lokasiSurvey') {
//                        console.log("ada data lokasi");
                        dataIcon = IconLocation;
                    }


                    if (val.type == 'lokasiSurvey') {
                         if(val.x_coord!=''){
                        var latLng = new google.maps.LatLng(val.x_coord, val.y_coord);
                        bounds.extend(latLng);
                        if (val.type == 'lokasiSurvey') {
 //                          
                                var marker = new google.maps.Marker({
                                    position: latLng,
                                    map: map,
                                    icon: dataIcon});
                                markers.push(marker);
   //                         
                            if (val.type == 'kabupaten') {
                                $type = 'kabupaten';
                            } else if (val.type == 'propinsi') {
                                $type = 'propinsi';
                            } else if (val.type == 'lokasiSurvey') {
                                $type = 'lokasiSurvey';
                            }

                            bindInfoWindowAjax(val.kode, val.nama, marker, map, infowindow, $type, val.id);
                        }
                        }
                    }
                    //bounds.extend(marker.position);



                    //bindInfoWindow(marker, map, infowindow,html_view);		
                });
            }
            map.fitBounds(bounds);
            map.panToBounds(bounds);
        }
    });


}	 