package interfaces;

import java.io.Serializable;

public interface VectorInterface extends Serializable {

	public void increaseVectorTimeStamp(int index);

	public int getUserId();

	public void setVectorTimeStamp(int index, int newValue);

	public int getVectorTimeStamp();

}
