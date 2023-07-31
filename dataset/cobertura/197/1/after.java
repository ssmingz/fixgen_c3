class PlaceHold {
  public void addLineSwitch(int lineNumber, int switchNumber, int[] keys) {
    lock.lock();
    try {
      LineData lineData = getLineData(lineNumber);
      if (lineData != null) {
        lineData.addSwitch(switchNumber, keys);
        this.branches.put(Integer.valueOf(lineNumber), lineData);
      }
    } finally {
      lock.unlock();
    }
  }
}
