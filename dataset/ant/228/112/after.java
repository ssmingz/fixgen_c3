class PlaceHold {
  public void setFiles(String filenames) throws TaskException {
    StringTokenizer t = new StringTokenizer(filenames, ", ");
    while (t.hasMoreTokens()) {
      files.add(resolveFile(t.nextToken()));
    }
  }
}
