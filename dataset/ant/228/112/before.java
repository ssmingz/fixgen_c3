class PlaceHold {
  public void setFiles(String filenames) throws TaskException {
    StringTokenizer t = new StringTokenizer(filenames, ", ");
    while (t.hasMoreTokens()) {
      files.addElement(resolveFile(t.nextToken()));
    }
  }
}
