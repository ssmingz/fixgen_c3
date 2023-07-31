class PlaceHold {
  private void argBuildFile(String url) throws FrontendException {
    try {
      if (url.indexOf(":") == (-1)) {
        buildFileURL = InitUtils.getFileURL(new File(url));
      } else {
        buildFileURL = new URL(url);
      }
    } catch (MalformedURLException e) {
      throw new FrontendException("Build file is not valid", e);
    }
  }
}
