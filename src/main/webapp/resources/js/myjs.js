var ajaxCalls = (function( ) {

    function addToCart( productId ) {
        $.ajax({
            type: "POST",
            data: {
                "productId": productId
            },
            url: "ajax?action=add",
            success: function () {
                var total = $('#totalPrice').text( );
                var quantity = $('table tr #quantity' + productId).text();
                var subTotal = $('table tr #sub-total' + productId).text();
                var price = $('table tr #price' + productId).text();

                $('table tr #quantity' + productId).text( (Number)(quantity) + 1 );
                $('table tr #sub-total' + productId).text( (Number)(subTotal) + (Number)(price) );


                $( "#totalPrice" ).text( (Number)(total) + (Number)(price) );
                $( ".mini-cart span" ).text( (Number)($( ".mini-cart span" ).text()) + 1);
            },
            error: function () {
                alert("FAIL");
            }
        });
    }

    function removeFromCart( productId ) {
        $.ajax({
            type: "POST",
            data: {
                "productId": productId
            },
            url: "ajax?action=remove",
            success: function () {
                var total = $('#totalPrice').text( );
                var cartDiv = $('#cartDiv');
                var emptyCartDiv = $('#emptyCartDiv');
                var quantity = $('table tr #quantity' + productId).text();
                var subTotal = $('table tr #sub-total' + productId).text();
                var price = $('table tr #price' + productId).text();

                if( quantity == 1)
                {
                    $('#tr' + productId).remove();

                    if ($('table tr').length == 2)
                    {
                        cartDiv.hide();
                        emptyCartDiv.show();
                    }

                }
                else
                {
                    $('table tr #quantity' + productId).text( quantity - 1 )
                    $('table tr #sub-total' + productId).text( subTotal - price )
                }

                $( "#totalPrice" ).text( total - price );
                $( ".mini-cart span" ).text( $( ".mini-cart span" ).text() - 1);
            },
            error: function () {
                alert("FAIL");
            }
        });
    }

    function placeOrder( ) {
        $.ajax({
            type: "POST",
            data: {},
            url: "order",
            success: function ( needsRedirect ) {
                var cartDiv = $('#cartDiv');
                var emptyCartDiv = $('#emptyCartDiv');

                if( needsRedirect === 'N' ) {
                    cartDiv.hide();
                    emptyCartDiv.show();
                    $( ".mini-cart span" ).text( 0 );

                    displayOverlay("Your Order has been placed.");
                    setTimeout(function () {
                        removeOverlay();
                    }, 3000);
                }
                else
                {
                    window.location.href ="login"
                }
            },
            error: function () {
                alert("FAIL");
            }
        });
    }

    function displayOverlay(text) {
        $("<table id='overlay'><tbody><tr><td>" + text + "</td></tr></tbody></table>").css({
            "position": "fixed",
            "top": 0,
            "left": 0,
            "width": "100%",
            "height": "100%",
            "background-color": "rgba(0,0,0,.5)",
            "z-index": 10000,
            "vertical-align": "middle",
            "text-align": "center",
            "color": "#fff",
            "font-size": "25px",
            "font-weight": "bold",
            "cursor": "wait"
        }).appendTo("body");
    }

    function removeOverlay() {
        $("#overlay").remove();
    }

    return {
        add: function( productId ) {
             addToCart( productId );
        },
        remove: function( productId ) {
            removeFromCart( productId );
        },
        order: function( ) {
            placeOrder();
        }
    };
})();

function setProduct( productId, productName, productDescription, productPrice )
{
    document.getElementById('product_id').value=productId;
    document.getElementById('product_name').value=productName;
    document.getElementById('product_description').value=productDescription;
    document.getElementById('product_price').value=productPrice;
}
