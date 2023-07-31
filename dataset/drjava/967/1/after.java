class PlaceHold {
  public void javadocAll(DirectorySelector select, final FileSaveSelector saver)
      throws IOException {
    if (_model.hasModifiedDocuments() || _model.hasUntitledDocuments()) {
      return;
    }
    Configuration config = DrJava.getConfig();
    File destDir = config.getSetting(JAVADOC_DESTINATION);
    try {
      if (destDir.equals(NULL_FILE)) {
        destDir = select.getDirectory(null);
      } else {
        destDir = select.getDirectory(destDir);
      }
      while (((!destDir.exists()) || (!destDir.isDirectory())) || (!destDir.canWrite())) {
        if ((!destDir.getPath().equals("")) && (!destDir.exists())) {
          boolean create =
              select.askUser(
                  ("The directory you chose does not exist:\n\'" + destDir)
                      + "\'\nWould you like to create it?",
                  "Create Directory?");
          if (create) {
            boolean dirMade = destDir.mkdirs();
            if (!dirMade) {
              throw new IOException("Could not create directory: " + destDir);
            }
          } else {
            return;
          }
        } else if ((!destDir.isDirectory()) || destDir.getPath().equals("")) {
          select.warnUser(
              ((("The file you chose is not a directory:\n" + "'") + destDir) + "\'\n")
                  + "Please choose another.",
              "Not a Directory!");
          destDir = select.getDirectory(null);
        } else {
          select.warnUser(
              ((("The directory you chose is not writable:\n" + "'") + destDir) + "\'\n")
                  + "Please choose another directory.",
              "Cannot Write to Destination!");
          destDir = select.getDirectory(null);
        }
      }
    } catch (OperationCanceledException oce) {
      return;
    }
    _notifier.javadocStarted();
    final File destDirF = destDir;
    new Thread("DrJava Javadoc Thread") {
      public void run() {
        _javadocAllWorker(destDirF, saver);
      }
    }.start();
  }
}
