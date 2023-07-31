class PlaceHold {
  public void execute() throws TaskException {
    if (usedMatchingTask) {
      getLogger()
          .info(
              "DEPRECATED - Use of the implicit FileSet is deprecated.  Use a nested fileset"
                  + " element instead.");
    }
    if (((file == null) && (dir == null)) && (filesets.size() == 0)) {
      throw new TaskException(
          "At least one of the file or dir attributes, or a fileset element, must be set.");
    }
    if (quiet && failonerror) {
      throw new TaskException("quiet and failonerror cannot both be set to true");
    }
    if (file != null) {
      if (file.exists()) {
        if (file.isDirectory()) {
          getLogger()
              .info(
                  ("Directory " + file.getAbsolutePath())
                      + " cannot be removed using the file attribute.  Use dir instead.");
        } else {
          getLogger().info("Deleting: " + file.getAbsolutePath());
          if (!file.delete()) {
            String message = "Unable to delete file " + file.getAbsolutePath();
            if (failonerror) {
              throw new TaskException(message);
            } else {
              log(message, quiet ? Project.MSG_VERBOSE : Project.MSG_WARN);
            }
          }
        }
      } else {
        log(("Could not find file " + file.getAbsolutePath()) + " to delete.", MSG_VERBOSE);
      }
    }
    if ((((dir != null) && dir.exists()) && dir.isDirectory()) && (!usedMatchingTask)) {
      if (verbosity == Project.MSG_VERBOSE) {
        getLogger().info("Deleting directory " + dir.getAbsolutePath());
      }
      removeDir(dir);
    }
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.get(i)));
      try {
        DirectoryScanner ds = fs.getDirectoryScanner(getProject());
        String[] files = ds.getIncludedFiles();
        String[] dirs = ds.getIncludedDirectories();
        removeFiles(fs.getDir(getProject()), files, dirs);
      } catch (TaskException be) {
        if (failonerror) {
          throw be;
        } else {
          log(be.getMessage(), quiet ? Project.MSG_VERBOSE : Project.MSG_WARN);
        }
      }
    }
    if (usedMatchingTask && (dir != null)) {
      try {
        DirectoryScanner ds = super.getDirectoryScanner(dir);
        String[] files = ds.getIncludedFiles();
        String[] dirs = ds.getIncludedDirectories();
        removeFiles(dir, files, dirs);
      } catch (TaskException be) {
        if (failonerror) {
          throw be;
        } else {
          log(be.getMessage(), quiet ? Project.MSG_VERBOSE : Project.MSG_WARN);
        }
      }
    }
  }
}
