class PlaceHold {
  public Date getModTime() {
    return new Date(this.modTime * MILLIS_PER_SECOND);
  }
}
