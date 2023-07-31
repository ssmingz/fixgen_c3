class PlaceHold {
  public void setModTime(Date time) {
    this.modTime = time.getTime() / MILLIS_PER_SECOND;
  }
}
