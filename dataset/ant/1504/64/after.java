class PlaceHold {
  public Path createFilepath() {
    if (this.filepath == null) {
      this.filepath = new Path(getProject());
    }
    return this.filepath.createPath();
  }
}
