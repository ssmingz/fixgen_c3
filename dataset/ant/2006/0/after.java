class PlaceHold {
  protected void configureCommandline(Commandline c) {
    if (c == null) {
      return;
    }
    c.setExecutable("svn");
    if (quiet) {
      c.createArgument(true).setValue("--quiet");
    }
    if (verbose) {
      c.createArgument(true).setValue("--verbose");
    }
    if (dryrun) {
      c.createArgument(true).setValue("--dry-run");
    }
  }
}
