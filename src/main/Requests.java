package main;
/**
 * Created by carlos, alberto, pablo y ruben on 23/02/17.
 */
public class Requests {
	
	private Video video;
	private Endpoint endpoint;
	private int numberR;

	public Requests (Video video, Endpoint endpoint, int numberR) {
		this.video = video;
		this.endpoint = endpoint;
		this.numberR = numberR;
	}

	public Video getVideo() {
		return video;
	}

	public Endpoint getEndpoint() {
		return endpoint;
	}

	public int getNumberR() {
		return numberR;
	}

}