/**
 * Accommodate Custom JavaScript / jQuery Scripts. Obviously, this script depends on jquery & bootstrap v3
 * Created by Agung Andika on 18/08/2016.
 */

var CustomScript = function() {
    /**
     * Handle BtnGroup with DropDown Menu on DataTable.
     * Because, it sucks btw. More tests: http://jsfiddle.net/os0wgxj1/
     */
    var handleBtnGroupOnDataTable = function() {
        $(document).on('shown.bs.dropdown','.btn-action-datatable', function () {
            var menu = $("ul", this);
            offset = menu.offset();
            position = menu.position();
            $('body').append(menu);
            menu.show();
            menu.css('position', 'absolute');
            menu.css('top', (offset.top - 20) + 'px');
            menu.css('left', (offset.left) + 'px');
            $(this).data("myDropdownMenu", menu);
        });
        $(document).on('hide.bs.dropdown','.btn-action-datatable', function () {
            $(this).append($(this).data("myDropdownMenu"));
            $(this).data("myDropdownMenu").removeAttr('style');
        });
    };

    return {
        // constructor
        init: function() {
            // handles handleBtnGroupOnDataTable
            handleBtnGroupOnDataTable();
        }
    };
}();

jQuery(document).ready(function() {
    CustomScript.init();
});