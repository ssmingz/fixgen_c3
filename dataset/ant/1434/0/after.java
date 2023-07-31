class PlaceHold {
  public int hashCode() {
    if (isReferenceOrProxy()) {
      return getReferencedOrProxied().hashCode();
    }
    return super.hashCode() * PROPERTY_MAGIC;
  }
}
