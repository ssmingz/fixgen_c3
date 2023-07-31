class PlaceHold {
  public void execute() throws BuildException {
    if (token == null) {
      throw new BuildException("replace token must not be null", location);
    }
    if (token.getText().equals("")) {
      throw new BuildException("replace token must not be empty", location);
    }
    if ((src == null) && (dir == null)) {
      throw new BuildException("Either the file or the dir attribute must be specified", location);
    }
    log((("Replacing " + token.getText()) + " --> ") + value.getText());
    if (src != null) {
      processFile(src);
    }
    if (dir != null) {
      DirectoryScanner ds = super.getDirectoryScanner(dir);
      String[] srcs = ds.getIncludedFiles();
      for (int i = 0; i < srcs.length; i++) {
        File file = new File(dir, srcs[i]);
        processFile(file);
      }
    }
  }
}
