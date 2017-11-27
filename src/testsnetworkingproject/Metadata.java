package testsnetworkingproject;

/**
 *
 * @author Anton
 */
public class Metadata {
    
    private final String messageDigest;
    private final String contentType;
    private final long contentLength;
    
    public Metadata(final String messageDigest, 
                    final String contentType, 
                    final long contentLength) {
        this.messageDigest = messageDigest;
        this.contentType = contentType;
        this.contentLength = contentLength;
    }

    /**
     * @return the messageDigest
     */
    public String getMessageDigest() {
        return messageDigest;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @return the contentLength
     */
    public long getContentLength() {
        return contentLength;
    }
}
