<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${blog.title} - BLog</title>
    <link rel="icon" href="/static/image/logo.ico" type="img/x-ico"/>
    <link href="/static/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/plugin/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/idea.css">
    <link rel="stylesheet" type="text/css" href="/static/plugin/mdeditor/css/editormd.min.css">

    <!-- Custom styles for this template -->
    <link href="/static/css/global.css" rel="stylesheet">
    <link href="/static/css/article.css" rel="stylesheet">
</head>

<style>

</style>

<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top"
     style="background-color: rgb(255,255,255); border-bottom: 1px solid #e9e9e9">
    <div class="container">
        <a class="navbar-brand" href="#" style="color: #ea6f5a; font-weight: bold">Blog</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto text-center">
                <li class="nav-item">
                    <a class="nav-link" href="/index.html">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/about.html">About</a>
                </li>
            </ul>
            <div id="search" class="input-group col-lg-3">
                <input type="text" class="form-control" placeholder="Search for..." style="border-radius: 12px">
                <span class="input-group-btn">
                    <a class="btn btn-secondary" onclick="getArticleByTitle($('#search-title').val())"
                       style="z-index: 100;position: absolute; top: 0; right: 15px; background-color: #ea6f5a; color: #ffffff; border-color: #ea6f5a; border-top-right-radius: 12px; border-bottom-right-radius: 12px">
                        <i class="fa fa-search"></i>
                    </a>
                </span>
            </div>
        </div>
    </div>
</nav>

<!-- container -->
<div class="container">
    <div class="row">
        <div class="post">

            <div id="article" class="article">
                <h1 id="title" class="title">${blog.title}</h1>
                <%--????????????--%>
                <div id="reprint" class="reprint" <c:if test="${not empty blog.reprint}">style="visibility: visible"</c:if> <c:if test="${not empty blog.reprint}">style="visibility: hidden"</c:if>>
                    <c:if test="${not empty blog.reprint}">
                        <a target="_blank" href="${blog.reprint}" style="font-size: 12px; color: #333333">??????${blog.reprint}</a>
                     </c:if>
                </div>
                <!-- ???????????? -->
                <div id="doc-content" style="word-wrap: break-word; word-break: normal;padding: 0">
                    <textarea id="doc-content-markdown-doc" style="display:none;">${blog.content}</textarea>
                </div>

                <div class="copyright"><i class="fa fa-copyright"></i>??????????????????????????????</div>
                <div style="text-align: center; margin-top: 40px">
                    <button id="like-btn" class="btn btn-like" onclick="addLike(articleId)">?????? ${blog.likeCount}</button>
                </div>
            </div>

            <div id="comment-list" class="comment-list">
                <div>
                    <form class="new-comment">
                        <a class="avatar">
                            <img id="myAvatar" onclick="openModal()">
                        </a>
                        <textarea id="comment-text" placeholder="??????????????????..."></textarea>
                        <div class="write-function-block">
                            <div class="emoji-modal-wrap">
                                <a data-v-b36e9416="" class="emoji">
                                    <i data-v-b36e9416="" class="fa fa-smile-o"></i>
                                </a>
                            </div>
                            <a id="submitComment" class="btn btn-send" href="javascript:void(0)" onclick="submitComment(commentSendInterval, articleId)">??????</a>
                        </div>
                    </form>
                </div>
                <hr style="color: #f0f0f0;"/>
                <div id="featured-comment-list"></div>
            </div>

            <!--??????????????????-->

        </div>
    </div>
</div>


<!-- Profile Modal -->
<div class="modal fade" id="visitorProfile" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content" style="border-radius: 16px">
            <div class="modal-header">
                <h5 class="modal-title" id="visitorProfileTitle">???????????????????????????</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="comment-form" enctype="multipart/form-data">
                    <div class="form-group row">
                        <label for="username" class="col-sm-2 col-form-label">??????</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="username" placeholder="??????" name="visitorName">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="imageFile" class="col-sm-2 col-form-label">??????</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control-file" id="imageFile" name="imageFile">
                        </div>
                    </div>
                    <input type="hidden" name="content" id="content">
                    <input type="hidden" name="blogId" id="blogId">
                    <input type="hidden" name="visitorAvatar" id="visitorAvatar">
                </form>
            </div>
            <div class="modal-footer" id="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="closeModal()">??????</button>
                <button type="button" class="btn btn-primary" onclick="addProfile()">??????</button>
            </div>
        </div>
    </div>
</div>

<!--back to top-->
<a href="#" class="cd-top"><img style="width: 48px; height: 48px;"
                                src="/static/image/top.png"></a>

<!-- Bootstrap core JavaScript -->
<script src="/static/js/jquery-3.3.1.min.js"></script>
<script src="/static/js/jquery.form.min.js"></script>
<script src="/static/js/jquery.cookie.js"></script>
<script src="/static/plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/plugin/qqFace/js/jquery.qqFace.js"></script>
<script src="/static/plugin/mdeditor/lib/marked.min.js"></script>
<script src="/static/plugin/mdeditor/lib/prettify.min.js"></script>
<script src="/static/plugin/mdeditor/lib/raphael.min.js"></script>
<script src="/static/plugin/mdeditor/lib/underscore.min.js"></script>
<script src="/static/plugin/mdeditor/lib/sequence-diagram.min.js"></script>
<script src="/static/plugin/mdeditor/lib/flowchart.min.js"></script>
<script src="/static/plugin/mdeditor/lib/jquery.flowchart.min.js"></script>
<script src="/static/plugin/mdeditor/editormd.min.js"></script>
<!-- Custom JavaScript -->
<script src="/static/js/global.js"></script>
<script src="/static/js/article.js"></script>

<script>

    //Article?????????????????????id
    var articleId = ${blog.id};

    // ???????????????????????????????????????
    var commentSendInterval = 10;

    // ??????????????????
    var defaultAvatar = "/static/image/addProfile.png";

    $(function () {

        //?????????qqFace??????
        $('.emoji').qqFace({
            id: 'facebox',
            assign: 'comment-text',
            path: '/static/plugin/qqFace/arclist/'	//?????????????????????
        });

        editormd.markdownToHTML("doc-content", {//????????????????????????DIV???id
            htmlDecode: "style,script,iframe",
            // emoji: true,
            taskList: true,
            tex: true, // ???????????????
            flowChart: true, // ???????????????
            sequenceDiagram: true, // ???????????????
            codeFold: true,
        });

        //?????????????????????
        addClick(articleId);

        //??????cookie
        if($.cookie("ck_avatar")) {
            $("#myAvatar").attr("src", $.cookie("ck_avatar"));
        }else {
            console.log("undefined avatar");
            $("#myAvatar").attr("src", defaultAvatar);
        }
        $("#username").val($.cookie("ck_visitorName"));

        //?????????????????????
        var page = 1;
        var rows = 10;
        var isEnd = false;

        /*????????????*/
        isEnd = addMore(page, rows, articleId);

        /*??????????????????*/
        $(window).scroll(function () {
            if (isEnd == true) {
                return;
            }

            // ???????????????????????????100???????????? ???????????????
            // ????????????
            if ($(document).height() - $(this).scrollTop() - $(this).height() < 100) {
                page++;
                isEnd = addMore(page, rows, articleId);
            }
        });
        //?????????????????????end
    });

</script>
</body>
</html>

