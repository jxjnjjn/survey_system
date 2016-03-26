<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<shiro:authenticated>
	<div id="lside" class="col-sm-2 col-md-2 sidebar">
		<ul class="nav nav-pills nav-stacked">
			<li role="presentation"><a href="/system/composite/datacompositeindicator" target="mainframe">综合指数</a>
			</li>
			<li role="presentation"><a class="collapsed" href="#shuiliubiao" data-toggle="collapse" role="button"> 流水表 </a>
				<ul id="shuiliubiao" class="collapse nav nav-pills nav-stacked">
					<li role="presentation"><a href="/system/journal/dataoverview" target="mainframe">数据总览</a></li>
					<li role="presentation"><a href="/system/journal/database" target="mainframe">基础数据</a></li>
					<li role="presentation"><a href="/system/journal/lotteryroutine" target="mainframe">彩票常规数据</a></li>
					<shiro:hasAnyRoles name="caizhong">
						<li role="presentation"><a href="/system/journal/dataregularlotteryservice" target="mainframe">常规彩票业务数据</a></li>
					</shiro:hasAnyRoles>
					<shiro:hasAnyRoles name="caizhong">
						<li role="presentation"><a href="/system/journal/dataselflotteryservice" target="mainframe">自营彩票业务数据</a></li>
					</shiro:hasAnyRoles>
					<li role="presentation"><a href="/system/journal/datagame" target="mainframe">外接游戏数据</a></li>
					<li role="presentation"><a href="/system/journal/dataptgame" target="mainframe">PT游戏数据</a></li>
					<shiro:hasAnyRoles name="admin,quying">
						<li role="presentation"><a href="/system/journal/datagtgame" target="mainframe">GT游戏数据</a></li>
					</shiro:hasAnyRoles>
					<li role="presentation"><a href="/system/journal/datawithdraw" target="mainframe">提款数据</a></li>
					<li role="presentation"><a href="/system/journal/datarecharge" target="mainframe">充值数据</a></li>
					<li role="presentation"><a href="/system/journal/datawithdrawservice" target="mainframe">提现服务信息</a></li>
					<li role="presentation"><a href="/system/journal/datarechargeservice" target="mainframe">充值服务信息</a></li>
				</ul>
			</li>
			<li role="presentation"><a class="collapsed" href="#baobiao" data-toggle="collapse" role="button"> 实时数据 </a>
				<ul id="baobiao" class="collapse nav nav-pills nav-stacked">
					<li role="presentation"><a href="/system/report/reporteveryday" target="mainframe">每日充提数据</a></li>
				</ul>
			</li>
			<shiro:hasAnyRoles name="caiwu">
				<li role="presentation"><a class="collapsed" href="#finance" data-toggle="collapse" role="button"> 财务数据 </a>
					<ul id="finance" class="collapse nav nav-pills nav-stacked">
						<li role="presentation"><a href="/system/finance/data" target="mainframe">外接平台充提数据</a></li>
					</ul>
				</li>
			</shiro:hasAnyRoles>
			<shiro:hasAnyRoles name="ziguan1,ziguan2">
				<li role="presentation"><a class="collapsed" href="#fund" data-toggle="collapse" role="button"> 资管数据 </a>
					<ul id="fund" class="collapse nav nav-pills nav-stacked">
						<shiro:hasAnyRoles name="ziguan1">
							<li role="presentation"><a href="/system/fund/surplus" target="mainframe">沉余资金</a></li>
						</shiro:hasAnyRoles>
						<shiro:hasAnyRoles name="ziguan2">
							<li role="presentation"><a href="/system/fund/surplusclassify" target="mainframe">用户余额分类</a></li>
						</shiro:hasAnyRoles>
					</ul>
				</li>
			</shiro:hasAnyRoles>
			<li role="presentation"><a href="/system/export" target="mainframe">导出数据</a></li>
		</ul>
	</div>
</shiro:authenticated>