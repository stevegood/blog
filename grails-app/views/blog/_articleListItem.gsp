<div class="blog-post">

    <div class="blog-post-date">
        <!-- Icon -->
        <div class="blog-post-icon">
            <i class="icon-pencil"> </i>
        </div>
        <!-- Icon -->
    </div>

    <div class="blog-span">
        <div class="blog-post-featured-img img-overlay">
            <asset:image src="zeina/blog1.jpg" alt=""/>

            <div class="item-img-overlay">
                <div class="item_img_overlay_content">
                    <a href="${assetPath(src: 'zeina/blog1.jpg')}"
                       data-rel="prettyPhoto[portfolio]" title="${article.title}">
                        <i class="icon-search"></i>
                    </a>
                    <g:link controller="blog" action="show" params="${[slug: article.slug]}">
                        <i class="icon-link"></i>
                    </g:link>
                </div>
            </div>
        </div>
        <h2>
            <g:link controller="blog" action="show" params="${[slug: article.slug]}">
                ${article.title}
            </g:link>
        </h2>
        <div class="blog-post-details">

            <!-- Author Name -->
            <div class="blog-post-details-item blog-post-details-item-left">
                <i class="icon-time"></i>
                <a href="">
                    12 Nov, 2013
                </a>
            </div>
            <!-- //Author Name// -->

            <!-- Author Name -->
            <div class="blog-post-details-item blog-post-details-item-left">
                <i class="icon-user"></i>
                <g:link controller="user" action="profile" params="${[username: article.author.username]}">
                    ${article.author.fullName}
                </g:link>
            </div>
            <!-- //Author Name// -->

            <!-- Tags -->
            <div class="blog-post-details-item blog-post-details-item-left">
                <i class="icon-tags"></i>
                <a href="">
                    Business
                </a> ,
                <a href="">
                    Investment
                </a> ,
                <a href="">
                    Freelancing
                </a>
            </div>
            <!-- //Tags// -->

            <g:set var="commentCount" value="${article.commentCount}" />
            <g:if test="${commentCount}">
                <!-- Comments -->
                <div class="blog-post-details-item blog-post-details-item-left">
                    <i class="icon-comment"></i>
                    <a href="">
                        ${commentCount} Comment${commentCount != 1 ? 's' : ''}
                    </a>
                </div>
                <!-- //Comments// -->
            </g:if>

        </div>
        <div class="space-sep20"></div>
        <div class="blog-post-body">
            Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.                <!-- Read More -->
            <div class="blog-post-details-item blog-read-more">
                <a href="">
                    Continue Reading ....
                </a>
            </div>
            <!-- //Read More// -->
        </div>

    </div>
</div>
