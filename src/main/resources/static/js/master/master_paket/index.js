/**
 * Handle master paket javascript interaction to service rest, etc etc etc...
 *
 * @returns {{init: init}}
 */

var MasterPaketScript = function () {

    /**
     * Constant Variables
     * @type {string}
     */
    var searchList = '/rest/master/master_paket/search';
    var formApiEndPoint = '/admin/master/master_paket/form';
    var formCreateApiEndPoint = '/rest/master/master_paket/save';
    var formUpdateApiEndPoint = '/rest/master/master_paket/update';

    /**
     * Return URL API End-point of delete master paket
     * @param id
     * @returns {string}
     */
    var deleteList = function (id) {
        return '/rest/master/master_paket/remove/' + id;
    };

    /**
     * Initialize Data Table Grid
     * @param gridId
     * @param searchElementId
     */
    var initDataTableGrid = function (gridId, searchElementId, tahunElementId) {
        var targetGrid = $('#' + gridId);
        targetGrid.DataTable({
            "responsive": true,
            "drawCallback": function (settings) {
                setTimeout(function () {
                    //targetGrid.responsive.rebuild();
                    table.responsive.recalc();
                }, 50);
            },
            "processing": true,
            "serverSide": true,
            "bFilter": false,
            "bSort": false,
            "sScrollX": "80%",
            "sScrollXInner": "110%",
            "bScrollCollapse": true,
            ajax: function (data, callback, settings) {
                var page = 0;
                if (data.start == 0) {
                } else {
                    page = data.start / data.length;
                }
                var searchValue = $('#' + searchElementId).val();
                var tahunValue = $('#' + tahunElementId).val();
                $.get(searchList, {search: searchValue, tahun: tahunValue, rows: data.length, page: page + 1}, function (res) {
                    LIST_DATA = new Object();
                    $.each(res.rows, function (keyResult, valueResult) {
                        LIST_DATA[valueResult.id] = valueResult;
                    });
                    callback({
                        recordsTotal: res.recordsTotal,
                        recordsFiltered: res.recordsFiltered,
                        data: res.rows
                    });
                });
            },
            "columns": [
                {data: "kodePaket"},
                {data: "namaPaket"},
                {data: "deskripsi"},
                {data: "tahun"},
                {
                    "data": "id",
                    "render": function (data, type, row) {
                        return '<div class="btn-group btn-action-datatable">' +
                                '<a class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" href="javascript:;">' +
                                'Actions <i class="fa fa-caret-down"></i>' +
                                '</a>' +
                                '<ul class="dropdown-menu">' +
                                '<li>' +
                                '<a href="javascript:;" class="update-item" data-key-id="' +
                                data + '"><i class="glyphicon glyphicon-edit"></i> Update </a>' +
                                '</li>' +
                                '<li>' +
                                '<a href="javascript:;" class="delete-item" data-key-id="' +
                                data + '"><i class="glyphicon glyphicon-trash"></i> Delete </a>' +
                                '</li>' +
                                '</ul>' +
                                '</div>';
                    }
                }
            ]
        });

        // call delete item on grid data
        $(document).on('click', '.delete-item', function (event) {
            event.preventDefault();
            var itemId = $(this).data('key-id');
            var message = "Do you want to delete this data?";
            showModalWarning(message, performDeleteItem, [itemId]);
        });

        // call update item on grid data
        $(document).on('click', '.update-item', function (event) {
            event.preventDefault();
            var itemId = $(this).data('key-id');

            performUpdateItem(itemId);
        });
    };

    /**
     * Relad DataTable Grid
     * @param gridId
     */
    var reloadDataTableGrid = function (gridId) {
        var table = $('#' + gridId).DataTable();

        table.ajax.reload(function (json) {
            // do nothing
        });
    };

    /**
     * Perform Delete item via ajax
     * @param dataId
     */
    var performDeleteItem = function (dataId) {
        $.ajax({
            url: deleteList(dataId),
            type: 'DELETE',
            dataType: 'json',
            success: function (response) {
                // Do something with response
                response = parseJson(response);
                if (response.isSuccess) {
                    hideAllModal();
                    showModalSuccess(response.message);
                    reloadDataTableGrid('grid');
                } else {
                    showModalError(result);
                }
            }
        });
    };

    /**
     * Perform Update Item
     * @param dataId
     */
    var performUpdateItem = function (dataId) {
        performViewCreateUpdateItem(
                dataId,
                'containerFormData',
                'formData',
                'containerFromAndGrid'
                );
    };

    /**
     * Perform Create Item.
     * @param dataId
     * @param targetContainer
     * @param formElemenName
     * @param targetListDataContainer
     */
    var performViewCreateUpdateItem = function (dataId, targetContainer, formElemenName, targetListDataContainer) {
        $("#" + targetContainer).load(formApiEndPoint, function (data) {
            // update form action
            if (dataId == undefined) {
                $("#" + formElemenName).attr('action', formCreateApiEndPoint);
            } else {
                $("#" + formElemenName).attr('action', formUpdateApiEndPoint);
                FormLoadByData(LIST_DATA[dataId], "formData");
            }

            $('#' + targetListDataContainer).fadeOut('fast', function () {
                $('#' + targetContainer).fadeIn('fast');
            });
        });
    };

    /**
     * Perform Submit Form Data
     * For additional info, see [here](http://stackoverflow.com/questions/29277024/jquery-validate-change-error-class-replacing-another-class)
     * @param formName
     */
    var performSubmitFormData = function (formName) {
        var formObject = $('#' + formName);
        var validateForm = {
            rules: {
                kodePaket: {
                    required: true
                },
                namaPaket: {
                    required: true
                },
                deskripsi: {
                    required: true
                }
            },
            messages: {
                kodePaket: {
                    required: "Kode Paket wajib diisi."
                },
                namaPaket: {
                    required: "Nama Paket wajib diisi."
                },
                deskripsi: {
                    required: "Deskripsi Paket wajib diisi."
                }
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
            success: function (response) {
                response = parseJson(response);
                if (response.isSuccess) {
                    showModalSuccess(response.message);
                    backToListData(
                            'containerFormData',
                            'containerFromAndGrid'
                            );
                } else {
                    showModalError(response);
                }
            }
        };

        formObject.validate(validateForm);
        formObject.ajaxForm(optionsForm);
        formObject.submit();
    };

    /**
     * Back to List Data after displaying Create / Update Form.
     * @param targetListDataContainer
     * @param targetContainer
     */
    var backToListData = function (targetListDataContainer, targetContainer) {
        $('#' + targetListDataContainer).fadeOut('fast', function () {
            $('#' + targetContainer).fadeIn('fast', function () {
                reloadDataTableGrid('grid');
            });
        });
    };

    /**
     * Initialize Master Paket
     */
    var initializeMasterPaket = function () {
        initDataTableGrid('grid', 'search', 'tahun');
        window.console.log("Master Paket initialized!");
    };
    
    /*
     * 
     * Untuk combobox tahun
     */
    var min = 2016,
            max = new Date().getFullYear(),
            select = document.getElementById('tahun');

    for (var i = min; i <= max; i++) {
        var opt = document.createElement('option');
        opt.value = i;
        opt.innerHTML = i;
        select.appendChild(opt);
    }
    //////////////////////////////////////////

    return {
        // initialize function for master paket page
        init: function () {
            initializeMasterPaket();

            // trigger create button
            $(document).on('click', '#create_data', function (event) {
                event.preventDefault();
                performViewCreateUpdateItem(
                        undefined,
                        'containerFormData',
                        'formData',
                        'containerFromAndGrid'
                        );
            });

            // trigger back to list
            $(document).on('click', '#back_to_list_data', function (event) {
                event.preventDefault();
                backToListData(
                        'containerFormData',
                        'containerFromAndGrid'
                        );
            });

            // trigger back to list
            $(document).on('click', '#submit_form', function (event) {
                event.preventDefault();
                performSubmitFormData('formData');
            });

            // trigger search data
            $(document).on('click', '#search_data', function (event) {
                event.preventDefault();
                reloadDataTableGrid('grid');
            });
            
            // trigger search data
            $(document).on('change', '#tahun', function (event) {
                event.preventDefault();
                reloadDataTableGrid('grid');
            });
        }
    };

    
//    $('#tahun').on('change', function () {
//        reloadGrid();
//    });
}();

jQuery(document).ready(function () {
    MasterPaketScript.init();
});
