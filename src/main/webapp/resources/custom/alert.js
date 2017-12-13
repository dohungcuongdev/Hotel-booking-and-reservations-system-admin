function remove(id, item, text) {
    swal({
        title: "Are you sure?",
        text: text,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        closeOnConfirm: false
    }, function () {
        window.location.href = 'remove-' + item + '/' + id + '.html';
    });
}

function deleteService(serviceid) {
	remove(serviceid, 'service', 'Delete this item from restaurant now!');
}

function deleteRoom(roomid) {
	remove(roomid, 'room', 'Delete this room now!');
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