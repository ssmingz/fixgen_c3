class PlaceHold {
  public int getNumberOfValidBranches() {
    int number = 0;
    Iterator iter = this.children.values().iterator();
    while (iter.hasNext()) {
      CoverageData coverageContainer = ((CoverageData) (iter.next()));
      number += coverageContainer.getNumberOfValidBranches();
    }
    return number;
  }
}
