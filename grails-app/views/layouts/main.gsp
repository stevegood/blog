<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default=""/> - stevegood.org</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <asset:stylesheet src="main"/>
    <g:layoutHead/>
</head>

<body>

<div id="wrapper">
    <div class="top_wrapper">
        <g:render template="/layouts/main/topnav"/>

        <div class="top-title-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 page-info">
                        <h1 class="h1-page-title"><g:layoutTitle/></h1>

                        <h2 class="h2-page-desc">
                            What We have Done Lately
                        </h2>

                        <!-- BreadCrumb -->
                        <div class="breadcrumb-container">
                            <ol class="breadcrumb">
                                <li>
                                    <a href="">
                                        <i class="icon-home"></i>
                                        Home
                                    </a>
                                </li>
                                <li>Blog</li>
                            </ol>
                        </div>
                        <!-- BreadCrumb -->

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="loading-container">
        <div class="loading">
            <i></i><i></i>
        </div>

        <div class="loading-fallback">
            <asset:image src="zeina/loading.gif" alt="Loading"/>
        </div>

        <div class="loading-text">
            loading..
        </div>
    </div>


    <div class="content-wrapper hide-until-loading">
        <div class="body-wrapper">
            <div class="container">
                <g:layoutBody/>
            </div>
        </div>
    </div>
</div>


<asset:javascript src="main"/>
</body>
</html>
