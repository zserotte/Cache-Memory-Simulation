import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cache {

	public static void main(String[] args) {
		// make sure we have command line arguments
		if (args.length < 4) {
			System.out.println("Needs command line arguments!");
			System.exit(0);
		}

		// Declare all needed variables for calculations
		int s = 0;
		int E = 0;
		int b = 0;
		int S = 0;
		int m = 32;
		int t = 0;

		// used for LRU tracking
		int counter = 0;

		// Declare counting variables
		int hits = 0;
		int miss = 0;
		int missEviction = 0;

		// display the command line arguments
		for (int i = 0; i < args.length; i++) {
			//	         System.out.printf("args[%d] = %s\n", i, args[i]);
			s = Integer.parseInt(args[0]);
			E = Integer.parseInt(args[1]);
			b = Integer.parseInt(args[2]);   
		}

		// Calculate value of S
		S = (int) Math.pow(2, s);

		// Calculate other values
		t = m - (s + b);

		// Initialize an array of S cacheSet objects
		CacheSet[] bigCache = new CacheSet[S];

		// Create an array of S cacheSets, each with E cacheLines
		for (int i = 0; i < bigCache.length; i++) {
			bigCache[i] = new CacheSet(E);
		}

		// input file is the first command line argument
		File file = new File(args[3]);

		try {
			// scanner for reading the file
			Scanner sc = new Scanner(file);

			// loop over the entire file
			while (sc.hasNextLine()) {

				// Way to keep track of LRU
				counter = counter + 1;

				// read a line of input
				String line = sc.nextLine();

				// Check to see if first character is an I
				char firstIndex = line.charAt(0);

				if (firstIndex == 'I') {		// First character is an I
					// do nothing
				}
				else {							// First character is not an I

					// Declare variables we need for conversions
					int lineLength = line.length();	            	
					String addressInHex = "";
					long binaryLong = 0;
					String binaryString = "";
					int digitsNeeded = 0;
					String addressTag = "";
					String setIndex = "";
					int setIndexDecimal = 0;
					int addressTagDecimal = 0;
					int validTags = 0;
					int LRUTracker = 0;
					char H = 'F';

					// Getting the address substring of the line. If the size value of the file line is 2 digits we must
					// return a different substring than if the size value of the file line is 1 digit
					if (line.charAt(lineLength - 3) == ',') {
						addressInHex = line.substring(3, lineLength - 3);
					}
					else {
						addressInHex = line.substring(3, lineLength - 2);
					}

					binaryLong = Long.parseLong(addressInHex, 16);	// Convert from Hex to binary as a long
					binaryString = Long.toBinaryString(binaryLong);	// Convert from long to string

					// Add leading 0's to our binary address if it is not 32 bits long
					if (binaryString.length() != 32) {
						digitsNeeded = 32 - binaryString.length();
						for (int i = 0; i < digitsNeeded; i++) {
							binaryString = "0" + binaryString;
						}
					}

					// Get the tag and index of address
					addressTag = binaryString.substring(0, t);
					setIndex = binaryString.substring(t, t + s);

					// Convert the tag and index of the address into a decimal
					setIndexDecimal = Integer.parseInt(setIndex, 2);
					addressTagDecimal = Integer.parseInt(addressTag, 2);

					// Loop through each cacheLine and check if tags are equal
					for (int i = 0; i < E; i++) {
						// Handles in case of hit
						if (bigCache[setIndexDecimal].cacheSetObject[i].tag == addressTagDecimal && bigCache[setIndexDecimal].cacheSetObject[i].valid == true) {
							System.out.println(line + " hit");
							bigCache[setIndexDecimal].cacheSetObject[i].setLRU(counter);
							hits = hits + 1;
							H = 'T';	// Way of avoiding entering the miss and miss eviction statements below
						}
					}

					// Checks to see if setIndex is full
					for (int i = 0; i < E; i++) {
						if(bigCache[setIndexDecimal].cacheSetObject[i].valid == true) {
							validTags = validTags + 1;
						}
					}

					if (H == 'T') {
						// do nothing
					}
					else {
						// Handles miss evictions
						if (validTags == E) { // Cache is full and we must evict
							for (int i = 1; i < E; i++) {
								// Figuring out which object to evict
								if (bigCache[setIndexDecimal].cacheSetObject[LRUTracker].LRU > bigCache[setIndexDecimal].cacheSetObject[i].LRU) {
									LRUTracker = i;
								}
							}
							bigCache[setIndexDecimal].cacheSetObject[LRUTracker].setLRU(counter);
							bigCache[setIndexDecimal].cacheSetObject[LRUTracker].setValidState(true);
							bigCache[setIndexDecimal].cacheSetObject[LRUTracker].setTag(addressTagDecimal);
							missEviction = missEviction + 1;
							miss = miss + 1;
							System.out.println(line + " miss eviction");
						}

						// Handles misses
						else { // Cache is not full and we can simply insert the cacheLine object into the first empty index
							bigCache[setIndexDecimal].cacheSetObject[validTags].setLRU(counter);			// At this point it is editing 
							bigCache[setIndexDecimal].cacheSetObject[validTags].setTag(addressTagDecimal);	// Both bigCache[0] and bigCache[1]
							bigCache[setIndexDecimal].cacheSetObject[validTags].setValidState(true);		// Even tho the parameter of bigCache is 0
							miss = miss + 1;
							System.out.println(line + " miss");
						}
					}				// End else statement signaling the line was not a hit 

				}				// End else statement for first character was not an 'I'

			}					// End while statement signaling end of file
		}						// End try statement

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("hits:" + hits + " misses:" + miss + " evictions:" + missEviction);
	}

}
