package DAOabstract;

import beans.TrackedObject;

public interface DAOTrackedObject {

  public TrackedObject getrackedObjectById(Long id);

  public TrackedObject getrackedObjectByNameAndValue(String name, int value);

  public void removeTrackedObject(Long id);

  public Long createTrackedObject(String name, int value, String category);
  
  public void updateTrackedObject(Long id, String name, int value, String category);

}