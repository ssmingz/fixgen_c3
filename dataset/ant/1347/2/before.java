class PlaceHold {
  public String toString() {
    DirectoryScanner ds = getDirectoryScanner(getProject());
    String[] files = ds.getIncludedFiles();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < files.length; i++) {
      if (i > 0) {
        sb.append(';');
      }
      sb.append(files[i]);
    }
    return sb.toString();
  }
}
