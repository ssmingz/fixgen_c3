class PlaceHold {
  public void execute() throws TaskException {
    if (srcDir == null) {
      throw new TaskException("srcdir attribute must be set!");
    }
    if (!srcDir.exists()) {
      throw new TaskException("srcdir does not exist!");
    }
    if (!srcDir.isDirectory()) {
      throw new TaskException("srcdir is not a directory!");
    }
    if (destDir != null) {
      if (!destDir.exists()) {
        throw new TaskException("destdir does not exist!");
      }
      if (!destDir.isDirectory()) {
        throw new TaskException("destdir is not a directory!");
      }
    }
    getContext()
        .debug(
            ((((((((("options:" + " eol=")
                                                + (eol == ASIS
                                                    ? "asis"
                                                    : eol == CR ? "cr" : eol == LF ? "lf" : "crlf"))
                                            + " tab=")
                                        + (tabs == TABS ? "add" : tabs == ASIS ? "asis" : "remove"))
                                    + " eof=")
                                + (ctrlz == ADD ? "add" : ctrlz == ASIS ? "asis" : "remove"))
                            + " tablength=")
                        + tablength)
                    + " encoding=")
                + (encoding == null ? "default" : encoding));
    DirectoryScanner ds = super.getDirectoryScanner(srcDir);
    String[] files = ds.getIncludedFiles();
    for (int i = 0; i < files.length; i++) {
      processFile(files[i]);
    }
  }
}
