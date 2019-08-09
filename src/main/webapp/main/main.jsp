<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>


<nav class="navbar navbar-default">
    <!--导航条将充满整个屏幕-->
    <div class="container-fluid">
        <!--导航条的标题-->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <!--按钮上的横杠-->
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持明法洲管理系统</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <!--标签据右-->                               <!--导航条中的超级链接-->

            <div class="dropdown collapse navbar-collapse navbar-text navbar-right">

                <ul class="nav navbar-nav dropdown-toggle "   data-toggle="dropdown">
                    欢迎：${requestScope.adminname}
                </ul>

                <ul class="navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/admin/exit">退出登录<span class="glyphicon glyphicon-share" aria-hidden="true"></span></a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
    <!--栅格系统-->
<div class="container-fluid">
    <div class="row">

        <div class="col-md-2">
            <!--手风琴的样式 面板-->
            <div class="panel-group" id="accordion">
                <!--默认的面版样式-->
                <div class="panel panel-info">
                    <!--面板头-->
                    <div class="panel-heading">
                        <!--面板标题-->
                        <h4 class="panel-title">
                            <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <!--面板的主体内容-->
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${path}/user/user.jsp')"> 用户信息</a>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${path}/kindeduit/testEcharts.jsp')"> 用户统计</a>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${path}/kindeduit/testEchartsChina.jsp')"> 用户分布</a>
                                    </button>
                                </li>


                            </ul>

                        </div>
                    </div>
                </div>
                &nbsp;
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${path}/main/banner.jsp')"> 轮播图信息</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                &nbsp;
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-success">
                                        <a href="javascript:$('#mainId').load('${path}/amble/amble.jsp')"> 专辑信息</a>
                                    </button>
                                </li>
                            </ul>

                        </div>
                    </div>
                </div>
                &nbsp;
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-success">
                                        <a href="javascript:$('#mainId').load('${path}/article/article.jsp')"> 文章信息</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                &nbsp;
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
                                上师管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="#">类别添加</a> <br/>
                            <a href="#">类别修改</a> <br/>
                            <a href="#">类别删除</a> <br/>
                        </div>
                    </div>
                </div>
                &nbsp;
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix">
                                管理员管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="#">类别添加</a> <br/>
                            <a href="#">类别修改</a> <br/>
                            <a href="#">类别删除</a> <br/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-10" id="mainId">
            <div class="jumbotron" height="100px">
                <p>欢迎来到持名法洲后台管理系统
                </p>
            </div>

        <!--轮播图-->
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <!--幻灯片的图片-->
            <div class="carousel-inner ">
                <div class="item active" align="center">
                    <img src=".././bootstrap/img/1.png" width="50%" height="50px" />
                </div>
                <div class="item" align="center">
                    <img src=".././bootstrap/img/2.png" width="50%" height="50px" />
                </div>
                <div class="item" align="center">

                    <img src=".././upload/photo/1564496510985-11.jpg"width="30%" height="50px" />
                </div>
                <div class="item" align="center">
                    <img src=".././bootstrap/img/4.png"width="50%" height="30px" />
                </div>
                <%--<div class="item" align="center">
                    <img src=".././bootstrap/img/arrow-up.png"width="60%" height="50px" />
                </div>--%>
                <div class="item" align="center">
                    <img src=".././bootstrap/img/shouye.jpg"width="100%" height="50px" />
                </div>
            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic"  data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic"  data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
             </div>
        </div>
    </div>
</div>

        <!--左边手风琴部分-->


        <!--巨幕开始-->
        <!--右边轮播图部分-->
        <!--页脚-->
    <!--栅格系统-->

</body>
</html>
