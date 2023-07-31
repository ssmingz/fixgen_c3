class PlaceHold {
  private void closeCurrent() {
    FileUtils.close(currentStream);
    currentStream = null;
  }
}
