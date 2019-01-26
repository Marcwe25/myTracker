package DAOabstract;

import java.util.HashSet;
import java.util.List;

import beans.TrackedObject;

public interface DAOentry {

  public List<TrackedObject> getEntryByTrackedObjectId();

  public void getEntryByUserId();

  public HashSet getObjectIdByUserId();

  public void removeEntry(Long id);

  public Long addEntry(String objectName, int objectValue, String date, int quantity);

}