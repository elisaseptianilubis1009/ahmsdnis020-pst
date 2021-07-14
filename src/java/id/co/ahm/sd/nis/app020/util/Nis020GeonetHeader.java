package id.co.ahm.sd.nis.app020.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Nis020GeonetHeader {

    public String sqldefault() default "";

    public String sqlorder() default "";

    public String sqlparam() default "";

    public String tableName() default "";

}
