<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>

<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">
                Manage Restaurant and Hotel Services
            </header>
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
            <div class="panel-body table-responsive" id="manage-services-box">
                <table id="table-management">
                    <tr id="tableHeader">
                        <th class="tr-p" onclick="sortAlpha(0,'table-management')">No.</th>
                        <th class="tr-p" onclick="sortAlpha(1,'table-management')">Name</th>
                        <th class="tr-p" onclick="sortAlpha(2,'table-management')">Type</th>
                        <th class="tr-p" onclick="sortNum(3,'table-management')">Pirce($)</th>
                        <th class="tr-p" onclick="sortNum(4,'table-management')">Quantity</th>
                        <th class="tr-p" onclick="sortAlpha(5,'table-management')">Note</th>
                        <th>View</th>
                        <th>Edit</th>
                        <th>Del</th>
                    </tr>
                    <c:forEach var="service" items="${listservices}" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${service.name}</td>
                            <c:if test="${service.type.equalsIgnoreCase('ice-cream')}">  
                                <td><span style="font-size: 14px" class="label label-danger">${service.type}</span></td>
                                </c:if>   
                                <c:if test="${service.type.equalsIgnoreCase('drink')}">  
                                <td><span style="font-size: 14px" class="label label-success">${service.type}</span></td>
                                </c:if> 
                                <c:if test="${service.type.equalsIgnoreCase('food')}">  
                                <td><span style="font-size: 14px" class="label label-primary">${service.type}</span></td>
                                </c:if>
                                <c:if test="${service.type.equalsIgnoreCase('fruit')}">  
                                <td><span style="font-size: 14px" class="label label-warning">${service.type}</span></td>
                                </c:if>  
                            <td>${service.price}</td>
                            <td>${service.quantity}</td>
                            <td>${service.note}</td>
                            <td><button onclick="location.href = '${pageContext.request.contextPath}/service/${service.name}.html'" class="btn btn-default btn-xs"><i class="fa fa-check"></i></button></td>
                            <td><button onclick="location.href = '${pageContext.request.contextPath}/edit-service/${service.name}.html'" class="btn btn-default btn-xs"><i class="fa fa-pencil"></i></button></td>
                            <td><button onclick ="deleteService('${service.name}')" class="btn btn-default btn-xs"><i class="fa fa-times"></i></button></td>
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
<script>
    window.onload = function () { //first loat page
        var r = '${deleteResult}';
        if (r !== undefined && r === "success") {
            swal("Deleted!", "The service has been deleted.", "success");
            window.history.pushState("string", "Hotel Admin", "${pageContext.request.contextPath}/manage-restaurant.html");
        }
    };
</script>