class PlaceHold {
  void mouseDown(Event event) {
    if (items == null) {
      return;
    }
    if (isLeftButtonHit(event)) {
      scrollButtonDown = true;
      redrawHitButton(event);
      scrollLeft();
    } else if (isRightButtonHit(event)) {
      scrollButtonDown = true;
      redrawHitButton(event);
      scrollRight();
    } else {
      for (int i = 0; i < items.length; i++) {
        if (items[i].getBounds().contains(new Point(event.x, event.y))) {
          setSelectionNotify(i);
          return;
        }
      }
    }
  }
}
