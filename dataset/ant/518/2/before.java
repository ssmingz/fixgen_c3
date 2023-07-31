class PlaceHold {
  private boolean causesIllegalSymlinkLoop(
      String dirName, File parent, LinkedList directoryNamesFollowed) {
    try {
      if (((directoryNamesFollowed.size() >= maxLevelsOfSymlinks)
              && (CollectionUtils.frequency(directoryNamesFollowed, dirName)
                  >= maxLevelsOfSymlinks))
          && FILE_UTILS.isSymbolicLink(parent, dirName)) {
        ArrayList files = new ArrayList();
        File f = FILE_UTILS.resolveFile(parent, dirName);
        String target = f.getCanonicalPath();
        files.add(target);
        String relPath = "";
        for (Iterator i = directoryNamesFollowed.iterator(); i.hasNext(); ) {
          relPath += "../";
          String dir = ((String) (i.next()));
          if (dirName.equals(dir)) {
            f = FILE_UTILS.resolveFile(parent, relPath + dir);
            files.add(f.getCanonicalPath());
            if ((files.size() > maxLevelsOfSymlinks)
                && (CollectionUtils.frequency(files, target) > maxLevelsOfSymlinks)) {
              return true;
            }
          }
        }
      }
      return false;
    } catch (IOException ex) {
      throw new BuildException("Caught error while checking for" + " symbolic links", ex);
    }
  }
}
