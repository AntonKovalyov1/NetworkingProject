package testsnetworkingproject;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Anton
 */
public class Providers {
    
    private final HashMap<IPEndpoint, Message>  map;
    
    public Providers() {
        map = new HashMap<>();
    }
    
    public boolean update(final Message message) {
        Message old = map.get(message.getSource());
        if (old == null) {
            map.put(message.getSource(), message);
            return true;
        }
        long oldSequenceNumber = old.getSequenceNumber();
        if (oldSequenceNumber > message.getSequenceNumber())
            return false;
        map.replace(old.getSource(), message);
        return true;
    }
    
    public ReachInfo getReachInfo(IPEndpoint preferred) {
        if (map.isEmpty())
            return null;
        Message preferredMessage = map.get(preferred);
        if (preferredMessage != null && 
            preferredMessage.getState().isPublished()) {
            return new ReachInfo(preferredMessage.getMetadata(), 
                                 preferredMessage.getSource());
        }
        for (Map.Entry<IPEndpoint, Message> current : map.entrySet()) {
            Message message = current.getValue();
            if (message.getState().isPublished())
                return new ReachInfo(message.getMetadata(), 
                                     message.getSource());
        }
        return null;
    }
}
