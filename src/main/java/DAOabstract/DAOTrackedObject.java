package DAOabstract;

public interface DAOTrackedObject {

  public void getTrackedObjectById();

  public void removeTrackedObject(Long id);

  public void getTrackedObjectByNameAndValue();

}