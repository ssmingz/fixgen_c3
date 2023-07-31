class PlaceHold {
  public Iterator iterator() {
    if (isReference()) {
      return getRef().iterator();
    }
    dieOnCircularReference();
    final Enumeration e = getProperties().propertyNames();
    return new Iterator() {
      public boolean hasNext() {
        return e.hasMoreElements();
      }

      public Object next() {
        return new PropertyResource(getProject(), ((String) (e.nextElement())));
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
}
