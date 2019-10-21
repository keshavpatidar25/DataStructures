package demo;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@MyAnnotation(name="abc", value="def")
public class Pojo implements Cloneable{
    private String dm;

    public Pojo(String dm) {
        this.dm = dm;
    }

   /* @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Pojo(this.dm);
    }*/

    public void setDm(String dm) {
        this.dm = dm;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "dm='" + dm + '\'' +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Pojo pojo = new Pojo("dm1");
        pojo.setDm("dm2");
        Map<String, String> testMap = new ConcurrentHashMap<>();
        testMap.put(null, null);
        System.out.println(pojo.clone());
        Arrays.stream(Pojo.class.getAnnotations()).forEach(annotation -> {
            if(annotation instanceof MyAnnotation) {
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("name  = " + myAnnotation.name());
                System.out.println("value = " + myAnnotation.value());
            }
        });
    }
}
