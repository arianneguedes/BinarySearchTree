import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author mmurshed
 *
 */
class UnitTestSubBinarySearchTreeTest {
	
	static ArrayList<String>nameList= new ArrayList<>(List.of(
			"Liam", "Noah", "Oliver", "William","Elijah","James","Benjamin","Lucas","Mason","Ethan","Alexander","Henry","Jacob","Michael", "Daniel",
			"Logan","Jackson","Sebastian","Jack","Aiden","Owen","Samuel","Matthew","Joseph","Levi","Mateo","David","John","Wyatt", "Mahbub", "Murshed"));
	
	static BinarySearchTree<String> myBStreeStr;
	static BinarySearchTree<Integer> myBStreeInt;
		
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		myBStreeStr=new BinarySearchTree<String>(nameList);
		myBStreeInt=new BinarySearchTree<Integer>();
		Random r=new Random(7);
		for(int i=0; i<100; i++)
		{
			myBStreeInt.add(r.nextInt(100));
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void test1(){
		assertEquals(myBStreeStr.toString(), "Aiden Alexander Benjamin Daniel David Elijah Ethan Henry Jack Jackson Jacob James John Joseph Levi Liam Logan Lucas Mahbub Mason Mateo Matthew Michael Murshed Noah Oliver Owen Samuel Sebastian William Wyatt");
	}
	
	@Test
	void test2()
	{
		assertEquals(myBStreeInt.toString(), "0 1 4 5 6 7 8 11 12 14 18 20 21 23 24 25 26 29 31 33 34 35 36 37 38 39 40 42 44 45 46 47 49 50 51 52 54 55 56 57 59 61 62 64 65 68 70 72 73 74 76 79 80 81 83 85 86 87 89 91 92 94 95 96 97 98");
	}

	@Test
	void test3() {
		BinarySearchTree<String> subTreeByLength = myBStreeStr.getSubBST(4);
		assertEquals(subTreeByLength.toString(), "Jack John Levi Liam Noah Owen");
		}
	@Test
	void test4() {
		BinarySearchTree<String> subTreeByLength = myBStreeStr.getSubBST(5);
		assertEquals(subTreeByLength.toString(), "Aiden David Ethan Henry Jacob James Logan Lucas Mason Mateo Wyatt");
		}
	@Test
	void test5() {
		BinarySearchTree<String> subTreeByLength = myBStreeStr.getSubBST(8);
		assertEquals(subTreeByLength.toString(), "Benjamin");
		}
		
	@Test
	void test6() {
		assertEquals(myBStreeStr.showValuesInRange("Samuel", "John").toString(), "John Joseph Levi Liam Logan Lucas Mahbub Mason Mateo Matthew Michael Murshed Noah Oliver Owen Samuel");
		}
	@Test
	void test7() {
		assertEquals(myBStreeStr.showValuesInRange("John", "Samuel").toString(), "John Joseph Levi Liam Logan Lucas Mahbub Mason Mateo Matthew Michael Murshed Noah Oliver Owen Samuel");
		}
	@Test
	void test8()
	{
		ArrayList<Integer>intList= new ArrayList<>(List.of(5,2,10,1,5,6,7,8,9, 20, 30, 40));
		BinarySearchTree<Integer> myBStreeIntList=new BinarySearchTree<Integer>(intList);
		assertEquals(myBStreeIntList.showValuesInRange(30, 5),"5 6 7 8 9 10 20 30");
	}
	@Test
	void test9()
	{
		ArrayList<Integer>intList= new ArrayList<>(List.of(5,2,10,1,5,6,7, 1010, 8,9, 20, 30, 40));
		BinarySearchTree<Integer> myBStreeIntList=new BinarySearchTree<Integer>(intList);
		assertEquals(myBStreeIntList.findLargestValue(),"1010");
	}
	
	@Test
	void test10()
	{
		ArrayList<String>nameList= new ArrayList<>(List.of(
				"Liam", "Noah", "Oliver", "William","Elijah","James","Benjamin","Lucas","Mason","Ethan","Alexander","Henry","Jacob","Michael", "Daniel","Owen","Samuel","Matthew","Joseph","Levi","Mateo","David","John","Wyatt", "Mahbub", "Murshed"));
		BinarySearchTree<String> myBStreeStr=new BinarySearchTree<String>(nameList);
		assertEquals(myBStreeStr.findLowestValue(),"Alexander");	
	}
	
}