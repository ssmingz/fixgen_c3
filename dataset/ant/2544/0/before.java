class PlaceHold {
  public String getNoCompress() {
    if (!noCompress) {
      return "";
    } else {
      return SOSCmd.FLAG_NO_COMPRESSION;
    }
  }
}
