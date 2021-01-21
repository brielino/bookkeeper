package org.apache.bookkeeper.client;

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

import junit.framework.Assert;

public class TestArrayGroupSort {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	long[] parameter = new long[] {2,7,3,1,
									5,7,1,1,
									1,1,2,1,
									1,2,1,2,
									5,7,1,1,
									2,6,1,2};
	long[] parameter_null = new long[] {};
	
	long[] no_valid_array = new long[] {1,2}; //non valido perchè troppo piccolo rispetto ad ArraySortGroup(3,4) 
	
	long[] result = new long[] {1,1,2,1,
			 					 1,2,1,2,
			 					 2,6,1,2,
								 2,7,3,1,
								 5,7,1,1,
								 5,7,1,1};
	//Coverage
	@Test(expected = IllegalArgumentException.class)
	public void createTest1() {
		ArrayGroupSort arrayG = new ArrayGroupSort(0,1);
	}
	//Coverage
	@Test(expected = IllegalArgumentException.class)
	public void createTest2() {
		ArrayGroupSort arrayG = new ArrayGroupSort(1,0);
	}
	
    @Test
    public void testSort() {
    	ArrayGroupSort array = new ArrayGroupSort(3,4);
    	array.sort(parameter);
    	assertArrayEquals(result,parameter);
    }
    	
    @Test
    public void testSort2() {
    	//test per un array vuoto
    	ArrayGroupSort array = new ArrayGroupSort(1,2);
    	array.sort(parameter_null);
    	assertArrayEquals(new long[] {},parameter_null);
    }
    
    public void testArrayNotValid() {
    	ArrayGroupSort array = new ArrayGroupSort(2,3);
    	exception.expect(IllegalArgumentException.class);
    	array.sort(no_valid_array);	
    }
    
  
    //Coverage
    public void testWrongArgument() {
    	exception.expect(IllegalArgumentException.class);
    	ArrayGroupSort array = new ArrayGroupSort(5,4);
    }
    
    //Coverage
    public void testNoMultipleArray() {
    	ArrayGroupSort array = new ArrayGroupSort(2,3);
    	exception.expect(IllegalArgumentException.class);
    	array.sort(new long[] {1,5,9,7});	
    }
    
    
}