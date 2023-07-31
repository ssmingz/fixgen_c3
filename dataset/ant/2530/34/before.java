class PlaceHold {
  protected void transferFiles(FTPClient ftp) throws IOException, TaskException {
    transferred = 0;
    skipped = 0;
    if (filesets.size() == 0) {
      throw new TaskException("at least one fileset must be specified.");
    } else {
      for (int i = 0; i < filesets.size(); i++) {
        FileSet fs = ((FileSet) (filesets.elementAt(i)));
        if (fs != null) {
          transferFiles(ftp, fs);
        }
      }
    }
    getLogger().info((transferred + " files ") + COMPLETED_ACTION_STRS[action]);
    if (skipped != 0) {
      getLogger().info((skipped + " files were not successfully ") + COMPLETED_ACTION_STRS[action]);
    }
  }
}
