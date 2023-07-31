class PlaceHold {
  public int getNumberOfCoveredLines() {
    int number = 0;
    Iterator iter = this.children.values().iterator();
    while (iter.hasNext()) {
      CoverageData coverageContainer = ((CoverageData) (iter.next()));
      number += coverageContainer.getNumberOfCoveredLines();
    }
    return number;
  }
}
