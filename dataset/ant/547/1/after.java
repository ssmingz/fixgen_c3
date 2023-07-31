class PlaceHold {
  private void cat() {
    OutputStream os = null;
    Reader reader = null;
    char[] buffer = new char[8192];
    try {
      if (destinationFile == null) {
        os = new LogOutputStream(this, Project.MSG_WARN);
      } else {
        File parent = fileUtils.getParentFile(destinationFile);
        if (!parent.exists()) {
          parent.mkdirs();
        }
        os = new FileOutputStream(destinationFile.getAbsolutePath(), append);
      }
      PrintWriter writer = null;
      if (encoding == null) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
      } else {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, encoding)));
      }
      if (header != null) {
        if (header.getFiltering()) {
          concatenate(buffer, writer, new StringReader(header.getValue()));
        } else {
          writer.print(header.getValue());
        }
      }
      if (textBuffer != null) {
        reader = new StringReader(getProject().replaceProperties(textBuffer.substring(0)));
      } else {
        reader = new MultiReader();
      }
      concatenate(buffer, writer, reader);
      if (footer != null) {
        if (footer.getFiltering()) {
          concatenate(buffer, writer, new StringReader(footer.getValue()));
        } else {
          writer.print(footer.getValue());
        }
      }
      writer.flush();
      os.flush();
    } catch (IOException ioex) {
      throw new BuildException("Error while concatenating: " + ioex.getMessage(), ioex);
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException ignore) {
        }
      }
      if (os != null) {
        try {
          os.close();
        } catch (IOException ignore) {
        }
      }
    }
  }
}
