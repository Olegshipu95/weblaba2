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
        if (isYOk() && fieldsAreNotEmpty(x, yElement.value, r)) {
            $.ajax({
                url: "/weblab2-1.0-SNAPSHOT/",
                async: true,
                type: "GET",
                data: {
                    "shoot": "yes",
                    "x": x,
                    "y": yElement.value,
                    "r": r
                },
                cache: false,
                success: function (response) {
                    let points = new Points(x, yElement.value)
                    updateCanvas(points , r)
                },
                error: function (jqXHR, exception) {
                    errorCheck(jqXHR, exception)
                }
            })
        }
    })
})
$(document).ready(function () {
    $('[data-clear]').on('click', function (e) {
        e.preventDefault();

        if (xButton != null) {
            xButton.style.borderBottom = ""
            x = "";
        }
        formWithFields.reset();
        r = ""
        $.ajax({
            url: "/weblab2-1.0-SNAPSHOT/",
            async: true,
            type: "GET",
            data: {
                "clear": "ok",
            },
            cache: false,
            success: function (response) {
                alert("data is delete")
                ctx.clearRect(0,0,350,350)
                frame(myColor)
                arr = []
            },
            error: function (jqXHR, exception) {
                errorCheck(jqXHR, exception)
            }
        })


    })
})

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
    } else if (jqXHR.status == 400) {
        alert(jqXHR.responseText)
    } else {
        msg = 'Uncaught Error.\n' + jqXHR.responseText;
    }
    alert(msg);
}

// $(document).ready(function () {
//     $.ajax({
//         url: "/weblab2-1.0-SNAPSHOT/",
//         async: true,
//         type: "GET",
//         data: {
//             "data": "ok",
//         },
//         success: function (response) {
//             tbody.innerHTML += response
//         },
//         error: function (jqXHR, exception) {
//             errorCheck(jqXHR, exception)
//         }
//     })
// })
function fieldsAreNotEmpty(x, y, r) {
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
canvas.onmousedown = function (event){
    if(r) {
        messageR.innerHTML = ""
        let myX = event.offsetX;
        let myY = event.offsetY;
        let indent = 160;
        console.log("x: "+ myX + " y: " + myY +" r: "+ r)
        myX = myX - 175
        myY = myY - 175
        myY = -(myY)/indent*r
        myX = myX/indent*r
        console.log("new x: "+ myX + " y: " + myY+" r: "+ r)
        $.ajax({
            url: "/weblab2-1.0-SNAPSHOT/",
            async: true,
            type: "GET",
            data: {
                "shoot": "yes",
                "canvas": "yes",
                "x": myX,
                "y": myY,
                "r": r
            },
            cache: false,
            success: function (response) {
                let points = new Points(myX, myY)
                updateCanvas(points, r)
            },
            error: function (jqXHR, exception) {
                errorCheck(jqXHR, exception)
            }
        })
    }
    else{
        messageR.innerHTML = 'Это поле обязательно для заполнения';
    }
}
function module(x){
    if(x>=0) {
        if (120 >= x && x >= 40) {
            return 0.5
        } else if (x > 120) {
            return 1
        } else return 0
    }
    else{
        if (120 >= Math.abs(x) && Math.abs(x) >= 40) {
            return -0.5
        } else if (Math.abs(x) > 120) {
            return -1
        } else return 0
    }
}

