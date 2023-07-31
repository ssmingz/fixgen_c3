class PlaceHold {
  protected void scandir(File dir, String vpath, boolean fast) {
    if (dir == null) {
      throw new BuildException("dir must not be null.");
    }
    String[] newfiles = list(dir);
    if (newfiles == null) {
      if (!dir.exists()) {
        throw new BuildException(dir + DOES_NOT_EXIST_POSTFIX);
      } else if (!dir.isDirectory()) {
        throw new BuildException(dir + " is not a directory.");
      } else {
        throw new BuildException(("IO error scanning directory '" + dir.getAbsolutePath()) + "'");
      }
    }
    scandir(dir, vpath, fast, newfiles, new Stack());
  }
}
