<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Details for ${ipDetails.external_ip_address}</header>
            <div class="panel-body table-responsive">
            	<table>
            		<tr><th>Details</th><th>Value</th></tr>
            		<tr><td>External IP </td><td>${ipDetails.external_ip_address}</td></tr>
            		<tr><td>Range [ low bound of IP block, high bound of IP block ] </td><td>${ipDetails.range}</td></tr>
            		<tr><td>Country code ISO-3166-1 </td><td>${ipDetails.country}</td></tr>
            		<tr><td>Region code </td><td>${ipDetails.region}</td></tr>
            		<tr><td>City Name </td><td>${ipDetails.city}</td></tr>
            		<tr><td>[ Latitude, Longitude ] of the city </td><td>${ipDetails.ll}</td></tr>
	            	<tr><td>Metro code </td><td>${ipDetails.metro}</td></tr>
	            	<tr><td>Postal code </td><td>${ipDetails.zip}</td></tr>
            	</table>
            </div>
        </div>
    <%@ include file="common/google-map.jspf"%>
    </div>            
</div>
<%@ include file="common/footer.jspf"%>