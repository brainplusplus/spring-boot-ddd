/*<![CDATA[*/

var comboMingguKe = $('#laporanPeriodeMingguKe');

for(var i=1; i<=60; i++){
	comboMingguKe.append("<option value='"+i+"' > "+i+" </option>");
}

function hitung_realisasi_rencana(){

	var rencana=(parseFloat($("#pekerjaanStrukturRencana").val())+
                parseFloat($("#pekerjaanPersiapanRencana").val())+
                parseFloat($("#pekerjaanBongkaranRencana").val())+
                parseFloat($("#pekerjaanPasangDindingLantaiRencana").val())+
                parseFloat($("#pekerjaanKusenPintuJendelaPenggantungRencana").val())+
                parseFloat($("#pekerjaanAtapPlafoundRencana").val())+
                parseFloat($("#pekerjaanListrikAirSanitairRencana").val())+
                parseFloat($("#pekerjaanPengecetanRencana").val())+
                parseFloat($("#pekerjaanGarasiPosPropostRencana").val())+
                parseFloat($("#pekerjaanPrasaranaRencana").val())+
                parseFloat($("#pekerjaanTanahPondasiBetonRencana").val())+
                parseFloat($("#pekerjaanMenaraRencana").val())+
                parseFloat($("#pekerjaanHrRencana").val())
                );
	var realisasi=(parseFloat($("#pekerjaanStrukturRealisasi").val())+
                parseFloat($("#pekerjaanPersiapanRealisasi").val())+
                parseFloat($("#pekerjaanBongkaranRealisasi").val())+
                parseFloat($("#pekerjaanPasangDindingLantaiRealisasi").val())+
                parseFloat($("#pekerjaanKusenPintuJendelaPenggantungRealisasi").val())+
                parseFloat($("#pekerjaanAtapPlafoundRealisasi").val())+
                parseFloat($("#pekerjaanListrikAirSanitairRealisasi").val())+
                parseFloat($("#pekerjaanPengecetanRealisasi").val())+
                parseFloat($("#pekerjaanGarasiPosPropostRealisasi").val())+
                parseFloat($("#pekerjaanPrasaranaRealisasi").val())+
                parseFloat($("#pekerjaanTanahPondasiBetonRealisasi").val())+
                parseFloat($("#pekerjaanMenaraRealisasi").val())+
                parseFloat($("#pekerjaanHrRealisasi").val())
                );

	$("#rencanaProgressMingguIni").val(rencana);
	$("#progressMingguIni").val(realisasi);

	var deviasi=(parseFloat(realisasi)-parseFloat(rencana));
	$("#deviasi").val(deviasi);

}

$('#rencanaProgressMingguIni').attr('readonly',true);
$('#progressMingguIni').attr('readonly',true);
$('#deviasi').attr('readonly',true);

/*]]>*/