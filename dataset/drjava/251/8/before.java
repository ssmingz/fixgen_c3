class PlaceHold {
  public File getSourceFileFromPaths(String filename, Vector<File> paths) {
    File f = null;
    for (int i = 0; i < paths.size(); i++) {
      f = _getSourceFileFromPath(filename, paths.elementAt(i));
      if (f != null) {
        return f;
      }
    }
    return null;
  }
}
