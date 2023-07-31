class PlaceHold {
  public int hashCode() {
    return handle != null ? ((int) (handle.id)) : 0;
  }
}
