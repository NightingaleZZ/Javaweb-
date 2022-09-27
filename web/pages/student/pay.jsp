<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/28
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/head.jsp" %>

    <script>
        $(function () {

            // var uuid;

            $("#create_code").click(function () {

                $("#m_state").val(1);

                $("#qr_code").html("");

                let moneyCount = $("#m_count").val();
                let moneyTime = $("#m_time").val();
                let moneyUse = $("#m_use").val();

                let contents = moneyCount + "," + moneyTime + "," + moneyUse;

                if (moneyCount.length > 0 && moneyTime.length > 0) {

                    // $.ajax({
                    //     type: "post",
                    //     url: "studentServlet?action=qrCode",
                    //     success: function (data) {
                    //         alert(data.uuid)
                    //         this.uuid = data.uuid;
                    $("#qr_code").attr("src", "studentServlet?action=qrCode&contents=" + contents);
                    // confirm_();
                    //     }
                    // })

                } else {
                    alert("缴费金额和缴费时间不能为空, 生成二维码失败!");
                    return false;
                }
            });

            $("#sub").click(function () {
                let moneyCount = $("#m_count").val();
                let moneyTime = $("#m_time").val();

                if (!(moneyCount.length > 0 && moneyTime.length > 0)) {
                    alert("缴费金额和缴费时间不能为空, 提交失败!");
                    return false;
                } else {
                    if ($("#m_state").val() !== "1") {
                        return confirm("确定提交?当前提交状态为(未提交)");
                    }
                }
            })

            // function confirm_() {
            //     $.ajax({
            //         type: "post",
            //         url: "studentServlet?action=confirm",
            //         data: {
            //             uuid: this.uuid
            //         },
            //         success: function (result) {
            //             if (result === 1) {
            //                 window.location.href = 'pages/student/pay_success.jsp';
            //             } else {
            //                 confirm_();
            //             }
            //         }
            //     })
            // }

            $("#get_now_time").click(function () {
                let date = new Date();
                let year = date.getFullYear();
                let month = date.getMonth() + 1;
                let day = date.getDate();

                $("#m_time").val(year + "-" + month + "-" + day);

                return false;
            })
        })
    </script>

</head>
<body>

<br><br><br>

<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <button class="btn btn-warning" id="create_code">生成付款码</button>
        </div>
        <div class="col-sm-10">
            <img id="qr_code">
        </div>
    </div>
</div>


<form action="studentServlet">
    <input type="hidden" name="action" value="pay">
    <input type="hidden" name="moneyState" id="m_state">
    <input type="hidden" name="classId" value="${param.classId}">
    <input type="hidden" name="studentId" value="${param.studentId}">
    <table class="table table-condensed" style="width: 50%; text-align: center; margin: 50px auto;">
        <tr class="text-center">
            <td>缴费金额</td>
            <td><input type="text" name="moneyCount" class="form-control" id="m_count"></td>
        </tr>

        <tr class="text-center">
            <td>
                缴费时间
                <span style="font-size: 0.8em; color: #9d9d9d">(yyyy-MM-dd)</span>
                <button id="get_now_time" class="btn btn-default btn-sm">现在</button>
            </td>
            <td><input type="text" name="moneyTime" class="form-control" id="m_time"></td>
        </tr>

        <tr class="text-center">
            <td>缴费用途</td>
            <td><input type="text" name="moneyUse" class="form-control" id="m_use"></td>
        </tr>

        <tr class="text-center">
            <td colspan="2" style="font-size: 0.8em; color: #9d9d9d">需要付款才会是已提交, 否则为未提交!(至少先把二维码生成...)</td>
        </tr>

        <tr class="text-center">
            <td colspan="2"><input id="sub" type="submit" class="btn btn-success btn-block"
                                   style="margin:auto; width: 50%;" value="提交"></td>
        </tr>

    </table>

</form>

<br><br><br><br><br><br><br>
<div class="container">
    <div class="row">
        <div class="col-sm-1 col-sm-offset-11">
            <a href="pages/student/stu_welcome.jsp">
                <button class="btn btn-default">返回</button>
            </a>
        </div>
    </div>
</div>
<br>

</body>
</html>
