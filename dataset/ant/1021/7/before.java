class PlaceHold {
  private String readChecksum(File f) {
    BufferedReader diskChecksumReader = null;
    try {
      diskChecksumReader = new BufferedReader(new FileReader(f));
      Object[] result = format.parse(diskChecksumReader.readLine());
      if (((result == null) || (result.length == 0)) || (result[0] == null)) {
        throw new BuildException("failed to find a checksum");
      }
      return ((String) (result[0]));
    } catch (IOException e) {
      throw new BuildException("Couldn't read checksum file " + f, e);
    } catch (ParseException e) {
      throw new BuildException("Couldn't read checksum file " + f, e);
    } finally {
      if (diskChecksumReader != null) {
        try {
          diskChecksumReader.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
