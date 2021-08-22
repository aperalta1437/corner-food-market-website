var $generalForm = $('#form-general-review');

$generalForm.on('submit', function (e) {
  e.preventDefault();
  $('#div-general-review-form').fadeOut(500, function () {
    $('#div-submitted-general-review-spinner').fadeIn(250);
  });
  $.ajax({
    url: $generalForm.attr('action'),
    type: 'POST',
    data: $generalForm.serialize(),
    success: function (response) {
      $('#div-submitted-general-review-response').html(response);
      $('.spinner-component').fadeOut(250, function () {
        $('#div-submitted-general-review-response').fadeIn(500);
      });
    },
  });
  document.getElementById('form-general-review').reset();
});

document.getElementById('btn-submit-general-review').onclick = function () {
  if (!document.getElementById('chkbox-general-review-terms-n-conditions').checked) {
    document
      .getElementById('chkbox-general-review-terms-n-conditions')
      .setCustomValidity('You must agree with our terms and conditions.');
  } else {
    // input is valid -- reset the error message
    document.getElementById('chkbox-general-review-terms-n-conditions').setCustomValidity('');
  }
};

function makeAnotherReview() {
  $('#div-submitted-general-review-response').fadeOut(500, function () {
    $('#div-general-review-form').fadeIn(500);
  });
}
