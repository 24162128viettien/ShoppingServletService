<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<form role="form" action="add" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label>Tên danh mục:</label>
        <input class="form-control" placeholder="please enter category Name" name="name" value="${category.name}" />
        <c:if test="${errors.name != null}"><div class="text-danger small">${errors.name}</div></c:if>
    </div>
    <div class="form-group">
        <label>Ảnh đại diện</label>
        <input type="file" name="icon" />
    </div>
    <button type="submit" class="btn btn-default">Thêm</button>
    <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-primary">Hủy</a>
</form>
