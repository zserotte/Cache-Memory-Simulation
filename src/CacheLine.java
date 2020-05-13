/*
 * @author Zach Serotte
 */

public class CacheLine {

	int tag = 0;
	int LRU = 0;
	boolean valid = false;

	public CacheLine(int currentTag, int currentLRU, boolean currentState) {
		LRU = currentLRU;
		tag = currentTag;
		valid = currentState;
	}

	public int getTag() {
		return this.tag;
	}

	public void setTag(int newTag) {
		this.tag = newTag;
	}

	public int getLRU() {
		return this.LRU;
	}

	public void setLRU(int newLRU) {
		this.LRU = newLRU;
	}

	public boolean getValidState() {
		return this.valid;
	}

	public void setValidState(boolean newState) {
		this.valid = newState;
	}

}
