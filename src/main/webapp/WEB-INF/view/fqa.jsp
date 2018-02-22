<%@ include file="common/sub-content.jspf"%>
<div class="row">
	<div class="col-lg-12">
		<div class="iframe-container">
			<iframe src="${pageContext.request.contextPath}/resources/pdf/fqa.pdf" width="1100px" height="1000px"></iframe>
		</div>
		<form action="${pageContext.request.contextPath}/upload-fqa.html"
			enctype="multipart/form-data" method="post">
			<div class="form-group">
				<input type="file" name="fqaPDF" />
			</div>
			<button type="submit" class="btn btn-info"> <b>Upload PDF</b> <i class="fa fa-upload"></i>
			</button>
			<button type="button" class="btn btn-success"> <a href="${pageContext.request.contextPath}/resources/pdf/fqa.pdf" download><b>Download PDF </b><i class="fa fa-download"></i></a>
			</button>
		</form>
	</div>
</div>
<%@ include file="common/footer.jspf"%>
<script type="text/javascript">
	window.onload = function() { //first load page
		var uploadResult = '${uploadResult}';
		if (uploadResult === undefined) {
		} else if (uploadResult === "Upload successfully")
			swal('Congrats!', uploadResult, 'success');
		else if (uploadResult !== '')
			swal('Oops...!', uploadResult, 'error');
	};
</script>