/*
    Setting's for movie hall;
*/
var settings = {
    rows: 5,
    cols: 15,
    rowCssPrefix: "row-",
    colCssPrefix: "col-",
    seatWidth: 35,
    seatHeight: 35,
    seatCss: "seat",
    selectedSeatCss: "selectedSeat",
    selectingSeatCss: "selectingSeat",
    seatBusyCss: "seatBusy"
};

/*
    Generate HTML movie hall;
*/
function init(seatBusy, seatSelected) {
    var str = [];
    var seatNo, className, i, j;
    for (i = 0; i < settings.rows; i++) {
        for (j = 0; j < settings.cols; j++) {
            seatNo = (i * settings.cols + j + 1);
            className = settings.seatCss + " " + settings.rowCssPrefix +
                i.toString() + " " + settings.colCssPrefix + j.toString();
            if ($.isArray(seatBusy) &&
                $.inArray(seatNo.toString(), seatBusy) != -1) {
                className += " " + settings.seatBusyCss;
            } else {
                if ($.isArray(seatSelected) &&
                    $.inArray(seatNo.toString(), seatSelected) != -1) {
                    className += " " + settings.selectedSeatCss;
                }
            }
            str.push("<li class=\"" + className + "\" style=\"top:" +
                (i * settings.seatHeight).toString() + "px;left:" +
                (j * settings.seatWidth).toString() + "px\">" +
                "<a title=\"" + seatNo + "\">" + seatNo + "</a>" + "</li>");
        }
    }
    $("#place").html(str.join(""));
}

$(document).ready(function () {
    if(typeof seat !== "undefined" && typeof seatSelected !== "undefined") {
        init(seat, seatSelected);
    } else {
        init("", "");
    }
});

/*
    onClick seatCss class handlers;
 */
$(document).on("click", "." + settings.seatCss, function () {
    var userSession = $("#userSession").val();
    if (userSession !== "") {
        if ($(this).hasClass(settings.seatBusyCss)) {
            $(".hall-info-msg").html("<strong>This seat has been reserved</strong>");
            $(".hall-info-msg").children().fadeOut(5000);
        }
        else{
            if ($(this).hasClass(settings.selectedSeatCss)){
                $(".hall-info-msg").html("<strong>This place has been reserved before by you</strong>");
                $(".hall-info-msg").children().fadeOut(5000);
            } else {
                $(this).toggleClass(settings.selectingSeatCss);
            }
        }
    } else {
        $(".hall-info-msg").html("<strong>You need login, please</strong>");
        $(".hall-info-msg").children().fadeOut(5000);
    }
})

$("#buyBtn").click(function () {

    var str = [], item;
    $.each($("#place li." + settings.selectingSeatCss + " a"), function () {
        item = $(this).attr("title");
        str.push(item);
    });
    var json = JSON.stringify(str);
    var idEvent = $("#idEvent").val();

    $.post("/booking", {ticketArray: json, idEvent: idEvent},
        function (data, status){
            var busySeat = JSON.parse(data);
            var seatBusy = JSON.parse(busySeat.seatBusy);
            var seatSelected = JSON.parse(busySeat.seatSelected);
            init(seatBusy, seatSelected);
        });
})

function getSelectedSeatLength() {
    var str = [], item;
    $.each($("#place li." + settings.selectingSeatCss + " a"), function () {
        item = $(this).attr("title");
        str.push(item);
    });
    return str.length;
}

$("#liAuthors").click(function () {
    $("#content .row").hide();
    $("#divAuthorsTable").show();
})

$("#liFilms").click(function () {
    $("#content .row").hide();
    $("#divFilmsTable").show();
})

$("#liEvents").click(function () {
    $("#content .row").hide();
    $("#divEventsTable").show();
})

$("#liOrders").click(function () {
    $("#content .row").hide();
    $("#divOrdersTable").show();
})

$("#liRole").click(function () {
    $("#content .row").hide();
    $("#divRoleTable").show();
})

$("#liUsers").click(function () {
    $("#content .row").hide();
    $("#divUsersTable").show();
})

$(document).ready(function () {
    if (typeof liId !== "undefined") {
       $(liId).click();
    } else {
        $("#liAuthors").click();
        $("#liUserInfo").click();
    }
})

$("#liUserInfo").click(function () {
    $("#content .row").hide();
    $("#divUserInfo").show();
})

$("#liActualOrders").click(function () {
    $("#content .row").hide();
    $("#divActualOrders").show();
})

$("#liArchiveOrders").click(function () {
    $("#content .row").hide();
    $("#divArchiveOrders").show();
})

function sendFormData(formName, inputAction, actionValue) {
    var input = $("input[name = " + inputAction + "]");
    input.val(actionValue);
    var form = $("form[name = " + formName + "]");
    form.submit();
}