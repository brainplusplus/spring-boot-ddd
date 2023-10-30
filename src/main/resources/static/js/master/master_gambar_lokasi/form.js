/*<![CDATA[*/

//CKEDITOR.replace('umum_Data');
//
//CKEDITOR.instances['umum_Data'].on('change', function() {$('#dataUmum').val(this.getData());});
//
//function setDataCK(data){
//	CKEDITOR.instances['umum_Data'].setData(data);
//}

function backToListData() {
    $('#divFormData').fadeOut('fast', function () {
        $('#divListData').fadeIn('fast', function () {
            reloadGrid();
        });
    });
}

function showResponseForm(response, statusText, xhr, $form) {
    if (response.isSuccess) {
        alert("Save data is successfully !");
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
        namaFile: {
            required: true,
        },
        file: {
            required: true
        },
        type: {
            required: true
        },
    },
    messages: {
        namaFile: {
            required: "Silahkan memasukan nama!"
        },
        file: {
            required: "Silahkan pilih gambar!"
        },
        type: {
            required: "Silahkan pilih tpe gambar!"
        },
    },
    errorElement: 'span',
    errorClass: 'help-block help-block-error',
    focusInvalid: false,
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