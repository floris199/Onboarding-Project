

var cart = (function() {
    var contents = [{}]

    var addContent = function( productId ) {

        if( contents.indexOf( productId ) > -1)
        {
            contents.indexOf( productId ).quantity++;
        }
        else
        {
            contents.push( { prodId: productId ,
                            quantity: 1} );
        }

        console.log(contents);

    }

    return {
        addProductToCart: function( productId ) {
            addContent( productId );
        },
        getContent: function ( ) {
            return contents;
        }

    };

})();

var selectCategory = function () {
    $(Config.DROPDOWN_CATEGORY).click(function (event) {
        var category = $(event.target).text();
        $.ajax({
            type: "POST",
            data: {
                category: category
            },
            url: "product",
            success: function ( ) {
            },
            error: function () {
                alert("FAIL");
            }
        });
    });
}

function clearSession() {
    sessionStorage.clear();
}

function setProduct( productId, productName, productDescription, productPrice )
{
    document.getElementById('product_id').value=productId;
    document.getElementById('product_name').value=productName;
    document.getElementById('product_description').value=productDescription;
    document.getElementById('product_price').value=productPrice;
}

var removeFromCart = function ( productId) {
        $.ajax({
            type: "POST",
            data: {
                "productId": productId
            },
            url: "ajax",
            success: function () {
                var quantity = $('table tr #quantity' + productId).text();
                var subTotal = $('table tr #sub-total' + productId).text();
                var price = $('table tr #price' + productId).text();
                var total = $('#totalPrice').text( );
                if( quantity == 1)
                {
                    $('#tr' + productId).remove();

                    alert( $('table tr').length );
                    if ($('table tr').length == 2)
                    {
                        $('#cartDiv').hide();
                        $('#emptyCartLabel').show();
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

var order = function ( ) {
    $.ajax({
        type: "POST",
        data: {},
        url: "order",
        success: function ( needsRedirect ) {

            if( needsRedirect === 'N' ) {

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

