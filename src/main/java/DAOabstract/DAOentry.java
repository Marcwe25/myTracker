package DAOabstract;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import beans.TrackedObject;
import beans.Entry;

public interface DAOentry {

  public List<Entry> getEntryByTrackedObjectId(Long id);

  public List<Entry> getEntryByUserId(Long id);

  public List<Entry> getEntryByUserIdAfter(Long id, Date date);

  public void removeEntry(Long id);

  public void removeEntry(Date date, long user_id);

  public Long addEntry(Entry entry);

}