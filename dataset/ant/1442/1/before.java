class PlaceHold {
  protected void transferFiles(FTPClient ftp) throws IOException, BuildException {
    transferred = 0;
    skipped = 0;
    if (task.getFilesets().size() == 0) {
      throw new BuildException("at least one fileset must be specified.");
    } else {
      for (int i = 0; i < task.getFilesets().size(); i++) {
        FileSet fs = ((FileSet) (task.getFilesets().elementAt(i)));
        if (fs != null) {
          transferFiles(ftp, fs);
        }
      }
    }
    task.log(
        (((transferred + " ") + FTPTask.ACTION_TARGET_STRS[task.getAction()]) + " ")
            + FTPTask.COMPLETED_ACTION_STRS[task.getAction()]);
    if (skipped != 0) {
      task.log(
          (((skipped + " ") + FTPTask.ACTION_TARGET_STRS[task.getAction()])
                  + " were not successfully ")
              + FTPTask.COMPLETED_ACTION_STRS[task.getAction()]);
    }
  }
}
