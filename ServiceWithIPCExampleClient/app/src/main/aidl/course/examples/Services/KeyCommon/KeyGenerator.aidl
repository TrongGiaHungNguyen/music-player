package course.examples.Services.KeyCommon;

interface KeyGenerator {
    String[] listClips();
    void playClip(int index);
    void stopClip();
    void pauseClip();
    void resumeClip();
}