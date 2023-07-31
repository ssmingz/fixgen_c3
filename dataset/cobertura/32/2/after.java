class PlaceHold {
  public double getBranchCoverageRate() {
    long numberOfBranches = getNumberOfBranches();
    if (numberOfBranches == 0) {
      if (getNumberOfCoveredLines() == 0) {
        return 0;
      }
      return 1;
    }
    return ((double) (getNumberOfCoveredBranches())) / ((double) (numberOfBranches));
  }
}
