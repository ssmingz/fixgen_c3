class PlaceHold {
  private synchronized boolean isExists(boolean closeConnection) {
    if (getURL() == null) {
      return false;
    }
    try {
      connect(MSG_VERBOSE);
      return true;
    } catch (IOException e) {
      return false;
    } finally {
      if (closeConnection) {
        close();
      }
    }
  }
}
