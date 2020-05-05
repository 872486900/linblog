package com.lzx.linblog.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by 87248 on 2020-04-18 19:08
 */
@Aspect
@Component//零件
public class LogAspect {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    //切面
    @Pointcut("execution(* com.lzx.linblog.web.*.*(..))")
    public void log(){

    }
    //切面之前
    @Before("log()")
    public void doBedore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);

        logger.info("Request:{}",requestLog);
    }

    //切面之后
    @After("log()")
    public void doAfter(){
        logger.info("====doAfter=======");
    }


    //获取返回内容
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfrerRuturn(Object result){
        logger.info("Result:{}"+result);

    }

    public class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
