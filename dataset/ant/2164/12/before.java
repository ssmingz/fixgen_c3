class PlaceHold {
  public void execute() throws TaskException {
    if (regex == null) {
      throw new TaskException("No expression to match.");
    }
    if (subs == null) {
      throw new TaskException("Nothing to replace expression with.");
    }
    if ((file != null) && (filesets.size() > 0)) {
      throw new TaskException(
          "You cannot supply the 'file' attribute and filesets at the same time.");
    }
    int options = 0;
    if (flags.indexOf('g') != (-1)) {
      options |= Regexp.REPLACE_ALL;
    }
    if (flags.indexOf('i') != (-1)) {
      options |= Regexp.MATCH_CASE_INSENSITIVE;
    }
    if (flags.indexOf('m') != (-1)) {
      options |= Regexp.MATCH_MULTILINE;
    }
    if (flags.indexOf('s') != (-1)) {
      options |= Regexp.MATCH_SINGLELINE;
    }
    if ((file != null) && file.exists()) {
      try {
        doReplace(file, options);
      } catch (IOException e) {
        final String message =
            (("An error occurred processing file: '" + file.getAbsolutePath()) + "': ")
                + e.toString();
        getLogger().error(message, e);
      }
    } else if (file != null) {
      final String message = ("The following file is missing: '" + file.getAbsolutePath()) + "'";
      getLogger().error(message);
    }
    int sz = filesets.size();
    for (int i = 0; i < sz; i++) {
      FileSet fs = ((FileSet) (filesets.get(i)));
      DirectoryScanner ds = fs.getDirectoryScanner();
      String files[] = ds.getIncludedFiles();
      for (int j = 0; j < files.length; j++) {
        File f = new File(files[j]);
        if (f.exists()) {
          try {
            doReplace(f, options);
          } catch (Exception e) {
            final String message =
                (("An error occurred processing file: '" + f.getAbsolutePath()) + "': ")
                    + e.toString();
            getLogger().error(message);
          }
        } else {
          final String message =
              ("The following file is missing: '" + file.getAbsolutePath()) + "'";
          getLogger().error(message);
        }
      }
    }
  }
}
