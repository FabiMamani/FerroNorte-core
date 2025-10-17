package aplicacion;

import java.io.IOException;
import java.nio.file.*;

public class MonitoreadorDeArchivo {
    private final Path directory;
    private final String targetFile;
    private final Runnable onFileChanged;

    public MonitoreadorDeArchivo(Path directory, String targetFile, Runnable onFileChanged) {
        this.directory = directory;
        this.targetFile = targetFile;
        this.onFileChanged = onFileChanged;
    }

    public void iniciarMonitoreo() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            directory.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            Thread watchThread = new Thread(() -> {
                try {
                    while (true) {
                        WatchKey key = watchService.take();
                        for (WatchEvent<?> event : key.pollEvents()) {
                            if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                                Path changedFile = (Path) event.context();
                                if (changedFile.toString().equals(targetFile)) {
                                    onFileChanged.run();
                                }
                            }
                        }
                        key.reset();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            watchThread.setDaemon(true);
            watchThread.start();
        } catch (IOException e) {
            System.err.println("Error iniciando el monitor de archivo: " + e.getMessage());
        }
    }
}
