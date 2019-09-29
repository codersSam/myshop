<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"></jsp:include>
     <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css">
     <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css">
     <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/dropzone.min.css">
     <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css">

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
            <div class="row">

                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>

                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbContent.id == null ? "新增":"编辑"}内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post"
                                   modelAttribute="tbContent">
                            <form:hidden path="id"></form:hidden>

                            <div class="box-body">

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">父级类目</label>

                                    <div class="col-sm-10">
                                        <form:hidden id="categoryId" path="tbContentCategory.id"></form:hidden>
                                        <input id = "categoryName" class="form-control required" placeholder="请选择" readonly="true" data-toggle="modal" data-target="#modal-default" value="${tbContent.tbContentCategory.name}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label">标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="title" cssClass="form-control required" placeholder="子标题"></form:input>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-2 control-label">子标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="subTitle" cssClass="form-control required" placeholder="子标题"></form:input>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>

                                    <div class="col-sm-10">
                                        <form:input path="titleDesc" cssClass="form-control required" placeholder="标题描述"></form:input>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">链接</label>

                                    <div class="col-sm-10">
                                        <form:input path="url" cssClass="form-control required" placeholder="链接"></form:input>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label">图片1</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic" cssClass="form-control required" placeholder="图片1"></form:input>
                                        <div id="dropz" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic2" class="col-sm-2 control-label">图片2</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic2" cssClass="form-control required" placeholder="图片2"></form:input>
                                        <div id="dropz2" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="content" class="col-sm-2 control-label">详情</label>
                                    <form:hidden path="content"></form:hidden>

                                    <div class="col-sm-10">
                                        <div id="editor">
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button id="btn_submit" type="submit" class="btn btn-info pull-right">提交</button>
                            </div>

                        </form:form>


                    </div>
                    <!-- /.box -->
                </div>

            </div>
        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"></jsp:include>

</div>

<jsp:include page="../includes/footer.jsp"></jsp:include>
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.js"></script>
<script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
<sys:modal title="请选择" message="<ul id='myTree' class='ztree'></ul>"></sys:modal>

</body>
<script>
    $(function () {
        App.initZTreeData("/content/category/tree/data",["id"],function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree"),
                nodes = zTree.getSelectedNodes();
            if(nodes.length == 0){
                alert("请选择一个父节点");
            }else {
                $("#categoryId").val(nodes[0].id);
                $("#categoryName").val(nodes[0].name);
                $("#modal-default").modal("hide");
            }
        })

        initWangEditor();
    })
   App.initDropzone({
       id:"#dropz",
       url:"/upload",
       method:"post",
       dictDefaultMessage: '拖动文件至此或者点击上传',
       init:function () {
           this.on("success", function (file, data) {
               var _pic = data.fileName;
               $("#pic").val(_pic);
           });
       }
   });
    App.initDropzone({
        id:"#dropz2",
        url:"/upload",
        method:"post",
        dictDefaultMessage: '拖动文件至此或者点击上传',
        init:function () {
            this.on("success", function (file, data) {
                var _pic2 = data.fileName;
                $("#pic2").val(_pic2);
            });
        }
    });

    /**
     * 初始化wangEditor
     */
    function initWangEditor() {
        var E = window.wangEditor
        var editor = new E('#editor')
        editor.customConfig.uploadImgServer = '/upload'
        editor.customConfig.uploadFileName = 'editorFile'
        editor.create()

        $("#btn_submit").bind("click",function () {
            var _content = editor.txt.html();
            $("#content").val(_content);
        })
    }
</script>
</html>
