package lab5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import balloon4.Balloon;

public class BalloonTests {

    // margin of error for floating-point comparisons
    private static final double EPSILON = 10e-07;

    @Test
    public void testInitial() {
        Balloon b = new Balloon(5);
        assertEquals(false, b.isPopped());
        assertEquals(0.0, b.getRadius(), EPSILON);
    }

    @Test
    public void testRadius() {
        Balloon b = new Balloon(5);
        assertEquals(0.0, b.getRadius(), EPSILON);
        b.blow(5);
        assertEquals(5.0, b.getRadius(), EPSILON);
        b.deflate();
        assertEquals(0.0, b.getRadius(), EPSILON);
        b.pop();
        assertEquals(0.0, b.getRadius(), EPSILON);
    }

    @Test
    public void testDeflate() {
        Balloon b = new Balloon(5);
        assertEquals(0.0, b.getRadius(), EPSILON);
        b.blow(5);
        assertEquals(5.0, b.getRadius(), EPSILON);
        b.deflate();
        assertEquals(0.0, b.getRadius(), EPSILON);
        b.deflate();
        assertEquals(0.0, b.getRadius(), EPSILON);
        assertEquals(false, b.isPopped());
        b.blow(10);
        assertEquals(0.0, b.getRadius(), EPSILON);

    }

    @Test
    public void testPop() {
        Balloon b = new Balloon(5);
        assertEquals(false, b.isPopped());
        b.blow(10);
        assertEquals(true, b.isPopped());
        b.pop();
        assertEquals(true, b.isPopped());
        b.blow(10);
        assertEquals(true, b.isPopped());
        b.blow(5);
        assertEquals(0.0, b.getRadius(), EPSILON);
        b.pop();
        assertEquals(true, b.isPopped());
    }

    @Test
    public void testBlow() {
        Balloon b = new Balloon(5);
        assertEquals(false, b.isPopped());
        b.blow(2);
        assertEquals(false, b.isPopped());
        b.blow(3);
        assertEquals(false, b.isPopped());
        b.blow(1);
        assertEquals(true, b.isPopped());
    }
}
