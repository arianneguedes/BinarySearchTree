import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinarySearchTreeTest {
	
	public static void main(String[] args) {
			
		ArrayList<String>nameList= new ArrayList<>(List.of(
				"Liam", "Noah", "Oliver", "William","Elijah","James","Benjamin","Lucas","Mason","Ethan","Alexander","Henry","Jacob","Michael", "Daniel",
				"Logan","Jackson","Sebastian","Jack","Aiden","Owen","Samuel","Matthew","Joseph","Levi","Mateo","David","John","Wyatt", "Mahbub", "Murshed"));
		
		BinarySearchTree<String> myBStreeStr=new BinarySearchTree<String>(nameList);
		
//		myBStreeStr.add("Adding");
//		myBStreeStr.add("One");
//		myBStreeStr.add("item");
//		myBStreeStr.add("individually");
		
		System.out.println(myBStreeStr);
		System.out.println(myBStreeStr.findLargestValue());
		System.out.println(myBStreeStr.findLowestValue());
		
		
		ArrayList<Integer>intList= new ArrayList<>(List.of(5,2,10,1,5,6,7,8,9, 20, 30, 40, 11));
		BinarySearchTree<Integer> myBStreeIntList=new BinarySearchTree<Integer>(intList);
		System.out.println(myBStreeIntList);
		System.out.println(myBStreeIntList.findLargestValue());
		System.out.println(myBStreeIntList.findLowestValue());
		
	
		BinarySearchTree<Integer> myBStreeInt=new BinarySearchTree<Integer>();
		Random r=new Random(7);
		for(int i=0; i<100; i++) {
			myBStreeInt.add(r.nextInt(100));
		}
		System.out.println(myBStreeInt);
		
		int minLength=nameList.get(0).length();
		int maxLength=nameList.get(0).length();
		for(var name: nameList) {
			if(name.length()<minLength)
				minLength=name.length();
			if(name.length()>maxLength)
				maxLength=name.length();
		}
		
		
		BinarySearchTree<String> subTreeByLength;
		for(int i=minLength; i<=maxLength; i++) {
			System.out.print("Names with length ( "+i+" ) sorted in ascending order: ");
			subTreeByLength=myBStreeStr.getSubBST(i);
			System.out.println(subTreeByLength);
			System.out.println(subTreeByLength.displayTree(1));
		}
		
		System.out.println(myBStreeStr);
		System.out.println(myBStreeStr.showValuesInRange("Samuel", "John"));
		System.out.println(myBStreeStr.showValuesInRange("John", "Samuel"));
		
		System.out.println(myBStreeIntList);
		System.out.println(myBStreeIntList.showValuesInRange(30, 5));
		System.out.println(myBStreeIntList.showValuesInRange(5, 30));
	}
}