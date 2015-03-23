<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<portlet:defineObjects />

<liferay-theme:defineObjects />
 <portlet:renderURL var="addItemlURL" > 
 <portlet:param name="jspPage" value="/addItem.jsp"/>
 </portlet:renderURL>
 <c:if test="<%=request.getAttribute("msg_")!=null %>">
<br>
<br>
<span class="portlet-msg-error" >${msg_}</span>
<br>
 <br>
</c:if>
<b>ADD A NEW PRODUCT : </b> <br><br>

 <portlet:actionURL name="addProduct" var="addItemURL" /> 
 <form action="${addItemURL}" method="post">
 <table border="0">
 
 <tr>
 <td align="left">Description:</td>
 <td align="left"><input name="<portlet:namespace/>description" type="text" /></td>
 </tr>
  <tr>
 <td align="left">Cost Value: </td>
 <td align="left"><input name="<portlet:namespace/>cost" type="text" /> </td>
 </tr>
  <tr>
 <td align="left">Price Value: </td>
 <td align="left"><input name="<portlet:namespace/>price" type="text" /> </td>
 </tr>
   <tr>
 <td align="left">Initial Quantity:</td>
 <td align="left"> <input name="<portlet:namespace/>qty" type="text" /> </td>
 </tr>
 </table>
<br>
<input type="submit" value="Save Product"/>
</form>
<br>

 