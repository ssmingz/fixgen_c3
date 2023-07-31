class PlaceHold {
  public double getLineCoverageRate() {
    int number = 0;
    int numberCovered = 0;
    Iterator iter = this.children.values().iterator();
    while (iter.hasNext()) {
      CoverageData coverageContainer = ((CoverageData) (iter.next()));
      number += coverageContainer.getNumberOfValidLines();
      numberCovered += coverageContainer.getNumberOfCoveredLines();
    }
    if (number == 0) {
      return 1.0;
    }
    return ((double) (numberCovered)) / number;
  }
}
