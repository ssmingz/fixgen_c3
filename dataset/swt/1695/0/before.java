class PlaceHold {
  TableItem _getItem(int index, boolean create, int count) {
    if (keys == null) {
      if (((style & SWT.VIRTUAL) == 0) || (!create)) {
        return items[index];
      }
      if (items[index] != null) {
        return items[index];
      }
      return items[index] = new TableItem(this, SWT.NONE, -1, false);
    } else {
      if (((style & SWT.VIRTUAL) == 0) || (!create)) {
        if (keyCount == 0) {
          return null;
        }
        if (index > keys[keyCount - 1]) {
          return null;
        }
      }
      int keyIndex = binarySearch(keys, 0, keyCount, index);
      if (((style & SWT.VIRTUAL) == 0) || (!create)) {
        return keyIndex < 0 ? null : items[keyIndex];
      }
      if (keyIndex < 0) {
        if (count == (-1)) {
          count = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
        }
        if (_checkGrow(count)) {
          if (items[index] != null) {
            return items[index];
          }
          return items[index] = new TableItem(this, SWT.NONE, -1, false);
        }
        keyIndex = (-keyIndex) - 1;
        if (keyIndex < keyCount) {
          System.arraycopy(keys, keyIndex, keys, keyIndex + 1, keyCount - keyIndex);
          System.arraycopy(items, keyIndex, items, keyIndex + 1, keyCount - keyIndex);
        }
        keyCount++;
        keys[keyIndex] = index;
      } else if (items[keyIndex] != null) {
        return items[keyIndex];
      }
      return items[keyIndex] = new TableItem(this, SWT.NONE, -1, false);
    }
  }
}
