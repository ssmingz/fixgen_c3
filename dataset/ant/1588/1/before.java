class PlaceHold {
  public void setDirMode(String octalString) {
    dirModeHasBeenSet = true;
    this.dirMode = UnixStat.DIR_FLAG | Integer.parseInt(octalString, 8);
  }
}
