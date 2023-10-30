/*<![CDATA[*/
function printDiv(divName){
    var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}

function lokasiSurvey() {
    location.href = "/admin/master/master_lokasi_survey/index/";
}
/*]]>*/