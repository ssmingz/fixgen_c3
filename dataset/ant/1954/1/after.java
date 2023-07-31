class PlaceHold {
  protected ExecuteStreamHandler createStreamHandler() throws BuildException {
    ExecuteStreamHandler handler = null;
    if (outFile == null) {
      handler = new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_INFO);
    } else {
      try {
        OutputStream out = new FileOutputStream(outFile);
        handler = new MAuditStreamHandler(this, out);
      } catch (IOException e) {
        throw new BuildException("Error", e);
      }
    }
    return handler;
  }
}
