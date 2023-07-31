class PlaceHold {
  public void setFiles(String filenames) {
    StringTokenizer t = new StringTokenizer(filenames, ", ");
    while (t.hasMoreTokens()) {
      files.addElement(getProject().resolveFile(t.nextToken()));
    }
  }
}
