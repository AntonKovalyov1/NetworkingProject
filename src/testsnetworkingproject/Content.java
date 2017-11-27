package testsnetworkingproject;

import java.io.File;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;


/**
 *
 * @author Anton
 */
public class Content {
    
    public final static String TEXT = "text/html";
    public final static String IMAGE = "image/png";
    
    private final File file;
    private final Metadata metadata;
    
    public Content(final File file) {
        this.file = file;
        metadata = computeMetadata(file);
    }

    private Metadata computeMetadata(File file) {
        String contentType = getContentType(file.getPath());
        long contentLength = file.length();
        String messageDigest;
        try {
            messageDigest = new DigestUtils("SHA1").digestAsHex(file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return new Metadata(messageDigest, contentType, contentLength);
    }
    
    private String getContentType(String filePath) {
        int i = filePath.lastIndexOf(".");
        String s = filePath.substring(i + 1).toLowerCase();
        if (s.equals("html"))
            return TEXT;
        return IMAGE;
    }    
    
    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @return the metadata
     */
    public Metadata getMetadata() {
        return metadata;
    }
}
