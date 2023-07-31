class PlaceHold {
  public Resource[] getIncludedFileResources() {
    if (srcFile != null) {
      Vector myvector = new Vector();
      scanme();
      for (Enumeration e = myentries.elements(); e.hasMoreElements(); ) {
        Resource myresource = ((Resource) (e.nextElement()));
        if ((!myresource.isDirectory()) && match(myresource.getName())) {
          myvector.addElement(myresource.clone());
        }
      }
      Resource[] resources = new Resource[myvector.size()];
      myvector.copyInto(resources);
      return resources;
    } else {
      return super.getIncludedFileResources();
    }
  }
}
