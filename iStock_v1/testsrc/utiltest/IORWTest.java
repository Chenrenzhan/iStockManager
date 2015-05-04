/**
 * 
 */
package utiltest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import controller.IORW;

/**
 * @author jasoncar
 *
 */

public class IORWTest {

	final private String emptyfile="testsrc/data/testempty.json";
	final private String normalfile="testsrc/data/testnormal.json";
	final private String writefile="testsrc/data/testwrite.json";
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link controller.IORW#read(java.lang.String)}.
	 */
	@Test
	public void testReadEmpty() {
		String empty=IORW.read(emptyfile);
	assertEquals("", empty);	
		
	}
	@Test
	public void testReadNormal(){
		String context=IORW.read(normalfile);
		assertEquals("assets:10805.499999999985enter", context);
	}
	
	/**
	 * Test method for {@link controller.IORW#write(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testWriteEmpty() {
		String context="";
		try {
			IORW.write(writefile, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(context, IORW.read(writefile));
		
	}
	@Test
	public void testWriteNormal() {
		String context="\"Write for test\ntest enter";
		try {
			IORW.write(writefile, context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("\"Write for testtest enter", IORW.read(writefile));
		
	}

}
