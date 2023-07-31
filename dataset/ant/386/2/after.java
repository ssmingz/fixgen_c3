class PlaceHold {
  public String[] getIncludedDirectories() {
    if (srcFile != null) {
      Vector myvector = new Vector();
      scanme();
      for (Enumeration e = myentries.elements(); e.hasMoreElements(); ) {
        Resource myresource = ((Resource) (e.nextElement()));
        if (myresource.isDirectory() && match(myresource.getName())) {
          myvector.addElement(myresource.getName());
        }
      }
      String[] files = new String[myvector.size()];
      myvector.copyInto(files);
      return files;
    } else {
      return super.getIncludedDirectories();
    }
  }
}
