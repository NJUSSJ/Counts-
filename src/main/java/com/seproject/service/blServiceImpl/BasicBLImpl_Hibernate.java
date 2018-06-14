package com.seproject.service.blServiceImpl;

import com.seproject.service.BasicUtilService;
import com.seproject.common.RM;
import com.seproject.common.SearchCategory;
import com.seproject.service.blService.BasicBLService;
import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class BasicBLImpl_Hibernate<T> implements BasicBLService<T> {

    T type;
    private SessionFactory sessionFactory;
    private Session session = null;

    public BasicBLImpl_Hibernate(T t) {
        type = t;
        sessionFactory = new Configuration().configure().addPackage("com.seproject.domain").addAnnotatedClass(type.getClass()).buildSessionFactory();
    }

    @Override
    public RM add(T t0) {

        session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(t0);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.out.println("新增失败，发生回滚");
                tx.rollback();

                e.printStackTrace();

            }

        } catch (PersistenceException e) {        //数据库中已有此主键
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("主键已经存在");
            return RM.KEY_EXISTS;

        } finally {
            session.close();
        }
        return RM.SUCCESS;
    }

    @Override
    public RM delete(String keyValue) {
        boolean res = true;
        session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(session.get(type.getClass(), keyValue));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("删除失败");
            }
            res = false;
        } finally {
            session.close();
        }
        return RM.SUCCESS;
    }

    @Override
    public RM update(T t0) {

        boolean res = true;
        session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(t0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("更新失败");

            }
            res = false;

        } finally {
            session.close();
        }
        return RM.SUCCESS;
    }

    @Override
    public T findByKey(String keyValue) {
        session = sessionFactory.openSession();
        Transaction tx = null;
        T po = null;
        try {
            tx = session.beginTransaction();
            po = (T) session.get(type.getClass(), keyValue);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("获取失败");

            }

        } finally {
            session.close();
        }
        return po;
    }

    @Override
    public ArrayList<T> search(String keyName, SearchCategory category, String keyValue) {
        ArrayList<T> arr = new ArrayList<T>();
        ArrayList<T> objects = getAllObjects();
        if(objects==null||objects.size()<=0){
            return arr;
        }
        System.out.println("size:"+objects.size());
        int searchId = BasicUtilService.getKeyID(type, keyName);
        for (int i = 0; i < objects.size(); i++) {
            String value = BasicUtilService.getKeyValue(objects.get(i), searchId);
            switch (category) {
                case EQUAL:
                    if (value.equals(keyValue)) {
                        arr.add(objects.get(i));
                    }
                    break;
                case NOT_EQUAL:
                    if (!value.equals(keyValue)) {
                        arr.add(objects.get(i));
                    }
                    break;
                case CONTAINS:
                    if (value.contains(keyValue)) {
                        arr.add(objects.get(i));
                    }
                    break;
                case LARGER_THAN:
                    if (Double.parseDouble(value) > Double.parseDouble(keyValue)) {
                        arr.add(objects.get(i));
                    }
                    break;
                case SMALLER_THAN:
                    if (Double.parseDouble(value) < Double.parseDouble(keyValue)) {
                        arr.add(objects.get(i));
                    }
                    break;
            }
        }
        return arr;
    }



    @Override
    public boolean checkKeyExists(String keyValue) {
        if(findByKey(keyValue)==null) {
            return false;
        }else{
            return true;
        }

    }

    @Override
    public ArrayList<T> getAllObjects() {

        session=sessionFactory.openSession();
        Transaction tx=null;
        List<T> list=new ArrayList<T>();//初始化
        try {
            tx=session.beginTransaction();
            String s0="FROM "+type.getClass().getName();
            list=session.createQuery(s0).list();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
                System.out.println("新增失败");

            }

        }finally {
            session.close();
        }
        return (ArrayList<T>) list;
    }
}
