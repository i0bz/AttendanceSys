package utility;

import controllers.ControllerBootstrap;

public class PersistenceFlusher {
    private static int threads = 0;

    public static void startDaemon(ControllerBootstrap bootstrap) {
        if (threads > 0) return;

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(60000);
                    bootstrap.saveData();
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
