package com.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 *  2、类上范型
 *  @param <T>
 *  @author zhqiang
 *  @created 2013-3-15 上午09:29:27
 *  @lastModified       
 *  @history
 */
public class StudentDAO<T> {
    
    public void addStudent(T t){
        
    }
    
    public void delStudent(T t){
        
    }
    public void delStudent(int id){
        
    }

    public void updateStudent(int id){
        
    }
    
    public T getStudent(int id){
        return null;
    }
    
    public List<T> getStudents(int classID){
        return new ArrayList();
    }
    
    public List<T> getList(Map map){
        return new ArrayList();
    }
    
    public <E>List<E> getElementList(Class clazz,Map map) throws InstantiationException, IllegalAccessException{
        List<E> list =  new ArrayList<E>();    
        for(int i = 0,j=7;i<j;i++){            
            list.add((E) clazz.newInstance());
        }
        return list;
    }


}




