package testsnetworkingproject;

/**
 *
 * @author Anton
 */
public class Message {
    
    private final IPEndpoint source;
    private long sequenceNumber;
    private State state;
    private final Metadata metadata;
    
    public Message(final IPEndpoint source, 
                   final long sequenceNumber, 
                   final State state, 
                   final Metadata metadata) {
        this.source = source;
        this.sequenceNumber = sequenceNumber;
        this.state = state;
        this.metadata = metadata;
    }
    
    public Message(final IPEndpoint source, final Metadata metadata) {
        this(source, 0, State.PUBLISHED, metadata);
    }
    
    /**
     * @return the source
     */
    public IPEndpoint getSource() {
        return source;
    }

    /**
     * @return the sequenceNumber
     */
    public long getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @param sequenceNumber the sequenceNumber to set
     */
    public void setSequenceNumber(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return the metadata
     */
    public Metadata getMetadata() {
        return metadata;
    }
}
