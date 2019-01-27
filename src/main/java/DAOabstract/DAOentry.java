package DAOabstract;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import beans.TrackedObject;
import beans.entry;

public interface DAOentry {

  public List<entry> getEntryByTrackedObjectId(Long id);

  public List<entry> getEntryByUserId(Long id);

  public void removeEntry(Long id);

  public Long addEntry(Long object_id, Long user_id, Date date, int quantity);

}