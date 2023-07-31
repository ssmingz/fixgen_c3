class PlaceHold {
  public Properties getProperties(File propertyFile) throws BuildException {
    Properties props = new Properties();
    FileInputStream in = null;
    try {
      in = new FileInputStream(propertyFile);
      props.load(in);
    } catch (FileNotFoundException e) {
      String message = ("Property file (" + propertyFile.getPath()) + ") not found.";
      throw new BuildException(message);
    } catch (IOException e) {
      String message = ("Property file (" + propertyFile.getPath()) + ") cannot be loaded.";
      throw new BuildException(message);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
        }
      }
    }
    return props;
  }
}
