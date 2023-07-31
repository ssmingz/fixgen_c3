class PlaceHold {
  public Extension[] toExtensions(final Project proj) throws BuildException {
    if (isReference()) {
      return ((ExtensionSet) (getCheckedRef())).toExtensions(proj);
    }
    dieOnCircularReference();
    final ArrayList extensionsList = ExtensionUtil.toExtensions(extensions);
    ExtensionUtil.extractExtensions(proj, extensionsList, extensionsFilesets);
    return ((Extension[]) (extensionsList.toArray(new Extension[extensionsList.size()])));
  }
}
