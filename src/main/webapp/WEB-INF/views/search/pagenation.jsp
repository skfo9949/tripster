<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="hpadding20">
	<ul class="pagination right paddingbtm20">
		<c:set value="contents" var="go"/>
		
		<c:if test="${pageMaker.prev }">
			<li><a href="${pageMaker.makeQuery(pageMaker.startPage-1,go) }">&laquo;</a></li>
		</c:if>
		
		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
			
			<li id="contentsPage"
				<c:out value="${pageMaker.cri.page == idx?'class=active':'' }"></c:out>>
				<a href=${pageMaker.makeQuery(idx,go) } >${idx }</a>
			</li>
			
		</c:forEach>
		
		<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
			<li><a href="${pageMaker.makeQuery(pageMaker.endPage+1,go) }">&raquo;</a></li>
		</c:if>
	</ul>
</div>