/*=======================================*/
/*	分页js                                */
/*	@author noviachan                    */
/*=======================================*/
function creatpage(pageInfo){
	var ulinfo = creatulinfo(pageInfo);
	var html = "<span class=\"title\" style=\"margin-left: 20px;\">共"+pageInfo.pageCount+" 页， 总共"+pageInfo.rowsCount+"条记录</span>";
	html = html + "<ul class=\"pagination\" style=\"float: right;margin: 8px 30px;\">"+ulinfo+"</ul>";
	$("#pageinfo").html(html);
}

function creatulinfo(pageInfo){
	var ulinfo;
	if(pageInfo.pageCount <= 1){
		ulinfo = "<li class=\"disabled\"><a>上一页</a></li>"
			   + "<li class=\"active\"><a>1</a></li>"
			   + "<li class=\"disabled\"><a>下一页</a></li>";
	}else if(pageInfo.pageCount >1){
		if(pageInfo.pageNo == 1){
			ulinfo = "<li class=\"disabled\"><a onclick=\"return false;\">上一页</a></li>";
		}
		if(pageInfo.pageNo > 1){
			ulinfo = "<li><a onclick=\"return previous("+pageInfo.pageNo+")\">上一页</a></li>";
		}
		var pageStart = 1;
		var pageStop = pageInfo.pageCount;
		if(pageInfo.pageNo + 2 >= pageInfo.pageCount && pageInfo.pageCount > 5){
			pageStart = pageInfo.pageCount - 4;
		}
		if(pageInfo.pageNo + 2 < pageInfo.pageCount && pageInfo.pageCount > 5){
			pageStart = pageInfo.pageNo - 2;
			pageStop = pageInfo.pageNo + 2;
			if(pageInfo.pageNo < 3){
				pageStart = 1;
				pageStop = 5;
			}
		}
		for(var iNum = pageStart;iNum<=pageStop;iNum++){
			if(pageInfo.pageNo != iNum){
				ulinfo = ulinfo + "<li><a onclick=\"return jumpPage("+iNum+")\">"+iNum+"</a></li>";
			}else{
				ulinfo = ulinfo + "<li class=\"active\"><a>"+iNum+"</a></li>";
			}
		}
		if(pageInfo.pageNo >= pageInfo.pageCount){
			ulinfo = ulinfo + "<li class=\"disabled\"><a onclick=\"return false;\">下一页</a></li>";
		}
		if(pageInfo.pageNo < pageInfo.pageCount){
			ulinfo = ulinfo + "<li><a onclick=\"return next("+pageInfo.pageNo+")\">下一页</a></li>";
		}
	}

	return ulinfo;
}

function previous(pageNo){
	jumpPage(pageNo-1);
}

function next(pageNo){
	jumpPage(pageNo+1);
}

function jumpPage(pageNo){
	getData(pageNo);
}