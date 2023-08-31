package io.helidon.examples.quickstart.mp;

import jakarta.inject.Inject;
import io.helidon.microprofile.config.ConfigCdiExtension;
import io.helidon.microprofile.tests.junit5.AddBean;
import io.helidon.microprofile.tests.junit5.AddConfig;
import io.helidon.microprofile.tests.junit5.AddExtension;
import io.helidon.microprofile.tests.junit5.DisableDiscovery;
import io.helidon.microprofile.tests.junit5.HelidonTest;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@HelidonTest
@DisableDiscovery
@AddExtension(ConfigCdiExtension.class)
@AddBean(MessageTest.ConfiguredBean.class)
@AddConfig(key = "test.message", value = "Hello Guide!")
class MessageTest {
    @Inject
    ConfiguredBean bean;

    @Test
    void testBean() {
        assertEquals("Hello Guide!", bean.message());
    }

    public static class ConfiguredBean {
        @Inject
        @ConfigProperty(name = "test.message")
        private String message;

        String message() {
            return message;
        }
    }
}