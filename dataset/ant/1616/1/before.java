class PlaceHold {
  public Object getPropertyHook(String ns, String name, boolean user) {
    if (getNext() != null) {
      Object o = getNext().getPropertyHook(ns, name, user);
      if (o != null) {
        return o;
      }
    }
    if (name.startsWith("toString:")) {
      name = name.substring("toString:".length());
      Object v = project.getReference(name);
      return v == null ? null : v.toString();
    }
    return null;
  }
}
