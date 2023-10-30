/**
 * Handle javascript interaction to service rest, etc etc etc...
 *
 * @author agung andika
 * @depends jquery.min.js, bootstrap.min.js, app.js
 */

var MasterDataScript = function () {
    /**
     * URL Config
     * @type {{searchList: string, formApiEndPoint: string, formCreateApiEndPoint: string, formUpdateApiEndPoint: string, deleteList: string}}
     */
    var urlConfig = {
        searchList: '/index',
        formApiEndPoint: '/form',
        formCreateApiEndPoint: '/save',
        formUpdateApiEndPoint: '/update',
        deleteList: '/delete'
    };

    /**
     * Element Config
     * targetContainer: Variable for Page Element of Container Object
     * formElementName: Variable for Form Element Name
     * targetListDataContainer: Variable for DataGrid List Container
     * dataTableElementId: DataTable Element ID for Table Grid
     * dataTableElementSearch: DataTable Element ID for Search Box
     *
     * @type {{targetContainer: string, formElementName: string, targetListDataContainer: string, dataTableElementId: string, dataTableElementSearch: string}}
     */
    var elementConfig = {
        targetContainer: 'containerFormData',
        formElementName: 'formData',
        targetListDataContainer: 'containerFromAndGrid',
        dataTableElementId: 'grid',
        dataTableElementSearch: 'search'
    };

    /**
     *
     * @type {{}}
     */
    var formValidationConfig = {};

    /**
     * Initialize Data Table Grid
     * @param config
     */
    var initDataTableGrid = function (config) {
        var targetGrid = $('#' + elementConfig.dataTableElementId);
        /**
         * Column Action for Data Table
         * @type {*[]}
         */
        var columnsAction = [
            {
                "data": "id",
                "render": function (data, type, row) {
                    // this is old configuration, can be changed later.
                    var buttonUpdate = '<a href="javascript:;" class="btn btn-sm btn-default update-item" data-key-id="' +
                        data + '"><i class="glyphicon glyphicon-edit"></i> Update </a>';
                    var buttonDelete = '<a href="javascript:;" class="btn btn-sm btn-danger delete-item" data-key-id="' +
                        data + '"><i class="glyphicon glyphicon-trash"></i> Delete</a>';

                    return '' +
                        '<div class="btn-group btn-action-datatable">' +
                            '<a class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" href="javascript:;">' +
                                'Actions <i class="fa fa-caret-down"></i>' +
                            '</a>' +
                            '<ul class="dropdown-menu">'+
                                '<li>' +
                                    '<a href="javascript:;" class="update-item" data-key-id="' +
                                        data +'"><i class="glyphicon glyphicon-edit"></i> Update </a>' +
                                '</li>' +
                                '<li>' +
                                    '<a href="javascript:;" class="delete-item" data-key-id="' +
                                        data +'"><i class="glyphicon glyphicon-trash"></i> Delete </a>' +
                                '</li>' +
                            '</ul>' +
                        '</div>';
                }
            }
        ];
        var columns = $.merge($.merge( [], config.columns ), columnsAction );
        /**
         * DataTable Default Config
         * @type {array|json}
         */
        var dataTableConfig = {
            "responsive": true,
            "autoWidth": false,  // set autoWidth to false, so that DataTable responsive could be re-build & re-calc!
            "processing": true,
            "serverSide": true,
            "bFilter": false,
            "bSort": false,
            "drawCallback": function (settings) {
                setTimeout(function () {
                    var table = $('#' + elementConfig.dataTableElementId).DataTable();
                    if (table != undefined) {
                        table.responsive.rebuild();
                        table.responsive.recalc();
                    }
                }, 50);
            },
            "language": {
                "zeroRecords": "Tidak ada data.",
                "infoEmpty": "Data yang Anda cari / tidak ada, mohon periksa kata kunci yang Anda gunakan."
            },
            "ajax": function (data, callback, settings) {
                var page = 0;
                if (data.start == 0) {
                } else {
                    page = data.start / data.length;
                }
                var searchValue = $('#' + elementConfig.dataTableElementSearch).val();

                $.ajax({
                    url: urlConfig.searchList,
                    type: 'GET',
                    dataType: 'json',
                    data: {
                        search: searchValue,
                        rows: data.length,
                        page: page + 1
                    },
                    success: function (response) {
                        LIST_DATA = new Object();
                        $.each(response.rows, function (keyResult, valueResult) {
                            LIST_DATA[valueResult.id] = valueResult;
                        });
                        callback({
                            recordsTotal: response.recordsTotal,
                            recordsFiltered: response.recordsFiltered,
                            data: response.rows
                        });
                    }
                });
            },
            "columns": columns
        };

        // Merge configs
        config = $.extend({}, dataTableConfig, config.config);

        // Apply DataTable config to targetGrid
        targetGrid.DataTable(config);

        // call delete item on grid data
        $(document).on('click','.delete-item', function(event) {
            event.preventDefault();
            var itemId = $(this).data('key-id');
            var message = "Do you want to delete this data?";
            showModalWarning(message, performDeleteItem, [itemId]);
        });

        // call update item on grid data
        $(document).on('click','.update-item',  function(event) {
            event.preventDefault();
            var itemId = $(this).data('key-id');

            performViewUpdateItem(itemId);
        });
    };
    /**
     * Reload DataTable Grid
     */
    var reloadDataTableGrid = function() {
        var table = $('#' + elementConfig.dataTableElementId).DataTable();

        table.ajax.reload(function (json) {
            // do nothing, just reload and process data.
        });
    };
    /**
     * Perform Delete item via ajax
     * @param dataId
     */
    var performDeleteItem = function(dataId) {
        $.ajax({
            url: urlConfig.deleteList + '/' + dataId,
            type: 'DELETE',
            dataType: 'json',
            success: function (response) {
                // Do something with response
                response = parseJson(response);
                if (response.isSuccess) {
                    hideAllModal();
                    showModalSuccess(response.message);
                    reloadDataTableGrid();
                } else {
                    showModalError(result);
                }
            }
        });
    };
    /**
     * Perform Create Item.
     */
    var performViewCreateItem = function() {
        $("#" + elementConfig.targetContainer).load(urlConfig.formApiEndPoint, function (data) {
            // update form action
            $("#" + elementConfig.formElementName).attr('action', urlConfig.formCreateApiEndPoint);
            $('#' + elementConfig.targetListDataContainer).fadeOut('fast', function () {
                $('#' + elementConfig.targetContainer).fadeIn('fast');
            });
        });
    };
    /**
     * Perform Update Item
     * @param dataId
     */
    var performViewUpdateItem = function(dataId) {
        $("#" + elementConfig.targetContainer).load(urlConfig.formApiEndPoint, function (data) {
            // update form action
            $("#" + elementConfig.formElementName).attr('action', urlConfig.formUpdateApiEndPoint + '/' + dataId);
            FormLoadByData(LIST_DATA[dataId], "formData");

            $('#' + elementConfig.targetListDataContainer).fadeOut('fast', function () {
                $('#' + elementConfig.targetContainer).fadeIn('fast');
            });
        });
    };

    /**
     * Perform Submit Form Data
     */
    var performSubmitFormData = function() {
        var formObject = $('#' + elementConfig.formElementName);
        var validateForm = {
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
        validateForm = $.extend({}, validateForm, formValidationConfig);

        var optionsForm = {
            success: function(response) {
                response = parseJson(response);
                if (response.isSuccess) {
                    showModalSuccess(response.message);
                    backToListData();
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
     */
    var backToListData = function() {
        $('#' + elementConfig.targetContainer).fadeOut('fast', function () {
            $('#' + elementConfig.targetListDataContainer).fadeIn('fast', function () {
                reloadDataTableGrid();
            });
        });
    };

    /**
     * Initialize function
     */
    var initializeModule = function (config) {
        if (config.length != 0) {
            // apply url config
            if (config.hasOwnProperty("urlConfig")) {
                urlConfig = $.extend({}, urlConfig, config.urlConfig);
            }

            // apply element config
            if (config.hasOwnProperty("elementConfig")) {
                elementConfig = $.extend({}, elementConfig, config.elementConfig);
            }

            if (config.hasOwnProperty("gridConfig")) {
                // apply options to DataTable config
                initDataTableGrid(config.gridConfig);
            } else {
                window.console.log("ERROR! gridConfig is not provided / initialized. Please check module conifg.");
            }

            if (config.hasOwnProperty("formValidationConfig")) {
                formValidationConfig = $.extend({}, formValidationConfig, config.formValidationConfig);
            } else {
                window.console.log("ERROR! formValidationConfig is not provided / initialized. Please check module conifg.");
            }
        } else {
            window.console.log("ERROR! Module config is not provided. Please check and try again.");
        }
        window.console.log("Module initialized!");
    };

    return {
        init: function(config) {
            initializeModule(config);
        },
        viewCreateItem: function () {
            performViewCreateItem();
        },
        performBackToListData: function () {
            backToListData();
        },
        performSubmitForm: function () {
            performSubmitFormData();
        },
        reloadDataTable: function() {
            reloadDataTableGrid();
        }
    };
}();
