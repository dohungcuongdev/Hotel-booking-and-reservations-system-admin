<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-md-8">
        <section class="panel">
            <header class="panel-heading">Notifications</header>
            <div class="panel-body" id="noti-box">
                <c:if test="${activity.click.equals('contact') || activity.click.equals('reservation')}"> 
                    <div class="alert alert-warning">
                        <button data-dismiss="alert" class="close close-sm" type="button"></button>
                        <%@ include file="common/noti.jspf"%>
                    </div>
                </c:if>
                <c:if test="${activity.click.equals('feedback') || activity.name.equals('Feedback Room')}"> 
                    <div class="alert alert-info">
                        <button data-dismiss="alert" class="close close-sm" type="button"></button>
                        <%@ include file="common/noti.jspf"%>
                    </div>
                </c:if>
                <c:if test="${activity.click.equals('register')}"> 
                    <div class="alert alert-success">
                        <button data-dismiss="alert" class="close close-sm" type="button"></button>
                       <%@ include file="common/noti.jspf"%>
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
            <header class="panel-heading">Email Reply</header>
            <ul class="media-list">
                <li class="media">
                    <a href="#" class="pull-left"><img src="${pageContext.request.contextPath}/resources/img/users/${ad.img}" alt="Avatar" class="img-circle" width="48" height="48"></a>
                    <div class="media-body">
                        <a href="${pageContext.request.contextPath}/profile.html"><strong>${ad.name}</strong></a>
                        <p>Send an email to reply this message! Forget the contents?<a href="#" class="text-danger"><strong>#view the message again!</strong></a></p>
                    </div>
                </li>
            </ul>
			<c:forEach var="emailTemplate" items="${emailTemplates}" varStatus="loop">
			<c:if test="${loop.index > 0}">
            <header class="panel-heading">Sample email reply ${loop.index + 1}</header>
            </c:if>
            <div class="panel-body">
                <div class="twt-area">
                    <form action="${pageContext.request.contextPath}/send-mail.html" method="post" accept-charset="UTF-8">
                        <textarea class="form-control" name="message" placeholder="Write something on reply.." rows="14">${emailTemplate}
                        </textarea>
                        <input type="hidden" name="activity-id" value="${activity._id}"/>
                        <c:choose>
                        <c:when test="${activity.username.contains('A guest')}">
                        <input type="hidden" name="user-email" value="${activity.username.replaceFirst("a guest with name: ", "").split(",")[1].replaceFirst(" email: ", "")}"/>
                        </c:when>
                        <c:otherwise>
                        <input type="hidden" name="user-email" value="${activity.username}"/>
                        </c:otherwise>
                        </c:choose>
                        <input type="hidden" name="subject" value="${activity.name}"/>
                        <div class="clearfix">
                            <button class="btn btn-sm btn-primary pull-right" type="submit"><i class="fa fa-envelope-o"></i> Send Email</button>
                            <a class="btn btn-link btn-icon fa fa-location-arrow" data-original-title="Add Location" data-placement="bottom" data-toggle="tooltip" href="#" style="text-decoration:none;" title=""></a>
                            <a class="btn btn-link btn-icon fa fa-camera" data-original-title="Add Photo" data-placement="bottom" data-toggle="tooltip" href="#" style="text-decoration:none;" title=""></a>
                        </div>
                    </form>
                </div>
            </div>
            </c:forEach>
        </section>
    </div>
</div>
<%@ include file="common/all-mes.jspf"%>
<%@ include file="common/footer.jspf"%>
<script type="text/javascript">
    window.onload = function () { //first load page
        checkSendEmail('${emailsent}');
    };
</script>