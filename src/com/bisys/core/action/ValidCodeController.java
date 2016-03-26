package com.bisys.core.action;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bisys.core.service.ValidCodeService;

/**
 * 验证码
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/valid")
public class ValidCodeController {
	
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
	public void getVaildCode(HttpServletRequest request, HttpServletResponse response, 
    		 String randomString) throws Exception{    	
		response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        Random random = new Random();

        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(alphas.length);
            sb.append(alphas[index]);
        }

        validCodeService.recordValidCode(randomString, sb.toString());
	}

}
