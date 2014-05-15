package interfaces;

import java.io.Serializable;

public interface NetworkHandler {
	public void register(NetworkGUI n);
	public void send(Serializable s);
	public void networkReceive(Serializable s, int n);
}
