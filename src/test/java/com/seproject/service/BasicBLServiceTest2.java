package com.seproject.service;

import com.seproject.common.RM;
import com.seproject.domain.testDomain.TestDomain;
import com.seproject.service.blService.BasicBLService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicBLServiceTest2 {
  /*  BasicBLService<TestDomain> service=Factory.getBasicBLService(new TestDomain());
    TestDomain t1=new TestDomain();
    TestDomain t2=new TestDomain();
    TestDomain t3=new TestDomain();
    TestDomain t4=new TestDomain();
    TestDomain t5=new TestDomain();
    @Before
    public void setUp() throws Exception {
        t1.setId(1);
        t2.setId(2);
        t3.setId(3);
        t4.setId(4);
        t5.setId(5);
        t1.setName("ssj");
        t2.setName("ssj");
        t3.setName("ssjdog");
        t4.setName("sdogsj");
        t5.setName("dogssj");
        t1.setNum(1);
        t2.setNum(2);
        t3.setNum(3);
        t4.setNum(4);
        t5.setNum(5);
        service.add(t1);
        service.add(t2);
        service.add(t3);
        service.add(t4);
        service.add(t5);
    }

    @After
    public void tearDown() throws Exception {
        service.delete("1");
        service.delete("2");
        service.delete("3");
        service.delete("4");
        service.delete("5");
    }

    @Test
    public void add() {
        TestDomain nothing=null;
        assertEquals(RM.FAILURE,service.add(nothing));
        TestDomain noKey=new TestDomain();//int型主键会把0加进去，但string型不会
        assertEquals(RM.SUCCESS,service.add(noKey));
        assertEquals(RM.FAILURE,service.add(noKey));
    }

    @Test
    public void findByKey() {
        assertEquals(null,service.findByKey(null));
    }

    @Test
    public void checkKeyExists() {
        assertEquals(false,service.checkKeyExists(null));
    }
*/
}