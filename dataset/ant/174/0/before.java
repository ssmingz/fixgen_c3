class PlaceHold {
  private void cat(ResourceCollection c) {
    OutputStream os = null;
    char[] buffer = new char[BUFFER_SIZE];
    try {
      PrintWriter writer = null;
      if (outputWriter != null) {
        writer = new PrintWriter(outputWriter);
      } else {
        if (destinationFile == null) {
          os = new LogOutputStream(this, Project.MSG_WARN);
        } else {
          File parent = destinationFile.getParentFile();
          if (!parent.exists()) {
            parent.mkdirs();
          }
          os = new FileOutputStream(destinationFile.getAbsolutePath(), append);
        }
        if (outputEncoding == null) {
          writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
        } else {
          writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, outputEncoding)));
        }
      }
      if (header != null) {
        if (header.getFiltering()) {
          concatenate(buffer, writer, new StringReader(header.getValue()));
        } else {
          writer.print(header.getValue());
        }
      }
      if (c.size() > 0) {
        concatenate(buffer, writer, new MultiReader(c));
      }
      if (footer != null) {
        if (footer.getFiltering()) {
          concatenate(buffer, writer, new StringReader(footer.getValue()));
        } else {
          writer.print(footer.getValue());
        }
      }
      writer.flush();
      if (os != null) {
        os.flush();
      }
    } catch (IOException ioex) {
      throw new BuildException("Error while concatenating: " + ioex.getMessage(), ioex);
    } finally {
      FILE_UTILS.close(os);
    }
  }
}
