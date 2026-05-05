
 jQuery(function() {
    jQuery('.money').maskMoney({
        thousands: '.',
        decimal: ',',
        allowZero: true
    });
 }); 

 function onClickBtLimpar() {
    jQuery('input[type="text"]').each(function() {
        jQuery(this).val('');
    });            
 }
