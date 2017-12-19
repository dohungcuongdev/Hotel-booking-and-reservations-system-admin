<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>

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
				<%@ include file="common/table-follow-members.jspf"%>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>