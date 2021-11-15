/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.loadtask;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ObjectUtils {

  private ObjectUtils() {}

  public static Map<String, Object> getFieldNamesAndValues(final Object obj, boolean publicOnly)
    throws IllegalArgumentException,IllegalAccessException
  {
    Class<? extends Object> c1 = obj.getClass();
    Map<String, Object> map = new HashMap<String, Object>();
    Field[] fields = c1.getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      String name = fields[i].getName();
      if (publicOnly) {
        if(Modifier.isPublic(fields[i].getModifiers())) {
          Object value = fields[i].get(obj);
          map.put(name, value);
        }
      }
      else {
        fields[i].setAccessible(true);
        Object value = fields[i].get(obj);
        map.put(name, value);
      }
    }
    return map;
  }

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception {

    Dummy dummy = new Dummy();
    System.out.println(ObjectUtils.getFieldNamesAndValues(dummy,true));
    System.out.println(ObjectUtils.getFieldNamesAndValues(dummy,false));
    /*
     * output :
     *  {value3=43, value1=foo, value2=42}
     *  {value3=43, value4=bar, value1=foo, value2=42}
     */
  }
}
