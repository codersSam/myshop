/**
 * Created by Administrator on 2019/1/22.
 */
var App = function () {
    //iCheck
    var _masterCheckbox;
    var _checkbox;
    //批量删除
    //定义一个存放ID的数组
    var _idArray = new Array();

    /**
     * 私有方法，初始化ICheck
     */
    var handlerInitCheckbox = function () {
        //激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        });
        //获取控制端checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');
        //获取全部的checkbox集合
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * 全选功能
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked", function (e) {
            //没有选中
            if (e.target.checked) {
                _checkbox.iCheck('uncheck');
            }
            //选中
            else {
                _checkbox.iCheck('check');
            }
        });
    };
    /**
     * 单个删除
     * @param url
     * @param id
     * @param msg
     */
    var handlerDeleteSingle = function (url,id,msg) {
        if(!msg) msg = null;
        _idArray = new Array();
        _idArray.push(id);

        $("#modal-message").html(msg == null?"您确认删除数据项吗" : msg);
        $("#modal-default").modal("show");
        $("#btnModalOk").bind("click",function () {
            handlerDeleteData(url);
        });
    }

    /**
     * 批量删除
     */
    var handlerDeleteMulti = function (url) {
        _checkbox.each(function () {
            var _id = $(this).attr("id")
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        })
        if (_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何信息，请选择至少一条信息");
        }
        else {
            $("#modal-message").html("您确认删除所选中的信息吗？");
            $("#btnModalDelet").bind("click",function () {
                window.location.reload();
            })
        }
        $("#modal-default").modal("show");

        $("#btnModalOk").bind("click", function () {
            handlerDeleteData(url);
        });
    };
    /**
     * 数据删除
     * @param url
     */
    var handlerDeleteData =function (url) {
        $("#modal-default").modal("hide");
        $("#btnModalOk").unbind("click");
        if (_idArray === 0) {
            //......
        }
        else {
            setTimeout(function () {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": {"ids": _idArray.toString()},
                    "dataType": "JSON",
                    "success": function (data) {
                        //删除成功
                        if (data.status === 200) {
                            $("#btnModalOk").bind("click", function () {
                                window.location.reload();
                            })
                        }
                        //删除失败
                        else {
                            $("#btnModalOk").bind("click", function () {
                                $("#modal-default").modal("hide");
                            });
                        }
                        //显示信息提示信息
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    }
                });
            }, 500);
        }
    }


    /**
     * 全选功能初始化
     * @param url
     * @param columns
     * @returns {jQuery}
     */
    var handlerIntiDataTables = function (url,columns) {
        var _dataTable = $('#pageList').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "processing": true,
            "deferRender": true,
            "ordering": false,
            "serverSide": true,
            "ajax": {
                "url": url
            },
            columns: columns,
            "drawCallback": function( settings ) {
                //重绘事件激活全选功能
                handlerInitCheckbox();
                handlerCheckboxAll();
            },
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });
        return _dataTable;
    }

    /**
     * dropzone参数对象
     * @type {{url: string, method: string, paramName: string, maxFiles: number, maxFilesize: number, acceptedFiles: string, addRemoveLinks: boolean, parallelUploads: number, dictDefaultMessage: string, dictMaxFilesExceeded: string, dictResponseError: string, dictInvalidFileType: string, dictFallbackMessage: string, dictFileTooBig: string, dictRemoveLinks: string, dictCancelUpload: string, init: init}}
     */
     var defaultDropzoneFile = {
         url: "", // 文件提交地址
         method: "",  // 也可用put
         paramName: "dropzFile", // 默认为file
         maxFiles: 1,// 一次性上传的文件数量上限
         maxFilesize: 2, // 文件大小，单位：MB
         acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
         addRemoveLinks: true,
         parallelUploads: 1,// 一次上传的文件数量
         dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles + "个文件！",
         dictResponseError: '文件上传失败!',
         dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
         dictFallbackMessage: "浏览器不受支持",
         dictFileTooBig: "文件过大上传文件最大支持.",
         dictRemoveLinks: "删除",
         dictCancelUpload: "取消"
     }
    /**
     * 初始化Dropzone
     */
    var handlerInitDropzone = function(obj){
        var dropzoneFile  = $.extend(defaultDropzoneFile,obj);
        var myDropzone = new Dropzone(dropzoneFile.id,dropzoneFile);

    }

    var handlerShowDetail = function (url) {
        //通过Ajax请求将jsp转载进模态框
        $.ajax({
            url:url,
            type:"get",
            dateType:"html",
            success:function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    }
    /**
     * 初始化zTreeData
     * @param url
     * @param autoParam
     * @param callback
     */
    var handlerInitZTreeData = function (url,autoParam,callback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url:url,
                autoParam: autoParam
            }
        }
        $.fn.zTree.init($("#myTree"), setting);

        $("#btnModalOk").bind("click",callback);
    }



    return {
        /**
         * 初始化
         */
        init: function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },
        /**
         * 批量删除
         * @param url
         */
        deleteMutil: function (url) {
            handlerDeleteMulti(url);
        },
        deleteSingle:function (url,id,msg) {
            handlerDeleteSingle(url,id,msg);
        },
        /**
         * 初始化 dataTable
         * @param url
         * @param columns
         */
        initDataTables: function (url,columns) {
            return handlerIntiDataTables(url,columns);
        },
        /**
         * 显示详情
         * @param url
         */
        showDetail:function (url) {
            handlerShowDetail(url);
        },
        /**
         * 初始化zTree对象
         * @param url
         * @param antoParamt
         * @param callback
         */
        initZTreeData:function (url,antoParamt,callback) {
            handlerInitZTreeData(url,antoParamt,callback);
        },
        /**
         * dropzone初始化
         * @param obj
         */
        initDropzone:function(obj){
            Dropzone.autoDiscover = false;
            handlerInitDropzone(obj);
        }



    };
}();

$(document).ready(function () {
    App.init();
});

