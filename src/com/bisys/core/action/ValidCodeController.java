package com.bisys.core.action;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bisys.core.entity.JsonResult;
import com.bisys.core.exception.ServiceException;
import com.bisys.core.service.ValidCodeService;
import com.google.gson.Gson;

/**
 * 验证码
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/valid")
public class ValidCodeController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ValidCodeService validCodeService;
	
	private static char[] alphas = new char[36];

    static {
        for (int i = 0; i < 10; i++) {
            alphas[i] = (char) ('0' + i);
        }

        /*for (int i = 0; i < 26; i++) {
            alphas[i + 10] = (char) ('A' + i);
        }*/

        for (int i = 0; i < 26; i++) {
            alphas[i + 10] = (char) ('a' + i);
        }
    }
	
    @RequestMapping(value = "getvaildcode")
    @ResponseBody 
	public String getVaildCode(HttpServletRequest request, HttpServletResponse response, String user_name,
    		 String randomString) throws Exception{    	
    	logger.info("获取验证码:" + randomString);
		
		boolean flag = false;
		String errorMessage = "获取验证码失败";
		JsonResult<String> jsonResult = new JsonResult<String>();
		
		try {
	        Random random = new Random();
	        StringBuffer sb = new StringBuffer("");
	        for (int i = 0; i < 4; i++) {
	            int index = random.nextInt(alphas.length);
	            sb.append(alphas[index]);
	        }
	        validCodeService.recordValidCode(randomString, sb.toString(),user_name);
			flag = true;
		}catch (ServiceException serviceE){
			logger.error("sys admin getvaildcode failed!"+serviceE.getMessage());
			errorMessage = serviceE.getMessage();
		}catch (Exception e) {
			logger.error("sys admin getvaildcode failed!", e);
		}
		
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "获取验证码成功 ，请查看手机信息" : errorMessage);
		return new Gson().toJson(jsonResult);

	}

}
