class PlaceHold {
  public boolean eval() throws BuildException {
    if (resources == null) {
      throw new BuildException("You must specify one or more nested resource collections");
    }
    if (resources.size() > 1) {
      Iterator i = resources.iterator();
      Resource r1 = ((Resource) (i.next()));
      Resource r2 = null;
      while (i.hasNext()) {
        r2 = ((Resource) (i.next()));
        try {
          if (!ResourceUtils.contentEquals(r1, r2, asText)) {
            return false;
          }
        } catch (IOException ioe) {
          throw new BuildException(
              (("when comparing resources " + r1.toString()) + " and ") + r2.toString(), ioe);
        }
        r1 = r2;
      }
    }
    return true;
  }
}
