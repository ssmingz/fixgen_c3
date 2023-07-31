class PlaceHold {
  public int getNumberOfValidLines() {
    int number = 0;
    Iterator iter = this.children.values().iterator();
    while (iter.hasNext()) {
      CoverageData coverageContainer = ((CoverageData) (iter.next()));
      number += coverageContainer.getNumberOfValidLines();
    }
    return number;
  }
}
