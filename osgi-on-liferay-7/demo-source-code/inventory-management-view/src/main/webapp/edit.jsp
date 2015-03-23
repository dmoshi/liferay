<%@page import="com.inventory.dao.model.Product"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<portlet:defineObjects />

<liferay-theme:defineObjects />
 <portlet:renderURL var="backURL" > 
 <portlet:param name="jspPage" value="/view.jsp"/>
 </portlet:renderURL>
 <%
 String description = ParamUtil.getString(request, "description");
	double cost =  ParamUtil.getDouble(request, "cost");
	double  price =  ParamUtil.getDouble(request, "price");
	int qty =  ParamUtil.getInteger(request, "qty");
	int index =  ParamUtil.getInteger(request, "index");
 %>
<b>UPDATE PRODUCT INFO : </b> <br><br>

 <portlet:actionURL name="updateProduct" var="updateProductURL" /> 
 <form action="${updateProductURL}" method="post">
 <table border="0">
 
 <tr>
 <td align="left">Description:</td>
 <td align="left"><input name="<portlet:namespace/>description" type="text" value="<%=description %>"/></td>
 </tr>
  <tr>
 <td align="left">Cost Value: </td>
 <td align="left"><input name="<portlet:namespace/>cost" type="text" value="<%=cost %>" /> </td>
 </tr>
  <tr>
 <td align="left">Price Value: </td>
 <td align="left"><input name="<portlet:namespace/>price" type="text" value="<%=price %>" /> </td>
 </tr>
   <tr>
 <td align="left">Initial Quantity:</td>
 <td align="left"> <input name="<portlet:namespace/>qty" type="text" value="<%=qty %>" /> </td>
 </tr>
 </table>
<br>
<input name="<portlet:namespace/>index" type="hidden" value="<%=index %>" />
<input type="submit" value="Update Product"/>
</form>
<br>

 