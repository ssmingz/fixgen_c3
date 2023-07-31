class PlaceHold {
  public void testLazyLoading() throws Exception {
    StringResourceCollection collectionTest = new StringResourceCollection();
    LazyResourceCollectionWrapper lazyCollection = new LazyResourceCollectionWrapper();
    lazyCollection.add(collectionTest);
    Iterator it = lazyCollection.iterator();
    assertOneCreatedIterator(collectionTest);
    StringResourceIterator stringResourceIterator =
        ((StringResourceIterator) (collectionTest.createdIterators.get(0)));
    assertEquals("A resource was loaded without iterating", 1, stringResourceIterator.cursor);
    StringResource r = ((StringResource) (it.next()));
    assertOneCreatedIterator(collectionTest);
    assertEquals("r1", r.getValue());
    assertEquals("Iterating once load more than 1 resource", 2, stringResourceIterator.cursor);
    r = ((StringResource) (it.next()));
    assertOneCreatedIterator(collectionTest);
    assertEquals("r2", r.getValue());
    assertEquals("Iterating twice load more than 2 resources", 3, stringResourceIterator.cursor);
    r = ((StringResource) (it.next()));
    assertOneCreatedIterator(collectionTest);
    assertEquals("r3", r.getValue());
    assertEquals("Iterating 3 times load more than 3 resources", 3, stringResourceIterator.cursor);
    try {
      it.next();
      fail("NoSuchElementException shoudl have been raised");
    } catch (NoSuchElementException e) {
    }
  }
}
