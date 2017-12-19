<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-lg-4">
        <section class="panel">
            <header class="panel-heading" style="font-weight: bold; color:red">${cusDataCollection.cus.name}</header>
            <div class="panel-body">
                <form role="form">
                    <div class="form-group"><strong>User Name</strong>: ${cusDataCollection.cus.username}</div>
                    <div class="form-group"><strong>Full Name</strong>: ${cusDataCollection.cus.name}</div>
                    <div class="form-group"><strong>Register Time</strong>: ${cusDataCollection.cus.getICTDateTime(cusDataCollection.cus.created_at)}</div>
                    <div class="form-group"><strong>Phone Number</strong>: ${cusDataCollection.cus.phone}</div>
                    <div class="form-group"><strong>Address</strong>: ${cusDataCollection.cus.address}</div>
                    <div class="form-group" style="color: #3c763d"><strong>Total Time Visited</strong>: ${cusDataCollection.dateVisited.size()}</div>
                    <div class="form-group" style="color: #31708f"><strong>Total Room Booked</strong>: ${cusDataCollection.action.roomBooked.size()}</div>
                    <div class="form-group" style="color: #a94442"><strong>Total Room Canceled</strong>: ${cusDataCollection.action.roomCanceled.size()}</div>
                    <div class="form-group" style="color: #8a6d3b"><strong>Average Feedback Room</strong>: ${cusDataCollection.action.avgfeedbackRoom} ★</div>
                    <div class="form-group" style="color: #8a6d3b"><strong>Average Feedback Service</strong>: ${cusDataCollection.action.avgFeedbackSV} ★</div>
                </form>
            </div>
        </section>    
        <section class="panel">
        <header class="panel-heading">Notes</header>
        <div class="panel-body">
            <div class="alert alert-info">
                <strong>Booking!</strong> This is the booking room request of customer.
            </div>
            <div class="alert alert-block alert-danger">
                <strong>Cancel!</strong> This is the canceling room request of customer.
            </div>
            <div class="alert alert-success">
                <strong>Date Visited!</strong> This is the date that customer visited.
            </div>
            <div class="alert alert-warning">
                <strong>Feedback!</strong> This is the feedback of customer.
            </div>
        </div>
    </section>
    </div>
    
    <div class="col-md-8">
        <section class="panel">
            <header class="panel-heading">List of Rooms Booked</header>
            <div class="panel-body" id="room-booked-box">
                <c:forEach var="roomBooked" items="${cusDataCollection.action.roomBooked}" varStatus="loop">
                    <div class="alert alert-block alert-info">
                        <button data-dismiss="alert" class="close close-sm" type="button"><i class="fa fa-times"></i></button> 
                        <a href = "${pageContext.request.contextPath}/room/${roomBooked.data}.html"> <strong>  ${roomBooked.date}! </strong></a>Room booked: ${roomBooked.data}
                    </div>
                </c:forEach>
            </div>
        </section>
        <section class="panel">
            <header class="panel-heading">List of Rooms Canceled</header>
            <div class="panel-body" id="room-canceled-box">
                <c:forEach var="roomCanceled" items="${cusDataCollection.action.roomCanceled}" varStatus="loop">
                    <div class="alert alert-block alert-danger">
                        <button data-dismiss="alert" class="close close-sm" type="button"><i class="fa fa-times"></i></button> 
                        <a href = "${pageContext.request.contextPath}/room/${roomCanceled.data}.html"> <strong>${roomCanceled.date}! </strong></a>
                        Room canceled: ${roomCanceled.data}
                    </div>
                </c:forEach>
            </div>
        </section>
        <section class="panel">
            <header class="panel-heading">List of Dates Visited</header>
            <div class="panel-body" id="date-visited-box">
                <c:forEach var="dateVisited" items="${cusDataCollection.dateVisited}" varStatus="loop">
                    <div class="alert alert-block alert-success">
                        <button data-dismiss="alert" class="close close-sm" type="button"><i class="fa fa-times"></i></button> 
                        <strong> Date Visited: ${dateVisited} </strong>
                    </div>
                </c:forEach>
            </div>
        </section>
        
        <section class="panel">
            <header class="panel-heading">List of Rooms Feedback</header>
            <div class="panel-body" id="feedback-room-box">
                <c:forEach var="feedbackroom" items="${cusDataCollection.action.feedbackroom}" varStatus="loop">
                    <div class="alert alert-block alert-warning">
                        <button data-dismiss="alert" class="close close-sm" type="button"><i class="fa fa-times"></i></button> 
                         <a href = "${pageContext.request.contextPath}/room/${feedbackroom.room}.html"> <strong>${feedbackroom.date}!  </strong></a>
                        <br>Room feedback: ${feedbackroom.room}
                        <br>Feedback Score: ${feedbackroom.star} ★
                        <br>Feedback content ${feedbackroom.feedback}
                    </div>
                </c:forEach>
            </div>
        </section>
        
        <section class="panel">
            <header class="panel-heading">List of Hotel Service Feedback</header>
            <div class="panel-body" id="feedback-box">
                <c:forEach var="feedbackservice" items="${cusDataCollection.action.feedbackservice}" varStatus="loop">
                    <div class="alert alert-block alert-warning">
                        <button data-dismiss="alert" class="close close-sm" type="button"><i class="fa fa-times"></i></button> 
                        <strong> ${feedbackservice.date}! </strong>
                        <br>Feedback Score: ${feedbackservice.star} ★
                        <br>Feedback content ${feedbackservice.feedback}
                    </div>
                </c:forEach>
            </div>
        </section>
    </div>
</div>
<%@ include file="common/footer.jspf"%>