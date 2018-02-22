<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Manage Restaurant and Hotel Services</header>
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input type="text" name="table_search" class="form-control input-sm pull-right"  style="width: 150px;" id="input-item" onkeyup="searchInputTable('input-item', 'table-restaurant')" placeholder="Search for restaurant.." title="Type in a restaurant item"/>
                    <div class="input-group-btn">
                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive" id="manage-services-box">
				<%@ include file="common/table-restaurant.jspf"%>
            </div>
            <div class="panel-body">
                 <center><button type="button" onclick="location.href='${pageContext.request.contextPath}/add-service.html'" class="btn btn-success">Add New Item <i class="fa fa-food"></i></button></center>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>
<script>
    window.onload = function () { //first loat page
        var r = '${deleteResult}';
        if (r !== undefined && r === "success") {
            swal("Deleted!", "The service has been deleted.", "success");
            window.history.pushState("string", "Hotel Admin", "${pageContext.request.contextPath}/manage-restaurant.html");
        }
    };
    
    function responsiveFn() {
    	responsiveGeneral('#manage-services-box', 500);
    }     
    
    // load() event and resize() event are combined 
    $(window).ready(responsiveFn).resize(responsiveFn); 
</script>