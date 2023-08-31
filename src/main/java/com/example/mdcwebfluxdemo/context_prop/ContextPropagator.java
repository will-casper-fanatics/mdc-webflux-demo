package com.example.mdcwebfluxdemo.context_prop;

import io.micrometer.context.ContextRegistry;
import io.micrometer.context.ThreadLocalAccessor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextPropagator {

    public static final String USER_ID = "userId";

    public ContextPropagator() {
        ContextRegistry.getInstance().registerThreadLocalAccessor(new UserIdThreadContextAccessor());
    }

    private static class UserIdThreadContextAccessor implements ThreadLocalAccessor<String> {
        @Override
        public Object key() {
            return USER_ID;
        }

        @Override
        public String getValue() {
            return MDC.get(USER_ID);
        }

        @Override
        public void setValue(String value) {
            MDC.put(USER_ID, value);
        }

        // method is deprecated, but reactor relies on it, so don't remove
        @Override
        @SuppressWarnings("deprecation")
        public void reset() {
            MDC.remove(USER_ID);
        }
    }
}
