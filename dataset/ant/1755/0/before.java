class PlaceHold {
  private void writeTagDiff(CvsTagEntry[] entries) throws BuildException {
    FileOutputStream output = null;
    try {
      output = new FileOutputStream(mydestfile);
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"));
      writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      Document doc = DOMUtils.newDocument();
      Element root = doc.createElement("tagdiff");
      if (mystartTag != null) {
        root.setAttribute("startTag", mystartTag);
      } else {
        root.setAttribute("startDate", mystartDate);
      }
      if (myendTag != null) {
        root.setAttribute("endTag", myendTag);
      } else {
        root.setAttribute("endDate", myendDate);
      }
      root.setAttribute("cvsroot", getCvsRoot());
      root.setAttribute("package", CollectionUtils.flattenToString(packageNames));
      DOM_WRITER.openElement(root, writer, 0, "\t");
      writer.println();
      for (int i = 0, c = entries.length; i < c; i++) {
        writeTagEntry(doc, writer, entries[i]);
      }
      DOM_WRITER.closeElement(root, writer, 0, "\t", true);
      writer.flush();
      writer.close();
    } catch (UnsupportedEncodingException uee) {
      log(uee.toString(), MSG_ERR);
    } catch (IOException ioe) {
      throw new BuildException(ioe.toString(), ioe);
    } finally {
      if (null != output) {
        try {
          output.close();
        } catch (IOException ioe) {
          log(ioe.toString(), MSG_ERR);
        }
      }
    }
  }
}
