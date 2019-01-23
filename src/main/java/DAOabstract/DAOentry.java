package DAOabstract;

import java.util.HashSet;
import java.util.List;

public interface DAOentry {

  public List getEntryByObjectId();

  public void getEntryByUserId();

  public HashSet getObjectIdByUserId();

  public void removeEntry(Long id);

  public Long addEntry(String objectName, int objectValue, String date, int quantity);

}