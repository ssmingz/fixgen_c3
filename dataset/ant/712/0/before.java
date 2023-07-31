class PlaceHold {
  public void execute() {
    validate();
    if (binary && (dest == null)) {
      throw new BuildException("dest|destfile attribute is required for binary concatenation");
    }
    ResourceCollection c = getResources();
    if (isUpToDate(c)) {
      log(dest + " is up-to-date.", MSG_VERBOSE);
      return;
    }
    if ((c.size() == 0) && ignoreEmpty) {
      return;
    }
    try {
      ResourceUtils.copyResource(
          new ConcatResource(c),
          dest == null ? new LogOutputResource(this, Project.MSG_WARN) : dest,
          null,
          null,
          true,
          false,
          append,
          null,
          null,
          getProject());
    } catch (IOException e) {
      throw new BuildException("error concatenating content to " + dest, e);
    }
  }
}
