class PlaceHold {
  public void setFiles(String filenames) throws TaskException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    if ((filenames != null) && (filenames.length() > 0)) {
      StringTokenizer tok = new StringTokenizer(filenames, ", \t\n\r\f", false);
      while (tok.hasMoreTokens()) {
        this.filenames.add(tok.nextToken());
      }
    }
  }
}
