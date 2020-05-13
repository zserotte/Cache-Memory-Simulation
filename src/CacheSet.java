/*
 * @author Zach Serotte
 */

public class CacheSet {

	public CacheLine[] cacheSetObject;

	public CacheSet(int cacheLineObjects) {
		cacheSetObject = new CacheLine[cacheLineObjects];
		for (int i = 0; i < cacheSetObject.length; i++) {
			cacheSetObject[i] = new CacheLine(0, 0, false);
		}
	}
}
