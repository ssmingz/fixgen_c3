class PlaceHold {
  private void moveFile(File fromFile, File toFile, boolean filtering, boolean overwrite) {
    boolean moved = false;
    try {
      log((("Attempting to rename: " + fromFile) + " to ") + toFile, verbosity);
      moved = renameFile(fromFile, toFile, filtering, forceOverwrite);
    } catch (IOException ioe) {
      String msg =
          (((("Failed to rename " + fromFile) + " to ") + toFile) + " due to ") + ioe.getMessage();
      throw new BuildException(msg, ioe, getLocation());
    }
    if (!moved) {
      copyFile(fromFile, toFile, filtering, overwrite);
      if (!getFileUtils().tryHardToDelete(fromFile, performGc)) {
        throw new BuildException(("Unable to delete " + "file ") + fromFile.getAbsolutePath());
      }
    }
  }
}
