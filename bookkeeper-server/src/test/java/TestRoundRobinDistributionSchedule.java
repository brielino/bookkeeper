import static org.junit.Assert.*;

import java.util.BitSet;

import org.apache.bookkeeper.client.RoundRobinDistributionSchedule;
import org.junit.Before;
import org.junit.Test;

public class TestRoundRobinDistributionSchedule {

	RoundRobinDistributionSchedule rb;
	
	@Before
	public void setup() {
		rb = new RoundRobinDistributionSchedule(4,2,5);
	}
	@Test
	public void test(){
		BitSet b = rb.getEntriesStripedToTheBookie( 4, 3, 7);
		BitSet b1 = new BitSet();
		b1.set(0);
		b1.set(1);
		b1.set(3);
		b1.set(4);
		assertEquals(b1.length(),b.length());
		for(int i = 0; i < b1.length(); i++) {
			assertEquals(b1.get(i),b.get(i));
		}
		assertTrue(rb.hasEntry(0, 3));
	}
	@Test(expected = IllegalArgumentException.class)
	public void test1() {
		/* passo dei parametri errati */
		BitSet b = rb.getEntriesStripedToTheBookie( 6, 1, 4);	
	}

}

