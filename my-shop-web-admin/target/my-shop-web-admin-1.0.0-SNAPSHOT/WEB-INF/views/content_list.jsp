<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../includes/nav.jsp"></jsp:include>

    <jsp:include page="../includes/menu.jsp"></jsp:include>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                内容管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- Horizontal Form -->
            <div class="box box-info box-info-search" style="display: none;">
                <div class="box-header with-border">
                    <h3 class="box-title">高级搜索</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <div class="box-body form-horizontal">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12">
                            <div class="col-xs-12 col-sm-3">
                                <div class="form-group">
                                    <label for="title" class="col-sm-3 control-label">标题</label>
                                    <div class="col-sm-8">
                                        <input class="form-control"id="title" placeholder="标题">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3">
                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-3 control-label">子标题</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="subTitle" placeholder="子标题">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3">
                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-3 control-label">标题描述</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="titleDesc" placeholder="标题描述">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="box-footer">
                    <button type="button" class="btn btn-info pull-right" onclick="search()">搜索</button>
                </div>
                <!-- /.box-footer -->
            </div>
            <!-- /.box -->

            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>
                        </div>
                        <div class="box-body">
                            <a href="/content/form" type="button" class="btn btn-sm  btn-default"><i
                                    class="fa fa-plus"></i>新增</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm  btn-default"
                                    onclick="App.deleteMutil('/content/delete')"><i
                                    class="fa fa-trash-o"></i>删除
                            </button>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm  btn-default"><i
                                    class="fa fa-download"></i>导入</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm  btn-default"><i
                                    class="fa fa-upload"></i>导出</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm  btn-primary"
                                    onclick="$('.box-info-search').css('display') == 'none' ?
                                             $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')"><i
                                    class="fa fa-search"></i>搜索
                            </button>&nbsp;&nbsp;&nbsp;
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="pageList" class="table table-hover">
                                <thead>
                                <tr>
                                    <td><input type="checkbox" class="minimal icheck_master"></td>
                                    <th>ID</th>
                                    <th>分类名称</th>
                                    <th>标题</th>
                                    <th>子标题</th>
                                    <th>标题描述</th>
                                    <th>url</th>
                                    <th>图片1</th>
                                    <th>图片2</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"></jsp:include>

</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<sys:modal></sys:modal>
<script>
    var _dataTable;
    $(function () {
        var _columns = [
            {
                "data": function (row, type, val, meta) {
                    return '<input id="' + row.id + '" type="checkbox" class="minimal" />';
                }
            },
            {"data": "id"},
            {"data": "tbContentCategory.name" },
            {"data": "title"},
            {"data": "subTitle"},
            {"data": "titleDesc"},
            {"data": function(row, type, val, meta){
                if(row.url == null || row.url == ""){
                    return "";
                }
                return '<a href= \"'+ row.url + ' \"  target="_blank">查看</a>'
            }},
            {"data": function(row, type, val, meta){
                if(row.pic == null || row.pic == ""){
                    return "";
                }
                return '<a href= \"'+ row.pic + ' \" target="_blank">查看</a>'
            }},
            {"data": function(row, type, val, meta){
                if(row.pic2 == null || row.pic2 == ""){
                    return "";
                }
                return '<a href= \"'+ row.pic2 + ' \" target="_blank">查看</a>'
            }},
            {"data": function(row, type, val, meta){
                return DateTime.format(row.updated,"yyyy-MM-dd HH:mm:ss")
            }},
            {
                "data": function (row, type, val, meta) {
                    return '<button type="button" class="btn btn-sm btn-default" onclick="App.showDetail(\'/content/detail?id=' + row.id + '\')"><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;' +
                        '<a href="/content/form?id=' + row.id + '\"' + 'type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;' +
                        '<button type="button" class="btn btn-sm btn-danger" onclick="App.deleteSingle(\'/content/delete\', ' + row.id  + ')"><i class="fa fa-trash-o"></i> 删除</button>'
                }
            }
        ]
        _dataTable = App.initDataTables("/content/page", _columns)
    });
    function search() {
        var _title = $("#title").val();
        var _subTitle = $("#subTitle").val();
        var _titleDesc = $("#titleDesc").val();
        var param = {
            "title":_title,
            "subTitle":_subTitle,
            "titleDesc":_titleDesc
        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }
</script>
</body>
</html>
