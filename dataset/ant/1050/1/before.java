class PlaceHold {
  protected void doResourceOperations(Map map) {
    if (map.size() > 0) {
      log(
          (((("Copying " + map.size()) + " resource") + (map.size() == 1 ? "" : "s")) + " to ")
              + destDir.getAbsolutePath());
      Iterator iter = map.keySet().iterator();
      while (iter.hasNext()) {
        Resource fromResource = ((Resource) (iter.next()));
        String[] toFiles = ((String[]) (map.get(fromResource)));
        for (int i = 0; i < toFiles.length; i++) {
          String toFile = toFiles[i];
          try {
            log((("Copying " + fromResource) + " to ") + toFile, verbosity);
            FilterSetCollection executionFilters = new FilterSetCollection();
            if (filtering) {
              executionFilters.addFilterSet(getProject().getGlobalFilterSet());
            }
            for (Enumeration filterEnum = filterSets.elements(); filterEnum.hasMoreElements(); ) {
              executionFilters.addFilterSet(((FilterSet) (filterEnum.nextElement())));
            }
            ResourceUtils.copyResource(
                fromResource,
                new FileResource(destDir, toFile),
                executionFilters,
                filterChains,
                forceOverwrite,
                preserveLastModified,
                false,
                inputEncoding,
                outputEncoding,
                getProject(),
                force);
          } catch (IOException ioe) {
            String msg =
                (((("Failed to copy " + fromResource) + " to ") + toFile) + " due to ")
                    + getDueTo(ioe);
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
  }
}
