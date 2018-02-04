<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-lg-6">
        <section class="panel">
            <header class="panel-heading" style="font-weight: bold; color:red">
                Profile
                <div class="pull-left image">
                    <img id="blah" src="${pageContext.request.contextPath}/resources/img/users/${ad.img}" class="img-circle" alt="Profile Image" style="height: 210px; width: 200px;" />
                </div>
            </header>
            <div class="panel-body">
                <div class="form-group"><strong style="margin-left: 10px">User Name</strong>: ${ad.username}</div>
                <div class="form-group"><strong style="margin-left: 10px">Name</strong>: ${ad.name}</div>
                <div class="form-group"><strong style="margin-left: 10px">Gender</strong>: ${ad.gender}</div>
                <div class="form-group"><strong style="margin-left: 10px">Phone Number</strong>: ${ad.phone}</div>
                <div class="form-group"><strong style="margin-left: 10px">Birthday</strong>: ${ad.birthday}</div>
                <form action="${pageContext.request.contextPath}/profile-img-edited.html" enctype="multipart/form-data" method="post">
                    <div class="form-group">
                        <input type="file" name="img" value="${ad.img}" onchange="readURL(this, '#blah', 200, 210);"/>
                    </div>
                    <button type="submit" class="btn btn-info">Change your image</button>
                    <button type="submit" class="btn btn-danger">Cancel</button>
                </form>
                <form:form method="post" commandName="changePassBean" action="${pageContext.request.contextPath}/change-password.html">
                    <div class="form-group">
                        <br><label style="font-size: 17px; color:blue; margin-top: 24px"><strong>Change password!</strong></label>
                    </div>
                    <div class="form-group">
                        <label>Current Password</label>
                        <form:input type="password" class="form-control" path="currentpassword" placeholder="Current Password" required="true"/>
                    </div>
                    <div class="form-group">
                        <label>New Password</label>
                        <form:input type="password" class="form-control" path="newpassword" placeholder="New Password" required="true"/>
                    </div>
                    <div class="form-group">
                        <label>Confirm Password</label>
                        <form:input type="password" class="form-control" path="confirm" placeholder="Confirm Password" required="true"/>
                        <p class="help-block">Make sure that your confirm password match.</p>
                    </div>
                    <button type="submit" class="btn btn-info">Submit</button>
                    <button type="submit" class="btn btn-danger">Cancel</button>
                </form:form>
            </div>
        </section>
    </div>
    <div class="col-lg-6">
        <section class="panel">
            <header class="panel-heading" style="font-weight: bold; color:red">
                Change personal information!
            </header>
            <div class="panel-body">
                <form:form method="post" commandName="adminEdit" action="${pageContext.request.contextPath}/profile-edited.html">
                    <div class="form-group">
                        <label>Name</label>
                    </div>
                    <form:input type="hidden" value="${ad.id}" readonly="true" path="id"/>
                    <div class="form-group">
                        <form:input type="text" class="form-control" value="${ad.name}" path="name" placeholder="Name" required="true"/>
                    </div>
                    <div class="form-group">
                        <label>Gender</label>
                    </div>
                    <div class="form-group">
                        <form:select class="form-control m-b-10" path="gender" required="true">
                            <form:option value="male">Male</form:option>
                            <form:option value="female">Female</form:option>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                    </div>
                    <div class="form-group">
                        <form:input type="number" class="form-control" value="${ad.phone}" required="true" path="phone" placeholder="Phone Number" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                    </div>
                    <div class="form-group">
                        <label>BirthDay</label>
                    </div>
                    <div class="form-group">
                        <form:input type="date" class="form-control" value="${ad.birthday}" required="true" path="birthday"/>
                    </div>
                    <form:input type="hidden" value="${ad.username}" path="username"/>
                    <form:input type="hidden" value="${ad.img}" path="img"/>
                    <form:input type="hidden" value="${ad.password}" path="password"/>
                    <p class="help-block">Your profile will be changed after submit.</p>
                    <button style="margin-top: 3.5px" type="submit" class="btn btn-info">Submit</button>
                    <button style="margin-top: 3.5px" type="reset" onclick="location.href = '${pageContext.request.contextPath}/profile.html'" class="btn btn-danger">Cancel</button>
                    <div class="form-group">
                        <label style="margin-top: 10px">*Suggestion</label>
                        <ul>
                            <li>You cannot change your email</li>
                            <li>All the information must not be null</li>
                            <li>Gender must be Male or Female</li>
                            <li>Phone must be a 9-digits natural number</li>
                            <li>Birthday must be a date</li>
                            <li>You have to input correctly your current password and confirm for a new password to change your password</li>
                        </ul>
                    </div>
                </form:form>
            </div>
        </section>
    </div>
</div>
<%@ include file="common/footer.jspf"%>
<script type="text/javascript">
    window.onload = function () { //first load page
        var editResult = '${editResult}';
        checkeditresult(editResult);
        window.history.pushState("string", "Hotel Admin", "${pageContext.request.contextPath}/profile.html");
        $("#gender").val('${ad.gender}');
        
        var pwCheckingResult = "${pwCheckingResult}";
        checkChangePWResult(pwCheckingResult);
    };
    
    function checkChangePWResult(r) {
        if (r === undefined) {
        } else if (r === "Password changed successfully")
            swal('Congrats!', r, 'success');
        else if (r !== '')
            swal('Oops...!', r, 'error');
    }
</script>