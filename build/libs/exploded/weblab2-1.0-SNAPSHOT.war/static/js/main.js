"use strict"
let x, r
let xButton
let yElement = document.getElementById("y-value");
let messageX = document.getElementById("messageX")
let messageY = document.getElementById("messageY")
let messageR = document.getElementById("messageR")
let formWithFields = document.getElementById("formWithFields")
let tbody = document.getElementById("tbody");


$(document).ready(function () {
    $('[data-submit]').on('click', function (e) {
        e.preventDefault();
        if (isYOk() && fieldsAreNotEmpty(x,yElement.value,r)) {
            $.ajax({
                url: "/weblab2-1.0-SNAPSHOT/",
                async: true,
                type: "get",
                data: {
                    "shoot": "yes",
                    "x": x,
                    "y": yElement.value,
                    "r": r
                },
                cache: false,
                success: function (response) {
                    let data = JSON.parse(response);
                    if ("message" in data) {
                        alert(data.message);
                    } else if ("entry" in data) {
                        tbody.innerHTML += '<tr><td>' + x + '</td><td>' + yElement.value + '</td><td>' + r + '</td><td>' + data.entry + '</td><td>' + data.time + '</td></tr>'
                        drawCoord(x, yElement.value, r);

                    } else {
                        alert("global error")
                    }
                },
                error: function (jqXHR, exception) {
                    errorCheck(jqXHR, exception)
                }
            })
        }
    })
})
$(document).ready(data_clear());

function data_clear() {
    $('[data-clear]').on('click', function (e) {
        e.preventDefault();

        if (xButton != null) {
            xButton.style.borderBottom = ""
            x = "";
        }
        formWithFields.reset();
        r = ""
        tbody.innerHTML = ""
        $.ajax({
            url: "/",
            async: true,
            type: "GET",
            data: {
                "clear": "ok",
            },
            cache: false,
            success: function (response) {
                alert(response)
            },
            error: function (jqXHR, exception) {
                errorCheck(jqXHR, exception)
            }
        })


    })
}

function setR(value) {
    r = value;
}

function setX(object, value) {
    x = value;
    if (xButton != null) {
        xButton.style.borderBottom = ""
    }
    xButton = object
    xButton.style.borderBottom = "2px solid black"
}



function isYOk() {
    let isOk = true;
    if (yElement.value >= 5 || yElement.value <= -5 || isNaN(yElement.value)) {
        messageY.innerHTML = 'Некорректный ввод, введите число от -5 до 5';
        isOk = false
    } else messageY.innerHTML = ''
    return isOk;
}

function errorCheck(jqXHR, exception) {
    let msg = '';
    if (jqXHR.status === 0) {
        msg = 'Not connect.\n Verify Network.';
    } else if (jqXHR.status == 404) {
        msg = 'Requested page not found. [404]';
    } else if (jqXHR.status == 500) {
        msg = 'Internal Server Error [500].';
    } else if (exception === 'parsererror') {
        msg = 'Requested JSON parse failed.';
    } else if (exception === 'timeout') {
        msg = 'Time out error.';
    } else if (exception === 'abort') {
        msg = 'Ajax request aborted.';
    } else {
        msg = 'Uncaught Error.\n' + jqXHR.responseText;
    }
    alert(msg);
}

/*$(document).ready(function () {
    $.ajax({
        url: "/weblab2-1.0-SNAPSHOT/",
        async: true,
        type: "GET",
        data: {
            "reset": "ok",
        },
        success: function (response) {
            tbody.innerHTML += response
        },
        error: function (jqXHR, exception) {
            errorCheck(jqXHR, exception)
        }
    })
})*/
function fieldsAreNotEmpty(x,y,r) {
    let isNotEmpty = true;

    if (!x) {
        messageX.innerHTML = 'Это поле обязательно для заполнения';
        isNotEmpty = false;
    } else messageX.innerHTML = "";

    if (!y) {
        messageY.innerHTML = 'Это поле обязательно для заполнения';
        isNotEmpty = false;
    } else messageY.innerHTML = "";
    if (!r) {
        messageR.innerHTML = 'Это поле обязательно для заполнения';
        isNotEmpty = false;
    } else messageR.innerHTML = "";
    return isNotEmpty;
}

