<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<portlet:defineObjects />

<liferay-theme:defineObjects />
<liferay-ui:error key="error" message="You have no permission to perform this operation" />
<c:if test="<%=request.getParameter("msg")!=null %>">
<br>
<br>
<span class="portlet-msg-error" >${msg}</span>
<br>
 <br>
</c:if>

<b>AVAILABLE PRODUCTS : </b> <br><br>
 <table  border="1"  style="width: 100%">
 <tr>
  <td align="center"><b>ID #</b></td>
 <td align="center"><b>DESCRIPTION</b></td>
 <td align="center"><b>COST</b></td>
  <td align="center"><b>PRICE</b></td>
   <td align="center"><b>QTY</b></td>
     <td align="center"><b>ACTION</b></td>
 </tr>
<c:forEach var="product" items="${products}" >
<portlet:renderURL var="editURL">
<portlet:param name="jspPage" value="/edit.jsp"/>
<portlet:param name="index" value="${product.index}"/>
<portlet:param name="description" value="${product.description}"/>
<portlet:param name="cost" value="${product.costValue}"/>
<portlet:param name="price" value="${product.priceValue}"/>
<portlet:param name="qty" value="${product.stockValue}"/>
</portlet:renderURL>

<portlet:actionURL name="deleteProduct" var="deleteURL">
<portlet:param name="index" value="${product.index}"/>
</portlet:actionURL>

 <tr >
  <td align="left" width="10%">${product.index}</td>
 <td align="left" width="30%">${product.description}</td>
 <td align="left" width="10%">${product.costValue}</td>
  <td align="left" width="10%">${product.priceValue}</td>
   <td align="left" width="10%">${product.stockValue}</td>
   <td align="center" width="30%"><c:if test="${signedIn}"><a href="${editURL}">Edit</a>&nbsp;&nbsp;&nbsp;||&nbsp;&nbsp;&nbsp;<a href="${deleteURL}">Delete</a></c:if></td>
 </tr>
 

</c:forEach>

</table>
<br><br>