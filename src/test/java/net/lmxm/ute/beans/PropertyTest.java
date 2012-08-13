package net.lmxm.ute.beans;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PropertyTest {
    @Test
    public void testEqualsObject() {
        EqualsVerifier.forClass(MavenArtifact.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void testIsEmptyNewInstance() {
        final MavenArtifact mavenArtifact = new MavenArtifact();
        assertTrue(mavenArtifact.isEmpty());
    }

    @Test
    public void testIsEmptyCoordinatesSet() {
        final MavenArtifact mavenArtifact = new MavenArtifact();
        mavenArtifact.setCoordinates("junit:junit:4.10");
        assertFalse(mavenArtifact.isEmpty());
    }

    @Test
    public void testIsEmptyTargetNameSet() {
        final MavenArtifact mavenArtifact = new MavenArtifact();
        mavenArtifact.setTargetName("junit.jar");
        assertFalse(mavenArtifact.isEmpty());
    }

    @Test
    public void testToString() {
        final MavenArtifact mavenArtifact = new MavenArtifact();
        mavenArtifact.setCoordinates("junit:junit:4.10");
        mavenArtifact.setTargetName("junit.jar");

        assertTrue(mavenArtifact.toString().contains("junit:junit:4.10"));
        assertTrue(mavenArtifact.toString().contains("junit.jar"));
    }

    @Test
    public void testRemoveEmptyObjects() {
        final MavenArtifact mavenArtifact1 = new MavenArtifact();
        mavenArtifact1.setCoordinates("junit:junit:4.10");
        mavenArtifact1.setTargetName("junit.jar");

        final MavenArtifact mavenArtifact2 = new MavenArtifact();
        mavenArtifact2.setCoordinates("junit:junit:4.10");
        mavenArtifact2.setTargetName("junit.jar");

        assertEquals(mavenArtifact1, mavenArtifact2);
        mavenArtifact1.removeEmptyObjects();
        assertEquals(mavenArtifact1, mavenArtifact2);
    }
}