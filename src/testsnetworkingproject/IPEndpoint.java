package testsnetworkingproject;

import java.util.Objects;

/**
 *
 * @author Anton
 */
public class IPEndpoint {
    
    private final String ip;
    private final int port;
    
    public IPEndpoint(final String ip, final int port) {
        this.ip = ip;
        this.port = port;
    }
    
    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof IPEndpoint))
            return false;
        IPEndpoint other = (IPEndpoint)o;
        return other.getIp().equals(this.getIp()) && 
               other.getPort() == this.getPort();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.ip);
        hash = 79 * hash + this.port;
        return hash;
    }
}
