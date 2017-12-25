<%@ include file="common/sub-content.jspf"%>
<%@ include file="common/single-room.jspf"%>
<div class="row">
    <div class="col-lg-6">
        <section class="panel">
            <div class="panel-body">
                <form action="${pageContext.request.contextPath}/room-img-edited/${room.name}.html" method="post" enctype="multipart/form-data">
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
                        <img id="blah" src="${pageContext.request.contextPath}/resources/img/rooms/${room.img}" alt="Room Image" style="height: 315px; width: 500px;" />
                    </div>
                    <div class="form-group">
                        <label style="margin-top: 28.5px">Image 2</label>
                    </div>
                    <div class="form-group">
                        <input type="file" name="img2" id="exampleInputFile" onchange="readURL(this, '#blah2', 500, 315);">
                    </div>
                    <div class="pull-left image">
                        <img id="blah2" src="${pageContext.request.contextPath}/resources/img/rooms/${room.img2}" alt="Room Image" style="height: 315px; width: 500px;" />
                    </div>
                    <p class="help-block">The image of the room will be changed after submit.</p>
                    <button type="submit" class="btn btn-info">Submit</button>
                    <button onclick="location.href = '${pageContext.request.contextPath}/edit-room/${room.name}.html'" type="reset" class="btn btn-danger">Cancel</button>
                </form>
                <label style="margin-top: 10px">*Suggestion</label>
                <ul>
                    <li>Name and Images of this room should be match</li>
                    <li>Type must be deluxe, family, couple or single</li>
                    <li>Size must be a natural number (sp)</li>
                    <li>Price must be a number ($)</li>
                    <li>Number of Adults must be a natural number (*Note that: 2 children are counted as 1 adult)</li>
                    <li>Amenities Score must be a natural number</li>
                    <li>You should write a suitable details for this room</li>
                    <li>Amenities should be written in one line with each amenity is separated by ',' </li>
                    <li>Status much be available or booked</li>
                    <li>If Status is available then the Booked By, Check in, Check out must be null</li>
                    <li>If Status is booked then the Booked By, Check in, Check out must be filled out</li>
                    <li>Booked By should be the username who booked this room</li>
                    <li>Check in must be the the check in date of the customer</li>
                    <li>Check out must be the the check out date of the customer</li>
                </ul>
            </div>
        </section>
    </div>
    <div class="col-lg-6">
        <section class="panel">
            <header class="panel-heading" style="font-weight: bold; color:red">Change Information!</header>
            <div class="panel-body">
                <form:form method="post" commandName="roomEdit" action="${pageContext.request.contextPath}/room-edited.html">
                    <div class="form-group">
                        <label>Room Name</label>
                    </div>
                    <form:input type="hidden" value="${room.id}" readonly="true" path="id"/>
                    <form:input type="hidden" value="${room.created_at}" readonly="true" path="created_at"/>
                    <form:input type="hidden" value="${room.created_by}" readonly="true" path="created_by"/>
                    <div class="form-group">
                        <form:input required="true" type="text" class="form-control" value="${room.name}" path="name" readonly="true"/>
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
                        <form:input required="true" type="number" class="form-control" value="${room.size}" placeholder="Size" path="size" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="number" class="form-control" value="${room.price}" placeholder="Price" path="price" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label>Number of Adults</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="number" class="form-control" value="${room.numpeople}" placeholder="Number of Adults" path="numpeople" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label style="margin-top: 13px">Amenities</label>
                    </div>
                    <div class="form-group">
                        <form:textarea required="true" id="amenities" class="form-control" value="${room.amenities}" path="amenities" placeholder="Write the amenities for this room..." rows="6"/>
                    </div>
                    <div class="form-group">
                        <label>Amenities Score</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="number" class="form-control" value="${room.avgAminities}" placeholder="Amenities Score" path="avgAminities" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label style="margin-top: 20px">Details</label>
                    </div>
                    <div class="form-group">
                        <form:textarea required="true" id="details" class="form-control" value="${room.details}" path="details" placeholder="Write the details for this room..." rows="4"/>
                    </div>
                    <div class="form-group">
                        <label>Status</label>
                    </div>
                    <div class="form-group">
                        <form:select class="form-control m-b-10" id="status" onchange="showBookedInfor()" path="status">
                            <form:option value="available">Available</form:option>
                            <form:option value="booked">Booked</form:option>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <label>Booked by</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="text" class="form-control" value="${room.booked_by}" id="bookedby" placeholder="Booked by" path="booked_by"/>
                    </div>
                    <div class="form-group">
                        <label>Check in</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="date" value="${room.checkin}" class="form-control" id="checkin" path="checkin"/>
                    </div>
                    <div class="form-group">
                        <label>Check out</label>
                    </div>
                    <div class="form-group">
                        <form:input required="true" type="date" value="${room.checkout}" class="form-control" id="checkout" path="checkout"/>
                    </div>
                    <form:input type="hidden" value="${room.img}" path="img"/>
                    <form:input type="hidden" value="${room.img2}" path="img2"/>
                    <p class="help-block">Your room will be changed after submit.</p>
                    <button style="margin-top: 3.5px" type="submit" class="btn btn-info">Submit</button>
                    <button style="margin-top: 3.5px" onclick="location.href = '${pageContext.request.contextPath}/edit-room/${room.name}.html'" type="reset" class="btn btn-danger">Cancel</button>
                </form:form>
            </div>
        </section>
    </div>
</div>
<%@ include file="common/related-room.jspf"%>
<%@ include file="common/footer.jspf"%>
<script type="text/javascript">
    window.onload = function () { //first load page
    	$("#name").val('${room.name}');
        $("#type").val('${room.type}');
        $("#status").val('${room.status}');
        $('#details').val('${room.details}');
        $('#amenities').val('${room.amenities}');
        showBookedInfor();
        var editResult = '${editResult}';
        checkeditresult(editResult);
        var addResult = '${addResult}';
        checkAddResult(addResult);
        window.history.pushState("string", "Hotel Admin", "${pageContext.request.contextPath}/edit-room/${room.name}.html");
    };
    function showBookedInfor() {
        var status = document.getElementById("status");
        var statusSelected = status.options[status.selectedIndex].value;
        if (statusSelected === 'booked') {
            document.getElementById("bookedby").readOnly = false;
            document.getElementById("checkin").readOnly = false;
            document.getElementById("checkout").readOnly = false;
        } else {
            document.getElementById("bookedby").readOnly = true;
            document.getElementById("checkin").readOnly = true;
            document.getElementById("checkout").readOnly = true;
        }
    }
</script>