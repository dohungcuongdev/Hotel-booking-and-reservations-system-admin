<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Details for ${externalip}</header>
            <div class="panel-body table-responsive">
            	<table>
            		<tr><th>Details</th><th>Value</th></tr>
            		<tr><td>External IP </td><td>${externalip}</td></tr>
            		<tr><td>Country code ISO-3166-1 </td><td>${ipDetails.countryCode}</td></tr>
            		<tr><td>Country Name</td><td>${ipDetails.countryName}</td></tr>
            		<tr><td>City Name </td><td>${ipDetails.city}</td></tr>
            		<tr><td>Region code </td><td>${ipDetails.region}</td></tr>
            		<tr><td>Latitude</td><td>${ipDetails.latitude}</td></tr>
					<tr><td>Longitude</td><td>${ipDetails.longitude}</td></tr>
            		<tr><td>Area Code </td><td>${ipDetails.areaCode}</td></tr>
            		<tr><td>DMA Code </td><td>${ipDetails.dmaCode}</td></tr>
	            	<tr><td>Metro code </td><td>${ipDetails.metroCode}</td></tr>
	            	<tr><td>Postal code </td><td>${ipDetails.postalCode}</td></tr>
            	</table>
            </div>
        </div>
    <%@ include file="common/google-map.jspf"%>
    </div>            
</div>
<%@ include file="common/footer.jspf"%>