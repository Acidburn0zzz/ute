package net.lmxm.ute.gui.statusoutput;

import net.lmxm.ute.event.StatusChangeEventType;
import org.junit.Test;

import javax.swing.text.StyleContext;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for StatusOutputPanel.
 */
public final class StatusOutputPanelTest {
    @Test
    public void testCreateStyleContext() {
        final StyleContext styleContext = StatusOutputPanel.createStyleContext();

        assertThat(styleContext.getStyle("foobar"), is(nullValue()));

        for (final StatusChangeEventType eventType : StatusChangeEventType.values()) {
            assertThat(styleContext.getStyle(eventType.name()), is(notNullValue()));
        }
    }
}
