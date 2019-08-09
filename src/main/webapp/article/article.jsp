<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.create('#editor_id', {
        uploadJson:"${path}/editor/upload",
        filePostName:"articlephoto",
        allowFileManager:true,
        fileManagerJson:"${path}/editor/queryAllPhoto",
        afterBlur:function (){  //编辑器失去焦点(blur)时执行的回调函数。
            this.sync();  //将编辑器中的内容同步到表单
        }
    });
</script>


<script>
    $(function(){
        $("#usertable").jqGrid({
            url : "${path}/article/showArticleBypage",
            editurl:"${path}/article/edit",
            datatype : "json",
            rowNum : 5,
            autowidth:true,
            height:"auto",
            styleUI:"Bootstrap",
            rowList : [ 2, 4, 6 ],
            pager : '#bnpager',
            viewrecords : true,  //是否展示总条数
            colNames : [ 'Id', '封面', '标题', '内容', '上传时间', '状态','上师ID','操作',],
            colModel : [
                {name : 'id',width : 55,align : "center" },
                {name : 'insert_img',width : 60,editable:true,edittype:"file",align : "center",
                    formatter:function(cellvalue){
                        return "<img src='${path}/upload/article/"+cellvalue+"' style='width:180px;height:80px' />";
                    },// 返回图片。
                },
                {name : 'title',width : 40,align : "center"},
                {name : 'content',width : 50,align : "center" ,},
                {name : 'up_date',width : 50,align : "center" ,formatter:"date",formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d'}},

                {name : 'status',width : 40,align : "center",
                    formatter:function(cellvalue,option,row){
                        if(cellvalue==1){
                            //展示状态
                            return "<button class='btn btn-success'  onclick='change(\""+row.id+"\",\""+cellvalue+"\")'  >不展示</button>";
                        }else{
                            //不展示状态
                            return "<button class='btn btn-danger' onclick='change(\""+row.id+"\",\""+cellvalue+"\")'  >展示</button>";
                        }
                    },
                },
                {name : 'guruId',editable:true,width : 40,align : "center"},
                {name : "url",align : "center",
                    formatter:function(cellVale){

                        return "<a href='#' onclick='playerCharpter(\""+cellVale+"\")' ><span class='glyphicon glyphicon-play-circle' /></a>";
                    }
                }
            ]

        });

        /*增删改查操作*/
        $("#usertable").jqGrid('navGrid', '#bnpager',
            {edit : true,add : false ,del : true,addtext:"添加",edittext:"编辑",},
            {afterSubmit:function(data){
                    //文件上传
                    $.ajaxFileUpload({
                        url:"${path}/article/uploadArticle",
                        type:"post",
                        dataType:"JSON",
                        fileElementId:"insert_img",
                        data:{id:data.responseText},
                        success:function(){
                            //刷新表单
                            $("#usertable").trigger("reloadGrid");
                        }
                    })
                    //一定要有返回值，返回什么都行
                    return "hello1";
                },
                closeAfterEdit:true,},
            {
                afterSubmit:function(data){
                    //文件上传
                    $.ajaxFileUpload({
                        url:"${path}/article/uploadArticle",
                        type:"post",
                        dataType:"JSON",
                        fileElementId:"insert_img",
                        data:{id:data.responseText},
                        success:function(){
                            //刷新表单
                            $("#usertable").trigger("reloadGrid");
                        }
                    })
                    //一定要有返回值，返回什么都行
                    return "hello1";
                },
                closeAfterAdd:true,

            },
            {}
        );
    });

    //点击修改
    function change(id,value){

        if(value==1){

            $.ajax({
                url:"${path}/article/updateStatus",
                type:"post",
                dataType:"JSON",
                data:{"id":id,"status":"2"},
                success:function(data){
                    //页面的刷新
                    $("#usertable").trigger("reloadGrid");
                    //提示框添加信息
                    $("#showMsg").html(data.message);
                    //展示错误信息
                    $("#show").show();

                    //设置提示框时间
                    setTimeout(function () {
                        //关闭提示框
                        $("#show").hide();
                    }, 3000);
                }
            });
        }else{
            $.ajax({
                url:"${path}/article/updateStatus",
                type:"post",
                dataType:"JSON",
                data:{"id":id,"status":"1"},
                success:function(data){
                    //页面的刷新
                    $("#usertable").trigger("reloadGrid");
                    //提示框添加信息
                    $("#showMsg").html(data.message);
                    //展示错误信息
                    $("#show").show();
                    //设置提示框时间
                    setTimeout(function () {
                        //关闭提示框
                        $("#show").hide();
                    }, 3000);
                }
            });
        }

    }

    /*点击展示详情*/
    $("#btn1").click(function(){
        //选中一行 获取行Id
        var rowId= $("#usertable").jqGrid("getGridParam","selrow");
        //判断是否选中一行
        if(rowId!=null){
            //根据行Id获取行数据
            var row= $("#usertable").jqGrid("getRowData",rowId);

            //展示模态框
            $("#myModal").modal("show");

            //给inout框设置内容
            $("#title").val(row.title);

            //给KindEditor框设置内容
            KindEditor.html("#editor_id",row.content);

            //添加按钮
            $("#modalFooter").html("<button type='button' onclick='updateArticle(\""+rowId+"\")' class='btn btn-default'>提交</button><button type='button' class='btn btn-primary' data-dismiss='modal'>关闭</button>");
        }else{
            alert("请选中一行数据");
        }

    });

    /*点击添加文章*/
    $("#btn2").click(function(){

        //给表单置空
        $("#articleFrom")[0].reset();

        //给KindEditor框置空
        KindEditor.html("#editor_id","");

        //展示模态框
        $("#myModal").modal("show");
        //添加按钮
        $("#modalFooter").html("<button type='button' onclick='addArticle()' class='btn btn-default'>提交"+
            "</button><button type='button' class='btn btn-primary' data-dismiss='modal'>关闭</button>");
            });

    /*点击添加按钮操作*/
    function addArticle(){
        //向后台提交
        $.ajax({
            url:"${path}/article/add",
            type:"post",
            dataType:"json",
            data:$("#articleFrom").serialize(),
            success:function(){
                $("#myModal").modal('hide');//隐藏模态框
                $("#usertable").trigger("reloadGrid");//刷新jqGrid
            }
        });
    }

    /*点击删除文章*/
    $("#btn3").click(function(){
        alert("点击删除文章");

        //选中一行 获取行Id
            var rowId= $("#usertable").jqGrid("getGridParam","selrow");
            //判断是否选中一行


            //向后台提交
            $.ajax({
                url:"${path}/article/deleteArticle?ArticleId="+rowId,
                type:"post",
                dataType:"json",
                data:$("#articleFrom").serialize(),
                success:function(){
                    $("#usertable").trigger("reloadGrid");//刷新jqGrid
                }
            });


    });
    /*修改的提交按钮*/
    function updateArticle(rowId){

        //向后台提交
        $.ajax({
            url:"${path}/article/update?ArticleId="+rowId,
            type:"post",
            dataType:"json",
            data:$("#articleFrom").serialize(),
            success:function(){
                $("#myModal").modal('hide');//隐藏模态框
                $("#usertable").trigger("reloadGrid");//刷新jqGrid
            }
        });

    }

</script>

<%--初始化面板--%>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>文章管理</h2>
    </div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#">文章信息</a></li>
    </ul>

    <div class="panel panel-body">
        <button type="button" id="btn1" class="btn btn-info" >查看文章</button>&emsp;
        <button type="button" id="btn2" class="btn btn-success" >添加文章</button>&emsp;
        <button type="button" id="btn3" class="btn btn-warning"  >删除文章</button>&emsp;
    </div>

    <div id="show" class="alert alert-warning alert-dismissible" role="alert"  style="width:200px;display: none">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong id="showMsg"></strong>
    </div>


    <%--初始化表单--%>
    <table id="usertable" align="center"  />

    <%--定义分页工具栏--%>
    <div id="bnpager"></div>

</div>

<%--初始化模态框--%>

<div id="myModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document" style="width: 730px">
        <div class="modal-content">
            <%--模态框标题--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
            </div>
            <%--模态框内容--%>
            <div class="modal-body">
                <form class="form-horizontal" id="articleFrom">

                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon1">标题</span>
                        <input id="title" name="title" type="text" class="form-control" aria-describedby="basic-addon1">
                    </div><br>
                    <div class="input-group">
                        <%--初始化富文本编辑器--%>
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;">

                        </textarea>
                    </div>
                </form>
            </div>
            <%--  模态框按钮  --%>
            <div class="modal-footer" id="modalFooter">
                <%--<button type="button" class="btn btn-default">提交</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>--%>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



