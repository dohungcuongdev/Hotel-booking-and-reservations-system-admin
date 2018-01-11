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
		        <buton class="btn btn-success" onclick="filter()">Filter & View</buton>
	        </div>
			
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input type="text" name="keyword" id="keyword" class="form-control input-sm pull-right" style="width: 220px;" placeholder="Search for tracking.." title="Type in tracking data" oninput="changeRangeVal()" />
					<input onchange="showRangeVal()" class="input-sm pull-right slider" min="0" max="3600000" type="range" style="display: none; margin-right: 20px;" id="range2" title="Type in duration..">
                    <input onchange="showRangeVal()" class="input-sm pull-right slider" min="0" max="3600000" type="range" style="display: none; margin-right: 20px;" id="range1" title="Type in duration..">
                    <h5 id="search-keyword" style="margin-left:15px; margin-top: 6px;"></h5>
                    <div class="input-group-btn">
                        <button id="search-fu-btn" class="btn btn-sm btn-default" onclick="search()"><i class="fa fa-search"></i></button>
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
            <center>
            	<button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/follow-all-users.html'"><b>View All</b> <i class="fa fa-user-circle"></i></button>
            	<button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/downloadCSV.html'"><b>Download CSV</b> <i class="fa fa-download"></i></button>
            </center><br>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>




<script>
var page = ${page};
var fieldname = '${fieldname}';
var sort = '${sort}';
var keyword = '${keyword}';
var totalPageAPI = TRACKING_TOTAL_PAGE_API;
var serverURL = '${pageContext.request.contextPath}/'
var clickPageURL = serverURL;

var tracking_api_url = '';

if(keyword != '') {
	totalPageAPI = TOTAL_PAGE_SEARCH_API + fieldname + '/' + keyword;
	tracking_api_url = TRACKING_SEARCH_API +  fieldname + '/' + keyword + '/' + sort + '/' + page;
	clickPageURL += 'follow-users-search/' + fieldname + '/' + keyword + '/' + sort  + '/';
} else {
	tracking_api_url = TRACKING_API_URL + fieldname + '/' + sort + '/' + page;
	clickPageURL += 'follow-users/' + fieldname + '/' + sort  + '/';
}

function alertInvalidDuration() {
	swal("Oops...", "Invalid Duration Format! \nCorrect Format: hh:MM:ss:SSS -> hh:MM:ss:SSS", "warning");
}

function search() {
	var input = $("#keyword").val();
	if(input == '') 
		swal("Oops...", "Please input keyword!", "warning");
	else if($("#fieldname").val() == 'duration') {
		let temp = input.split(" -> ");
		if(temp.length != 2)
			alertInvalidDuration();
		else {
			let i = getDurationFormat(temp[0]);
			let k = getDurationFormat(temp[1]);
			if(i == -1 || k == -1)
				alertInvalidDuration();
			else if(i <= k) {
				location.href= serverURL + 'follow-users-search/'+ $("#fieldname").val() + '/' + i + ',' + k + '/' + $("#sort").val() + '/1';
			}
			else 
				swal("Oops...", "The second time input must be greater than the first one", "warning");
		}
	} else
		location.href= serverURL + 'follow-users-search/'+ $("#fieldname").val() + '/' + input + '/' + $("#sort").val() + '/1';
}

function filter() {
	location.href= serverURL+ 'follow-users/' + $("#fieldname").val() + '/' + $("#sort").val() + '/1';
}

window.onload = function () { //first load page
	$("#fieldname").val(fieldname);
	$("#sort").val(sort);
	initFieldName();
	showSearchKeyword();
	if(keyword != '') {
		$("#search-fu-btn").css("margin-top", "-5px");
		if(fieldname == 'duration') {
		    let keywordDuration = getDurationKeywordFormat(keyword);
		    $("#keyword").val(keywordDuration);
		    changeRangeVal();
		} else
			$("#keyword").val(keyword);
	} else
		$("#search-fu-btn").css("margin-top", "0px");
};
</script>
<script src="${pageContext.request.contextPath}/resources/custom/follow-user-angular.js" type="text/javascript"></script>