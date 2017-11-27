package testsnetworkingproject;

/**
 *
 * @author Anton
 */
public enum State {
    
    PUBLISHED {
        @Override
        public boolean isPublished() {
            return true;
        }

        @Override
        public boolean isUnpublished() {
            return false;
        }
    },
    UNPUBLISHED {
        @Override
        public boolean isPublished() {
            return false;
        }

        @Override
        public boolean isUnpublished() {
            return true;
        }
    };
    
    public abstract boolean isPublished();
    public abstract boolean isUnpublished();
}
