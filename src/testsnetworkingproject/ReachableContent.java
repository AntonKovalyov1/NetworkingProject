package testsnetworkingproject;

import java.util.ArrayList;

/**
 *
 * @author Anton
 */
public class ReachableContent {
    
    private final Metadata metadata;
    private final ArrayList<IPEndpoint> ipEndPoints;
    
    public ReachableContent(final Metadata metadata, 
                            final ArrayList<IPEndpoint> ipEndPoints) {
        this.metadata = metadata;
        this.ipEndPoints = ipEndPoints;
    }
    
    /**
     * @return the metadata
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * @return the ipEndPoints
     */
    public ArrayList<IPEndpoint> getIpEndPoints() {
        return ipEndPoints;
    }
    
    public void add(IPEndpoint newEndPoint) {
        ipEndPoints.add(newEndPoint);
    }
    
    public void remove(IPEndpoint oldEndPoint) {
        ipEndPoints.remove(oldEndPoint);
    }
}
