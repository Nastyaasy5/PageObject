package core.hotline.page_object.common.utils;

import core.hotline.page_object.common.AbstractPage;
import core.hotline.page_object.common.annotations.DefaultPath;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class WebDriverUtils {

    private WebDriverUtils() {
    }

    public static <T extends AbstractPage> String getDefaultPathByPage(T page) throws IOException {
        String annotationValue=null;
        PropertyUtils propertyUtils = new PropertyUtils();
        Class pageClass = page.getClass();
        Annotation[] annotations = pageClass.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof DefaultPath) {
                annotationValue =  ((DefaultPath) annotation).defaultPath();
                break;
            }
        }
        return String.format("%s%s", propertyUtils.getProperty("main.site.url"), annotationValue);
    }
}
