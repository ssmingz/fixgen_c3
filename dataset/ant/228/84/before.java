class PlaceHold {
  public void setFiles(String filenames) {
    checkAttributesAllowed();
    if ((filenames != null) && (filenames.length() > 0)) {
      StringTokenizer tok = new StringTokenizer(filenames, ", \t\n\r\f", false);
      while (tok.hasMoreTokens()) {
        this.filenames.addElement(tok.nextToken());
      }
    }
  }
}
