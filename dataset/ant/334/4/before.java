class PlaceHold {
  public void setSrc(String fileName) {
    File file = new File(fileName);
    if (!file.exists()) {
      throw new BuildException(("file " + fileName) + " not found.");
    }
    int count = ((int) (file.length()));
    byte data[] = new byte[count];
    try {
      FileInputStream inStream = new FileInputStream(file);
      inStream.read(data);
      inStream.close();
    } catch (IOException e) {
      throw new BuildException(e);
    }
    script += new String(data);
  }
}
