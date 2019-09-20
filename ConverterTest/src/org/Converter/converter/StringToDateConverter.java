package org.Converter.converter;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String , Date> {
    private String datePattern;
    public void setDatePattern(String datePattern){
        this.datePattern=datePattern;
    }

    public Date convert(String date){
        SimpleDateFormat dateFormat=new SimpleDateFormat(this.datePattern);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.print("日期转换失败");
            return null;
        }
    }

}
