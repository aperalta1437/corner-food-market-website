document.getElementById('btn-signup-submit').onclick = function () {
  if (
    document.getElementById('input-password').value !=
    document.getElementById('input-password-confirm').value
  ) {
    document.getElementById('input-password-confirm').setCustomValidity('Passwords must match.');
  } else {
    // input is valid -- reset the error message
    document.getElementById('input-password-confirm').setCustomValidity('');
  }

  if (!document.getElementById('chkbox-terms-n-conditions').checked) {
    document
      .getElementById('chkbox-terms-n-conditions')
      .setCustomValidity('You must agree with our terms and conditions.');
  } else {
    // input is valid -- reset the error message
    document.getElementById('chkbox-terms-n-conditions').setCustomValidity('');
  }
};

$('#chkbox-delivery-address').change(function () {
  if (this.checked) {
    $('#div-delivery-address').fadeIn();
    $('.delivery-address-field').prop('required', true);
  } else {
    $('#div-delivery-address').fadeOut();
    $('.delivery-address-field').prop('required', false);
  }
});
