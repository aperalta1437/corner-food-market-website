$('.form-add-to-cart').on('submit', function (e) {
  e.preventDefault();
  var spinner = $('#div-add-to-cart-spinner-container');
  spinner.fadeIn(250);
  $.ajax({
    url: $(this).attr('action'),
    type: 'POST',
    dataType: 'json',
    data: $(this).serialize(),
    success: function (response) {
      spinner.fadeOut(250);
      var inCartAmountElement = $('#div-in-cart-' + response.requestedItemSku);
      var cartRequestedItemTotal = response.cartRequestedItemTotal;

      inCartAmountElement.find('.span-item-total').text(cartRequestedItemTotal);

      if (cartRequestedItemTotal > 0) {
        inCartAmountElement.removeClass('d-none');
      }
      $('#span-cart-total-items').text(response.cartTotalItems);

      var addToCartResponseElement = $('#div-add-to-cart-response');
      addToCartResponseElement.find('span').text(response.addToCartResponse);

      if (!response.isSuccess) {
        addToCartResponseElement.css({ opacity: 0, display: 'flex' }).animate(
          {
            opacity: 1,
          },
          1000,
          function () {
            addToCartResponseElement.addClass('attention');
            setTimeout(function () {
              addToCartResponseElement.fadeOut(1000);
            }, 7000);
          }
        );
      } else {
        inCartAmountElement.addClass('change');
        setTimeout(function () {
          inCartAmountElement.removeClass('change');
        }, 3000);
      }
    },
    error: function () {
      spinner.fadeOut(250);
      alert('Error while requesting. Please try again later.');
    },
  });
  $(this).trigger('reset');
});
