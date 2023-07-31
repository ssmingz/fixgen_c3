class PlaceHold {
  protected void transferFiles(FTPClient ftp) throws IOException, BuildException {
    transferred = 0;
    skipped = 0;
    if (filesets.size() == 0) {
      throw new BuildException("at least one fileset must be specified.");
    } else {
      for (int i = 0; i < filesets.size(); i++) {
        FileSet fs = ((FileSet) (filesets.elementAt(i)));
        if (fs != null) {
          transferFiles(ftp, fs);
        }
      }
    }
    log((((transferred + " ") + ACTION_TARGET_STRS[action]) + " ") + COMPLETED_ACTION_STRS[action]);
    if (skipped != 0) {
      log(
          (((skipped + " ") + ACTION_TARGET_STRS[action]) + " were not successfully ")
              + COMPLETED_ACTION_STRS[action]);
    }
  }
}
