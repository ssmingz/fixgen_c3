class PlaceHold {
  private File getGeneratedFile() throws BuildException {
    String generatedFileName = null;
    try {
      BufferedReader in = new BufferedReader(new FileReader(target));
      String line;
      while ((line = in.readLine()) != null) {
        int extendsIndex = line.indexOf(" extends ");
        if (line.startsWith("class ") && (extendsIndex > (-1))) {
          generatedFileName = line.substring(6, extendsIndex).trim();
          break;
        }
      }
      in.close();
    } catch (Exception e) {
      throw new BuildException("Unable to determine generated class");
    }
    if (generatedFileName == null) {
      throw new BuildException("Unable to determine generated class");
    }
    return new File(outputDirectory, generatedFileName + ".java");
  }
}
