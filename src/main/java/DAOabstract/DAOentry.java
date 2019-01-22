package DAOabstract;

import beans.TrackEntry;

public interface DAOentry {

	abstract TrackEntry addEntry(TrackEntry trackEntry);
	
	abstract TrackEntry removeTrackEntry(Long id);
	
	abstract TrackEntry getEntry(Long id);

}
