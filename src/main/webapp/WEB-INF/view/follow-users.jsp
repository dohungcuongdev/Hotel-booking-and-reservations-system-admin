<%@ include file="common/sub-content.jspf"%>
<div class="row" ng-app="follow-users" ng-controller="folowUserCtrl">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Follow Users</header>
			<div class="panel-body table-responsive">
				Field Name
		        <select id="fieldname" onchange="selectFieldName()">
		           	<option value="created_at">Date Access</option>
		           	<option value="user_ip_address">User IP Address</option>
		           	<option value="external_ip_address">External IP Address</option>
		           	<option value="username">User</option>
		           	<option value="page_access">Page Access</option>
		           	<option value="duration">Duration</option>
		        </select> 
		        &nbsp;&nbsp;
		        Sort Order
		        <select id="sort">
		           	<option value="asc">Ascending</option>
		           	<option value="des">Descending</option>
		        </select>
		        &nbsp;&nbsp;
		        <buton class="btn btn-success" onclick="filter()">Filter & View</a></buton>
	        </div>
			
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input min="0" max="10799999" type="text" name="keyword" id="keyword" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search for tracking.." title="Type in tracking data" onchange="showRangeVal()"/>
                    <input oninput="changeRangeVal()" type="text" style="display: none" id="range-value" class="input-sm pull-right" placeholder="Input duration.." title="Type in duration..">
                    <div class="input-group-btn">
                        <button class="btn btn-sm btn-default" onclick="search()"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            
            <div class="panel-body table-responsive">
                <table id="follow-user-table">
                   	<tr id="tableHeader" style="font-size:11px">
						<th class="tr-p">No.</th>
						<th class="tr-p" ng-click="sortDB('created_at')">Date Access</th>
						<th class="tr-p" ng-click="sortDB('user_ip_address')">User IP Address</th>
						<th class="tr-p" ng-click="sortDB('external_ip_address')">External IP Address</th>
						<th class="tr-p" ng-click="sortDB('username')">User</th>
						<th class="tr-p" ng-click="sortDB('page_access')">Page Access</th>
						<th class="tr-p" ng-click="sortDB('duration')">Duration</th>
					</tr>
                    <tbody id="followUserTableBody">
	                    <tr ng-repeat="d in followUserData" style="font-size:11px">
	                        <td>{{ $index + 1 }}</td>
	                    	<td>{{ d.created_at | date:'medium'}}</td>
							<td><a href = "${pageContext.request.contextPath}/page-access-chart/{{d.user_ip_address}}.html">{{ d.user_ip_address }}</a></td>
							<td><a href = "${pageContext.request.contextPath}/ip-details/{{d.external_ip_address}}.html">{{ d.external_ip_address }}</a></td>
							<td><a href = "${pageContext.request.contextPath}/member-chart/{{ d.username }}.html">{{ d.username }}</a></td>
							<td>{{ d.page_access }}</td>
							<td>{{ d.duration | secondsToTime }}</td>
	                    </tr>
                	</tbody>
                </table>
                <br>
                 <div class="table-foot">
                    <ul class="pagination pagination-sm no-margin pull-right">
                    <li><a ng-href="{{clickBackPageURL}}">&laquo;</a></li>
                    <li ng-repeat="page in arrPageDisplay">
                    	<a ng-if="currentPage == page" style="color: white; background-color: #CC0033">{{page}}</a>
                    	<a ng-if="currentPage != page" ng-href="{{clickPageURL}}{{page}}{{footerURL}}">{{page}}</a>
                    </li>
                    <li><a ng-href="{{clickNextPageURL}}">&raquo;</a></li>
                </ul>
                </div>
            </div>
            <center><button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/downloadCSV.html'">
            <b>Download CSV</b> <i class="fa fa-download"></i></button></center><br>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>
<script>
var page = ${page};
var action = '${action}';
var fieldname = '${fieldname}';
var sort = '${sort}';
var keyword = '${keyword}';
var totalPageAPI = TRACKING_TOTAL_PAGE_API;
var serverURL = '${pageContext.request.contextPath}/'
var clickPageURL = serverURL;


var tracking_api_url = '';

if(action == 'search') {
	totalPageAPI = TOTAL_PAGE_SEARCH_API + fieldname + '/' + keyword;
	tracking_api_url = TRACKING_SEARCH_API +  fieldname + '/' + keyword + '/' + sort + '/' + page;
	clickPageURL += 'follow-users-search/' + fieldname + '/' + keyword + '/' + sort  + '/';
} else {
	tracking_api_url = TRACKING_API_URL + fieldname + '/' + sort + '/' + page;
	clickPageURL += 'follow-users/' + fieldname + '/' + sort  + '/';
}

function search() {
	var input = $("#keyword").val();
	if(input == '') 
		swal("Oops...", "Please input keyword!", "warning");
	else
		location.href= serverURL + 'follow-users-search/'+ $("#fieldname").val() + '/' + input + '/' + $("#sort").val() + '/1';
}

function filter() {
	location.href= serverURL+ 'follow-users/' + $("#fieldname").val() + '/' + $("#sort").val() + '/1';
}

window.onload = function () { //first load page
	$("#fieldname").val(fieldname);
	$("#sort").val(sort);
	$("#keyword").val(keyword);
	selectFieldName();
};

function showRangeVal() {
	$("#range-value").val(getTimeBySecond($("#keyword").val()));
}

function isNDigit(s, n) {
	if(s.length != n)
		return false;
	else if(isNaN(s))
		return false;
	else
		return true;
}

function is2Digit(s) {
	return isNDigit(s,2);
}

function is3Digit(s) {
	return isNDigit(s,3);
}

function changeRangeVal() {
	var input = $("#range-value").val();
	var temp = input.split(":");
	var hour = temp[0];
	var min = temp[1];
	var second = temp[2];
	var millis = temp[3];
	if(!is2Digit(hour) || !is2Digit(min) || !is2Digit(second) || !is3Digit(millis) ||  min > 59 || second > 59 || millis > 999 || min < 0 || second < 0 || millis < 0) {
		swal("Oops...", "Invalid Time Format!", "warning");
	} else {
		var totalMills = 1000*(3600*parseInt(hour)+ 60*parseInt(min) + parseInt(second)) + parseInt(millis);
		$("#keyword").val(totalMills);
	}
}

function selectFieldName() {
	var selected = $("#fieldname").val();
	if(selected == 'duration') {
		document.getElementById('keyword').type = 'range';
		document.getElementById('range-value').style.display = 'block';
		if(keyword == '' || isNaN(parseInt(keyword))) {
			keyword = 0;
			$("#keyword").val(0);
		}
		$("#range-value").val(getTimeBySecond(keyword));
	} else {
		document.getElementById('range-value').style.display = 'none';
		if(selected == 'created_at')
			document.getElementById('keyword').type = 'date';
		else
			document.getElementById('keyword').type = 'text';
	}
}
</script>
<script src="${pageContext.request.contextPath}/resources/custom/follow-user-angular.js" type="text/javascript"></script>