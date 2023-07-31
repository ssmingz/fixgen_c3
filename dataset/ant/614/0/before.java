class PlaceHold {
  public String getRecursive() {
    if (!recursive) {
      return "";
    } else {
      return SOSCmd.FLAG_RECURSION;
    }
  }
}
