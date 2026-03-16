package utility;

import controllers.ControllerBootstrapSingleton;

public class PersistenceFlusher {
    private static int threads = 0;

    public static void startDaemon() {
        if (threads > 0) return;

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(60000);
                    ControllerBootstrapSingleton.getInstance().saveData();
                } catch (InterruptedException e) {
                    System.out.println("Flush Interrupted");
                }
            }
        });
        t.setDaemon(true);
        t.start();

        threads++;
    }
}
