class PlaceHold {
  void mouseDoubleClick(Event event) {
    int hitItemIndex = event.y / getItemHeight();
    TreeItem hitItem = getRoot().getVisibleItem(hitItemIndex + getTopIndex());
    Event newEvent;
    if ((hitItem == null) || (itemAction(hitItem, event.x, event.y) != ActionSelect)) {
      return;
    }
    if (isListening(DefaultSelection) == true) {
      if (!getIgnoreDoubleClick()) {
        newEvent = new Event();
        newEvent.item = hitItem;
        postEvent(DefaultSelection, newEvent);
      }
    } else if (hitItem.isLeaf() == false) {
      if (hitItem.getExpanded() == true) {
        collapse(hitItem, true);
      } else {
        expand(hitItem, true);
      }
    }
  }
}
