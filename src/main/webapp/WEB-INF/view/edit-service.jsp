<%@ include file="common/sub-content.jspf"%>
<%@ include file="common/single-service.jspf"%>
<div class="row">
    <div class="col-lg-6">
        <section class="panel">
            <div class="panel-body">
                <form action="${pageContext.request.contextPath}/service-img-edited/${service.name}.html" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <br><label style="font-size: 17px; color:blue"><strong>Change Image!</strong></label>
                    </div>
                    <div class="form-group">
                        <label>Image 1</label>
                    </div>
                    <div class="form-group">
                        <input type="file" name="img1" onchange="readURL(this, '#blah', 500, 315);">
                    </div>
                    <div class="pull-left image">
                        <img id="blah" src="${pageContext.request.contextPath}/resources/img/restaurant/${service.img}" alt="Room Image" style="height: 315px; width: 500px;" />
                    </div>
                    <div class="form-group">
                        <label style="margin-top: 60px">Image 2</label>
                    </div>
                    <div class="form-group">
                        <input type="file" name="img2" onchange="readURL(this, '#blah2', 500, 315);">
                    </div>
                    <div class="pull-left image" style="margin-bottom: 30px" >
                        <img id="blah2" src="${pageContext.request.contextPath}/resources/img/restaurant/${service.img2}" alt="Room Image" style="height: 315px; width: 500px;" />
                    </div>
                    <p class="help-block">The image of the service will be changed after submit.</p>
                    <button type="submit" class="btn btn-info">Submit</button>
                    <button type="reset" onclick="location.href = '${pageContext.request.contextPath}/edit-service/${service.name}.html'" class="btn btn-danger">Cancel</button>
                </form>
            </div>
        </section>
    </div>
    <div class="col-lg-6">
        <section class="panel">
            <header class="panel-heading" style="font-weight: bold; color:red">Change Information!</header>
            <div class="panel-body">
                <form:form method="post" commandName="serviceEdit" action="${pageContext.request.contextPath}/service-edited.html">
                    <form:input type="hidden" value="${service.id}" readonly="true" path="id"/>
                    <form:input type="hidden" value="${service.created_at}" readonly="true" path="created_at"/>
                    <form:input type="hidden" value="${service.created_by}" readonly="true" path="created_by"/>
                    <div class="form-group">
                        <label>Name</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="text" class="form-control" value="${service.name}" path="name" readonly="true"/>
                    </div>
                    <div class="form-group">
                        <label>Type</label>
                    </div>
                    <div class="form-group">
                        <form:select class="form-control m-b-10" path="type" id="type">
                            <form:option value="food">Food</form:option>
                            <form:option value="drink">Drink</form:option>
                            <form:option value="fruit">Fruit</form:option>
                            <form:option value="ice-cream">Ice-cream</form:option>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="number" class="form-control" value="${service.price}" placeholder="Price" path="price" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label>Quantity</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="number" class="form-control" value="${service.quantity}" placeholder="Quantity" path="quantity" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label>Note</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="text" class="form-control" value="${service.note}" id="note" placeholder="Note" path="note"/>
                    </div>
                    <div class="form-group">
                        <label>Details</label>
                    </div>
                    <div class="form-group">
                        <form:textarea required="true" id="details" class="form-control" value="${service.details}" path="details" placeholder="Write the details for this service..." rows="6"/>
                    </div>
                    <div class="form-group">
                        <label>Serve type - Serve time</label>
                    </div>
                    <div class="form-group">
                        <form:select class="form-control m-b-10" path="serveType" id="serveType">
                            <form:option value="breakfast">Breakfast - 7:00 am to 10:00 am</form:option>
                            <form:option value="lunch">Lunch - 11:00 am to 4:00 pm</form:option>
                            <form:option value="dinner">Dinner - 5:00 pm to 9:00 pm</form:option>
                            <form:option value="snack">Snack - Anytime</form:option>
                        </form:select>
                    </div>
                    <form:input type="hidden" value="${service.img}" path="img"/>
                    <form:input type="hidden" value="${service.img2}" path="img2"/>
                    <p class="help-block">Your item will be changed after submit.</p>
                    <button style="margin-top: 3.5px" type="submit" class="btn btn-info">Submit</button>
                    <button style="margin-top: 3.5px" type="reset" onclick="location.href = '${pageContext.request.contextPath}/edit-service/${service.name}.html'" class="btn btn-danger">Cancel</button>
                    <div class="form-group">
                        <label style="margin-top: 10px">*Suggestion</label>
                        <ul>
                            <li>Name and Images of this service should be match</li>
                            <li>Type must be Food, Drink, Fruit or Ice-cream</li>
                            <li>Price must be a number ($)</li>
                            <li>Quantity must be a natural number</li>
                            <li>You should write a suitable Note and Details for this service</li>
                            <li>Serve type much be breakfast, lunch, dinner or snack</li>
                        </ul>
                    </div>
                </form:form>
            </div>
        </section>
    </div>
</div>
<%@ include file="common/related-service.jspf"%>
<%@ include file="common/footer.jspf"%>
<script type="text/javascript">
    window.onload = function () { //first load page
        $("#type").val('${service.type}');
        $("#serveType").val('${service.serveType}');
        $('#details').val('${service.details}');
        var editResult = '${editResult}';
        checkeditresult(editResult);
        var addResult = '${addResult}';
        checkAddResult(addResult);
        window.history.pushState("string", "Hotel Admin", "${pageContext.request.contextPath}/edit-service/${service.name}.html");
    };
</script>