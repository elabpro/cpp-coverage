/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.elab.cppcoverage;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eugine
 */
public class coverageTest {

    public coverageTest() {
    }

    @Test
    public void testfindCoverage() {
        coverage c = new coverage();
        String result = c.findCoverageReportFile("test");
        assertTrue(result.equals(""));
        result = c.findCoverageReportFile("./build/nolicense.txt");
        assertTrue(result.equals("./report.info"));
    }

    @Test
    public void testparseCov() {
        coverage c = new coverage();
        int[] result = c.parseCov("test.gcno");
        assertTrue(result.length > 0);
    }

}
