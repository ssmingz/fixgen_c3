class PlaceHold {
  protected void removeFiles(File d, String[] files, String[] dirs) {
    if (files.length > 0) {
      log((("Deleting " + files.length) + " files from ") + d.getAbsolutePath());
      for (int j = 0; j < files.length; j++) {
        File f = new File(d, files[j]);
        log("Deleting " + f.getAbsolutePath(), verbosity);
        if (!delete(f)) {
          String message = "Unable to delete file " + f.getAbsolutePath();
          if (failonerror) {
            throw new BuildException(message);
          } else {
            log(message, quiet ? Project.MSG_VERBOSE : Project.MSG_WARN);
          }
        }
      }
    }
    if ((dirs.length > 0) && includeEmpty) {
      int dirCount = 0;
      for (int j = dirs.length - 1; j >= 0; j--) {
        File currDir = new File(d, dirs[j]);
        String[] dirFiles = currDir.list();
        if ((dirFiles == null) || (dirFiles.length == 0)) {
          log("Deleting " + currDir.getAbsolutePath(), verbosity);
          if (!delete(currDir)) {
            String message = "Unable to delete directory " + currDir.getAbsolutePath();
            if (failonerror) {
              throw new BuildException(message);
            } else {
              log(message, quiet ? Project.MSG_VERBOSE : Project.MSG_WARN);
            }
          } else {
            dirCount++;
          }
        }
      }
      if (dirCount > 0) {
        log(
            (((("Deleted " + dirCount) + " director") + (dirCount == 1 ? "y" : "ies")) + " from ")
                + d.getAbsolutePath());
      }
    }
  }
}
