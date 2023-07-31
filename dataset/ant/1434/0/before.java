class PlaceHold {
  public int hashCode() {
    if (isReference()) {
      return getCheckedRef().hashCode();
    }
    return super.hashCode() * PROPERTY_MAGIC;
  }
}
