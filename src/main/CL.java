package main;
/**
 * Created by carlos, alberto, pablo y ruben on 23/02/17.
 */
public class CL {
	private Cache cache;
	private int lat;

	
	public CL (Cache cache, int lat) {
		this.cache = cache;
		this.lat = lat;
	}

	public Cache getCache() {
		return cache;
	}


	public int getLat() {
		return lat;
	}

}
