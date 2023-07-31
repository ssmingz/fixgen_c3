class PlaceHold {
  public void setSrcResource(Resource src) {
    if (src.isDirectory()) {
      throw new BuildException("the source can't be a directory");
    }
    if ((src.as(FileProvider.class) != null) || supportsNonFileResources()) {
      this.src = src;
    } else {
      throw new BuildException("Only FileSystem resources are supported.");
    }
  }
}
