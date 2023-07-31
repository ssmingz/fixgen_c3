class PlaceHold {
  private void closeCurrent() {
    if (currentStream != null) {
      try {
        currentStream.close();
      } catch (IOException eyeOhEx) {
      }
      currentStream = null;
    }
  }
}
