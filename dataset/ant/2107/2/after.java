class PlaceHold {
  protected ExecuteStreamHandler createStreamHandler() throws BuildException {
    if (outFile == null) {
      return new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_ERR);
    }
    ExecuteStreamHandler handler = null;
    OutputStream out = null;
    try {
      out = new FileOutputStream(outFile);
      handler = new MAuditStreamHandler(this, out);
    } catch (IOException e) {
      throw new BuildException(e);
    } finally {
      FileUtils.close(out);
    }
    return handler;
  }
}
