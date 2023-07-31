class PlaceHold {
  public int getPositionBeforePrompt() {
    acquireReadLock();
    int len = _document.getLength();
    try {
      if (_document.hasPrompt()) {
        int promptStart = _promptPos - _prompt.length();
        return (promptStart < len) && (promptStart >= 0) ? promptStart : len;
      }
      return len;
    } finally {
      releaseReadLock();
    }
  }
}
