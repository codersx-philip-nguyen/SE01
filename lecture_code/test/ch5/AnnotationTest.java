package test.ch5;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;

import utils.Tool;
import utils.db.Serialisable;

/**
 * Demonstrate how to annotate methods of a class and how to read these annotated
 * methods at run-time
 * 
 * @author dmle
 *
 */
public class AnnotationTest {
  
  @Serialisable
  public void annotatedMethod() {
    //
  }
  
  @Test
  @Deprecated
  public void method2() {
    
  }
  
  public static void main(String[] args) {
    Class c = AnnotationTest.class;
    
    Method[] methods = c.getDeclaredMethods();

    Method m;
    
    Tool.print("Class " + c.getName());
    
    for (int i = 0; i < methods.length; i++) {
      m = methods[i];
      Tool.print("Method: " + m.toString());
      Annotation[] ans = m.getAnnotations();
      if (ans != null && ans.length > 0) {
        // print all annotations
        Tool.print("@annotated:");
        for (int j = 0; j < ans.length; j++) {
          Tool.print(ans[j].toString());
        }
      }
    }
  }
}
