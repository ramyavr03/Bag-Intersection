package bagIntersection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Intersection {
	public static ArrayList<Integer[]> intersect(ArrayList<Integer[]> newS, ArrayList<Integer[]> newR) 
	{
	
	    HashMap<String, Integer> map = new HashMap<String, Integer>();
	    ArrayList<Integer[]> bufferOutput = new ArrayList<Integer[]>(5); //Output buffer of size 5
        ArrayList<Integer[]> diskOutput = new ArrayList<Integer[]>(25);  //Output disk of size 15
	      
	     //  Storing the tuples of Relation S into hashmap for finding the intersection 
	       for(Integer[] i: newS)
	       {    
	    	   String keyString = "";
	    	  for (Integer integer : i) 
	    		  keyString += integer.toString();
	      
	              if(map.containsKey(keyString)) // Check if the hashmap already contains the values of Relation S
	              {         
	                         map.put(keyString, map.get(keyString)+1);//Storing the values of Relation S in hashmap as a key and count as the value
	                        
	              }
	              else
	              {      
	                    map.put(keyString, 1);//If the values of Relation S is seen for first time then add it to hashmap
	                   
	              }
	       }
	   
	   //Comparing the tuples of R with S which is in hashmap
	    for(Integer[] i: newR)
	    { 
	    	String keyString = "";
	    	  for (Integer integer : i) 
	    		  keyString += integer.toString();  
	           if(map.containsKey(keyString)) // Checking  if the element of Relation R  is present in hashmap or not 
	           {
	               if(map.get(keyString)>1)   // if the element of relation R  is present ,then check the count  
	            {
	                 map.put(keyString, map.get(keyString)-1); //if count is greater than 1 decrement it
	            }
	            else
	            {
	                map.remove(keyString); 
	            }
	            // writing the common elements to buffer 
	            if(bufferOutput.size()<5)
	            {
	             bufferOutput.add(i);
	              System.out.println("Buffer contents after writing to Outputbuffer:");

	              for(Integer[]  k:bufferOutput)
	              {
	                  System.out.println(Arrays.toString(k));
	              }
	             }
	             else
	            {
	                System.out.println("Buffer is full writing the buffer contents to disk:");
	                diskOutput.addAll(bufferOutput);
	                for(Integer[] k:diskOutput)
	                {
	                	System.out.println(Arrays.toString(k));

	                }
	                bufferOutput = new ArrayList<Integer[]>(5); //clear the buffer after writing it to the disk by reintializing it
	                bufferOutput.add(i);//after clearing the full buffer we add the next common elements in queue to be written into the buffer
	            }
	        }

	       }
	       diskOutput.addAll(bufferOutput);//to include the last common element
	       return diskOutput;
	}
	public static void main(String[] args) 
	  {
		// Block values of Relation S in disk
	   Integer[][] S= {{1,1,1},{2,4,6},{2,4,6},{3,2,4},{3,3,3},{3,9,6},{2,5,9},{1,8,9},{1,4,6},{3,2,4}};
	// Block values of Relation R in disk     			
	   Integer[][] R={{1,1,1},{2,4,6},{2,4,6},{3,2,4},{21,26,17},{3,3,3},{7,8,6},{3,9,6},{2,5,9},{3,7,8},{1,4,6},{11,13,15},{25,30,35},{8,9,10},{2,9,5},{6,11,17},{1,4,4},{0,2,5},{3,5,7},{1,5,9}};          
	   ArrayList<Integer[]> newS= new ArrayList<Integer[]>(15);
	   ArrayList<Integer[]> newR= new ArrayList<Integer[]>(15);
	               
              //Loading block values of Relation R and S into Memory
	               for(int i=0;i<S.length;i++)
	               {
	                  newS.add(S[i]);
	               } 
	               for(int i=0;i<R.length;i++)
	               {
	                                  
	                  newR.add(R[i]);
	               }  
	               //Function call to find the bag intersection
	               ArrayList<Integer[]> result= intersect(newS, newR);
	    
	               System.out.println("The Bag Intersection of two Relations R and S");
	               for (Integer[] integers : result) {
					System.out.print(Arrays.toString(integers)+" ");
				}
	    
	    
	   }
}
