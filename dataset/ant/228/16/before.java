class PlaceHold {
  protected void createParents(FTPClient ftp, String filename) throws IOException, BuildException {
    File dir = new File(filename);
    if (dirCache.contains(dir)) {
      return;
    }
    Vector parents = new Vector();
    String dirname;
    while ((dirname = dir.getParent()) != null) {
      File checkDir = new File(dirname);
      if (dirCache.contains(checkDir)) {
        break;
      }
      dir = checkDir;
      parents.addElement(dir);
    }
    int i = parents.size() - 1;
    if (i >= 0) {
      String cwd = ftp.printWorkingDirectory();
      String parent = dir.getParent();
      if (parent != null) {
        if (!ftp.changeWorkingDirectory(resolveFile(parent))) {
          throw new BuildException(("could not change to " + "directory: ") + ftp.getReplyString());
        }
      }
      while (i >= 0) {
        dir = ((File) (parents.elementAt(i--)));
        if (!ftp.changeWorkingDirectory(dir.getName())) {
          task.log("creating remote directory " + resolveFile(dir.getPath()), MSG_VERBOSE);
          if (!ftp.makeDirectory(dir.getName())) {
            handleMkDirFailure(ftp);
          }
          if (!ftp.changeWorkingDirectory(dir.getName())) {
            throw new BuildException(
                ("could not change to " + "directory: ") + ftp.getReplyString());
          }
        }
        dirCache.addElement(dir);
      }
      ftp.changeWorkingDirectory(cwd);
    }
  }
}
