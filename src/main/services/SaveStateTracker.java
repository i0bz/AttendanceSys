package services;

//For future can expand this to have seperate tracking for both repos
public class SaveStateTracker {
    private boolean flushed = true;

    public synchronized void markDirty() {
        flushed = false;
    }
    public synchronized void unMarkDirty() {
        flushed = true;
    }
    public synchronized boolean isDirty() {
        return !flushed;
    }
}
