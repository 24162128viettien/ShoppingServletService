<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:url value="/admin/category/edit" var="edit"></c:url>
<form role="form" action="${edit}" method="post"
enctype="multipart/form-data">
    <input name="id" value="${category.id }" hidden="">
    <div class="form-group">
        <label>Tên danh sách:</label>
        <input type="text" class="form-control" value="${category.name }" name="name" />
        <c:if test="${errors.name != null}"><div class="text-danger small">${errors.name}</div></c:if>
    </div>
    <div class="form-group">
        <c:url value="/image?fname=${category.icon }" var="imgUrl"></c:url>
        <img class="img-responsive" width="100px" src="${imgUrl}" alt="">
        <label>Ảnh đại diện</label>
        <input type="file" name="icon" value="${category.icon }" />
    </div>
    <button type="submit" class="btn btn-default">Edit</button>
    <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-primary">Hủy</a>
</form>
