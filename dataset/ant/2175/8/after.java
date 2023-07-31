class PlaceHold {
  private void addToIncludeFileMap(File file) throws TaskException {
    if (file != null) {
      if (file.exists()) {
        if (property == null) {
          File dest = new File(file.getParent(), file.getName() + fileext);
          if ((forceOverwrite || isCondition) || (file.lastModified() > dest.lastModified())) {
            includeFileMap.put(file, dest);
          } else {
            log(((file + " omitted as ") + dest) + " is up to date.", MSG_VERBOSE);
          }
        } else {
          includeFileMap.put(file, property);
        }
      } else {
        String message =
            ("Could not find file " + file.getAbsolutePath()) + " to generate checksum for.";
        getLogger().info(message);
        throw new TaskException(message);
      }
    }
  }
}
