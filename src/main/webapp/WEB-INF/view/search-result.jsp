<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="common/sub-content.jspf"%>
<div class="row">
	<div class="col-xs-12">
		<header id="result" style="background: #cffabd; color: #3c763d; font-size: 15px" class="panel-heading"><center><b>Result for keyword: ${keyword}</b></center></header>
	</div>
</div>
<div id="room-result"><div class="row">
	<div class="col-xs-12">
		<header class="panel-heading"><center><b>Rooms</b></center></header>
		<%@ include file="common/table-rooms.jspf"%>
	</div>
</div><br><br></div>
<div id="restaurant-result"><div class="row">
	<div class="col-xs-12">
		<header class="panel-heading"><center><b>Restaurant</b></center></header>
		<%@ include file="common/table-restaurant.jspf"%>
	</div><br><br>
</div><br><br></div>
<div id="message-result"><div class="row">
	<div class="col-xs-12">
		<section class="panel">
		<header class="panel-heading"><center><b>Message</b></center></header>
		<div class="panel-body" id="message-box">
			<%@ include file="common/mes-box.jspf"%>
		</div>
		</section>
	</div>
</div><br><br></div>
<div id="user-result"><div class="row">
	<div class="col-xs-12">
		<header class="panel-heading"><center><b>User</b></center></header>
		<%@ include file="common/table-users.jspf"%>
	</div>
</div><br><br></div>
<div id="cdc-result"><div class="row">
	<div class="col-xs-12">
		<header class="panel-heading"><center><b>Customer Data Collection</b></center></header>
		<%@ include file="common/data-collection.jspf"%>
	</div>
</div><br><br></div>
<div id="keep-footer-bottom"></div>
<%@ include file="common/footer.jspf"%>
<script src="${pageContext.request.contextPath}/resources/custom/search-all.js" type="text/javascript"></script>
<script>
	window.onload = function() {
		searchAllDisplay('${keyword}');
	}
</script>