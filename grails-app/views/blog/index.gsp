<%--
  User: steve
  Date: 9/8/14
  Time: 9:30 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Welcome!</title>
    <meta name="layout" content="main"/>
</head>

<body>

<div class="row">
    <div class="col-md-9 col-sm-9">
        <g:each in="${articles}" var="article">
            <g:render template="articleListItem" model="${[article: article]}" />
        </g:each>
    </div>

    <div class="col-md-3 col-sm-3">
        <g:render template="sidebar" />
    </div>
</div>

</body>
</html>
