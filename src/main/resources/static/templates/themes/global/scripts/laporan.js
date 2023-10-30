$(document).ready(function () {
    var s = $('#alamat').html();
            s = s.replace(/<p>/g, '');
            s = s.replace(/<\/p>/g, '');
            $('#alamat').html(s);
});

$( document ).ready(function() {
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

var nilaiKontrak = parseInt($('#nilaiKontrak1').html());
    if(isNaN(nilaiKontrak) || nilaiKontrak==undefined || nilaiKontrak==null || nilaiKontrak==0){
        $('#nilaiKontrak2').html('0');
    }else{
        $('#nilaiKontrak2').html(accounting.formatNumber(nilaiKontrak));
    }

var nilaiAdendum1 = parseInt($('#nilaiAdendum1').html());
    if(isNaN(nilaiAdendum1) || nilaiAdendum1==undefined || nilaiAdendum1==null || nilaiAdendum1==0){
        $('#nilaiAdendum2').html('0');
    }else{
        $('#nilaiAdendum2').html(accounting.formatNumber(nilaiAdendum1));
    }
});



function laporanMingguan() {
    var lokasiId = $("#lokasiId").html();
var laporanKe = $("#laporanKe").html();
    location.href = "/admin/master/master_laporan_mingguan/index/" + lokasiId;
}

function printDiv(divName){
    var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}


// Load the Visualization API and the piechart package.
//google.charts.load('current', {'packages':['corechart']});
google.load('visualization', '1', {'packages': ['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
//google.charts.setOnLoadCallback(drawChart);
google.setOnLoadCallback(drawChart);

function drawChart() {
    var lokasiId = $("#lokasiId").html();
var laporanKe = $("#laporanKe").html();
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

//});


