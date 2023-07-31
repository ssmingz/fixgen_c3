class PlaceHold {
  public void setExpanded(boolean value) {
    checkWidget();
    if (expanded == value) {
      return;
    }
    if (items.length == 0) {
      return;
    }
    if (parent.inExpand) {
      return;
    }
    if (value) {
      expanded = value;
      parent.makeDescendentsAvailable(this);
      parent.redrawFromItemDownwards(availableIndex);
    } else {
      int oldAvailableLength = parent.getAvailableItemsCount();
      TreeItem2[] descendents = computeAvailableDescendents();
      expanded = value;
      parent.makeDescendentsUnavailable(this, descendents);
      TreeItem2 focusItem = parent.getFocusItem();
      if (((focusItem != null) && (focusItem != this)) && focusItem.hasAncestor(this)) {
        parent.setFocusItem(this, true);
        if ((style & SWT.SINGLE) != 0) {
          parent.internalSetSelection(new TreeItem2[] {this});
        }
        Event newEvent = new Event();
        newEvent.item = this;
        parent.sendEvent(Selection, newEvent);
        parent.showItem(this);
      }
      parent.redrawItems(availableIndex, oldAvailableLength - 1);
    }
  }
}
