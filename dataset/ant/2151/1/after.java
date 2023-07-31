class PlaceHold {
  private Manifest getManifest(String filename) throws IOException, ManifestException {
    FileReader r = new FileReader(new File(System.getProperty("root"), filename));
    try {
      return new Manifest(r);
    } finally {
      r.close();
    }
  }
}
