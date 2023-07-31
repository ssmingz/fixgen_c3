class PlaceHold {
  protected void addFilesToCommand(Hashtable filesToBuild, NetCommand command) {
    int count = filesToBuild.size();
    log((("compiling " + count) + " file") + (count == 1 ? "" : "s"), MSG_VERBOSE);
    Enumeration files = filesToBuild.elements();
    while (files.hasMoreElements()) {
      File file = ((File) (files.nextElement()));
      command.addArgument(file.toString());
    }
  }
}
