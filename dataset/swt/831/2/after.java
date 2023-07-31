class PlaceHold {
  private void onMouseDown(Event event) {
    for (int i = 0; i < items.length; i++) {
      if (items[i].getBounds().contains(new Point(event.x, event.y))) {
        setSelection(i, true);
        return;
      }
    }
  }
}
