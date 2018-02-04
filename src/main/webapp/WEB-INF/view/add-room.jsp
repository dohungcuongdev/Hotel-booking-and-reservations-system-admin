<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-lg-12">
        <section class="panel">
            <header class="panel-heading" style="font-weight: bold; color:red">Add new Room!</header>
            <div class="panel-body">
                <form:form method="post" commandName="newRoom" action="${pageContext.request.contextPath}/room-added.html">
                    <div class="form-group">
                        <label>Room Name</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="text" class="form-control" path="name" placeholder="Room name"/>
                    </div>
                    <div class="form-group">
                        <label>Type</label>
                    </div>
                    <div class="form-group">
                        <form:select class="form-control m-b-10" path="type" id="type">
                            <form:option value="deluxe">Deluxe</form:option>
                            <form:option value="family">Family</form:option>
                            <form:option value="couple">Couple</form:option>
                            <form:option value="single">Single</form:option>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <label>Size</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="number" class="form-control" placeholder="Size" path="size" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="number" class="form-control" placeholder="Price" path="price" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label>Number of Adults</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="number" class="form-control" placeholder="Number of Adults" path="numpeople" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label style="margin-top: 13px">Amenities</label>
                    </div>
                    <div class="form-group">
                        <form:textarea required="true" id="amenities" class="form-control" path="amenities" placeholder="Write the amenities for this room..." rows="6"/>
                    </div>
                    <div class="form-group">
                        <label>Amenities Score</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="number" class="form-control" placeholder="Amenities Score" path="avgAminities" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label style="margin-top: 20px">Details</label>
                    </div>
                    <div class="form-group">
                        <form:textarea required="true" id="details" class="form-control" path="details" placeholder="Write the details for this room..." rows="4"/>
                    </div>
                    <p class="help-block">Please input enough information and click Submit to add a new room.</p>
                    <button style="margin-top: 3.5px" type="submit" class="btn btn-info">Submit</button>
                    <button style="margin-top: 3.5px" onclick="location.href = '${pageContext.request.contextPath}/edit-room/${room.name}.html'" type="reset" class="btn btn-danger">Cancel</button>
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