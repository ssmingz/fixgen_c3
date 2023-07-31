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
        String[] toFiles = ((String[]) (fileCopyMap.get(fromFile)));
        for (int i = 0; i < toFiles.length; i++) {
          String toFile = toFiles[i];
          if (fromFile.equals(toFile)) {
            log("Skipping self-copy of " + fromFile, verbosity);
            continue;
          }
          try {
            log((("Copying " + fromFile) + " to ") + toFile, verbosity);
            FilterSetCollection executionFilters = new FilterSetCollection();
            if (filtering) {
              executionFilters.addFilterSet(getProject().getGlobalFilterSet());
            }
            for (Enumeration filterEnum = filterSets.elements(); filterEnum.hasMoreElements(); ) {
              executionFilters.addFilterSet(((FilterSet) (filterEnum.nextElement())));
            }
            fileUtils.copyFile(
                new File(fromFile),
                new File(toFile),
                executionFilters,
                filterChains,
                forceOverwrite,
                preserveLastModified,
                false,
                inputEncoding,
                outputEncoding,
                getProject(),
                getForce());
          } catch (IOException ioe) {
            String msg =
                (((("Failed to copy " + fromFile) + " to ") + toFile) + " due to ") + getDueTo(ioe);
            File targetFile = new File(toFile);
            if (targetFile.exists() && (!targetFile.delete())) {
              msg += " and I couldn't delete the corrupt " + toFile;
            }
            if (failonerror) {
              throw new BuildException(msg, ioe, getLocation());
            }
            log(msg, MSG_ERR);
          }
        }
      }
    }
    if (includeEmpty) {
      Enumeration e = dirCopyMap.elements();
      int createCount = 0;
      while (e.hasMoreElements()) {
        String[] dirs = ((String[]) (e.nextElement()));
        for (int i = 0; i < dirs.length; i++) {
          File d = new File(dirs[i]);
          if (!d.exists()) {
            if (!d.mkdirs()) {
              log("Unable to create directory " + d.getAbsolutePath(), MSG_ERR);
            } else {
              createCount++;
            }
          }
        }
      }
      if (createCount > 0) {
        log(
            (((((((("Copied " + dirCopyMap.size()) + " empty director")
                                        + (dirCopyMap.size() == 1 ? "y" : "ies"))
                                    + " to ")
                                + createCount)
                            + " empty director")
                        + (createCount == 1 ? "y" : "ies"))
                    + " under ")
                + destDir.getAbsolutePath());
      }
    }
  }
}
