class PlaceHold {
  private void filesetManifest(File file, InputStream is) throws IOException {
    if ((manifestFile != null) && manifestFile.equals(file)) {
      log("Found manifest " + file, MSG_VERBOSE);
      try {
        if (is != null) {
          InputStreamReader isr;
          if (manifestEncoding == null) {
            isr = new InputStreamReader(is);
          } else {
            isr = new InputStreamReader(is, manifestEncoding);
          }
          manifest = getManifest(isr);
        } else {
          manifest = getManifest(file);
        }
      } catch (UnsupportedEncodingException e) {
        throw new BuildException(
            ("Unsupported encoding while reading " + "manifest: ") + e.getMessage(), e);
      }
    } else if ((filesetManifestConfig != null)
        && (!filesetManifestConfig.getValue().equals("skip"))) {
      logWhenWriting("Found manifest to merge in file " + file, MSG_VERBOSE);
      try {
        Manifest newManifest = null;
        if (is != null) {
          InputStreamReader isr;
          if (manifestEncoding == null) {
            isr = new InputStreamReader(is);
          } else {
            isr = new InputStreamReader(is, manifestEncoding);
          }
          newManifest = getManifest(isr);
        } else {
          newManifest = getManifest(file);
        }
        if (filesetManifest == null) {
          filesetManifest = newManifest;
        } else {
          filesetManifest.merge(newManifest, false, mergeClassPaths);
        }
      } catch (UnsupportedEncodingException e) {
        throw new BuildException(
            ("Unsupported encoding while reading " + "manifest: ") + e.getMessage(), e);
      } catch (ManifestException e) {
        log((("Manifest in file " + file) + " is invalid: ") + e.getMessage(), MSG_ERR);
        throw new BuildException("Invalid Manifest", e, getLocation());
      }
    } else {
    }
  }
}
