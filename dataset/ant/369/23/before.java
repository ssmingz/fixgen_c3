class PlaceHold {
  private void loadResourceMap(FileInputStream ins) throws BuildException {
    try {
      BufferedReader in = null;
      InputStreamReader isr = new InputStreamReader(ins, bundleEncoding);
      in = new BufferedReader(isr);
      String line = null;
      while ((line = in.readLine()) != null) {
        if ((line.trim().length() > 1) && (('#' != line.charAt(0)) || ('!' != line.charAt(0)))) {
          int sepIndex = line.indexOf('=');
          if ((-1) == sepIndex) {
            sepIndex = line.indexOf(':');
          }
          if ((-1) == sepIndex) {
            for (int k = 0; k < line.length(); k++) {
              if (Character.isSpaceChar(line.charAt(k))) {
                sepIndex = k;
                break;
              }
            }
          }
          if ((-1) != sepIndex) {
            String key = line.substring(0, sepIndex).trim();
            String value = line.substring(sepIndex + 1).trim();
            while (value.endsWith("\\")) {
              value = value.substring(0, value.length() - 1);
              if ((line = in.readLine()) != null) {
                value = value + line.trim();
              } else {
                break;
              }
            }
            if (key.length() > 0) {
              if (resourceMap.get(key) == null) {
                resourceMap.put(key, value);
              }
            }
          }
        }
      }
      if (in != null) {
        in.close();
      }
    } catch (IOException ioe) {
      throw new BuildException(ioe.getMessage(), location);
    }
  }
}
