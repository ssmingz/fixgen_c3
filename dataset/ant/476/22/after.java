class PlaceHold {
  private void processTasks() {
    if (implicitTag != null) {
      return;
    }
    for (Iterator i = unknownElements.iterator(); i.hasNext(); ) {
      UnknownElement ue = ((UnknownElement) (i.next()));
      String name =
          ProjectHelper.extractNameFromComponentName(ue.getTag()).toLowerCase(Locale.ENGLISH);
      if (getNsElements().get(name) == null) {
        throw new BuildException("unsupported element " + name);
      }
      if (presentElements.get(name) != null) {
        throw new BuildException(("Element " + name) + " already present");
      }
      presentElements.put(name, ue);
    }
  }
}
