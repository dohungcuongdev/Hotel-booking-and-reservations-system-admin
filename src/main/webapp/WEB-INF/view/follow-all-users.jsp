<%@ include file="common/sub-content.jspf"%>
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
            <div class="panel-body table-responsive" id="follow-user-box">
                <table id="follow-user-table" ng-app="follow-users" ng-controller="folowAllUserCtrl">
                   	<tr id="tableHeader" style="font-size:11px">
						<th class="tr-p" ng-click="sortNum(followUserData)">No.</th>
						<th class="tr-p" ng-click="sortDateAccess(followUserData)">Date Access</th>
						<th class="tr-p" ng-click="sortIP(followUserData)">User IP Address</th>
						<th class="tr-p" ng-click="sortExIP(followUserData)">External IP Address</th>
						<th class="tr-p" ng-click="sortUser(followUserData)">User</th>
						<th class="tr-p" ng-click="sortPageAccess(followUserData)">Page Access</th>
						<th class="tr-p" ng-click="sortDuration(followUserData)">Duration</th>
					</tr>
                    <tbody id="followUserTableBody">
	                    <tr ng-repeat="d in followUserData" style="font-size:11px">
	                        <td>{{ $index + 1 }}</td>
	                    	<td>{{ d.created_at | date:'medium'}}</td>
							<td><a href = "${pageContext.request.contextPath}/page-access-chart/{{d.user_ip_address}}.html">{{ d.user_ip_address }}</a></td>
							<td><a href = "${pageContext.request.contextPath}/ip-details/{{d.external_ip_address}}.html">{{ d.external_ip_address }}</a></td>
							<td ng-if="d.username != 'guest'"><a href = "${pageContext.request.contextPath}/customer/{{ d.username }}.html">{{ d.username }}</a></td>
							<td ng-if="d.username == 'guest'">{{ d.username }}</td>
							<td>{{ d.page_access }}</td>
							<td>{{ d.duration | secondsToTime }}</td>
	                    </tr>
                	</tbody>
                </table>
            </div>
            <br><center>
            <button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/follow-users.html'"><b>View Top 10</b> <i class="fa fa-user-circle-o"></i></button>
            <button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/downloadCSV.html'"><b>Download CSV</b> <i class="fa fa-download"></i></button>
            </center><br>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>
<script src="${pageContext.request.contextPath}/resources/custom/follow-user-angular.js" type="text/javascript"></script>
<script>
    function responsiveFn() {
    	responsiveGeneral('#follow-user-box', 500);
    }

     // load() event and resize() event are combined 
    $(window).ready(responsiveFn).resize(responsiveFn); 
</script>