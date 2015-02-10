package com.wasu.upm.config;

import org.junit.Rule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

/**
 * TODO(description behaviour)
 *
 * @author   czheng
 * @data 14-11-5下午2:57
 */
public class BaseTest {

    private long l ;

    @org.junit.Before
    public void before(){
        l=System.nanoTime();
    }


    @org.junit.After
    public void after(){
        System.out.println("the Test Execution time(s):"+new java.math.BigDecimal((System.nanoTime()-l)/1000000000f,new java.math.MathContext(5)).toString());
    }

    @Rule
    public TestWatchman watchman =new TestWatchman() {
        @Override
        public void finished(FrameworkMethod method) {
            System.out.println(method.getName()+" execute end");
        }
    };
}