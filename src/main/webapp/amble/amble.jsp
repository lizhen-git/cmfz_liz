<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<script>
    $(function(){
        $("#bntable").jqGrid({
            url : "${path}/album/showAlbumByPage",
            editurl:"${path}/album/edit",
            datatype : "json",
            rowNum : 5,
            autowidth:true,
            height:"auto",
            styleUI:"Bootstrap",
            rowList : [ 2, 4, 6 ],
            pager : '#bnpager',
            viewrecords : true,  //是否展示总条数
            colNames : [ 'Id', '名字', '封面', '作者','集数','评分','播音','内容', '发布时间' ],
            colModel : [
                {name : 'id',width : 55,align : "center" },
                {name : 'title',editable:true,width : 50,align : "center"},
                {name : 'cover_img',width : 80,editable:true,edittype:"file",align : "center",
                    formatter:function(cellvalue){
                        return "<img src='${path}/upload/album/"+cellvalue+"' style='width:180px;height:80px' />";
                    },// 返回图片。
                },
                {name : 'author',editable:true,width : 40,align : "center"},
                {name : 'counts',index : 'tax',width : 80,align : "center"},
                {name : 'score',index : 'tax',width : 80,align : "center"},
                {name : 'broadcast',index : 'tax',editable:true,width : 80,align : "center"},
                {name : 'content',index : 'tax',editable:true,width : 80,align : "center"},
                {name : 'pub_date',width : 50,align : "center" ,formatter:"date",formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d'}}
            ],
            subGrid : true, //开启子表格支持
            //subgrid_id  子表格的Id,当开启子表格式,会在主表格中创建一个子表格，subgrid_id就是这个子表格的Id
            subGridRowExpanded : function(subgridId, rowId) {
                addSubGrid(subgridId,rowId);
            },

        });

        /*增删改查操作*/
        $("#bntable").jqGrid('navGrid', '#bnpager',
            {edit : true,add : true,del : true,addtext:"添加",edittext:"编辑",deltext:"删除",},
            {afterSubmit:function(data){
                    //文件上传
                    $.ajaxFileUpload({
                        url:"${path}/album/uploadAlbum",
                        type:"post",
                        dataType:"JSON",
                        fileElementId:"cover_img",
                        data:{id:data.responseText},
                        success:function(){
                            //刷新表单
                            $("#bntable").trigger("reloadGrid");
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
                        url:"${path}/album/uploadAlbum",
                        type:"post",
                        dataType:"JSON",
                        fileElementId:"cover_img",
                        data:{id:data.responseText},
                        success:function(){
                            //刷新表单
                            $("#bntable").trigger("reloadGrid");
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

    //创建子表单
    function addSubGrid(subgridId,rowId) {

        var subgridTableId = subgridId + "table";
        var pagerId = subgridId+"pager";

        //初始化表单,分页工具栏
        $("#" + subgridId).html("<table id='" + subgridTableId+ "'/><div id='"+ pagerId + "'/>");

        //创建表单
        $("#" + subgridTableId).jqGrid({
            //url:"/chapter/queryByPage?albumId="+rowId,
            url:"${path}/charpter/showCharpterByPage?album_Id="+rowId,
            editurl: "${path}/charpter/edit?album_Id="+rowId,
            datatype : "json",
            rowNum : 20,
            pager : "#"+pagerId,
            sortname : 'num',
            sortorder : "asc",
            autowidth:true,
            height : "auto",
            styleUI:"Bootstrap",
            colNames : [ 'Id', '名字', '音频大小', '音频时长','上传时间','操做' ,],
            colModel : [
                {name : "id",   width : 80, },
                {name : "url",index : "item", editable:true , width : 130,align:"center",edittype: "file", },
                {name : "size",index : "qty",width : 70,align : "center"},
                {name : "duration",index : "unit",width : 70,align : "center"},
                {name : "up_date",index : "total",width : 70,align : "center",formatter:"date",formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d'}},
                {name : "url",align : "center",
                    formatter:function(cellVale){

                        return "<a href='#' onclick='playerCharpter(\""+cellVale+"\")' ><span class='glyphicon glyphicon-play-circle' /></a>&nbsp;&emsp;&emsp;" +
                            "<a href='#' onclick='downloadCharpter(\""+cellVale+"\")' ><span class='glyphicon glyphicon-download' /></a>";
                    }
                }


            ]
        });

        /*子表格增删改查*/
        $("#" + subgridTableId).jqGrid('navGrid',"#" + pagerId,
            {edit : false,add : true,del : true,addtext:"添加",edittext:"编辑",deltext:"删除",},
            {},
            {
                afterSubmit:function(data){
                    //文件上传
                    $.ajaxFileUpload({
                        url:"${path}/charpter/uploadChpater",
                        type:"post",
                        dataType:"JSON",
                        fileElementId:"url",
                        data:{id:data.responseText},
                        success:function(){
                            //刷新表单
                            $("#bntable").trigger("reloadGrid");
                        }
                    })
                    //一定要有返回值，返回什么都行
                    return "hello1";
                },
                closeAfterAdd:true,
            },
            {}
        );
    }
    //下载
    function downloadCharpter(fileName){
        location.href="${path}/charpter/downloadCharpter?fileName="+fileName;
    }

    //在线播放
    function playerCharpter(fileName){

        //展示模态框
        $("#audioModal").modal("show");
        //播放
        $("#playAudio").attr("src","${path}/upload/music/"+fileName);
    }


</script>

<%--初始化面板--%>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>专辑信息</h2>
    </div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#">专辑管理</a></li>
    </ul>

    <div class="panel panel-body">
        <button type="button" class="btn btn-info" >添加专辑</button>
        <button type="button" class="btn btn-success" >修改专辑</button>
        <button type="button" class="btn btn-success" onclick="" >显示所有轮播图</button>
    </div>

    <div id="show" class="alert alert-warning alert-dismissible" role="alert"  style="width:200px;display: none">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong id="showMsg"></strong>
    </div>


    <%--初始化表单--%>
    <table id="bntable" align="center"  />

    <%--定义分页工具栏--%>
    <div id="bnpager"></div>

    <div id="audioModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <audio id="playAudio" src="" controls/>
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>



