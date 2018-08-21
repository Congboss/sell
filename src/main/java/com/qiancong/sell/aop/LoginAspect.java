package com.qiancong.sell.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @program: sell
 * @description:登陆鉴权
 * @author: Cong.Qian
 * @create: 2018-08-21 21:34
 **/
@Slf4j
@Component
@Aspect
public class LoginAspect {
    @Autowired
    HttpSession session;
    @Pointcut("execution(public * com.qiancong.sell.controller.*.*(..))&&!execution(public * com.qiancong.sell.controller.LoginController.*(..))")
    public void loginAuth(){};

    @Around("loginAuth()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        if(session.getAttribute("name")==null){
log.info("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            return new ModelAndView("redirect:/") ;
        }
        return pjp.proceed();
    }
}
