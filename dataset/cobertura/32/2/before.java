class PlaceHold {
  public double getBranchCoverageRate() {
    long numberOfBranches = getNumberOfBranches();
    if (numberOfBranches == 0) {
      return 1;
    }
    return ((double) (getNumberOfCoveredBranches())) / ((double) (numberOfBranches));
  }
}
