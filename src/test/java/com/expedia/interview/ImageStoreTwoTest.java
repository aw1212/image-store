package com.expedia.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class ImageStoreTwoTest {

    private ImageStoreTwo imageStoreTwo;

    @Before
    public void init() {
        imageStoreTwo = new ImageStoreTwo();
    }

    @Test
    public void givenOneImageWithOneId_whenStoringImage_thenImageIsStored() {
        byte[] image = { 1, 2, 3 };
        String sunshineId = "Sunshine hotels";

        imageStoreTwo.storeImage(sunshineId, image);
        byte[] imageWithSunshineId = imageStoreTwo.fetchImage(sunshineId);

        assertEquals(1, imageStoreTwo.size());
        assertEquals(3, imageWithSunshineId.length);
    }

    @Test
    public void givenOneImageWithTwoDifferentIds_whenStoringImage_thenImageIsStored() {
        byte[] image = { 1, 2, 3 };
        String sunshineId = "Sunshine hotels";
        String stormyId = "Stormy hotels";

        imageStoreTwo.storeImage(sunshineId, image);
        imageStoreTwo.storeImage(stormyId, image);
        byte[] imageWithSunshineId = imageStoreTwo.fetchImage(sunshineId);
        byte[] imageWithStormyId = imageStoreTwo.fetchImage(stormyId);

        assertEquals(1, imageStoreTwo.size());
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

        imageStoreTwo.storeImage(sunshineId, image1);
        imageStoreTwo.storeImage(stormyId, image2);
        byte[] imageWithSunshineId = imageStoreTwo.fetchImage(sunshineId);
        byte[] imageWithStormyId = imageStoreTwo.fetchImage(stormyId);

        assertEquals(2, imageStoreTwo.size());
        assertEquals(3, imageWithSunshineId.length);
        assertEquals(4, imageWithStormyId.length);
        assertFalse(imageWithSunshineId.equals(imageWithStormyId));
    }

    @Test
    public void givenTwoImagesWithOneSameIdEach_whenStoringImage_thenOneImageRemains() {
        byte[] image1 = { 1, 2, 3 };
        byte[] image2 = { 4, 5, 6, 7 };
        String sunshineId = "Sunshine hotels";

        imageStoreTwo.storeImage(sunshineId, image1);
        imageStoreTwo.storeImage(sunshineId, image2);
        byte[] imageWithSunshineId = imageStoreTwo.fetchImage(sunshineId);

        assertEquals(1, imageStoreTwo.size());
        assertEquals(4, imageWithSunshineId.length);
    }

    @Test
    public void givenTwoImagesWithOneSameIdAndOneDifferentIdEach_whenStoringImage_thenBothImagesAreStored() {
        byte[] image1 = { 1, 2, 3 };
        byte[] image2 = { 4, 5, 6, 7 };
        String sunshineId = "Sunshine hotels";
        String stormyId = "Stormy hotels";
        String beachfrontId = "Beachfront hotels";

        imageStoreTwo.storeImage(sunshineId, image1);
        imageStoreTwo.storeImage(stormyId, image1);
        imageStoreTwo.storeImage(beachfrontId, image2);
        imageStoreTwo.storeImage(sunshineId, image2);
        byte[] imageWithSunshineId = imageStoreTwo.fetchImage(sunshineId);
        byte[] imageWithStormyId = imageStoreTwo.fetchImage(stormyId);
        byte[] imageWithBeachFrontId = imageStoreTwo.fetchImage(beachfrontId);

        assertEquals(2, imageStoreTwo.size());
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

        imageStoreTwo.storeImage(sunshineId, image1);
        imageStoreTwo.storeImage(stormyId, image1);
        imageStoreTwo.storeImage(sunshineId, image2);
        imageStoreTwo.storeImage(stormyId, image2);
        byte[] imageWithSunshineId = imageStoreTwo.fetchImage(sunshineId);
        byte[] imageWithStormyId = imageStoreTwo.fetchImage(stormyId);

        assertEquals(1, imageStoreTwo.size());
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

        imageStoreTwo.storeImage(sunshineId, image1);
        imageStoreTwo.storeImage(sunshineId, image2);
        imageStoreTwo.storeImage(stormyId, image2);
        imageStoreTwo.storeImage(stormyId, image1);
        byte[] imageWithSunshineId = imageStoreTwo.fetchImage(sunshineId);
        byte[] imageWithStormyId = imageStoreTwo.fetchImage(stormyId);

        assertEquals(2, imageStoreTwo.size());
        assertEquals(4, imageWithSunshineId.length);
        assertEquals(3, imageWithStormyId.length);
        assertFalse(imageWithSunshineId.equals(imageWithStormyId));
    }

    @Test(expected = NullPointerException.class)
    public void givenImageWithNullId_whenStoringImage_thenExceptionIsThrown() {
        byte[] image = { 1, 2, 3 };
        String id = null;

        imageStoreTwo.storeImage(id, image);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullImageWithId_whenStoringImage_thenExceptionIsThrown() {
        byte[] image = null;
        String sunshineId = "Sunshine hotels";

        imageStoreTwo.storeImage(sunshineId, image);
    }

}
