<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<%@ include file="common/member-colunm-chart.jspf"%>
<div  ng-app=pageAccessChart ng-controller="pageAccessChartCtrl">
	<div class="row">
	    <div class="col-xs-12">
	        <div class="panel">
	            <header class="panel-heading">Page Access Chart for ${username}</header>
	            <div id="chartdiv"></div>
	        </div>
	    </div>            
	</div>
	<div class="row">
	    <div class="col-xs-12">
	        <div class="panel">
	            <header class="panel-heading">Page Access Statistics for ${username}</header>
				<%@ include file="common/tracking.jspf"%>
	            <br><center><button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/click-tracking-member/${username}.html'"><i class="fa fa-globe"></i><b> Check more details </b> </button></center><br>
	        </div>
	    </div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>