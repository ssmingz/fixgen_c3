class PlaceHold {
  public void internal_dispose_Accessible() {
    if (iaccessible != null) {
      iaccessible.Release();
    }
    iaccessible = null;
    Release();
    for (int i = 0; i < children.size(); i++) {
      Accessible child = ((Accessible) (children.elementAt(i)));
      child.dispose();
    }
  }
}
