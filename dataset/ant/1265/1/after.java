class PlaceHold {
  public void setSrcdir(Path srcDir) throws TaskException {
    if (src == null) {
      src = srcDir;
    } else {
      src.add(srcDir);
    }
  }
}
