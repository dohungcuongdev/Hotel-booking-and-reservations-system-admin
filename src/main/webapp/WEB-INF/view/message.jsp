<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-md-8" id="all-notifications">
        <section class="panel">
            <header class="panel-heading">Notifications</header>
            <div class="panel-body" id="noti-box">
                <c:choose>
                    <c:when test="${newNotifications.size() <= 0}"> 
                        <div class="alert alert-block alert-danger" style="text-align: center">
                            <strong>No notification available!</strong> 
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="activity" items="${newNotifications}">
                            <c:if test="${activity.click.equals('feedback') || activity.name.equals('Feedback Room')}"> 
                                <div class="alert alert-block alert-info">
                                    <button data-dismiss="alert" class="close close-sm" type="button">
                                        <i class="fa fa-times"></i>
                                    </button> 
                                    <a href="${pageContext.request.contextPath}/notification/${activity._id}.html"><strong>${activity.getICTDateTime(activity.created_at)}! </strong></a>
                                    </strong>
                                    <br>Username: <a href="${pageContext.request.contextPath}/user/${activity.username}.html">${activity.username}</a>
                                    <br><b>Sent content</b>: ${activity.content}
                                </div>
                            </c:if>
                            <c:if test="${activity.click.equals('register')}"> 
                                <div class="alert alert-block alert-success">
                                    <button data-dismiss="alert" class="close close-sm" type="button">
                                        <i class="fa fa-times"></i>
                                    </button> 
                                    <a href="${pageContext.request.contextPath}/notification/${activity._id}.html"><strong>${activity.getICTDateTime(activity.created_at)}! </strong></a>
                                    <br>Username: <a href="${pageContext.request.contextPath}/user/${activity.username}.html">${activity.username}</a>
                                    <br><b>Received content</b>: ${activity.content}
                                </div>
                            </c:if>
                            <c:if test="${activity.name.equals('Book Room') || activity.name.equals('Cancel Room')}"> 
                                <div class="alert alert-block alert-danger">
                                    <button data-dismiss="alert" class="close close-sm" type="button">
                                        <i class="fa fa-times"></i>
                                    </button> 
                                    <a href="${pageContext.request.contextPath}/reply ${activity.name}/${activity._id}.html"><strong>${activity.getICTDateTime(activity.created_at)}! </strong></a>
                                    <br>Username: <a href="${pageContext.request.contextPath}/user/${activity.username}.html">${activity.username}</a>
                                    <br><b>Received content</b>: ${activity.content}
                                </div>
                            </c:if>
                            <c:if test="${activity.click.equals('contact') || activity.click.equals('reservation')}"> 
                                <div class="alert alert-block alert-warning">
                                    <button data-dismiss="alert" class="close close-sm" type="button">
                                        <i class="fa fa-times"></i>
                                    </button> 
                                    <a href="${pageContext.request.contextPath}/notification/${activity._id}.html"><strong>${activity.getICTDateTime(activity.created_at)}! </strong></a>
                                    </strong>
                                    <br>
                                    <c:choose>
									<c:when test="${activity.username.contains('A guest')}">
									${activity.username}
									</c:when>
									 <c:otherwise>
									Username: <a href="${pageContext.request.contextPath}/user/${activity.username}.html">${activity.username}</a>
									</c:otherwise>
									</c:choose>
                                    <br><b>Sent content</b>: ${activity.content}
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </div>
    <%@ include file="common/mes-note.jspf"%>
</div>
<%@ include file="common/all-mes.jspf"%>
<%@ include file="common/footer.jspf"%>