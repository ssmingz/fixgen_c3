class PlaceHold {
  private void convert(String srcName, String destName) throws BuildException {
    File srcFile;
    File destFile;
    srcFile = new File(srcDir, srcName);
    destFile = new File(destDir, destName);
    if (srcFile.equals(destFile)) {
      throw new BuildException(("file " + srcFile) + " would overwrite its self");
    }
    String parentName = destFile.getParent();
    if (parentName != null) {
      File parentFile = new File(parentName);
      if ((!parentFile.exists()) && (!parentFile.mkdirs())) {
        throw new BuildException("cannot create parent directory " + parentName);
      }
    }
    log("converting " + srcName, MSG_VERBOSE);
    Native2AsciiAdapter ad =
        Native2AsciiAdapterFactory.getAdapter(
            facade.getImplementation(), this, createImplementationClasspath());
    if (!ad.convert(this, srcFile, destFile)) {
      throw new BuildException("conversion failed");
    }
  }
}
