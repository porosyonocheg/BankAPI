package ru.sberbank.bankapi;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class TestExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static boolean started = false;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (!started) {
            started = true;
            context.getRoot().getStore(GLOBAL).put("AnyUniqueName", this);
            H2Server.run();
        }
    }

    @Override
    public void close() throws Exception {
        H2Server.stop();
    }
}
