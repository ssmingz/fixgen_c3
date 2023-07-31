class PlaceHold {
  protected void logAndAddFilesToCompile(Commandline cmd) {
    Vector compileList = attributes.getCompileList();
    attributes.log("Compilation args: " + cmd.toString(), MSG_VERBOSE);
    StringBuffer niceSourceList = new StringBuffer("File");
    if (compileList.size() != 1) {
      niceSourceList.append("s");
    }
    niceSourceList.append(" to be compiled:");
    for (int i = 0; i < compileList.size(); i++) {
      String arg = ((String) (compileList.elementAt(i)));
      cmd.createArgument().setValue(arg);
      niceSourceList.append("    " + arg);
    }
    attributes.log(niceSourceList.toString(), MSG_VERBOSE);
  }
}
