    var pwd = "";
    /*==================================================================
    [ Validate after type ]*/
    $('.validate-input .input').each(function(){
        $(this).on('blur', function(){
            if(validate(this) == false){
                showValidate(this);
            }
            else {
                $(this).parent().addClass('true-validate');
            }
        })    
    })

    /*==================================================================
    [ Validate ]*/



    $('.validate-form .input').each(function(){
        $(this).focus(function(){
           hideValidate(this);
           $(this).parent().removeClass('true-validate');
        });
    });

    function validate (input) {
        if($(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }else if($(input).attr('name') == 'username'){
            if($(input).val().trim().match(/^[a-z0-9]+([_-]?[a-z0-9]?)*$/) == null) {
                return false;
            }
        }
        else if($(input).attr('name') == 'oldpassword'){
            if($(input).val().trim().match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[!@#$&*]).{8,}$/) == null) {
                return false;
            }
        }
        else if($(input).attr('name') == 'password'){
            if($(input).val().trim().match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[!@#$&*]).{8,}$/) == null) {
                return false;
            }else{
                pwd = $(input).val().trim();
            }
        }
        else if($(input).attr('name') == 'passwordAgain'){
            if($(input).val().trim() != pwd || pwd == "") {
                return false;
            }
        }
        else if($(input).attr('name') == 'phone'){
            if($(input).val().trim() == null || $(input).val().trim() ==""){
                return true;
            }
            if($(input).val().trim().match(/^\+?\d{1,3}?[- .]?\(?(?:\d{2,3})\)?[- .]?\d\d\d[- .]?\d\d\d\d$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }

