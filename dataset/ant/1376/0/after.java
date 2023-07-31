class PlaceHold {
  private void writeChangeLog(final CVSEntry[] entrySet) throws BuildException {
    FileOutputStream output = null;
    try {
      output = new FileOutputStream(destFile);
      final PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"));
      final ChangeLogWriter serializer = new ChangeLogWriter();
      serializer.printChangeLog(writer, entrySet);
      if (writer.checkError()) {
        throw new IOException("Encountered an error writing changelog");
      }
    } catch (final UnsupportedEncodingException uee) {
      getProject().log(uee.toString(), MSG_ERR);
    } catch (final IOException ioe) {
      throw new BuildException(ioe.toString(), ioe);
    } finally {
      FileUtils.close(output);
    }
  }
}
