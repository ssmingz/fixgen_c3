class PlaceHold {
  public int read() throws IOException {
    int ch = -1;
    if ((queuedData != null) && (queuedData.length() == 0)) {
      queuedData = null;
    }
    if (queuedData != null) {
      ch = queuedData.charAt(0);
      queuedData = queuedData.substring(1);
      if (queuedData.length() == 0) {
        queuedData = null;
      }
    } else {
      queuedData = readFully();
      if (queuedData.length() == 0) {
        ch = -1;
      } else {
        Project project = getProject();
        queuedData = project.replaceProperties(queuedData);
        return read();
      }
    }
    return ch;
  }
}
