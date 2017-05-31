package com.expedia.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class ImageStoreTest {

    private ImageStore imageStore;

    @Before
    public void init() {
        imageStore = new ImageStore();
    }

    @Test
    public void givenOneImageWithOneId_whenStoringImage_thenImageIsStored() {
        byte[] image = { 1, 2, 3 };
        String sunshineId = "Sunshine hotels";

        imageStore.storeImage(sunshineId, image);
        byte[] imageWithSunshineId = imageStore.fetchImage(sunshineId);

        assertEquals(1, imageStore.size());
        assertEquals(3, imageWithSunshineId.length);
    }

    @Test
    public void givenOneImageWithTwoDifferentIds_whenStoringImage_thenImageIsStored() {
        byte[] image = { 1, 2, 3 };
        String sunshineId = "Sunshine hotels";
        String stormyId = "Stormy hotels";

        imageStore.storeImage(sunshineId, image);
        imageStore.storeImage(stormyId, image);
        byte[] imageWithSunshineId = imageStore.fetchImage(sunshineId);
        byte[] imageWithStormyId = imageStore.fetchImage(stormyId);

        assertEquals(1, imageStore.size());
        assertEquals(3, imageWithSunshineId.length);
        assertEquals(3, imageWithStormyId.length);
        assertEquals(imageWithSunshineId, imageWithStormyId);
    }

    @Test
    public void givenTwoImagesWithOneDifferentIdEach_whenStoringImage_thenBothImagesAreStored() {
        byte[] image1 = { 1, 2, 3 };
        byte[] image2 = { 4, 5, 6, 7 };
        String sunshineId = "Sunshine hotels";
        String stormyId = "Stormy hotels";

        imageStore.storeImage(sunshineId, image1);
        imageStore.storeImage(stormyId, image2);
        byte[] imageWithSunshineId = imageStore.fetchImage(sunshineId);
        byte[] imageWithStormyId = imageStore.fetchImage(stormyId);

        assertEquals(2, imageStore.size());
        assertEquals(3, imageWithSunshineId.length);
        assertEquals(4, imageWithStormyId.length);
        assertFalse(imageWithSunshineId.equals(imageWithStormyId));
    }

    @Test
    public void givenTwoImagesWithOneSameIdEach_whenStoringImage_thenOneImageRemains() {
        byte[] image1 = { 1, 2, 3 };
        byte[] image2 = { 4, 5, 6, 7 };
        String sunshineId = "Sunshine hotels";

        imageStore.storeImage(sunshineId, image1);
        imageStore.storeImage(sunshineId, image2);
        byte[] imageWithSunshineId = imageStore.fetchImage(sunshineId);

        assertEquals(1, imageStore.size());
        assertEquals(4, imageWithSunshineId.length);
    }

    @Test
    public void givenTwoImagesWithOneSameIdAndOneDifferentIdEach_whenStoringImage_thenBothImagesAreStored() {
        byte[] image1 = { 1, 2, 3 };
        byte[] image2 = { 4, 5, 6, 7 };
        String sunshineId = "Sunshine hotels";
        String stormyId = "Stormy hotels";
        String beachfrontId = "Beachfront hotels";

        imageStore.storeImage(sunshineId, image1);
        imageStore.storeImage(stormyId, image1);
        imageStore.storeImage(beachfrontId, image2);
        imageStore.storeImage(sunshineId, image2);
        byte[] imageWithSunshineId = imageStore.fetchImage(sunshineId);
        byte[] imageWithStormyId = imageStore.fetchImage(stormyId);
        byte[] imageWithBeachFrontId = imageStore.fetchImage(beachfrontId);

        assertEquals(2, imageStore.size());
        assertEquals(4, imageWithSunshineId.length);
        assertEquals(3, imageWithStormyId.length);
        assertEquals(4, imageWithBeachFrontId.length);
        assertFalse(imageWithSunshineId.equals(imageWithStormyId));
        assertFalse(imageWithBeachFrontId.equals(imageWithStormyId));
        assertEquals(imageWithSunshineId, imageWithBeachFrontId);
    }

    @Test
    public void givenTwoImagesWithTwoSameIdsEach_whenStoringImage_thenOneImageRemains() {
        byte[] image1 = { 1, 2, 3 };
        byte[] image2 = { 4, 5, 6, 7 };
        String sunshineId = "Sunshine hotels";
        String stormyId = "Stormy hotels";

        imageStore.storeImage(sunshineId, image1);
        imageStore.storeImage(stormyId, image1);
        imageStore.storeImage(sunshineId, image2);
        imageStore.storeImage(stormyId, image2);
        byte[] imageWithSunshineId = imageStore.fetchImage(sunshineId);
        byte[] imageWithStormyId = imageStore.fetchImage(stormyId);

        assertEquals(1, imageStore.size());
        assertEquals(4, imageWithSunshineId.length);
        assertEquals(4, imageWithStormyId.length);
        assertEquals(imageWithSunshineId, imageWithStormyId);
    }

    @Test
    public void givenTwoImagesWithTwoSameIdsEachStoredAtDifferentTimes_whenStoringImage_thenTwoImagesStored() {
        byte[] image1 = { 1, 2, 3 };
        byte[] image2 = { 4, 5, 6, 7 };
        String sunshineId = "Sunshine hotels";
        String stormyId = "Stormy hotels";

        imageStore.storeImage(sunshineId, image1);
        imageStore.storeImage(sunshineId, image2);
        imageStore.storeImage(stormyId, image2);
        imageStore.storeImage(stormyId, image1);
        byte[] imageWithSunshineId = imageStore.fetchImage(sunshineId);
        byte[] imageWithStormyId = imageStore.fetchImage(stormyId);

        assertEquals(2, imageStore.size());
        assertEquals(4, imageWithSunshineId.length);
        assertEquals(3, imageWithStormyId.length);
        assertFalse(imageWithSunshineId.equals(imageWithStormyId));
    }

    @Test(expected = NullPointerException.class)
    public void givenImageWithNullId_whenStoringImage_thenExceptionIsThrown() {
        byte[] image = { 1, 2, 3 };
        String id = null;

        imageStore.storeImage(id, image);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullImageWithId_whenStoringImage_thenExceptionIsThrown() {
        byte[] image = null;
        String sunshineId = "Sunshine hotels";

        imageStore.storeImage(sunshineId, image);
    }

}
