package testsnetworkingproject;

/**
 *
 * @author Anton
 */
public class ReachInfo {
    
    private final Metadata metadata;
    private final IPEndpoint ipEndPoint;
    
    public ReachInfo(final Metadata metadata, 
                     final IPEndpoint ipEndPoint) {
        this.metadata = metadata;
        this.ipEndPoint = ipEndPoint;
    }
    
    /**
     * @return the metadata
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * @return the ipEndPoint
     */
    public IPEndpoint getIpEndPoint() {
        return ipEndPoint;
    }
}
