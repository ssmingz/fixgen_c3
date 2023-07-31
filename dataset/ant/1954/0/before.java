class PlaceHold {
  public void execute() throws BuildException {
    if (outfile == null) {
      throw new BuildException("outfile attribute is required! Please set.");
    }
    if ((!haveAddFiles()) && (!haveMergeFiles())) {
      throw new BuildException("addfiles or mergefiles required! Please set.");
    }
    log("linking:     " + outfile.getPath());
    log("compression: " + compress, MSG_VERBOSE);
    jlink linker = new jlink();
    linker.setOutfile(outfile.getPath());
    linker.setCompression(compress);
    if (haveMergeFiles()) {
      log("merge files: " + mergefiles.toString(), MSG_VERBOSE);
      linker.addMergeFiles(mergefiles.list());
    }
    if (haveAddFiles()) {
      log("add files: " + addfiles.toString(), MSG_VERBOSE);
      linker.addAddFiles(addfiles.list());
    }
    try {
      linker.link();
    } catch (Exception ex) {
      throw new BuildException(ex);
    }
  }
}
