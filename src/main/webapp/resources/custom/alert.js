function deleteService(serviceid) {
    swal({
        title: "Are you sure?",
        text: "Delete this item from restaurant now!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        closeOnConfirm: false
    }, function () {
        window.location.href = 'remove-service/' + serviceid + '.htm';
    });
}

function deleteRoom(roomid) {
    swal({
        title: "Are you sure?",
        text: "Delete this room now!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        closeOnConfirm: false
    }, function () {
        window.location.href = 'remove-room/' + roomid + '.htm';
    });
}

function checkSendEmail(r) {
    console.log(r);
    if (r === undefined || r === '') {
    } else if (r === "Sent successfully")
        swal('Congrats!', 'Email Sent successfully!', 'success');
    else 
        swal('Oops...!', r, 'error');
}

function checkUpdateResult(r, success_mes) {
    if (r === undefined) {
    } else if (r === "success")
        swal('Congrats!', success_mes, 'success');
    else if (r !== '')
        swal('Oops...!', r, 'error');
}

function checkeditresult(r) {
	checkUpdateResult(r, 'Edited successfully!');
}

function checkAddResult(r) {
	checkUpdateResult(r, 'Hotel Item Added with default image. You can change the image of this item!');
}