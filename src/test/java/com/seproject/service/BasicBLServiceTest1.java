package com.seproject.service;

import com.seproject.common.SearchCategory;
import com.seproject.domain.testDomain.TestDomain;
import com.seproject.service.blService.BasicBLService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BasicBLServiceTest1 {
 /*   BasicBLService<TestDomain> service=Factory.getBasicBLService(new TestDomain());
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
    public void search() {
        ArrayList<TestDomain> list1,list2,list3,list4,list5;
        list1=service.search("name", SearchCategory.EQUAL,"ssj");
        list2=service.search("name",SearchCategory.CONTAINS,"ssj");
        list3=service.search("name",SearchCategory.NOT_EQUAL,"ssj");
        list4=service.search("num",SearchCategory.SMALLER_THAN,"5");
        list5=service.search("num",SearchCategory.LARGER_THAN,"3");
        int size1=list1.size(),size2=list2.size(),size3=list3.size(),size4=list4.size(),size5=list5.size();
        assertEquals(2,size1);
        assertEquals(5,size2);
        assertEquals(3,size3);
        assertEquals(4,size4);
        assertEquals(2,size5);
    }
*/
}