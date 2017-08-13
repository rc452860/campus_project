package com.sakura.dev.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Created by rc452 on 2017/6/6.
 */
@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * 修正分页index从0开始的问题全部修正为1
     * @param proceedingJoinPoint 切入点
     * @param pageable 分页类
     */
//    @Around("execution(org.springframework.data.domain.Page com.sakura.dev.service..* .*(..))&&args(pageable)")
//    public Object paging(ProceedingJoinPoint proceedingJoinPoint, Pageable pageable){
//        pageable = pageable.previousOrFirst();
//        try {
//            Page page =  (Page) proceedingJoinPoint.proceed();
//            logger.info(page.toString());
//            return page;
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            return Result.ERR(throwable.getMessage());
//        }
//    }
}
