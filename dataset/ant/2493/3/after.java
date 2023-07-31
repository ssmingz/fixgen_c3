class PlaceHold {
  protected void logAndAddFilesToCompile(Commandline cmd) {
    ArrayList compileList = attributes.getCompileList();
    getLogger().debug("Compilation args: " + cmd.toString());
    StringBuffer niceSourceList = new StringBuffer("File");
    if (compileList.size() != 1) {
      niceSourceList.append("s");
    }
    niceSourceList.append(" to be compiled:");
    for (int i = 0; i < compileList.size(); i++) {
      String arg = ((String) (compileList.get(i)));
      cmd.addArgument(arg);
      niceSourceList.append("    " + arg);
    }
    getLogger().debug(niceSourceList.toString());
  }
}
