<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-lg-12">
        <section class="panel">
            <header class="panel-heading" style="font-weight: bold; color:red">Add New Restaurant Item!</header>
            <div class="panel-body">
                <form:form method="post" commandName="newService" action="${pageContext.request.contextPath}/service-added.html">
                    <div class="form-group">
                        <label>Name</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="text" class="form-control" path="name" placeholder="Item name"/>
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
                        <form:input required="true" type="number" class="form-control" placeholder="Price" path="price" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label>Quantity</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="number" class="form-control" placeholder="Quantity" path="quantity" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label>Note</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="text" class="form-control" id="note" placeholder="Note" path="note"/>
                    </div>
                    <div class="form-group">
                        <label>Details</label>
                    </div>
                    <div class="form-group">
                        <form:textarea required="true" id="details" class="form-control" path="details" placeholder="Write the details for this service..." rows="6"/>
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
                    <form:input type="hidden" path="img"/>
                    <form:input type="hidden" path="img2"/>
                    <p class="help-block">Please input all information of the item and submit.</p>
                    <button style="margin-top: 3.5px" type="submit" class="btn btn-info">Submit</button>
                    <button style="margin-top: 3.5px" type="reset" onclick="location.href = '${pageContext.request.contextPath}/edit-service/${service.name}.html'" class="btn btn-danger">Cancel</button>
                </form:form>
            </div>
        </section>
    </div>
</div>
<%@ include file="common/footer.jspf"%>
<script type="text/javascript">
    window.onload = function () { //first load page
        var addResult = '${addResult}';
        checkAddResult(addResult);
    };
</script>