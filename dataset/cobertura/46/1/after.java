class PlaceHold {
  public void addLineSwitch(int lineNumber, int switchNumber, int min, int max) {
    lock.lock();
    try {
      LineData lineData = getLineData(lineNumber);
      if (lineData != null) {
        lineData.addSwitch(switchNumber, min, max);
        this.branches.put(Integer.valueOf(lineNumber), lineData);
      }
    } finally {
      lock.unlock();
    }
  }
}
