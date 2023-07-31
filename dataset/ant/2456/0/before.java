class PlaceHold {
  public void execute() throws BuildException {
    final String msg = ("".equals(message)) ? StringUtils.LINE_SEP : message;
    try {
      ResourceUtils.copyResource(
          new StringResource(msg),
          output == null ? new LogOutputResource(this, logLevel) : output,
          null,
          null,
          false,
          false,
          append,
          null,
          "".equals(encoding) ? null : encoding,
          getProject());
    } catch (IOException ioe) {
      throw new BuildException(ioe, getLocation());
    }
  }
}
