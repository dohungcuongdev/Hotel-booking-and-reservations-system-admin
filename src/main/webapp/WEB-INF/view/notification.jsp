<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>

<%

%>

<!-- Main row -->
<div class="row">

    <div class="col-md-8">
        <section class="panel">
            <header class="panel-heading">
                Notifications
            </header>
            <div class="panel-body" id="noti-box">
                <c:if test="${activity.click.equals('contact') || activity.click.equals('reservation')}"> 
                    <div class="alert alert-warning">
                        <button data-dismiss="alert" class="close close-sm" type="button"></button>
                        <strong>${activity.time}!</strong>
                        <h5>${activity.username}</h5>
                        <h5>Details: ${activity.details}</h5>
                        <h5>Note: ${activity.note}</h5>
                        <h5>Response ${activity.response}</h5>
                        <h5>Contents: </h5>
                        <p>${activity.content}</P>
                    </div>
                </c:if>
                <c:if test="${activity.click.equals('feedback') || activity.name.equals('Feedback Room')}"> 
                    <div class="alert alert-info">
                        <button data-dismiss="alert" class="close close-sm" type="button"></button>
                        <strong>${activity.time}!</strong>
                        <h5>Username: ${activity.username}</h5>
                        <h5>Full Name: ${activity.fullname}</h5>
                        <h5>Email: ${activity.email}</h5>
                        <h5>Phone: ${activity.phone}</h5>
                        <h5>Details: ${activity.details}</h5>
                        <h5>Note: ${activity.note}</h5>
                        <h5>Response ${activity.response}</h5>
                        <h5>Contents: </h5>
                        <p>${activity.content}</P>
                    </div>
                </c:if>
                <c:if test="${activity.click.equals('register')}"> 
                    <div class="alert alert-success">
                        <button data-dismiss="alert" class="close close-sm" type="button"></button>
                        <strong>${activity.time}!</strong>
                        <h5>Username: ${activity.username}</h5>
                        <h5>Full Name: ${activity.fullname}</h5>
                        <h5>Email: ${activity.email}</h5>
                        <h5>Phone: ${activity.phone}</h5>
                        <h5>Details: ${activity.details}</h5>
                        <h5>Note: ${activity.note}</h5>
                        <h5>Response: ${activity.response}</h5>
                        <h5>Contents: </h5>
                        <p>${activity.content}</P>
                    </div>
                </c:if>
                <c:if test="${activity.name.equals('Book Room') || activity.name.equals('Cancel Room')}"> 
                    <div class="alert alert-danger">
                        <button data-dismiss="alert" class="close close-sm" type="button"></button>
                        <strong>${activity.time}!</strong>
                        <h5>Username: ${activity.username}</h5>
                        <h5>Full Name: ${activity.fullname}</h5>
                        <h5>Email: ${activity.email}</h5>
                        <h5>Phone: ${activity.phone}</h5>
                        <h5>Details: ${activity.details}</h5>
                        <h5>Note: ${activity.note}</h5>
                        <h5>Response: ${activity.response}</h5>
                        <h5>Contents: </h5>
                        <p>${activity.content}</P>
                    </div>
                </c:if>
            </div>
        </section>
    </div>

    <%@ include file="common/mes-note.jspf"%>


</div>

<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">
                Email Reply
            </header>

            <ul class="media-list">
                <li class="media">
                    <a href="#" class="pull-left">
                        <img src="${pageContext.request.contextPath}/resources/img/users/${ad.img}" alt="Avatar" class="img-circle" width="48" height="48">
                    </a>
                    <div class="media-body">
                        <a href="${pageContext.request.contextPath}/profile.html">
                            <strong>${ad.name}</strong>
                        </a>
                        <p>
                            Send an email to reply this message! Forget the contents?
                            <a href="#" class="text-danger">
                                <strong>#view the message again!</strong>
                            </a>
                        </p>
                    </div>
                </li>
            </ul>


            <div class="panel-body">
                <div class="twt-area">
                    <form action="${pageContext.request.contextPath}/send-mail.html" method="post" accept-charset="UTF-8">
                        <textarea name="message" class="form-control" placeholder="Write something on reply.." rows="7"></textarea>
                        <input type="hidden" name="activity-id" value="${activity.id}"/>
                       	<c:if test="${activity.click.equals('contact')}">
                        <input type="hidden" name="user-email" value="${activity.username.replaceFirst("a guest with name: ", "").split(",")[1].replaceFirst(" email: ", "")}"/>
                        </c:if>
                        <c:if test="${!activity.click.equals('contact')}">
                        <input type="hidden" name="user-email" value="${activity.username}"/>
                        </c:if>
                        <input type="hidden" name="subject" value="${activity.name}"/>
                        <div class="clearfix">
                            <button class="btn btn-sm btn-primary pull-right" type="submit">
                                <i class="fa fa-envelope-o"></i>
                                Send Email
                            </button>
                            <a class="btn btn-link btn-icon fa fa-location-arrow" data-original-title="Add Location" data-placement="bottom" data-toggle="tooltip"
                               href="#" style="text-decoration:none;" title=""></a>
                            <a class="btn btn-link btn-icon fa fa-camera" data-original-title="Add Photo" data-placement="bottom" data-toggle="tooltip"
                               href="#" style="text-decoration:none;" title=""></a>
                        </div>
                    </form>
                </div>
            </div>

            <header class="panel-heading">
                Sample email reply 1
            </header>
            <div class="panel-body">
                <div class="twt-area">
                    <form action="${pageContext.request.contextPath}/send-mail.html" method="post" accept-charset="UTF-8">
                        <textarea class="form-control" name="message" placeholder="Write something on reply.." rows="14">Dear Mr. Đỗ Hùng Cường, &#13;&#13;Thank you for your interest in our hotel. This email is to acknowledge the receipt of your email and thank you for sending us your request. We will carefully review your request. Should your request match our ability, we will contact you soon. In other cases, we will keep the request for future opportunities.&#13;&#13;With best regards, &#13Hùng Cường.&#13&#13...&#13Holiday Crown.&#13Address: 24 Street 7, Bình An Ward, District 2.&#13Phone Number: 0908998923.&#13Hotline: (08).37404802.
                        </textarea>
                        <input type="hidden" name="activity-id" value="${activity.id}"/>
                                               	<c:if test="${activity.click.equals('contact')}">
                        <input type="hidden" name="user-email" value="${activity.username.replaceFirst("a guest with name: ", "").split(",")[1].replaceFirst(" email: ", "")}"/>
                        </c:if>
                        <c:if test="${!activity.click.equals('contact')}">
                        <input type="hidden" name="user-email" value="${activity.username}"/>
                        </c:if>
                        <input type="hidden" name="subject" value="${activity.name}"/>

                        <div class="clearfix">
                            <button class="btn btn-sm btn-primary pull-right" type="submit">
                                <i class="fa fa-envelope-o"></i>
                                Send Email
                            </button>
                            <a class="btn btn-link btn-icon fa fa-location-arrow" data-original-title="Add Location" data-placement="bottom" data-toggle="tooltip"
                               href="#" style="text-decoration:none;" title=""></a>
                            <a class="btn btn-link btn-icon fa fa-camera" data-original-title="Add Photo" data-placement="bottom" data-toggle="tooltip"
                               href="#" style="text-decoration:none;" title=""></a>
                        </div>
                    </form>
                </div>
            </div>

            <header class="panel-heading">
                Sample email reply 2
            </header>
            <div class="panel-body">
                <div class="twt-area">
                    <form action="${pageContext.request.contextPath}/send-mail.html" method="post" accept-charset="UTF-8">
                        <textarea class="form-control" name="message" placeholder="Write something on reply.." rows="14">Dear Mr. Đỗ Hùng Cường, &#13;&#13;Thank you for your interest in our hotel. This email is to acknowledge the receipt of your email and thank you for sending us your request. We will carefully review your request. Should your request match our ability, we will contact you soon. In other cases, we will keep the request for future opportunities.&#13;&#13;With best regards, &#13Hùng Cường.&#13&#13...&#13Holiday Crown.&#13Address: 24 Street 7, Bình An Ward, District 2.&#13Phone Number: 0908998923.&#13Hotline: (08).37404802.
                        </textarea>

                        <input type="hidden" name="activity-id" value="${activity.id}"/>
                       	                       	<c:if test="${activity.click.equals('contact')}">
                        <input type="hidden" name="user-email" value="${activity.username.replaceFirst("a guest with name: ", "").split(",")[1].replaceFirst(" email: ", "")}"/>
                        </c:if>
                        <c:if test="${!activity.click.equals('contact')}">
                        <input type="hidden" name="user-email" value="${activity.username}"/>
                        </c:if>
                        <input type="hidden" name="subject" value="${activity.name}"/>

                        <div class="clearfix">
                            <button class="btn btn-sm btn-primary pull-right" type="submit">
                                <i class="fa fa-envelope-o"></i>
                                Send Email
                            </button>
                            <a class="btn btn-link btn-icon fa fa-location-arrow" data-original-title="Add Location" data-placement="bottom" data-toggle="tooltip"
                               href="#" style="text-decoration:none;" title=""></a>
                            <a class="btn btn-link btn-icon fa fa-camera" data-original-title="Add Photo" data-placement="bottom" data-toggle="tooltip"
                               href="#" style="text-decoration:none;" title=""></a>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </div>
</div>


<!-- Main row -->
<%@ include file="common/all-mes.jspf"%>
<!-- row end -->
<%@ include file="common/footer.jspf"%>
<script type="text/javascript">
    window.onload = function () { //first load page
        checkSendEmail('${emailsent}');
    };
</script>