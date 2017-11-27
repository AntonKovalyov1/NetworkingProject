package testsnetworkingproject;

import java.util.HashMap;

/**
 *
 * @author Anton
 */
public class MetadataTable {
    
    private final long sequenceNumber;
    private final HashMap<String, ReachableContent> data;
    
    public MetadataTable(final long sequenceNumber, 
                         final HashMap<String, ReachableContent> data) {
        this.sequenceNumber = sequenceNumber;
        this.data = data;
    }
    
    public MetadataTable() {
        this.sequenceNumber = 0;
        this.data = new HashMap<>();
    }

    /**
     * @return the sequenceNumber
     */
    public long getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @return the data
     */
    public HashMap<String, ReachableContent> getData() {
        return data;
    }
}
