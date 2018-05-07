<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String pathl = request.getContextPath();
	String basePathl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathl+"/";
%>
		<!-- 本页面涉及的js函数，都在head.jsp页面中     -->
		<div id="sidebar" class="">

				<div id="sidebar-shortcuts">

					<div id="sidebar-shortcuts-large"> 

						<c:if test="${NEW_USER.roleId==1 }">
							<%-- <button class="btn btn-small btn-info" title="样式参考" onclick="window.open('<%=basePathl%>static/UI_new');"><i class="icon-heart"></i></button> --%>
							<button class="btn btn-small btn-danger" title="菜单管理" id="adminmenu" onclick="menu();"><i class="icon-cogs"></i></button>
						</c:if> 
					</div>

					<div id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>

				</div><!-- #sidebar-shortcuts -->


				<ul class="nav nav-list">
					<c:if test="${NEW_USER.roleId==1 }">
						<li class="active" id="fhindex">
						  <a href="userControl/mainIndex"><i class="icon-dashboard"></i><span>后台首页</span></a>
						</li>
					</c:if>


			<c:forEach items="${menuList}" var="menu">
				<%-- <c:if test="${menu.hasMenu}"> --%>
				<%-- <li id="lm${menu.MENU_ID }"> --%>
				<li id="lm${menu.menuId }">
					  <a style="cursor:pointer;" class="dropdown-toggle" >
						<i class="${menu.menuIcon == null ? 'icon-desktop' : menu.menuIcon}"></i>
						<span>${menu.menuName }</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
							<c:forEach items="${menu.subMenu}" var="sub">
								<%-- <c:if test="${sub.hasMenu}"> --%>
								<c:choose>
									<c:when test="${not empty sub.menuUrl}">
									<li id="z${sub.menuId }">
									<a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('z${sub.menuId }','lm${menu.menuId }','${sub.menuName }','${sub.menuUrl }')"><i class="icon-double-angle-right"></i>${sub.menuName }</a></li>
									</c:when> 
									<c:otherwise>
									<li><a href="javascript:void(0);"><i class="icon-double-angle-right"></i>${sub.menuName }</a></li>
									</c:otherwise> 
								</c:choose>
								<%-- </c:if> --%>
							</c:forEach>
				  		</ul>
				</li>
				<%-- </c:if> --%>
			</c:forEach>

				</ul><!--/.nav-list-->

				<div id="sidebar-collapse"><i class="icon-double-angle-left"></i></div>

			</div><!--/#sidebar-->

