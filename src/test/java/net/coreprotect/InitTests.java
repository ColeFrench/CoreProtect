package net.coreprotect;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.plugin.PluginManagerMock;
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

    @Test
    @Order(1)
    @DisplayName("Test enabling and disabling plugin")
    void testAbling() {
        final PluginManagerMock pluginManager = MockBukkit.getMock().getPluginManager();

        pluginManager.disablePlugin(plugin);
        Assertions.assertFalse(pluginManager.isPluginEnabled(plugin));

        pluginManager.enablePlugin(plugin);
        Assertions.assertTrue(pluginManager.isPluginEnabled(plugin));
    }

    @AfterAll
    static void tearDown() {
        MockBukkit.unmock();
    }
}
