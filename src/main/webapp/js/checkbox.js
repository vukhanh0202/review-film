$(document).ready(function() {
    /*
    * Click on select all checkbox
    */
    $('#checkAll').click(function(e) {
        $('[name="cb"]').prop('checked', this.checked);
    });

    /*
    * Click on another checkbox can affect the select all checkbox
    */
    $('[name="cb"]').click(function(e) {
        if ($('[name="cb"]:checked').length == $('[name="cb"]').length || !this.checked)
            $('#checkAll').prop('checked', this.checked);
    });

});