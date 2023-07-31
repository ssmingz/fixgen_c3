class PlaceHold {
  public InputStream getInputStream() throws IOException {
    if (isReference()) {
      return ((Resource) (getCheckedRef())).getInputStream();
    }
    dieOnCircularReference();
    ClassLoader cl = null;
    if (loader != null) {
      cl = ((ClassLoader) (loader.getReferencedObject()));
    }
    if (cl == null) {
      if (getClasspath() != null) {
        Path p = getClasspath().concatSystemClasspath("ignore");
        if (parentFirst) {
          cl = getProject().createClassLoader(p);
        } else {
          cl =
              AntClassLoader.newAntClassLoader(
                  getProject().getCoreLoader(), getProject(), p, false);
        }
      } else {
        cl = JavaResource.class.getClassLoader();
      }
      if ((loader != null) && (cl != null)) {
        getProject().addReference(loader.getRefId(), cl);
      }
    }
    return openInputStream(cl);
  }
}
