class PlaceHold {
  public void execute() throws BuildException {
    if (manifestFile == null) {
      throw new BuildException("the file attribute is required");
    }
    Manifest toWrite = Manifest.getDefaultManifest();
    Manifest current = null;
    BuildException error = null;
    if (manifestFile.exists()) {
      FileInputStream fis = null;
      InputStreamReader isr = null;
      try {
        fis = new FileInputStream(manifestFile);
        if (encoding == null) {
          isr = new InputStreamReader(fis, "UTF-8");
        } else {
          isr = new InputStreamReader(fis, encoding);
        }
        current = new Manifest(isr);
      } catch (ManifestException m) {
        error =
            new BuildException(
                ("Existing manifest " + manifestFile) + " is invalid", m, getLocation());
      } catch (IOException e) {
        error = new BuildException("Failed to read " + manifestFile, e, getLocation());
      } finally {
        FileUtils.close(isr);
      }
    }
    for (Enumeration e = nestedManifest.getWarnings(); e.hasMoreElements(); ) {
      log("Manifest warning: " + ((String) (e.nextElement())), MSG_WARN);
    }
    try {
      if (mode.getValue().equals("update") && manifestFile.exists()) {
        if (current != null) {
          toWrite.merge(current);
        } else if (error != null) {
          throw error;
        }
      }
      toWrite.merge(nestedManifest);
    } catch (ManifestException m) {
      throw new BuildException("Manifest is invalid", m, getLocation());
    }
    if (toWrite.equals(current)) {
      log("Manifest has not changed, do not recreate", MSG_VERBOSE);
      return;
    }
    PrintWriter w = null;
    try {
      FileOutputStream fos = new FileOutputStream(manifestFile);
      OutputStreamWriter osw = new OutputStreamWriter(fos, Manifest.JAR_ENCODING);
      w = new PrintWriter(osw);
      toWrite.write(w);
    } catch (IOException e) {
      throw new BuildException("Failed to write " + manifestFile, e, getLocation());
    } finally {
      FileUtils.close(w);
    }
  }
}
