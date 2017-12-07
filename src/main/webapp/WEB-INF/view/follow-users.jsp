<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">IP Address Statistics</header>
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input type="text" name="table_search" class="form-control input-sm pull-right" 
                           style="width: 150px;" id="ip-stat-input" onkeyup="searchInputTable('ip-stat-input', 'ip-stat-table')" 
                           placeholder="Search for ip address.." title="Type in a ip address"/>
                    <div class="input-group-btn">
                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive">
				<%@ include file="common/table-ip-address.jspf"%>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">External IP Statistics</header>
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input type="text" name="table_search" class="form-control input-sm pull-right" 
                           style="width: 150px;" id="externalip-input" onkeyup="searchInputTable('externalip-input', 'externalip-table')" 
                           placeholder="Search for ip address.." title="Type in a ip address"/>
                    <div class="input-group-btn">
                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive">
				<%@ include file="common/table-external-ip.jspf"%>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Page Access Statistics</header>
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input type="text" name="table_search" class="form-control input-sm pull-right" 
                           style="width: 150px;" id="page-access-input" onkeyup="searchInputTable('page-access-input', 'page-access-table')" 
                           placeholder="Search for page access.." title="Type in a page access"/>
                    <div class="input-group-btn">
                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive" id="page-access-box">
				<%@ include file="common/table-page-access.jspf"%>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>