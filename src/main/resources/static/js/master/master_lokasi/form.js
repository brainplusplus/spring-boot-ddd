/*<![CDATA[*/

//CKEDITOR.replace('umum_Data');
//
//CKEDITOR.instances['umum_Data'].on('change', function() {$('#dataUmum').val(this.getData());});
//
//function setDataCK(data){
//	CKEDITOR.instances['umum_Data'].setData(data);
//}

var min = new Date().getFullYear(),
        max = min + 10,
        select = document.getElementById('tahun_form');

for (var i =2016; i <= max; i++) {
    var opt = document.createElement('option');
    opt.value = i;
    opt.innerHTML = i;
    select.appendChild(opt);
}

function backToListData() {
    $('#divFormData').fadeOut('fast', function () {
        $('#divListData').fadeIn('fast', function () {
            reloadGrid();
        });
    });
}

function showResponseForm(response, statusText, xhr, $form) {
    if (response.isSuccess) {
        showModalSuccess("Data saved successfully !");
        backToListData();
    } else {
        alert(response);
    }
}
function showRequestForm(formData, jqForm, options) {

    return true;
}

var validateForm = {
    rules: {
        prov_kode: {
            required: true,
        },
        kab_kode: {
            required: true,
        },
        namaLokasi: {
            required: true,
        },
        paket_id: {
            required: true
        },
        tanggalKontrak: {
            required: true
        },
        tanggalAkhirKontrak: {
            required: true
        },
        tanggalSpmk: {
            required: true
        },
        tanggalSurvey: {
            required: true
        },
        tahun: {
            required: true
        }
    },
    messages: {
        prov_kode: {
            required: "Silahkan Pilih Propinsi ! "
        },
        kab_kode: {
            required: "Silakan Pilih Kabupaten ! "
        },
        namaLokasi: {
            required: "Silahkan memasukan Lokasi !"
        },
        paket_id: {
            required: "Silahkan pilih paket !"
        },
        tanggalKontrak: {
            required: "Silahkan masukkan tanggal Kontrak !"
        },
        tanggalAkhirKontrak: {
            required: "Silahkan memasukan Tanggal Akhir Kontrak !"
        },
        tanggalSpmk: {
            required: "Silahkan Masukkan Tanggal SPMK !"
        },
        tanggalSurvey: {
            required: "Silahkan Masukkan Tanggal Survey !"
        },
        tahun: {
            required: "Silahkan Pilih Tahun !"
        }
    },
    errorElement: 'span',
    errorClass: 'help-block help-block-error',
    focusInvalid: true,
    highlight: function (element) {
        $(element).closest('.form-group').addClass('has-error');
    },
    unhighlight: function (element) {
        $(element).closest('.form-group').removeClass('has-error');
    },
    success: function (label) {
        label.closest('.form-group').removeClass('has-error');
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
/*]]>*/