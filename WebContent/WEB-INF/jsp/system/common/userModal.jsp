<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!-- Modal -->
<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button id="topclosebtn" type="button" class="close"
					data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="userModalLabel">账户管理</h4>
			</div>
			<div class="modal-body">
				<div id="userinfo" class="form-group">
					<label>账户：${sysUser.user_name}</label> <label
						style="margin-left: 300px; color: #949494;">管理员</label><br> <a
						class="navbar-link" id="managepass">修改密码</a>
				</div>
				<form id="changepass" class="form-vertical" style="display: none;">
					<input type="hidden" name="userName" id="userName"
						value="${sysUser.user_name}">
					<div class="form-group">
						<div class="input-group" style="margin-left: 80px;">
							<label for="oldPass">旧密码：</label> <input type="password"
								class="form-control" name="oldPass" id="oldPass"
								placeholder="请输入旧密码">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group" style="margin-left: 80px;">
							<label for="newPass">新密码：</label> <input type="password"
								class="form-control" name="newPass" id="newPass"
								placeholder="请输入新密码">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group" style="margin-left: 80px;">
							<label for="confirmPass">再确认：</label> <input type="password"
								class="form-control" name="confirmPass" id="confirmPass"
								placeholder="请确认新密码">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button id="closebtn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button id="submitbtn" type="button" class="btn btn-primary" style="display: none;">submit</button>
			</div>
		</div>
	</div>
</div>