package com.restaurant.tags;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberFormatterTag extends SimpleTagSupport {
    private final Logger log = LogManager.getLogger();
    private double number;
    private String format;

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void setNumber(double number) {
        this.number = round(number, 2);
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String numString = String.valueOf(number);
        try {
            if (format.equals("ukr")) {
                numString = numString.replace(".", ",");
            }
            getJspContext().getOut().write(numString);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
