$(function () {
    //Metis.MetisTable();
    // Metis.metisSortable();
    //Metis.formGeneral();
});
var li_policy_enquiry = "<li>Policy Enquiries:<br /><span><ul><li>Policy Information</li><li>Product Plan</li><li>Benefit Schedule</li></ul></span></li>";
var li_member_enquiry = "<li>Member Enquiries:<br><span><ul><li>Member Information</li><li>Benefit Information</li></ul></span></li>";
var li_claims_enquiry = "<li>Claims Enquiries:<br><span><ul><li>Claims Summary</li><li>Claims Assessment</li><li>Payment Details</li></ul></span></li>";
var li_benacc_enquiry = "<li>Benefit Accumulation<br/><br/></li>";
var li_report_enquiry = "<li>Reports:<br/><span><ul><li>Claim Details</li><li>Claims Rejected/Returned</li><li>Excess Claim Report</li><li>Active Member List</li><li>Reimbursement Claims Payment</li><li>Top Ten Providers by Product by Number of Cases</li><li>Top Ten Claimants by Product by Number of Cases</li><li>Top Ten Diagnosis by Product (All Members, Employee Only, Dependant Only) by Number of Cases</li><li>Top Ten Providers by Product by Paid Amount</li><li>Top Ten Claimants by Product by Paid Amount</li><li>Top Ten Diagnosis by Product (All Members, Employee Only, Dependant Only) by Paid Amount</li></ul></span></li>";
var li_upload_enquiry = "<li>Upload Member Data Alteration</li>";
function loadListMenu() {

}

var CALLBACK;
var ARGS;
function callFunction() {
    if (typeof ARGS == "undefined" || ARGS == null)
        CALLBACK();
    else
        CALLBACK.apply(this, ARGS);
}
function generalFunction(callback, args) {
    if (typeof args == "undefined" || args == null)
        callback();
    else
        callback.apply(this, args);
}

function showModalError(message) {
    $("#errorMessage").text(message);
    $('#modalError').modal('show');
}

function showModalWarning(message, func, args) {
    $("#warningMessage").text(message);
    $("#warningBtn").unbind("click");
    var callback = function () {
        generalFunction(func, args)
    };
    $("#warningBtn").bind("click", callback);
    $('#modalWarning').modal('show');
}

function showModalSuccess(message) {
    $("#successMessage").text(message);
    $('#modalSuccess').modal('show');
}

function showModalLoading() {
    $("#modalLoading").modal('show');
}

function hideAllModal() {
    $('#modalError').modal('hide');
    $('#modalWarning').modal('hide');
    $('#modalSuccess').modal('hide');
    $("#modalLoading").modal('hide');
    $(".modal-backdrop").remove();
}
function reloadComboGET(id, url, value, text, defaultSelect, callback) {
    var cbx = "";
    var v = "";
    var t = "";
    if (defaultSelect != null && typeof defaultSelect != "undefined")
        cbx += "<option value=''>" + defaultSelect + "</option>";
    $.get(url, function (result) {
        result = parseJson(result);
        $.each(result, function (keyResult, valueResult) {
            v = "";
            t = "";
            $.each(valueResult, function (keySingle, valueSingle) {
                if (keySingle == value) {
                    v = valueSingle;
                }
                if (keySingle == text) {
                    t = valueSingle;
                }
            });
            cbx += "<option value='" + v + "'>" + t + "</option>";
        });
        $("#" + id).html(cbx);
        if (callback != null && typeof callback != "undefined")
            callback();
    });

}
//$(document).ready(function () {
//    /*
//
//     $.active = false;
//     $('body').bind('click keypress', function() {
//     $.active = true;
//     });
//     checkActivity(1800000, 60000, 0); // timeout = 30 minutes  */
//    $(document).on("ajaxStart", function () {
//        showModalLoading();
//    }).on("ajaxStop", function () {
//        $("#modalLoading").modal('hide');
//    }).on("ajaxError", function (event, jqxhr, settings, thrownError) {
//        hideAllModal();
//        //showModalError(jqxhr.responseText);
//        window.console.log(jqxhr);
//    });
//    $('.tgl').datepicker({'format': "dd/mm/yyyy"});
//    $(".tahun").inputmask("y", {
//            alias: "date",
//            placeholder: "yyyy",
//            yearrange: {minyear: 1900, maxyear: 2100}
//        }
//    );
//    setInterval(function () {
//        checkLogin();
//    }, 15 * 60 * 1000);
//    //loadListMenu();
//});
function checkLogin() {
    /*$.get("${urlApp}/checkSession",function(resultSession){
     resultSession = parseJson(resultSession);
     if(!resultSession.isLogin){
     alert("Session login anda sudah habis ! silahkan login lagi");
     window.location.href="${urlApp}/login";
     }
     });*/
}
function checkActivity(timeout, interval, elapsed) {
    if ($.active) {
        elapsed = 0;
        $.active = false;
        $.get('checkEBOnlineSession');
    }
    if (elapsed < timeout) {
        elapsed += interval;
        setTimeout(function () {
            checkActivity(timeout, interval, elapsed);
        }, interval);
    } else {
        window.location.href = 'login'; // Redirect to login page
    }
}

function parseJson(data) {
    if (typeof data == "string")
        data = jQuery.parseJSON(data);

    return data;
}
function FormLoadByUrlJson(url, formid) {
    $.get(url, function (data) {

        $.each(data, function (key, value) {
            //window.console.log(key+' '+ value);
            if ($('#' + formid + ' [name="' + key + '"]').attr('data-role') == "datepicker") {
                //window.console.log('1');
                $('#' + formid + ' [name="' + key + '"]').val(value);
            }
            else if ($('#' + formid + ' [name="' + key + '"]').attr('data-role') == "combobox") {
                //window.console.log('2');
                $('#' + formid + ' [name="' + key + '"]').val(value);
            }
            else if ($('#' + formid + ' [name="' + key + '"]').attr('data-role') == "numeric") {
                //window.console.log('3');
                $('#' + formid + ' [name="' + key + '"]').val(value);
            }
            else if ($('#' + formid + ' [name="' + key + '"]').attr('data-role') == "checkbox") {
                //window.console.log('3');
                if (value == "1" || value == 1)
                    $('#' + formid + ' [name="' + key + '"]').prop('checked', true);
                else
                    $('#' + formid + ' [name="' + key + '"]').removeAttr('checked');
            }
            else {
                //window.console.log('4');
                $('#' + formid + ' [name="' + key + '"]').val(value);
            }
        });
    }, 'json');
}

function FormLoadByData(data, formid) {
    $.each(data, function (key, value) {
        //console.log(key+' '+ value);
        if ($('#' + formid + ' [name="' + key + '"]').attr('data-role') == "datepicker") {
            //window.console.log('1');
            $('#' + formid + ' [name="' + key + '"]').val(value);
        }
        else if ($('#' + formid + ' [name="' + key + '"]').attr('data-role') == "combobox") {
            //window.console.log('2');
            $('#' + formid + ' [name="' + key + '"]').val(value);
        }
        else if ($('#' + formid + ' [name="' + key + '"]').attr('data-role') == "numeric") {
            //window.console.log('3');
            $('#' + formid + ' [name="' + key + '"]').val(value);
        }
        else if ($('#' + formid + ' [name="' + key + '"]').attr('data-role') == "checkbox") {
            //window.console.log('3');
        	if (value == "1" || value == 1 || value == "true" || value ==  true)
                $('#' + formid + ' [name="' + key + '"]').prop('checked', true);
            else
                $('#' + formid + ' [name="' + key + '"]').removeAttr('checked');
        }
        else {
            //window.console.log('4');
            $('#' + formid + ' [name="' + key + '"]').val(value);
        }
    });
}
function FormLoadByDataUsingID(data, formid) {
    $.each(data, function (key, value) {
        //console.log(key+' '+ value);
        if ($('#' + formid + ' [id="' + key + '"]').attr('data-role') == "datepicker") {
            //window.console.log('1');
            $('#' + formid + ' [id="' + key + '"]').val(value);
        }
        else if ($('#' + formid + ' [id="' + key + '"]').attr('data-role') == "combobox") {
            //window.console.log('2');
            $('#' + formid + ' [id="' + key + '"]').val(value);
        }
        else if ($('#' + formid + ' [id="' + key + '"]').attr('data-role') == "numeric") {
            //window.console.log('3');
            $('#' + formid + ' [id="' + key + '"]').val(value);
        }
        else if ($('#' + formid + ' [id="' + key + '"]').attr('data-role') == "checkbox") {
            //window.console.log('3');
            if (value == "1" || value == 1 || value == "true" || value ==  true)
                $('#' + formid + ' [id="' + key + '"]').prop('checked', true);
            else
                $('#' + formid + ' [id="' + key + '"]').removeAttr('checked');
        }
        else {
            //window.console.log('4');
            $('#' + formid + ' [id="' + key + '"]').val(value);
        }
    });
}