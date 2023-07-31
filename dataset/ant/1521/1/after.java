class PlaceHold {
  public static Manifest getDefaultManifest() throws BuildException {
    try {
      String s = "/org/apache/tools/ant/defaultManifest.mf";
      InputStream in = Manifest.class.getResourceAsStream(s);
      if (in == null) {
        throw new BuildException("Could not find default manifest: " + s);
      }
      try {
        return new Manifest(new InputStreamReader(in, "ASCII"));
      } catch (UnsupportedEncodingException e) {
        return new Manifest(new InputStreamReader(in));
      }
    } catch (ManifestException e) {
      throw new BuildException("Default manifest is invalid !!", e);
    } catch (IOException e) {
      throw new BuildException("Unable to read default manifest", e);
    }
  }
}
