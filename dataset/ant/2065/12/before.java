class PlaceHold {
  protected void doFileOperations() {
    if (fileCopyMap.size() > 0) {
      log(
          (((("Copying " + fileCopyMap.size()) + " file") + (fileCopyMap.size() == 1 ? "" : "s"))
                  + " to ")
              + destDir.getAbsolutePath());
      Enumeration e = fileCopyMap.keys();
      while (e.hasMoreElements()) {
        String fromFile = ((String) (e.nextElement()));
        String toFile = ((String) (fileCopyMap.get(fromFile)));
        if (fromFile.equals(toFile)) {
          log("Skipping self-copy of " + fromFile, verbosity);
          continue;
        }
        try {
          log((("Copying " + fromFile) + " to ") + toFile, verbosity);
          FilterSetCollection executionFilters = new FilterSetCollection();
          if (filtering) {
            executionFilters.addFilterSet(project.getGlobalFilterSet());
          }
          for (Enumeration filterEnum = filterSets.elements(); filterEnum.hasMoreElements(); ) {
            executionFilters.addFilterSet(((FilterSet) (filterEnum.nextElement())));
          }
          fileUtils.copyFile(
              fromFile, toFile, executionFilters, forceOverwrite, preserveLastModified);
        } catch (IOException ioe) {
          String msg =
              (((("Failed to copy " + fromFile) + " to ") + toFile) + " due to ")
                  + ioe.getMessage();
          throw new BuildException(msg, ioe, location);
        }
      }
    }
    if (includeEmpty) {
      Enumeration e = dirCopyMap.elements();
      int count = 0;
      while (e.hasMoreElements()) {
        File d = new File(((String) (e.nextElement())));
        if (!d.exists()) {
          if (!d.mkdirs()) {
            log("Unable to create directory " + d.getAbsolutePath(), MSG_ERR);
          } else {
            count++;
          }
        }
      }
      if (count > 0) {
        log(
            (((("Copied " + count) + " empty director") + (count == 1 ? "y" : "ies")) + " to ")
                + destDir.getAbsolutePath());
      }
    }
  }
}
