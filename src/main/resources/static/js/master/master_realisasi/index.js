/**
 * Handle interaction between MasterData script and page script.
 *
 * @depends masterdata.js
 * @returns {{init: init}}
 */
var lokasiId = $('#grid').data('key-id');
var config = {
    urlConfig: {
        searchList: '/rest/master/realisasi/search/' + lokasiId,
        formApiEndPoint: '/admin/master/realisasi/form/' + lokasiId,
        formCreateApiEndPoint: '/rest/master/realisasi/save/' + lokasiId,
        formUpdateApiEndPoint: '/rest/master/realisasi/update',
        deleteList: '/rest/master/realisasi/remove'
    },
    elementConfig: {
        // optional, unless you mess with my layout
    },
    gridConfig: {
        config: {
            // optional, unless (again) you mess with my settings
        },
        columns: [
            {data: "jenisRealisasi"},
            {data: "kopRealisasi"},
            {
                data: "tanggalRealisasi",
                render: function (data, type, full, meta) {
                    var givenDate = new Date(data);
                    return givenDate.toDateString();
                }
            },
            {data: "persenRealisasi"},
            {data: "totalPersenRealisasi"},
            {
                data: "nilaiRealisasi",
                render: function (data, type, full, meta) {
                    window.console.log(data);
                    if (isNaN(data) || data == undefined || data == null || data.length == 0) {
                        return 0;
                    } else {
                        return accounting.formatNumber(data);
                    }
                }
            },
            {data: "keterangan"}
        ]
    },
    formValidationConfig: {
        rules: {
            jenisRealisasi: {
                required: true
            },
            kopRealisasi: {
                required: true
            },
            tanggalRealisasi: {
                required: true
            },
            persenRealisasi: {
                required: true
            },
            totalPersenRealisasi: {
                required: true
            },
            nilaiRealisasi: {
                required: true
            },
            keterangan: {
                required: true
            }
        },
        messages: {
            jenisRealisasi: {
                required: 'Mohon Pilih Jenis Realisasi.'
            },
            kopRealisasi: {
                required: 'Kop Realisasi wajib diisi.'
            },
            tanggalRealisasi: {
                required: 'Mohon isi Tanggal Realisasi.'
            },
            persenRealisasi: {
                required: 'Mohon isi realisasi pekerjaan (dalam persentase).'
            },
            totalPersenRealisasi: {
                required: 'Mohon isi total realisasi pekerjaan (dalam persentase).'
            },
            nilaiRealisasi: {
                required: 'Nilai Realisasi (dalam Rupiah) wajib diisi.'
            },
            keterangan: {
                required: 'Keterangan wajib diisi.'
            }
        }
    }
};
jQuery(document).ready(function() {
    MasterDataScript.init(config);

    // trigger create button
    $(document).on('click','#create_data', function(event) {
        event.preventDefault();
        window.console.log('create data triggered!');
        MasterDataScript.viewCreateItem();
    });

    // trigger back to list
    $(document).on('click', '#back_to_list_data', function(event) {
        event.preventDefault();
        MasterDataScript.performBackToListData();
    });

    // trigger back to list
    $(document).on('click', '#submit_form', function(event) {
        event.preventDefault();
        MasterDataScript.performSubmitForm();
    });

    // trigger search data
    $(document).on('click', '#search_data', function(event) {
        event.preventDefault();
        MasterDataScript.reloadDataTable();
    });
});