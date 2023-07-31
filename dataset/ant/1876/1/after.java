class PlaceHold {
  private void processDoUpdate() {
    if (doUpdate && (!zipFile.exists())) {
      doUpdate = false;
      logWhenWriting(
          ("ignoring update attribute as " + archiveType) + " doesn't exist.", MSG_DEBUG);
    }
  }
}
