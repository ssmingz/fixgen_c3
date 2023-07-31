class PlaceHold {
  public String getNoCache() {
    if (!noCache) {
      return "";
    } else {
      return SOSCmd.FLAG_NO_CACHE;
    }
  }
}
