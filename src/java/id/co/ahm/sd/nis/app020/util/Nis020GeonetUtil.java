package id.co.ahm.sd.nis.app020.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Nis020GeonetUtil {

    public boolean isSearchField() default false;

    public String embedSearchField() default ""; // separator pake #

    public String query() default "";

    public String dropDownQuery() default "";

    public String embedDropDownQuery() default ""; // separator pake #

    public String joinFieldQuery() default "";

    public String dateFormat() default "";

    public String dateFormatJoinQuery() default "";

}
