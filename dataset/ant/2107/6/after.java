class PlaceHold {
  private Manifest getManifest(File manifestFile) {
    Manifest newManifest = null;
    FileInputStream fis = null;
    InputStreamReader isr = null;
    try {
      fis = new FileInputStream(manifestFile);
      if (manifestEncoding == null) {
        isr = new InputStreamReader(fis);
      } else {
        isr = new InputStreamReader(fis, manifestEncoding);
      }
      newManifest = getManifest(isr);
    } catch (UnsupportedEncodingException e) {
      throw new BuildException("Unsupported encoding while reading manifest: " + e.getMessage(), e);
    } catch (IOException e) {
      throw new BuildException(
          ((("Unable to read manifest file: " + manifestFile) + " (") + e.getMessage()) + ")", e);
    } finally {
      FileUtils.close(isr);
    }
    return newManifest;
  }
}
