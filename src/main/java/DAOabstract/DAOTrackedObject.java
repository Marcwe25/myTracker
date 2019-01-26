package DAOabstract;

import beans.TrackedObject;

public interface DAOTrackedObject {

  public TrackedObject getrackedObjectById(Long id);

  public void removeObject(Long id);

  public TrackedObject getrackedObjectByNameAndValue(String name, int value);

}