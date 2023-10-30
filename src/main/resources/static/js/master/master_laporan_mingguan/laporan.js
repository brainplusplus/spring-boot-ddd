/*<![CDATA[*/

var lokasiId = $("#lokasiId").html();
var laporanKe = $("#laporanKe").html();

function printDiv(divName){
    var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}

function laporanMingguan() {
    location.href = "/admin/master/master_laporan_mingguan/index/" + lokasiId;
}

var rencana=(parseFloat($("#pekerjaanPersiapanRencana").html())+
        parseFloat($("#pekerjaanBongkaranRencana").html())+
        parseFloat($("#pekerjaanPasangDindingLantaiRencana").html())+
        parseFloat($("#pekerjaanKusenPintuJendelaPenggantungRencana").html())+
        parseFloat($("#pekerjaanAtapPlafoundRencana").html())+
        parseFloat($("#pekerjaanListrikAirSanitairRencana").html())+
        parseFloat($("#pekerjaanPengecetanRencana").html())+
        parseFloat($("#pekerjaanGarasiPosPropostRencana").html())+
        parseFloat($("#pekerjaanPrasaranaRencana").html())+
        parseFloat($("#pekerjaanTanahPondasiBetonRencana").html())+
        parseFloat($("#pekerjaanMenaraRencana").html())+
        parseFloat($("#pekerjaanHrRencana").html())
        );
var realisasi=(parseFloat($("#pekerjaanPersiapanRealisasi").html())+
        parseFloat($("#pekerjaanBongkaranRealisasi").html())+
        parseFloat($("#pekerjaanPasangDindingLantaiRealisasi").html())+
        parseFloat($("#pekerjaanKusenPintuJendelaPenggantungRealisasi").html())+
        parseFloat($("#pekerjaanAtapPlafoundRealisasi").html())+
        parseFloat($("#pekerjaanListrikAirSanitairRealisasi").html())+
        parseFloat($("#pekerjaanPengecetanRealisasi").html())+
        parseFloat($("#pekerjaanGarasiPosPropostRealisasi").html())+
        parseFloat($("#pekerjaanPrasaranaRealisasi").html())+
        parseFloat($("#pekerjaanTanahPondasiBetonRealisasi").html())+
        parseFloat($("#pekerjaanMenaraRealisasi").html())+
        parseFloat($("#pekerjaanHrRealisasi").html())
        );


var jangkaWaktuPelaksanaan = $('#jangkaWaktuPelaksanaan').html();
var jangka =0;
if((jangkaWaktuPelaksanaan % 7)<5){
    jangka = Math.round((jangkaWaktuPelaksanaan / 7)) +1;
}else{
    jangka = Math.round(jangkaWaktuPelaksanaan / 7);
}
$('#jangkaWaktuPelaksanaanMinggu').html(jangka);

var waktuPelaksanaan = $('#waktuPelaksanaanMinggu').html();
$('#waktuPelaksanaan').html(waktuPelaksanaan*7);

$('#sisaWaktuPelaksanaanMinggu').html(jangka-waktuPelaksanaan);
$('#sisaWaktuPelaksanaan').html(jangkaWaktuPelaksanaan-(waktuPelaksanaan*7));



// Load the Visualization API and the piechart package.
//google.charts.load('current', {'packages':['corechart']});
google.load('visualization', '1', {'packages': ['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
//google.charts.setOnLoadCallback(drawChart);
google.setOnLoadCallback(drawChart);

function drawChart() {
    $.get("/rest/master/master_laporan_mingguan/ajax/"+lokasiId+"/"+laporanKe,function(res) {
        var data = new google.visualization.DataTable();

        data.addColumn('number', 'Laporan Periode Minggu Ke');
        data.addColumn('number', 'Rencana Progress Minggu Ini');
        data.addColumn('number', 'Realisasi Progress Minggu Ini');
        var arr = new Array();
        var i = 0;
        arr=[[0,0,0]];
        
        $.each(res.rows, function(a,b){
            i++;
            arr.push([i,parseFloat(b[1]),parseFloat(b[2])]);
        });
        data.addRows(arr);
        var options = {"title": "Kurva S"};
        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.LineChart(document.getElementById('stock_div'));
        chart.draw(data,options);
    });

}
//google.charts.load('current', {'packages':['corechart']});
/*]]>*/