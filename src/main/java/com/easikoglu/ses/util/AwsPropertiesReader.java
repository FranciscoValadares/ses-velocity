package com.easikoglu.ses.util;

import java.io.InputStream;

public class AwsPropertiesReader {

    InputStream stream;

    private AwsPropertiesReader() {
        stream = this.getClass().getClassLoader()
                .getResourceAsStream("awscredentials.properties");

    }

    private static class LazyHolder {
        private static final AwsPropertiesReader INSTANCE = new AwsPropertiesReader();
    }

    public static AwsPropertiesReader getInstance() {
        return LazyHolder.INSTANCE;
    }


    public InputStream getStream() {
        return stream;
    }

}
