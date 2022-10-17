<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="itmo.web2.weblab2.data.CollectionWithDataPoints" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/net.css">
    <title>My first lab</title>
</head>
<body>
<div class="All_Lab">
    <header class="main-header">
        <div class="container">
            <div class="first-logo-in-header  logo-in-header">
                <a href="https://github.com/Olegshipu95/weblab1">
                    <img src="static/img/github.png" alt="I am cool" data-tooltip="my git" class="liteTooltip"
                         data-tooltip-mouseover="This">
                </a>
            </div>
            <div class="manual">
                <p style="font-style: italic">Шипулин Олег Игоревич, группа P32311, 1ая лаба, вариант 3119</p>
            </div>
        </div>
    </header>
    <hr>
    <div class="container">
        <section class="UI">
            <div class="img_with_my_var">
                <canvas id="c1" width="350" height="350"></canvas>
            </div>
            <div class="filling_fields">
                <form id="formWithFields" method="post">
                    <p>
                        Выберите x:
                        <input class="cell-button-class" type="button" name="xChoose"
                               onclick="setX(this, this.value);" value="-2">
                        <input class="cell-button-class" type="button" name="xChoose"
                               onclick="setX(this, this.value);" value="-1.5">
                        <input class="cell-button-class" type="button" name="xChoose"
                               onclick="setX(this, this.value);" value="-1">
                        <input class="cell-button-class" type="button" name="xChoose"
                               onclick="setX(this, this.value);" value="-0.5">
                        <input class="cell-button-class" type="button" name="xChoose"
                               t onclick="setX(this, this.value);" value="0">
                        <input class="cell-button-class" type="button" name="xChoose"
                               onclick="setX(this, this.value);" value="1">
                        <input class="cell-button-class" type="button" name="xChoose"
                               onclick="setX(this, this.value);" value="1.5">
                        <input class="cell-button-class" type="button" name="xChoose"
                               onclick="setX(this, this.value);" value="2">
                    </p>
                    <p class="error_in_validation" id="messageX"></p>
                    <p>
                        Выберите y из диапозона(-5...5):
                        <label>
                            <input type="text" name="y-value" id="y-value" maxlength="4" placeholder="(-5 to 5)">
                        </label>
                    </p>
                    <p class="error_in_validation" id="messageY"></p>
                    <p>
                        Выберите значение для R:<br>
                        <label>
                            <input type="radio" name="r-value" value="1" onclick="setR(this.value)" >
                            1
                        </label>
                        <label>
                            <input type="radio" name="r-value" value="1.5" onclick="setR(this.value)">
                            1.5
                        </label>
                        <label>
                            <input type="radio" name="r-value" value="2" onclick="setR(this.value)">
                            2
                        </label>
                        <label>
                            <input type="radio" name="r-value" value="2.5" onclick="setR(this.value)">
                            2.5
                        </label>
                        <label>
                            <input type="radio" name="r-value" value="3" onclick="setR(this.value)">
                            3
                        </label>
                    </p>
                    <p class="error_in_validation" id="messageR"></p>
                    <input type="button" value="Проверить" class="button" data-submit>
                    <input type="button" value="Сбросить" class="button" data-clear>
                </form>
                <a href="${pageContext.request.contextPath}/?result=true">
                    <button class="button send_to_result" name="result">Результаты</button>
                </a>
            </div>
        </section>
    </div>
    <!--    <footer>-->
    <!--        <div class="copyright"><br>Все права защищены © 2022</div>-->
    <!--        <div class="footer-social">-->
    <!--            <a href="https://vk.com/real.chill" class="social-btn social-btn-vk">Vk</a>-->
    <!--            <a href="https://t.me/Olegenka2003" class="social-btn social-btn-fb">Telegram</a>-->
    <!--            <a href="https://instagram.com/_real.chill?igshid=YmMyMTA2M2Y=" class="social-btn social-btn-tw">Inst</a>-->
    <!--        </div>-->
    <!--    </footer>-->
</div>
<script src="${pageContext.request.contextPath}/static/js/points.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/Animation.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/main.js" type="text/javascript"></script>
</body>
</html>