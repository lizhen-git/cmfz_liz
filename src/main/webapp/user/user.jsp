<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<script>
    $(function(){
        $("#usertable").jqGrid({
            url : "${path}/user/showUserByPage",
            editurl:"${path}/user/edit",
            datatype : "json",
            rowNum : 5,
            autowidth:true,
            height:"auto",
            styleUI:"Bootstrap",
            rowList : [ 2, 4, 6 ],
            pager : '#bnpager',
            viewrecords : true,  //是否展示总条数
            colNames : [ 'Id', '头像', '名字', '法名', '密码','性别','地址', '状态','手机号','注册时间',],
            colModel : [
                {name : 'id',width : 55,align : "center" },
                {name : 'pic_img',width : 60,editable:true,edittype:"file",align : "center",
                    formatter:function(cellvalue){
                        return "<img src='${path}/upload/userphoto/"+cellvalue+"' style='width:180px;height:80px' />";
                    },// 返回图片。
                },
                {name : 'name',editable:true,width : 40,align : "center"},
                {name : 'ahama',editable:true,width : 40,align : "center"},
                {name : 'password',editable:true,width : 40,align : "center"},
                {name : 'sex',editable:true,width : 40,align : "center"},
                {name : 'city',editable:true,width : 40,align : "center"},
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
                {name : 'phone',editable:true,width : 40,align : "center"},
                {name : 'reg_date',width : 50,align : "center" ,formatter:"date",formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d'}}
            ]

        });

        /*增删改查操作*/
        $("#usertable").jqGrid('navGrid', '#bnpager',
            {edit : true,add : true,del : true,addtext:"添加",edittext:"编辑",},
            {afterSubmit:function(data){
                    //文件上传
                    $.ajaxFileUpload({
                        url:"${path}/user/uploadUser",
                        type:"post",
                        dataType:"JSON",
                        fileElementId:"pic_img",
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
                        url:"${path}/user/uploadUser",
                        type:"post",
                        dataType:"JSON",
                        fileElementId:"pic_img",
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
                url:"${path}/user/updateStatus",
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
                url:"${path}/user/updateStatus",
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

    /*点击导出用户*/
    $("#btn1").click(function(){
        alert("点击导出用户信息");



            //向后台提交
            $.ajax({
                url:"${path}/user/selectAllUser",
                type:"post",
                dataType:"json",
                data:$("#articleFrom").serialize(),
                success:function(data){
                    //提示框添加信息
                    $("#showMsg").html(data.message);
                    //展示错误信息
                    $("#show").show();
                }
            });
    });

</script>

<%--初始化面板--%>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#">用户管理</a></li>
    </ul>

    <div class="panel panel-body">
        <button type="button" id="btn1" class="btn btn-info" >导出用户信息</button>
        <button type="button" class="btn btn-success" >导入用户信息</button>
        <button type="button" class="btn btn-success" onclick="" >测试按钮</button>
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



