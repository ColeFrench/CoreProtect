package net.coreprotect;

import be.seeseemelk.mockbukkit.MockBukkit;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Loading and accessibility tests")
class InitTests {
    private static CoreProtect plugin;

    @BeforeAll
    static void setUp() {
        MockBukkit.mock();
    }

    @Test
    @Order(0)
    void testPluginAccess() {
        plugin = MockBukkit.load(CoreProtect.class);
        Assertions.assertNotNull(plugin);
    }

    @Test
    @Order(1)
    void testAPIAccess() {
        final CoreProtectAPI api = plugin.getAPI();
        Assertions.assertNotNull(api);
        Assertions.assertTrue(api.isEnabled());
    }

    @Test
    @Order(1)
    void testEnvironment() {
        Assertions.assertSame(CoreProtect.Environment.TESTING, plugin.getEnvironment());
    }

    @AfterAll
    static void tearDown() {
        MockBukkit.unmock();
    }
}
