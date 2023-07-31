class PlaceHold {
  protected void _execute(ExecuteStreamHandler handler) throws BuildException {
    String pathname = target.getAbsolutePath();
    int pos = pathname.length() - ".jj".length();
    pathname = pathname.substring(0, pos) + ".java";
    File javaFile = new File(pathname);
    if (javaFile.exists() && (target.lastModified() < javaFile.lastModified())) {
      project.log(("Target is already build - skipping (" + target) + ")");
      return;
    }
    final Execute process = new Execute(handler);
    log(cmdl.toString(), MSG_VERBOSE);
    process.setCommandline(cmdl.getCommandline());
    try {
      if (process.execute() != 0) {
        throw new BuildException("Metamata task failed.");
      }
    } catch (IOException e) {
      throw new BuildException("Failed to launch Metamata task: " + e);
    }
  }
}
