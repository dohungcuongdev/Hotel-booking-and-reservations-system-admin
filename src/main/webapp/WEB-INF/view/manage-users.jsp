<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Manage Customers</header>
            <div class="panel-body table-responsive">
                <div class="box-tools m-b-15">
                    <div class="input-group">
                        <input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;" id="input-user" onkeyup="searchInputTable('input-user', 'table-users')" placeholder="Search for customers.." title="Type in a customer"/>
                        <div class="input-group-btn">
                            <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </div>
				<%@ include file="common/table-users.jspf"%>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Customer Data Collection</header>
            <div class="panel-body table-responsive">
                <div class="box-tools m-b-15">
                    <div class="input-group">
                        <input type="text" name="table_search" class="form-control input-sm pull-right"  style="width: 150px;" id="cdc-input" onkeyup="searchInputTable('cdc-input', 'cdc-table')"  placeholder="Search for data.." title="Type in data collection"/>
                        <div class="input-group-btn">
                            <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </div>
				<%@ include file="common/data-collection.jspf"%>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>