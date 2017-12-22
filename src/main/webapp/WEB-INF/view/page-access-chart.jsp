<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<%@ include file="common/ip-colunm-chart.jspf"%>
<div  ng-app=pageAccessChart ng-controller="pageAccessChartCtrl">
	<div class="row">
	    <div class="col-xs-12">
	        <div class="panel">
	            <header class="panel-heading">Page Access Chart for ${ipaddress}</header>
	            <div id="chartdiv"></div>
	        </div>
	    </div>            
	</div>
	<div class="row">
	    <div class="col-xs-12">
	        <div class="panel">
	            <header class="panel-heading">Page Access Statistics for ${ipaddress}</header>
				<%@ include file="common/tracking.jspf"%>
	            <c:if test="${ipaddress.equals('All IP address')}"> 
	            	<br><center><button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/follow-users.html'">
	            </c:if>
	            <c:if test="${!ipaddress.equals('All IP address')}"> 
	            	<br><center><button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/click-tracking-ip/${ipaddress}.html'">
	            </c:if>
	            <i class="fa fa-globe"></i><b> Check more details </b> </button></center><br>
	        </div>
	    </div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>