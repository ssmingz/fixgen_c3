class PlaceHold {
  private void processDoUpdate() {
    if (doUpdate && (!zipFile.exists())) {
      doUpdate = false;
      logOnFirstPass(
          ("ignoring update attribute as " + archiveType) + " doesn't exist.", MSG_DEBUG);
    }
  }
}
