<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>


<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">
                Manage Customers

            </header>
            <div class="panel-body table-responsive">
                <div class="box-tools m-b-15">
                    <div class="input-group">
                        <input type="text" name="table_search" class="form-control input-sm pull-right" 
                               style="width: 150px;" id="input-management" onkeyup="search()" 
                               placeholder="Search for names.." title="Type in a name"/>
                        <div class="input-group-btn">
                            <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </div>
                <table id="table-management">
                    <tr id="tableHeader">
                        <th class="tr-p" onclick="sortNum(0,'table-management')">No.</th>
                        <th class="tr-p" onclick="sortAlpha(1,'table-management')">User</th>
                        <th class="tr-p" onclick="sortDate(2,'table-management')">Register Time</th>
                        <th class="tr-p" onclick="sortAlpha(3,'table-management')">Full Name</th>
                        <th class="tr-p" onclick="sortAlpha(4,'table-management')">Phone</th>
                        <th class="tr-p" onclick="sortAlpha(5,'table-management')">Address</th>
                        <th class="tr-p" onclick="sortNum(6,'table-management')">Total Dates Visited</th>
                        <th>View</th>
                        <th>Del</th>
                        <th>Ban</th>
                    </tr>

                    <c:forEach var="user" items="${listusers}" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${user.username}</td>
                            <td>${user.registered_date}</td>
                            <td>${user.name}</td>
                            <td><span class="label label-success">${user.phone}</span></td>
                            <td>${user.address}</td>
                            <td>${user.dateVisit.size()}</td>
                            <td><button onclick="location.href = '${pageContext.request.contextPath}/user/${user.username}.html'" class="btn btn-default btn-xs"><i class="fa fa-check"></i></button></td>
                            <td><button class="btn btn-default btn-xs"><i class="fa fa-times"></i></button></td>
                            <td><button class="btn btn-default btn-xs"><i class="fa fa-ban"></i></button></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>


<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">
                Customer Data Collection

            </header>
            <div class="panel-body table-responsive">
                <div class="box-tools m-b-15">
                    <div class="input-group">
                        <input type="text" name="table_search" class="form-control input-sm pull-right" 
                               style="width: 150px;" id="cdc-input" onkeyup="searchInputTable('cdc-input', 'cdc-table')" 
                               placeholder="Search for names.." title="Type in a name"/>
                        <div class="input-group-btn">
                            <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </div>
                <table id="cdc-table">
                    <tr id="tableHeader">
                        <th class="tr-p" onclick="sortNum(0,'cdc-table')">No.</th>
                        <th class="tr-p" onclick="sortAlpha(1,'cdc-table')">User</th>
                        <th class="tr-p" onclick="sortNum(2,'cdc-table')">Total Time Visited</th>
                        <th class="tr-p" onclick="sortNum(3,'cdc-table')">Total Room Booked</th>
                        <th class="tr-p" onclick="sortNum(4,'cdc-table')">Total Room Canceled</th>
                        <th class="tr-p" onclick="sortNum(5,'cdc-table')">Average Feedback Room</th>
                        <th class="tr-p" onclick="sortNum(6,'cdc-table')">Average Feedback Service</th>
                        <th>View</th>
                        <th>Del</th>
                        <th>Ban</th>
                    </tr>

                    <c:forEach var="user" items="${cusDataCollection}" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${user.cus.username}</td>
                            <td>${user.dateVisited.size()}</td>
                            <td>${user.action.roomBooked.size()}</td>
                            <td>${user.action.roomCanceled.size()}</td>
                            <td>${user.action.avgfeedbackRoom} ★</td>
                            <td>${user.action.avgFeedbackSV} ★</td>
                            <td><button onclick="location.href = '${pageContext.request.contextPath}/customer/${user.cus.username}.html'" class="btn btn-default btn-xs"><i class="fa fa-check"></i></button></td>
                            <td><button class="btn btn-default btn-xs"><i class="fa fa-times"></i></button></td>
                            <td><button class="btn btn-default btn-xs"><i class="fa fa-ban"></i></button></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>
<%@ include file="common/footer.jspf"%>