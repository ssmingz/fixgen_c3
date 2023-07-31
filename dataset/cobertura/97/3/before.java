class PlaceHold {
  public int getNumberOfCoveredBranches() {
    int number = 0;
    lock.lock();
    try {
      Iterator iter = this.children.values().iterator();
      while (iter.hasNext()) {
        CoverageData coverageContainer = ((CoverageData) (iter.next()));
        number += coverageContainer.getNumberOfCoveredBranches();
      }
    } finally {
      lock.unlock();
    }
    return number;
  }
}
