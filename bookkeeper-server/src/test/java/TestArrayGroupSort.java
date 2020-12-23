import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import org.apache.bookkeeper.bookie.storage.ldb.ArrayGroupSort;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;

public class TestArrayGroupSort {

	ArrayGroupSort arrayG;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	public TestName testName = new TestName();

    @Before
    public void setUp() {
    	arrayG = new ArrayGroupSort(3,4);
    	System.out.println("eseguito before");
    }
    @Test
    public void testsortNegative() {
    	long[] arraylong = new long[8];
    	for(int i = 0; i < arraylong.length; i++) {
    		arraylong[i] = -4+i;
    	}
    	arrayG.sort(arraylong);
    	for(int i = 0; i < arraylong.length; i++) {
    		System.out.println(arraylong[i]);
    	}
    	
    }
    
	@Test
	public void build() {
		// verifcare la corretta creazione di arrayGroupSort
		ArrayGroupSort tryarray;
		exception.expect(IllegalArgumentException.class);
		tryarray = new ArrayGroupSort(6,5);
		exception.expect(IllegalArgumentException.class);
		tryarray = new ArrayGroupSort(-1,5);	
	}
	
	

}