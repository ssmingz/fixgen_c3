class PlaceHold {
  public void setFileMode(String octalString) {
    fileModeHasBeenSet = true;
    this.fileMode = UnixStat.FILE_FLAG | Integer.parseInt(octalString, 8);
  }
}
