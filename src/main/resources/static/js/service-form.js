
$(document).ready(() => {
    toggleElement();

    $('#operation').change(function () {
    toggleElement();
    });

    $('#newOperation').on('input', function () {
        toggleElement();
    });
    function toggleElement(){
        const operations = $('#operation option').length-1;
        const selectedValue = $('#operation').val();
        const newOperationValue = $('#newOperation').val();

        if(newOperationValue.length > 0 || selectedValue !== '0' || operations === 0) {
            if(newOperationValue.length > 0 || operations === 0){
                $('#operation').val('0');
                $('#operationDiv').hide();
            }
            else{
                $('#operation').show();
                $('#operationDiv').toggleClass('col-md-12', true).toggleClass('col-md-6', false);
            }

            if(selectedValue !== '0'){
                $('#newOperation').val('');
                $('#newOperationDiv').hide();
            }
            else{
                $('#newOperationDiv').toggleClass('col-md-12', true).toggleClass('col-md-6', false);
            }
        }
        else {
            $('#operationDiv, #newOperationDiv').toggleClass('col-md-12', false).toggleClass('col-md-6', true).show();
        }
    }
})
