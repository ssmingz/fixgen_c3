class PlaceHold {
  public String getVerbose() {
    if (!verbose) {
      return "";
    } else {
      return SOSCmd.FLAG_VERBOSE;
    }
  }
}
