/**
 * Created by Tenece on 16/03/2020.
 */

$(document).ready(function () {

    $("#payment-form").keyup(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        getCardDetails();
    });
});

function getCardDetails() {

    var search = $("#cardNo").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/card-scheme/verify/" + search,
        //data: search,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var cardScheme = data.payload.scheme;
            var cardType = data.payload.type;
            var cardBank = data.payload.bank;
            $('#cardScheme').html(cardScheme);
            $('#cardType').html(cardType);
            $('#cardBank').html(cardBank);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Error Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}