<%@ include file="common/sub-content.jspf"%>
<script>
var page = ${page};
if(page <= 0)
	  location.href='index.html';
var tracking_api_url = TRACKING_API_URL_PAGE + page
</script>
<script src="${pageContext.request.contextPath}/resources/custom/follow-user-angular.js" type="text/javascript"></script>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Follow Users</header>
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;" id="input-follow-user" onkeyup="searchInputTable('input-follow-user', 'follow-user-table')" placeholder="Search for ip address.." title="Type in a ip address"/>
                    <div class="input-group-btn">
                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive" ng-app="follow-users" ng-controller="folowUserCtrl">
                <table id="follow-user-table">
                   	<tr id="tableHeader" style="font-size:11px">
						<th class="tr-p">No.</th>
						<th class="tr-p" ng-click="sortDB('created_at')">Date Access</th>
						<th class="tr-p" ng-click="sortDB('user_ip_address')">User IP Address</th>
						<th class="tr-p" ng-click="sortDB('external_ip_address')">External IP Address</th>
						<th class="tr-p" ng-click="sortDB('country')">Country</th>
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
							<td>{{ d.country }}</td>
							<td><a href = "${pageContext.request.contextPath}/member-chart/{{ d.username }}.html">{{ d.username }}</a></td>
							<td>{{ d.page_access }}</td>
							<td>{{ d.duration | secondsToTime }}</td>
	                    </tr>
                	</tbody>
                </table>
                 <div class="table-foot">
                    <ul class="pagination pagination-sm no-margin pull-right">
                    <li><a href="${pageContext.request.contextPath}/follow-users/{{backPage}}.html"">&laquo;</a></li>
                    <li ng-repeat="page in arrPageDisplay">
                    	<a ng-if="currentPage == page" style="color: white; background-color: #CC0033" href="${pageContext.request.contextPath}/follow-users/{{page}}.html">{{page}}</a>
                    	<a ng-if="currentPage != page" href="${pageContext.request.contextPath}/follow-users/{{page}}.html">{{page}}</a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/follow-users/{{nextPage}}.html"">&raquo;</a></li>
                </ul>
                </div>
            </div>
            <br><center><button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/downloadCSV.html'">
            <b>Download CSV</b> <i class="fa fa-download"></i></button></center><br>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>